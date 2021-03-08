plugins {
    id("dblp-parser.java-library-conventions")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":model"))
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.3")
    testImplementation("junit:junit:4.13")
}