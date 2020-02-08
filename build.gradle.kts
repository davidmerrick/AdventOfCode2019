group = "com.merricklabs.adventofcode2019"

repositories {
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

plugins {
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    kotlin("jvm") version Versions.org_jetbrains_kotlin
    jacoco
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk8)
    implementation(Libs.slf4j_api)
    implementation(Libs.slf4j_jdk14)
    implementation(Libs.kotlin_logging)
    implementation("com.github.dpaukov:combinatoricslib3:3.3.0")


    testImplementation(Libs.testng)
    testImplementation(Libs.kotlintest_runner_junit5)
    testImplementation(Libs.mockito_kotlin)
}

jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/customJacocoReportDir")
}

tasks {
    test {
        useTestNG()
    }

    jacocoTestReport {
        mustRunAfter(test)
        reports {
            xml.isEnabled = true
            csv.isEnabled = false
            xml.destination = file("${buildDir}/reports/jacoco/test/jacocoTestReport.xml")
            html.destination = file("${buildDir}/jacocoHtml")
        }
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage."

    dependsOn(":test", ":jacocoTestReport", ":jacocoTestCoverageVerification")
}