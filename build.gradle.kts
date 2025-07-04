plugins {
    `java-library`
    `maven-publish`
    id("io.github.apdevteam.github-packages") version "1.2.2"
}

repositories {
    gradlePluginPortal()
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven { githubPackage("apdevteam/movecraft")(this) }
}

dependencies {
    api("org.jetbrains:annotations-java5:24.1.0")
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    compileOnly("net.countercraft:movecraft:+")
}

group = "net.countercraft"
version = "1.0.0-beta-3"
description = "Movecraft-ShipRules"
java.toolchain.languageVersion = JavaLanguageVersion.of(21)

tasks.jar {
    archiveBaseName.set("Movecraft-ShipRules")
    archiveClassifier.set("")
    archiveVersion.set("")
}

tasks.processResources {
    from(rootProject.file("LICENSE.md"))
    filesMatching("*.yml") {
        expand(mapOf("projectVersion" to project.version))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.countercraft.movecraft.rules"
            artifactId = "movecraft-shiprules"
            version = "${project.version}"

            artifact(tasks.jar)
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/apdevteam/movecraft-shiprules")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
