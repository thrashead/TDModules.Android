package com.sinasalik.thrashead.tdlibrary;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public class TDResim {
    public static Drawable AssetResim(AssetManager manager, String folderName, String fileName) {
        Drawable drawable = null;

        try {
            InputStream inputStream = manager.open(folderName + "/" + fileName);
            drawable = Drawable.createFromStream(inputStream, null);
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return drawable;
    }

    public static Drawable[] AssetResimler(AssetManager manager, String folderName) {
        Drawable[] drawables = null;

        try {
            InputStream inputStream;
            String[] images = manager.list(folderName);
            drawables = new Drawable[images.length];

            for (int i = 0; i < images.length; i++) {
                inputStream = manager.open(folderName + "/" + images[i]);
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                drawables[i] = drawable;
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return drawables;
    }

    public static String[] AssetIsimler(AssetManager manager, String folderName) {
        try {
            return manager.list(folderName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
