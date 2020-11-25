plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":model"))
    implementation(project(":schema"))
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("jakarta.validation:jakarta.validation-api:3.0.0")
    testImplementation("junit:junit:4.13")
    api("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.guava:guava:29.0-jre")
}
