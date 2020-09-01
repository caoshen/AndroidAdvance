package com.example.javassist;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author caoshen* @date 2020/8/28
 */
class JavassistPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        def log = project.logger
        log.error("====================")
        log.error("Javassist 开始修改 Class！")
        log.error("====================")
        log.error "====================" - project.getClass().getName()

        project.android.registerTransform(new PreDexTransform(project))
    }
}
