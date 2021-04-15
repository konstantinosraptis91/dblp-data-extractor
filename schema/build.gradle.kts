plugins {
    id("dblp-parser.java-library-conventions")
    id("org.openrepose.gradle.plugins.jaxb") version "2.5.0"
}

var generatedDir = "${project.buildDir}".plus("generated-sources/xjc")

sourceSets.main {
    java.srcDirs(generatedDir)
}

dependencies {
    implementation(project(":model"))
    // implementation("org.glassfish.jaxb:jaxb-runtime:2.3.3")
    implementation("com.sun.xml.bind:jaxb-impl:3.0.0")
}




