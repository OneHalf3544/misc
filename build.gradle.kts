import org.gradle.api.JavaVersion.VERSION_1_9
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.ben-manes.versions").version("0.17.0")
    kotlin("jvm") version "1.2.41"
}

group = "ru.yandex.money"
version = "1.0-SNAPSHOT"

apply<GroovyPlugin>()
apply<ScalaPlugin>()

java {
    sourceCompatibility = VERSION_1_9
    targetCompatibility = VERSION_1_9
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        languageVersion = "1.2"
        apiVersion = "1.2"
        jvmTarget = "1.8"
    }
}

dependencies {
    compile("org.codehaus.groovy:groovy-all:2.4.13")
    compile("org.scala-lang:scala-library:2.12.4")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.2.40")

    compileOnly("org.projectlombok:lombok:1.16.20")
    annotationProcessor("org.projectlombok:lombok:1.16.20")

    testCompile("org.spockframework:spock-core:1.1-groovy-2.4")
    testCompile("org.scalactic:scalactic_2.12:3.0.5")
    testCompile("org.scalatest:scalatest_2.12:3.0.5")
    testCompile("junit:junit:4.12")
}

//dependencyUpdates.resolutionStrategy = {
//    componentSelection { rules ->
//        rules.all { ComponentSelection selection ->
//            boolean rejected = ['alpha', 'beta', 'rc', 'cr', 'm', 'snap'].any { qualifier ->
//                selection.candidate.version ==~ /(?i).*[.-]${qualifier}[.\d-]*/
//            }
//            if (rejected) {
//                selection.reject('Release candidate')
//            }
//        }
//    }
//}

tasks.withType<Test> {
    useTestNG()
}