package com.laundrypedia.laundrypedia.helper;


import com.laundrypedia.laundrypedia.BuildConfig;

public class Log {
    //To print log's and exceptions


    public static final boolean LOG = BuildConfig.DEBUG;    // do something for a debug build


    public static void i(String tag, String string) {
        if (LOG) android.util.Log.i(tag, string);
    }

    public static void e(String tag, String string) {
        if (LOG) android.util.Log.e(tag, string);
    }

    public static void e(String tag, String string, Exception e) {
        if (LOG) android.util.Log.e(tag, string, e);
    }

    public static void d(String tag, String string) {
        if (LOG) android.util.Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (LOG) android.util.Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (LOG) android.util.Log.w(tag, string);
    }

    public static void w(String tag, String string, Exception e) {
        if (LOG) android.util.Log.w(tag, string, e);
    }

    public static void printStackTrace(Exception e) {
        if (LOG) e.printStackTrace();
    }
}