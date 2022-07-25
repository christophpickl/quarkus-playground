import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

repositories {
	mavenCentral()
}

plugins {
	kotlin("jvm") version Versions.Plugins.kotlin
	kotlin("plugin.allopen") version Versions.Plugins.allOpen
//	kotlin("plugin.jpa") version Versions.Plugins.jpa
	id("io.quarkus") version Versions.Plugins.quarkus
	id("com.github.ben-manes.versions") version Versions.Plugins.versions
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(enforcedPlatform(Dependencies.Quarkus.bom))
	implementation(Dependencies.Quarkus.resteasy)
	implementation(Dependencies.Quarkus.kotlin)
	implementation(Dependencies.Quarkus.jackson)
	implementation(Dependencies.Jackson.kotlin)
	// quarkusDev("io.quarkus:quarkus-jdbc-h2")

	testImplementation(Dependencies.Quarkus.junit)
	testImplementation(Dependencies.Quarkus.mockito)
	testImplementation(Dependencies.mockito)
	testImplementation(Dependencies.RestAssured.restAssured)
	testImplementation(Dependencies.RestAssured.kotlinExtensions)
	testImplementation(Dependencies.hamkrest)
}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
	options.compilerArgs.add("-parameters")
}

allOpen {
	annotation("javax.ws.rs.Path")
	annotation("javax.enterprise.context.ApplicationScoped")
	annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<DependencyUpdatesTask> {
	val rejectPatterns = listOf("alpha", "beta", "EAP", "RC", "m").map { it.toLowerCase() }
	rejectVersionIf {
		val version = candidate.version.toLowerCase()
		rejectPatterns.any { version.contains(it) }
	}
}
