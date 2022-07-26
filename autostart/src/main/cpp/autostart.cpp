//
// Created by caoshen on 2022/7/26.
//

#include <jni.h>
#include <cstdio>
#include <android/log.h>
#include <unistd.h>
#include <cstdlib>
#include <cstring>
#include <wait.h>
#include <sys/file.h>

#define TAG "Autostart"

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

#define DAEMON_CALLBACK_NAME "onDaemonDead"

void create_file_if_not_exist(char *path) {
    FILE *fp = fopen(path, "ab+");
    if (fp) {
        fclose(fp);
    }
}

void set_process_name(JNIEnv *pEnv) {
    jclass process = pEnv->FindClass("android/os/Process");
    jmethodID setArgV0 = pEnv->GetStaticMethodID(process, "setArgV0", "(Ljava/lang/String;)V");
    jstring name = pEnv->NewStringUTF("app_d");
    pEnv->CallStaticVoidMethod(process, setArgV0, name);
}

int lock_file(const char *lock_file_path) {
    LOGD("start try to lock file >> %s <<", lock_file_path);
    int lockFileDescriptor = open(lock_file_path, O_RDONLY);
    if (lockFileDescriptor == -1) {
        lockFileDescriptor = open(lock_file_path, O_CREAT, S_IRUSR);
    }
    int lockRet = flock(lockFileDescriptor, LOCK_EX);
    if (lockRet == -1) {
        LOGE("lock file failed >> %s <<", lock_file_path);
        return 0;
    } else {
        LOGD("lock file success >> %s <<", lock_file_path);
        return 1;
    }
}

/**
 * wait for daemon process
 *
 * @param observer_self_path
 * @param observer_daemon_path
 */
void notify_and_waitfor(char *observer_self_path, char *observer_daemon_path) {
    int observer_self_descriptor = open(observer_self_path, O_RDONLY);
    if (observer_self_descriptor == -1) {
        observer_self_descriptor = open(observer_self_path, O_CREAT, S_IRUSR | S_IWUSR);
    }
    if (observer_self_descriptor == -1) {
        LOGE("create observer self failed.");
    } else {
        LOGD("create observer self success.");
    }
    int observer_daemon_descriptor = open(observer_daemon_path, O_RDONLY);
    LOGD("open daemon observer success. %d", observer_daemon_descriptor);
    while (observer_daemon_descriptor == -1) {
        usleep(1000);
//        LOGD("usleep 1000");
        observer_daemon_descriptor = open(observer_daemon_path, O_RDONLY);
    }
    LOGD("open daemon observer success. remove daemon path");
    remove(observer_daemon_path);
}

void java_callback(JNIEnv *env, jobject jobject, char *method_name) {
    jclass cls = env->GetObjectClass(jobject);
    jmethodID method = env->GetMethodID(cls, method_name, "()V");
    env->CallVoidMethod(jobject, method);
}

void do_daemon(JNIEnv *env, jobject jobj, char *indicator_self_path, char *indicator_daemon_path,
               char *observer_self_path, char *observer_daemon_path) {
    int lock_status = 0;
    int try_time = 0;
    // lock self
    LOGD("lock self start:%s", indicator_self_path);
    while (try_time < 3 && !(lock_status = lock_file(indicator_self_path))) {
        try_time++;
        LOGD("Persistent lock myself failed and try again as %d times.", try_time);
        usleep(10000);
    }
    if (!lock_status) {
        LOGE("Persistent lock myself failed and exit");
        return;
    }
    LOGD("lock self end:%s", indicator_self_path);
    // wait for daemon start
    notify_and_waitfor(observer_self_path, observer_daemon_path);
    LOGD("lock daemon start:%s", indicator_daemon_path);
    lock_status = lock_file(indicator_daemon_path);
    if (lock_status) {
        LOGE("Watch >>>>DAEMON<<<< Dead!");
        remove(observer_self_path);
        java_callback(env, jobj, DAEMON_CALLBACK_NAME);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_io_github_caoshen_androidadvance_autostart_NativeAutoStart_doDaemon(JNIEnv *env,
                                                                         jobject jobj,
                                                                         jstring indicatorSelfPath,
                                                                         jstring indicatorDaemonPath,
                                                                         jstring observerSelfPath,
                                                                         jstring observerDaemonPath
) {
    if (indicatorSelfPath == NULL
        || indicatorDaemonPath == NULL
        || observerSelfPath == NULL
        || observerDaemonPath == NULL) {
        LOGE("parameters cannot be NULL!");
        return;
    }

    char *indicator_self_path = (char *) (env->GetStringUTFChars(indicatorSelfPath, 0));
    char *indicator_daemon_path = (char *) env->GetStringUTFChars(indicatorDaemonPath, 0);
    char *observer_self_path = (char *) env->GetStringUTFChars(observerSelfPath, 0);
    char *observer_daemon_path = (char *) env->GetStringUTFChars(observerDaemonPath, 0);

    pid_t pid;
    if ((pid = fork()) < 0) {
        LOGE("fork 1 error\n");
        exit(-1);
    } else if (pid == 0) {
        if ((pid = fork()) < 0) {
            LOGE("fork 2 error\n");
            exit(-1);
        } else if (pid > 0) {
            exit(0);
        }

        LOGD("mypid: %d", getpid());
        const int MAX_PATH = 256;
        char indicator_self_path_child[MAX_PATH];
        char indicator_daemon_path_child[MAX_PATH];
        char observer_self_path_child[MAX_PATH];
        char observer_daemon_path_child[MAX_PATH];

        strcpy(indicator_self_path_child, indicator_self_path);
        strcat(indicator_self_path_child, "-c");
        strcpy(indicator_daemon_path_child, indicator_daemon_path);
        strcat(indicator_daemon_path_child, "-c");
        strcpy(observer_self_path_child, observer_self_path);
        strcat(observer_self_path_child, "-c");
        strcpy(observer_daemon_path_child, observer_daemon_path);
        strcat(observer_daemon_path_child, "-c");

        // 子进程 indicator
        create_file_if_not_exist(indicator_self_path_child);
        create_file_if_not_exist(indicator_daemon_path_child);

        set_process_name(env);

        do_daemon(env, jobj, indicator_self_path_child, indicator_daemon_path_child,
                  observer_self_path_child, observer_daemon_path_child);
        return;
    }

    if (waitpid(pid, NULL, 0) != pid) {
        LOGE("waitpid error\n");
    }

    do_daemon(env, jobj, indicator_self_path, indicator_daemon_path, observer_self_path, observer_daemon_path);

}