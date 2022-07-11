repositories {
	mavenCentral()
}

plugins {
	kotlin("jvm") version "1.6.21"//"1.7.0"
	kotlin("plugin.allopen") version "1.6.21" //"1.7.0"
//	kotlin("plugin.jpa") version "1.5.21"
	id("io.quarkus") version "2.10.2.Final"
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(enforcedPlatform(Dependencies.Quarkus.bom))
	implementation(Dependencies.Quarkus.resteasy)
	implementation(Dependencies.Quarkus.kotlin)
	implementation(Dependencies.Quarkus.jackson)
	implementation(Dependencies.Jackson.kotlin)
	// quarkus DB ...
	// quarkusDev("io.quarkus:quarkus-jdbc-h2")

	testImplementation(Dependencies.Quarkus.junit)
	testImplementation(Dependencies.Quarkus.mockito)
	testImplementation(Dependencies.mockito)
	testImplementation(Dependencies.restAssured)
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
