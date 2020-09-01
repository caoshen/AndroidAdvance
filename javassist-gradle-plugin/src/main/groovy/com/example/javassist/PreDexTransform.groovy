package com.example.javassist

import com.android.build.api.transform.Context
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project

/**
 * @author caoshen* @date 2020/8/28
 */
class PreDexTransform extends Transform {
    private Project mProject

    PreDexTransform(Project project) {
        mProject = project
    }

    /**
     * Transform 在 Task 列表的名字
     * TransformClassesWithPreDexForXXXX
     *
     * @return
     */
    @Override
    String getName() {
        return "PreDex"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        // 处理 Class 文件
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        // 指定 Transform 的作用范围，整个项目范围
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider, boolean isIncremental) throws IOException,
            TransformException, InterruptedException {
        log("transform >>>>>")
        inputs.each { TransformInput input ->
            // Transform 输入是目录
            input.directoryInputs.each {

            }

            // Transform 输入是 jar
            input.jarInputs.each {

            }

        }
    }

    void log(String log) {
        mProject.logger.error(log)
    }
}
