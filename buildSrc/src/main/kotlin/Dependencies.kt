object Dependencies {
    object Quarkus {
        private fun dependency(artifactIdSuffix: String) = "io.quarkus:quarkus-$artifactIdSuffix:${Versions.quarkus}"
        val bom = "io.quarkus.platform:quarkus-bom:${Versions.quarkus}"
        val kotlin = dependency("kotlin")
        val resteasy = dependency("resteasy")
        val jackson = dependency("resteasy-jackson")
        val junit = dependency("junit5")
        val mockito = dependency("junit5-mockito")
    }
    object Jackson {
        private fun dependency(groupSuffix: String, artifactIdSuffix: String) =
            "com.fasterxml.jackson.${groupSuffix}:jackson-${groupSuffix}-$artifactIdSuffix:${Versions.jackson}"
        val kotlin = dependency("module", "kotlin")
//        val jsr310 = dependency("datatype", "jsr310")
//        val joda = dependency("datatype", "joda")
//        val hibernate5 = dependency("datatype", "hibernate5")
    }

    val mockito = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    val restAssured = "io.rest-assured:rest-assured:${Versions.restAssured}"
    val hamkrest = "com.natpryce:hamkrest:${Versions.hamkrest}"
}
