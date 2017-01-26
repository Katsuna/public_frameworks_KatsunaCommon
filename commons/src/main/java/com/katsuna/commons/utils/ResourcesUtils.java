package com.katsuna.commons.utils;

import android.content.Context;

public class ResourcesUtils {

    private static int getResourceIdByName(String packageName, String className, String name) {
        Class r;
        int id = 0;
        try {
            r = Class.forName(packageName + ".R");

            Class[] classes = r.getClasses();
            Class desireClass = null;

            for (int i = 0; i < classes.length; i++) {
                if (classes[i].getName().split("\\$")[1].equals(className)) {
                    desireClass = classes[i];

                    break;
                }
            }

            if (desireClass != null) {
                id = desireClass.getField(name).getInt(desireClass);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return id;
    }

    private static int getResourceIdByName(Context context, String className, String name) {
        return getResourceIdByName(context.getPackageName(), className, name);
    }

    public static int getDimen(Context context, String name) {
        return getResourceIdByName(context.getPackageName(), "dimen", name);
    }

    public static int getColor(Context context, String name) {
        return getResourceIdByName(context.getPackageName(), "color", name);
    }

    public static int getString(Context context, String name) {
        return getResourceIdByName(context.getPackageName(), "string", name);
    }

    public static int getId(Context context, String name) {
        return getResourceIdByName(context.getPackageName(), "id", name);
    }

    public static int getStyle(Context context, String name) {
        return getResourceIdByName(context.getPackageName(), "style", name);
    }
}