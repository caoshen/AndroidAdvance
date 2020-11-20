package com.example.javassist

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author caoshen
 * @date 2020/10/20
 */
class DemoPlugin : Plugin<Project> {
    override fun apply(p0: Project) {
        val android = p0.extensions.findByType(BaseExtension::class.java)
        android?.registerTransform(DemoTransform())
    }
}