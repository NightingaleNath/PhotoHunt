import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.ksp)
}

android {
	namespace = "com.codelytical.photohunt"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.codelytical.photohunt"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	val localProperties = gradleLocalProperties(rootDir, providers)
	val apiKey: String = localProperties.getProperty("API_KEY") ?: ""

	buildTypes {

		getByName("debug") {
			versionNameSuffix = " - debug-1"
			applicationIdSuffix = ".debug"
			buildConfigField("String", "API_KEY", "\"$apiKey\"")
		}

		getByName("release") {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			buildConfigField("String", "API_KEY", "\"$apiKey\"")
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
		buildConfig = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.navigation.compose)

	// Gemini
	implementation(libs.generativeai)

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)
	ksp(libs.androidx.hilt.compiler)
	implementation (libs.androidx.lifecycle.viewmodel.compose)
	implementation(libs.androidx.hilt.navigation.compose)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}