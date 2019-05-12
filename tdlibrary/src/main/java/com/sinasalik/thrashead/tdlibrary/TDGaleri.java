package com.sinasalik.thrashead.tdlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class TDGaleri extends BaseAdapter {
    private Context _context;
    private GaleriTipi _galeriTipi;
    private String[] _resimler;
    private Drawable[] _resimlerDr;

    public TDGaleri(Context context, String[] resimler) {
        _context = context;
        _resimler = resimler;
        _galeriTipi = GaleriTipi.Web;
    }

    public TDGaleri(Context context, Drawable[] resimler) {
        _context = context;
        _resimlerDr = resimler;
        _galeriTipi = GaleriTipi.Drawable;
    }

    public int getCount() {
        switch (_galeriTipi) {
            case Drawable :
                return this._resimlerDr.length;
            case Web :
                return this._resimler.length;
        }

        return -1;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView img = new ImageView(_context);
        Bitmap bitmap = null;

        try {
            switch (_galeriTipi) {
                case Drawable :
                    bitmap = TDAraclar.DrawableToBitmap(_resimlerDr[position]);
                case Web :
                    bitmap = TDAraclar.BitMapFromUrl(_resimler[position].toString());
            }

            img.setImageBitmap(bitmap);
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            img.setLayoutParams(new android.widget.Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, android.widget.Gallery.LayoutParams.MATCH_PARENT));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return img;
    }

    public enum GaleriTipi {
        Drawable,
        Web
    }
}