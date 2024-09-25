package org.atxiong.cloud.plugins

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.exclude

/**
 * 导入子项目依赖
 * @param project 项目依赖
 * @sub 具体子项目名称
 *
 */
fun DependencyHandlerScope.importSubProjects(project: Project?, sub: List<String> = listOf()) {
    if (sub.isEmpty()) {
        project?.subprojects?.forEach {
            add("implementation", it)
        }
    }

    project?.subprojects?.forEach {
        if (it.name in sub) add("implementation", it)
    }
}

/**
 * 通过 libs 中传递的 依赖 排除项目中 指定依赖
 */
fun Project.excludeDependency(list: List<MinimalExternalModuleDependency>) {
    configurations.all {
        list.forEach { dep ->
            exclude(
                group = dep.module.group,
                module = dep.module.name
            )
        }
    }
}

fun Project.excludeDependency(dep: MinimalExternalModuleDependency) {
    configurations.all {
        exclude(
            group = dep.module.group,
            module = dep.module.name
        )
    }
}