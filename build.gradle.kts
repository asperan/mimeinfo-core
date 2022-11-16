plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlin.qa)
    signing
    `maven-publish`
    alias(libs.plugins.gitSemVer)
}

group = "io.github.asperan"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.bundles.kotest)
}

gitSemVer {
    buildMetadataSeparator.set("-")
    maxVersionLength.set(20)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
        vendor.set(JvmVendorSpec.ORACLE)
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        allWarningsAsErrors = true
    }
}

val javadocJar by tasks.registering(Jar::class) {
    from(tasks.dokkaJavadoc.get().outputDirectory)
    archiveClassifier.set("javadoc")
}

val sourceJar by tasks.registering(Jar::class) {
    from(sourceSets.named("main").get().allSource)
    archiveClassifier.set("sources")
}

publishing {
    repositories {
        maven {
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val mavenCentralPwd: String? by project
            credentials {
                username = "asperan"
                password = mavenCentralPwd
            }
        }
        publications {
            val mimeInfoCore by creating(MavenPublication::class) {
                from(components["java"])
                artifact(javadocJar)
                artifact(sourceJar)
                pom {
                    name.set("MimeInfo Core")
                    description.set("MimeInfo core library")
                    url.set("https://github.com/asperan/mimeinfo-core")
                    licenses {
                        license {
                            name.set("MPL2.0")
                        }
                    }
                    developers {
                        developer {
                            name.set("Alex Speranza")
                        }
                    }
                    scm {
                        url.set("git@github.com:asperan/mimeinfo-core.git")
                        connection.set("git@github.com:asperan/mimeinfo-core.git")
                    }
                }
            }
            signing { sign(mimeInfoCore) }
        }
    }
}
