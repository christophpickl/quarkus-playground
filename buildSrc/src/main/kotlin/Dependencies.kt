object Dependencies {
    object Quarkus {
        private fun dependency(artifactIdSuffix: String) = "io.quarkus:quarkus-$artifactIdSuffix:${Versions.quarkus}"
        val kotlin = dependency("kotlin")
        val resteasy = dependency("resteasy")
        val jackson = dependency("resteasy-jackson")
    }
    object Jackson {
        private fun dependency(groupSuffix: String, artifactIdSuffix: String) =
            "com.fasterxml.jackson.${groupSuffix}:jackson-${groupSuffix}-$artifactIdSuffix:${Versions.jackson}"
        val kotlin = dependency("module", "kotlin")
//        val jsr310 = dependency("datatype", "jsr310")
//        val joda = dependency("datatype", "joda")
//        val hibernate5 = dependency("datatype", "hibernate5")
    }
}
