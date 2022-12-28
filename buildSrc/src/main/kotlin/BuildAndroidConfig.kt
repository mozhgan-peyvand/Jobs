import java.text.SimpleDateFormat
import java.util.*

object BuildAndroidConfig {

    const val APPLICATION_ID = "com.example.jobs"
    const val TEST_INSTRUMENTATION_RUNNER = "com.example.jobs"
    const val COMPILE_SDK_VERSION = 32
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 32
    const val SUPPORT_LIBRARY_VECTOR_DRAWABLES = true
    const val BUILD_TOOLS_VERSION = "30.0.2"
}

object Ext {
    const val versionBusiness = 1 // versionBusiness rage only between  1 to 9
    const val versionMajor = 0
    const val versionMinor = 0
    const val versionPatch = 0
    const val minimumSdkVersion = 17
}

fun generateVersionCode(): Int {
    return Ext.minimumSdkVersion * 10000000 + Ext.versionBusiness * 1000000 + Ext.versionMajor * 10000 + Ext.versionMinor * 100 + Ext.versionPatch
}

fun generateVersionName(): String {
    return "${Ext.versionBusiness}.${Ext.versionMajor}.${Ext.versionMinor}.${Ext.versionPatch}"
}

@Suppress("SimpleDateFormat")
fun getDate(): String? {
    return SimpleDateFormat(
        "yyyy_MM_dd_HH_mm_ss",
        Locale.getDefault()
    ).format(Date())
}
