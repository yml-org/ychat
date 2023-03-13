object Versions {
    const val GRADLE_PLUGIN = "7.3.0"
    const val SPM_PLUGIN = "2.0.3"
    const val DETEKT_PLUGIN = "1.22.0"
    const val KTLINT_PLUGIN = "11.0.0"
    const val KOVER_PLUGIN = "0.6.1"
    const val PUBLISH_PLUGIN = "0.24.0"
    const val KOTLIN = "1.7.20"
    const val COMPOSE = "1.3.3"
    const val COMPOSE_FOUNDATION = "1.3.1"
    const val COMPOSE_ACTIVITY = "1.6.1"
    const val COMPOSE_NAVIGATION = "2.5.3"
    const val COMPOSE_LIVEDATA = "1.3.3"
    const val COIL = "2.2.2"
    const val KTOR = "2.2.2"
    const val KOIN = "3.2.0"
    const val MATERIAL_DESIGN = "1.6.1"
    const val MOCKK = "1.13.4"
    const val MOCKK_COMMON = "1.12.5"
    const val SPRING = "2.4.5"
}

object Dependencies {

    object Spring {
        const val SPRING_DEP = "org.springframework.boot:spring-boot-dependencies:${Versions.SPRING}"
        const val SPRING_WEB = "org.springframework.boot:spring-boot-starter-web"
    }

    object Network {
        const val KTOR_NEGOTIATION = "io.ktor:ktor-client-content-negotiation:${Versions.KTOR}"
        const val KTOR_SERIALIZATION = "io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR}"
        const val KTOR_CORE = "io.ktor:ktor-client-core:${Versions.KTOR}"
        const val KTOR_LOGGING = "io.ktor:ktor-client-logging:${Versions.KTOR}"
        const val KTOR_ANDROID = "io.ktor:ktor-client-android:${Versions.KTOR}"
        const val KTOR_IOS = "io.ktor:ktor-client-ios:${Versions.KTOR}"
        const val KTOR_JAVA = "io.ktor:ktor-client-java:${Versions.KTOR}"
    }

    object DI {
        const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
        const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
        const val KOIN_COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.KOIN}"
    }

    object UI {
        const val MATERIAL_DESIGN = "com.google.android.material:${Versions.MATERIAL_DESIGN}"
        const val COMPOSE_UI = "androidx.activity:activity-compose:${Versions.COMPOSE}"
        const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
        const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
        const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE_FOUNDATION}"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_FOUNDATION}"
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
        const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
        const val COMPOSE_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE_LIVEDATA}"
        const val COIL = "io.coil-kt:coil-compose:${Versions.COIL}"
    }

    object Test {
        const val MOCKK_COMMON = "io.mockk:mockk-common:${Versions.MOCKK_COMMON}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val MOCKK_JVM = "io.mockk:mockk-jvm:${Versions.MOCKK}"
        const val KTOR = "io.ktor:ktor-client-mock:${Versions.KTOR}"
        const val KOIN = "io.insert-koin:koin-test:${Versions.KOIN}"
    }
}
