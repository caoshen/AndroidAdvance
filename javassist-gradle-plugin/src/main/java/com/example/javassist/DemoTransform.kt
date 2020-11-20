package com.example.javassist

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager

class DemoTransform : Transform() {
    override fun getName(): String = "DemoTransform"

    /**
     * input types:
     * class files after compiled, or resource file in assets dir(not res dir)
     */
    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> = TransformManager.CONTENT_CLASS

    /**
     * processing scopes
     */
    override fun getScopes(): MutableSet<in QualifiedContent.Scope> = TransformManager.SCOPE_FULL_PROJECT

    /**
     * is incremental processing supported. Modify/Delete/Add files
     */
    override fun isIncremental(): Boolean = false

    /**
     * transform processing
     */
    override fun transform(transformInvocation: TransformInvocation?) {
        transformInvocation?.inputs?.forEach {
            // input directory
            it.directoryInputs.forEach { directoryInput ->
                with(directoryInput) {
                    // processing directory input
                    val dest = transformInvocation.outputProvider.getContentLocation(
                            name,
                            inputTypes,
                            scopes,
                            Format.DIRECTORY
                    )
                    file.copyTo(dest)
                }
            }

            // input jar
            it.jarInputs.forEach {jarInput ->
                with(jarInput) {
                    // processing jar input
                    val dest = transformInvocation.outputProvider.getContentLocation(
                            name,
                            inputTypes,
                            scopes,
                            Format.JAR
                    )
                    file.copyTo(dest)
                }
            }
        }
    }
}
