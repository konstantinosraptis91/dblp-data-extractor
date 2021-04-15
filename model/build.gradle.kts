plugins {
    id("dblp-parser.java-library-conventions")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.validation:jakarta.validation-api:3.0.0")
    // implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.0")
}
