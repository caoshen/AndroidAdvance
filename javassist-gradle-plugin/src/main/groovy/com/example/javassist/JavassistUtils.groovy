package com.example.javassist

import javassist.CtMethod


class JavassistUtils {

    static String getSimpleName(CtMethod ctMethod) {
        def methodName = ctMethod.getName()
        def index = methodName.lastIndexOf('.')
        return methodName.substring(index + 1)
    }

    static String getClassName(int index, String filePath) {
        // len '.class' = 6
        int end = filePath.length() - 6
        return filePath.substring(index, end).replace('\\', '.')
            .replace('/', '.')
    }
}