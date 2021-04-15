plugins {
    id("dblp-parser.java-library-conventions")
}

repositories {
    mavenCentral()
}

tasks.test {
    testLogging {
        outputs.upToDateWhen {false}
        showStandardStreams = true
    }
}

dependencies {
    implementation(project(":model"))
    implementation(project(":schema"))
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.0")
    implementation("jakarta.validation:jakarta.validation-api:3.0.0")
}
