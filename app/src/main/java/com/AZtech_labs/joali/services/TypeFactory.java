package com.AZtech_labs.joali.services;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TypeFactory {
    final String CAVIAR_REGULAR="fonts/CaviarDreams.ttf";
    final String CAVIAR_BOLD="fonts/CaviarDreams_Bold.ttf";
    final String CAVIAR_BOLDITALIC="fonts/CaviarDreams_BoldItalic.ttf";
    final String CAVIAR_ITALIC="fonts/CaviarDreams_Italic.ttf";

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
