package com.sinasalik.thrashead.tdlibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class TDImageView extends AsyncTask<Object, ImageView, Bitmap> {
    ImageView _resim;

    public TDImageView(ImageView resim) {
        _resim = resim;
    }

    public Bitmap doInBackground(Object... urls) {
        String url;
        Bitmap bitmap = null;

        if(urls[0] instanceof String) {
            url = TDAraclar.WebAdresDuzelt((String) urls[0]);
            try
            {
                InputStream is = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(is);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(urls[0] instanceof Drawable) {
            bitmap = TDAraclar.DrawableToBitmap((Drawable) urls[0]);
        }

        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        _resim.setImageBitmap(result);
    }
}