import com.vanniktech.maven.publish.SonatypeHost
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.JavadocJar
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.mavenPublish)
}

kotlin {
    // Explicit API mode - forces visibility modifiers and return types
    explicitApi()

    // XCFramework for SPM distribution
    val xcframeworkName = "TimeAgoKMP"
    val xcf = XCFramework(xcframeworkName)

    // Android target
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
        publishLibraryVariants("release")
    }

    // JVM target
    jvm()

    // iOS targets with XCFramework
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = xcframeworkName
            binaryOption("bundleId", "io.github.samuolis.timeago")
            xcf.add(this)
            isStatic = true
        }
    }

    // macOS targets with XCFramework
    listOf(
        macosX64(),
        macosArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = xcframeworkName
            binaryOption("bundleId", "io.github.samuolis.timeago")
            xcf.add(this)
            isStatic = true
        }
    }

    // JavaScript targets
    js(IR) {
        browser()
        nodejs()
        binaries.library()
    }

    // WebAssembly JS target
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
        binaries.library()
    }

    // Linux targets
    linuxX64()
    linuxArm64()

    // Windows target
    mingwX64()

    // Source sets configuration
    sourceSets {
        commonMain.dependencies {
            // No dependencies - pure Kotlin using kotlin.time
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.github.samuolis.timeago"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// Maven Central Publishing Configuration
mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    configure(KotlinMultiplatform(
        javadocJar = JavadocJar.Empty(),
        sourcesJar = true
    ))

    pom {
        name.set("TimeAgo KMP")
        description.set("Kotlin Multiplatform library for human-readable relative time formatting")
        url.set("https://github.com/samuolis/timeago-kmp")
        inceptionYear.set("2025")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("samuolis")
                name.set("Lukas Samuolis")
                url.set("https://github.com/samuolis")
            }
        }

        scm {
            url.set("https://github.com/samuolis/timeago-kmp")
            connection.set("scm:git:git://github.com/samuolis/timeago-kmp.git")
            developerConnection.set("scm:git:ssh://git@github.com/samuolis/timeago-kmp.git")
        }
    }
}
