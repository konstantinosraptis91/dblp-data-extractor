plugins {
    id("dblp-parser.java-library-conventions")
    id("org.openrepose.gradle.plugins.jaxb") version "2.5.0"
}

var generatedDir = "${project.buildDir}".plus("generated-sources/xjc")
// var jaxbImplVersion = "2.2.11";
var jaxbImplVersion = "2.3.0.1";
var jaxbApiVersion = "2.2.12"

sourceSets.main {
    java.srcDirs(generatedDir)
}

dependencies {
    implementation(project(":model"))
    // implementation ("com.googlecode.jaxb-namespaceprefixmapper-interfaces:JAXBNamespacePrefixMapper:2.2.4")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.3")

    jaxb("com.sun.xml.bind:jaxb-core:".plus(jaxbImplVersion))
    jaxb("com.sun.xml.bind:jaxb-xjc:".plus(jaxbImplVersion))
    jaxb("com.sun.xml.bind:jaxb-impl:".plus(jaxbImplVersion))
    jaxb("javax.xml.bind:jaxb-api:".plus(jaxbImplVersion))
    jaxb("javax.activation:activation:1.1.1")

    implementation("com.sun.xml.bind:jaxb-core:".plus(jaxbImplVersion))
    implementation ("com.sun.xml.bind:jaxb-xjc:".plus(jaxbImplVersion))
    implementation ("com.sun.xml.bind:jaxb-impl:".plus(jaxbImplVersion))
    api ("javax.xml.bind:jaxb-api:".plus(jaxbImplVersion))
    implementation ("javax.activation:activation:1.1.1")
}

jaxb {
    xsdDir = "$projectDir".plus("/src/main/resources/xsd/")

    xjc {
        destinationDir = "$projectDir".plus("/generated-sources/xjc")
        args = listOf("-verbose")
    }
}

tasks.withType<JavaCompile> {
    dependsOn("xjc")
}




