plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"

    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.56")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")
    testImplementation("io.cucumber:cucumber-java8:6.8.1")
    testImplementation("io.cucumber:cucumber-junit:6.8.1")
    testImplementation("io.cucumber:cucumber-picocontainer:6.8.1")
    testImplementation("org.amshove.kluent:kluent:1.50")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation ("junit:junit:4.12")
}

