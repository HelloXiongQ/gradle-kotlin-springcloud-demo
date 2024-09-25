import org.atxiong.cloud.plugins.importSubProjects

dependencies {
    implementation(libs.mybatisPlus)
    implementation(libs.swagger3)
    importSubProjects(findProject(":common"))
}