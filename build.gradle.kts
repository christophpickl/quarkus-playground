repositories {
	mavenCentral()
}

plugins {
	kotlin("jvm") version "1.6.21"//"1.7.0"
	kotlin("plugin.allopen") version "1.6.21" //"1.7.0"
//	kotlin("plugin.jpa") version "1.5.21"
	id("io.quarkus") version "2.10.2.Final"
}

object Versions {
	val quarkus = "2.10.1.Final" // "2.10.2.Final" ... not found :-( https://repo.maven.apache.org/maven2/io/quarkus/platform/quarkus-bom/
}
object Dependencies {
	object Quarkus {
		private fun dependency(artifactIdSuffix: String) = "io.quarkus:quarkus-$artifactIdSuffix:${Versions.quarkus}"
		val resteasy = dependency("resteasy")
		val kotlin = dependency("kotlin")
	}
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${Versions.quarkus}"))
	implementation(Dependencies.Quarkus.resteasy)
	implementation(Dependencies.Quarkus.kotlin)
	//	implementation("io.quarkus:quarkus-resteasy-jackson")
	// quarkus DB
	// quarkusDev("io.quarkus:quarkus-jdbc-h2")

	/*
	testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.quarkus:quarkus-junit5-mockito")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("io.rest-assured:rest-assured")
	 */
}

allOpen {
	annotation("javax.ws.rs.Path")
	annotation("javax.enterprise.context.ApplicationScoped")
	annotation("io.quarkus.test.junit.QuarkusTest")
}


java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
	options.compilerArgs.add("-parameters")
}