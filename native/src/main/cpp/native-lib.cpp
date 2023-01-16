#include <jni.h>
#include <string>
#include "Logger.h"

extern "C" JNIEXPORT jstring
Java_com_example_nativelib_MainActivity_stringFromJNI(JNIEnv *env, jobject) {
    std::string hello = "Hello from Native-lib.cpp";
    char *ptr3 = NULL;
    ptr3 = static_cast<char *>(calloc(3, 1 * 1024));
    if (ptr3) {
        LOGGER("ptr3 = static_cast<char *>(calloc(3, 1 * 1024)): %p\n", ptr3);
    }
    LOGGER("the program exit, ptr3 is not free, logcat will printf memory leak log\n");
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_nativelib_MainActivity_getStr(JNIEnv *env, jobject thiz) {
    std::string hello = "jingxun hello";
    int *ptr = (int *) malloc(sizeof(int) * 3);
    // 越界
    ptr[4] = 6;
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_nativelib_NativeHacker_nativeHook(JNIEnv *env, jclass clazz, jint type) {
    // TODO: implement nativeHook()
    return 0;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_nativelib_NativeHacker_nativeUnhook(JNIEnv
* env,
jclass clazz
) {
// TODO: implement nativeUnhook()
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_nativelib_NativeHacker_nativeDumpRecords(JNIEnv
* env,
jclass clazz
) {
// TODO: implement nativeDumpRecords()
}