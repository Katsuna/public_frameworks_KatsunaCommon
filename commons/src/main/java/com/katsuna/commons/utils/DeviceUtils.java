package com.katsuna.commons.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeviceUtils {

    private static String GETPROP_EXECUTABLE_PATH = "/system/bin/getprop";

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wManager.getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics;
    }

    public static int getDisplayHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static boolean isUserSetupComplete(Context context) {
        ContentResolver resolver = context.getContentResolver();
        return Settings.Secure.getInt(resolver, Settings.Global.DEVICE_PROVISIONED, 0) != 0;
    }

    public static boolean isPackageInstalled(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static String getProp(String propName) {
        Process process = null;
        BufferedReader bufferedReader = null;

        try {
            process = new ProcessBuilder().command(GETPROP_EXECUTABLE_PATH, propName).redirectErrorStream(true).start();
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bufferedReader.readLine();
            if (line == null){
                line = ""; //prop not set
            }
            return line;
        } catch (Exception e) {
            return ""; //prop not set
        } finally{
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {}
            }
            if (process != null){
                process.destroy();
            }
        }
    }

}
