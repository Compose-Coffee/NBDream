plugins {
    alias(libs.plugins.nbdream.android.library)
    alias(libs.plugins.nbdream.android.hilt)
}

android {
    namespace = "kr.co.nbdream.core.domain"
}

dependencies {
    implementation(projects.core.common)
}