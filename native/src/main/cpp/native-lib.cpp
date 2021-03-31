#include <jni.h>
#include <string>


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativelib_MainActivity_stringFromJNI(JNIEnv* env, jobject) {
    std::string hello = "Hello from Native-lib.cpp";
    return env->NewStringUTF(hello.c_str());
}
