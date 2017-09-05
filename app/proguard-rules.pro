# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\rober\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-useuniqueclassmembernames
-keepattributes SourceFile,LineNumberTable
-allowaccessmodification
# Ignore warnings:
-dontwarn org.mockito.**
-dontwarn org.junit.**
-dontwarn org.joda.convert.**
-dontwarn java.lang.invoke**
-dontwarn com.google.common.**

# Ignore warnings: https://github.com/square/okio/issues/60
-dontwarn okio.**
# Keep the pojos used by GSON or Jackson
-keep class com.futurice.project.models.pojo.** { *; }
# Keep GSON stuff
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
# Keep these for GSON and Jackson
-keepattributes *Annotation*
-keepattributes EnclosingMethod

-keepclassmembers class org.whatifitoldyou.model.** { *; }

# Keep Retrofit
-keep class retrofit.** { *; }
-keep class rx.operators.** {
    *;
}

-keep class com.google.android.gms.internal.** { *; }

# Retrofit
-keep class retrofit2.** { *; }

-dontwarn okhttp3.**
-dontwarn com.google.errorprone.annotations.*

-keepclassmembers class * {
  public <init>(android.content.Context);
}

# Keep support lib v4
-keep class android.support.v4.app.**
-keepclassmembers class android.support.v4.app.** { *; }

# Keep Retrofit
-keepclasseswithmembers class * {
@retrofit.** *;
}
-keepclassmembers class * {
@retrofit.** *;
}
