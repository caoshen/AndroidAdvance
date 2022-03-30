#include <jni.h>
#include <string>
#include "Logger.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativelib_MainActivity_stringFromJNI(JNIEnv* env, jobject) {
    std::string hello = "Hello from Native-lib.cpp";
    char* ptr3 = NULL;
    ptr3 = static_cast<char *>(calloc(3, 1 * 1024));
    if (ptr3) {
        LOGGER("ptr3 = static_cast<char *>(calloc(3, 1 * 1024)): %p\n", ptr3);
    }
    LOGGER("the program exit, ptr3 is not free, logcat will printf memory leak log\n");
    return env->NewStringUTF(hello.c_str());
}
