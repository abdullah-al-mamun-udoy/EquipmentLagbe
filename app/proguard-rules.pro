# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#********************************Start rules for gson****************************************#

-keep class com.metamamun.equipment.ui.player.** { <fields>; }

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

-keepclassmembers class com.metamamun.equipment.data.model.UserProfile {
    public <init>(...);
}
-keepnames class com.metamamun.equipment.data.model.** { *; }

#-keep class com.lazydeveloper.fit4u2.ui.common** { *; }

#-keepnames public class * extends androidx.fragment.app.Fragment
#-keepnames public class * extends com.google.android.material.appbar.AppBarLayout.*
#-keep class com.lazydeveloper.fit4u2.ui.widget.AppBarLayoutBehavior {
#    public <methods>;
#}
#-keepnames abstract class com.google.android.material.appbar.HeaderBehavior
#-keepclassmembers class com.google.android.material.appbar.HeaderBehavior {
#    private java.lang.Runnable flingRunnable;
#    android.widget.OverScroller scroller;
#}
#-keep class androidx.navigation** { *; }

-keep class androidx.mediarouter.app.MediaRouteActionProvider {*;}
-keepnames class com.google.android.exoplayer2.ext.cast.CastPlayer$StatusListener
-keepclassmembers class com.google.android.exoplayer2.ext.cast.CastPlayer {
    private com.google.android.exoplayer2.ext.cast.CastPlayer$StatusListener statusListener;
}

#-keep class com.google.ads.interactivemedia.** { *; }
#-keep interface com.google.ads.interactivemedia.** { *; }

-keep class * extends java.util.ListResourceBundle { *; }

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

-keep class com.google.protobuf.** { *; }

-keepattributes LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile
-keep public class * extends java.lang.Exception

-keepattributes InnerClasses -keep class **.R -keep class **.R$* { <fields>; }

-dontwarn com.google.protobuf.java_com_google_ads_interactivemedia_v3__sdk_1p_binary_b0308732GeneratedExtensionRegistryLite$Loader
#-dontwarn com.beloo.widget.chipslayoutmanager.Orientation
#-dontwarn com.google.firebase.messaging.TopicOperation$TopicOperations
#-dontwarn com.google.protobuf.java_com_google_ads_interactivemedia_v3__sdk_1p_binary_b0308732GeneratedExtensionRegistryLite$Loader

# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE