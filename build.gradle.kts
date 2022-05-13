import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    kotlin("plugin.jpa") version "1.4.10"
    kotlin("kapt") version "1.4.10"
}

group = "com.mogree"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

project.ext["buildNr"] = System.getProperty("buildNr")
project.ext["gitBranchName"] = System.getProperty("gitBranchName")
project.ext["gitCommitID"] = System.getProperty("gitCommitID")

springBoot {
    buildInfo()
}
tasks.processResources {
    filesMatching("application.*") {
        expand(project.properties)
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }

tasks.register("print_version") {
    doLast {
        println(project.version)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    val generatedCompile = configurations.create("generatedCompile")

    // include local jar file instead of getting them from mogree´s artifactory
    generatedCompile(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //generatedCompile("com.mogree.server:mogree-spring:1.0.21")
    generatedCompile("org.springframework.boot:spring-boot-starter-data-rest")
    generatedCompile("io.springfox:springfox-swagger2:2.9.2")
    generatedCompile("io.springfox:springfox-swagger-ui:2.9.2")

    //mogree spring library
    //implementation ("com.mogree.server:security:1.0.4")
    //implementation("com.mogree.server:library-jwt:1.0.20")
    //implementation("com.mogree.server:mogree-spring:1.0.21")
    //implementation("com.mogree.server:kotlin-extension:1.0.1")

    // include local jar file instead of getting them from mogree´s artifactory
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation ("com.auth0:java-jwt:3.3.0")

    //spring boot dependencies with mysql connector
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("mysql:mysql-connector-java")
    // html template engine for converting mail templates
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.thymeleaf:thymeleaf:3.0.11.RELEASE")

    // logging
    implementation("io.github.microutils:kotlin-logging:1.8.3")

    //springfox for swagger
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")

    // JetBrains
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // apache
    implementation("commons-validator:commons-validator:1.7")
    //check media files for media type
    implementation("org.apache.tika:tika-core:1.25")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Unirest
    implementation("com.mashape.unirest:unirest-java:1.3.1")

    // liquibase
    implementation("org.liquibase:liquibase-core:4.3.1")

    // vimeo video upload libraray
    implementation("com.clickntap:vimeo:2.0")

    // excel upload/download library
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.apache.poi:poi:4.1.2")
    implementation("org.apache.poi:poi-ooxml:4.1.2")

    implementation("org.quartz-scheduler:quartz:2.3.0")
    implementation("org.quartz-scheduler:quartz-jobs:2.3.0")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    testImplementation("com.h2database:h2:1.4.200")
    testImplementation("org.springframework.security:spring-security-test:5.3.4.RELEASE")
}

// this section adds the kotlin folder to java source folder in order to enable spring support for java classes inside kotlin folder
configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/main/kotlin")
        java.srcDir("src/generated")
    }
}
