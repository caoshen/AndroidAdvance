//
// Created by 曹燊 on 2023/1/16.
//
#include <jni.h>
#include "bytehook.h"

#define HACKER_JNI_VERSION JNI_VERSION_1_6

#define HACKER_JNI_CLASS_NAME "com/example/nativelib/NativeHacker"

static int hacker_hook(JNIEnv *env, jobject thiz, jint type) {
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

    if (0 != (*env)->RegisterNatives(env, cls, m, sizeof(m)/ sizeof(m[0])))
        return JNI_ERR;

    return HACKER_JNI_VERSION;
}
