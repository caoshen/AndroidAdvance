#include <jni.h>
#include <malloc.h>

extern "C"
JNIEXPORT void JNICALL
Java_com_example_nativelib_TestHookee_NativeTestMalloc(JNIEnv *env, jclass cls) {
    // 88M
    malloc(88 * 1024 * 1024);
}
