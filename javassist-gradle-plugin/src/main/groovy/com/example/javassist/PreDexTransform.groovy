package com.example.javassist

import com.android.build.api.transform.Context
import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
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
     * transformClassesWithPreDexForDebug
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
            // Transform 输入是目录，是自己项目的 classes
            input.directoryInputs.each { DirectoryInput directoryInput ->
                log("directoryInput name = " + directoryInput.name + ", path = " + directoryInput.file.absolutePath)
                def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes,
                    directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file, dest)
            }

            // Transform 输入是 jar，是项目依赖的 jar
            input.jarInputs.each { JarInput jarInput ->
                log("jarInput name = " + jarInput.name + ", path = " + jarInput.file.absolutePath)
                // 重命名输出文件（同目录执行 copyFile 操作会发生冲突）
                def jarName = jarInput.name
                def md5Name = jarInput.file.hashCode()
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
                def dest = outputProvider.getContentLocation(jarName + md5Name, jarInput.contentTypes,
                    jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }

        }
    }

    void log(String log) {
        mProject.logger.error(log)
    }
}
