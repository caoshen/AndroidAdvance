#include <android/log.h>
#include <dlfcn.h>
#include <fcntl.h>
#include <inttypes.h>
#include <jni.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>
#include <unistd.h>

#include "bytehook.h"

#define HACKER_JNI_VERSION JNI_VERSION_1_6
#define HACKER_JNI_CLASS_NAME "com/example/nativelib/NativeHacker"
#define HACKER_TAG "bytehook_tag"

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wgnu-zero-variadic-macro-arguments"
#define LOG(fmt, ...) __android_log_print(ANDROID_LOG_INFO, HACKER_TAG, fmt, ##__VA_ARGS__)
#pragma clang diagnostic pop

typedef int (*open_t)(const char *, int, mode_t);

typedef int (*open_real_t)(const char *, int, mode_t);

typedef int (*open2_t)(const char *, int);

#define OPEN_DEF(fn) \
    static fn##_t fn##_prev = NULL; \
    static bytehook_stub_t fn##_stub = NULL; \
    static void fn##_hooked_callback(bytehook_stub_t task_stub, int status_code, const char *caller_path_name, \
                         const char *sym_name, void *new_func, void *prev_func, void *arg) {                   \
        if (BYTEHOOK_STATUS_CODE_ORIG_ADDR == status_code) {                                                   \
            fn##_prev = (fn##_t) prev_func;  \
            LOG(">>>>> save original address: %" PRIxPTR, (uintptr_t)prev_func);\
        } else {     \
            LOG(">>>>> hooked. stub: %" PRIxPTR                                                                \
            ", status: %d, caller_path_name: %s, sym_name: %s, new_func: %" PRIxPTR ", prev_func: %" PRIxPTR       \
            ", arg: %" PRIxPTR,         \
            (uintptr_t)task_stub, status_code, caller_path_name, sym_name, (uintptr_t)new_func,        \
            (uintptr_t)prev_func, (uintptr_t)arg);\
        } \
    }

OPEN_DEF(open)

OPEN_DEF(open_real)

OPEN_DEF(open2)

static void debug(const char *sym, const char *pathname, int flags, int fd, void *lr) {
    Dl_info info;
    memset(&info, 0, sizeof(info));
    dladdr(lr, &info);

    LOG("proxy %s(\"%s\", %d), return FD: %d, called from: %s (%s)", sym, pathname, flags, fd, info.dli_fname,
        info.dli_sname);
}

static int open_proxy_auto(const char *pathname, int flags, mode_t modes) {
    int fd = BYTEHOOK_CALL_PREV(open_proxy_auto, open_t, pathname, flags, modes);
    debug("open", pathname, flags, fd, BYTEHOOK_RETURN_ADDRESS());
    BYTEHOOK_POP_STACK();
    return fd;
}

static bool allow_filter_for_hook_all(const char *caller_path_name, void *arg) {
    (void)arg;

    if (NULL != strstr(caller_path_name, "liblog.so"))
        return false;
    return true;
}

static int hacker_hook(JNIEnv *env, jobject thiz, jint type) {
    if (NULL != open_stub || NULL != open_real_stub || NULL != open2_stub)
        return -1;

    void *open_proxy, *open_real_proxy, *open2_proxy;
    if (BYTEHOOK_MODE_MANUAL == bytehook_get_mode()) {
    } else {
        open_proxy = (void *)open_proxy_auto;
    }

    if (type == 2) {
        open_stub = bytehook_hook_partial(allow_filter_for_hook_all, NULL,
                                          NULL, "open", open_proxy, open_hooked_callback, NULL);
    }
    return 0;
}

static int hacker_unhook(JNIEnv *env, jobject thiz) {
    return 0;
}

static void hacker_dump_records(JNIEnv *env, jobject thiz, jstring pathname) {

}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    (void) reserved;

    if (NULL == vm) return JNI_ERR;

    JNIEnv *env;
    if (JNI_OK != (*vm)->GetEnv(vm, (void **) &env, HACKER_JNI_VERSION))
        return JNI_ERR;
    if (NULL == env || NULL == *env)
        return JNI_ERR;

    jclass cls;
    if (NULL == (cls = (*env)->FindClass(env, HACKER_JNI_CLASS_NAME)))
        return JNI_ERR;

    JNINativeMethod m[] = {
            {"nativeHook",        "(I)I",                  (void *) hacker_hook},
            {"nativeUnhook",      "()I",                   (void *) hacker_unhook},
            {"nativeDumpRecords", "(Ljava/lang/String;)V", (void *) hacker_dump_records},
    };

    if (0 != (*env)->RegisterNatives(env, cls, m, sizeof(m) / sizeof(m[0])))
        return JNI_ERR;

    return HACKER_JNI_VERSION;
}
