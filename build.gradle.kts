import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.mavenPublish) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
}

// Read version from version.properties or use VERSION_NAME from command line
val versionProps = Properties().apply {
    file("version.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}

val libVersion: String = findProperty("VERSION_NAME") as String?
    ?: "${versionProps["VERSION_MAJOR"]}.${versionProps["VERSION_MINOR"]}.${versionProps["VERSION_PATCH"]}"

allprojects {
    group = "io.github.samuolis"
    version = libVersion
}
