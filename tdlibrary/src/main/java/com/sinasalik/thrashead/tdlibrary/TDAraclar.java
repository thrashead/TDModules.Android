package com.sinasalik.thrashead.tdlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.content.Context.MODE_PRIVATE;

public class TDAraclar {
    public static String WebAdresDon(String metin, boolean kucukHarfYap) {
        if (kucukHarfYap == true) {
            metin = metin.toLowerCase();
        }

        metin = WebAdresDegistir(metin);
        metin = TDAraclar.TekYap(metin, "-");
        metin.substring(0, metin.lastIndexOf("-") - 1);

        return metin;
    }

    private static String WebAdresDegistir(String metin) {
        metin = metin.replace("&amp;", "");
        metin = metin.replace("&#304;", "İ");
        metin = metin.replace("&#305;", "ı");
        metin = metin.replace("&#214;", "Ö");
        metin = metin.replace("&#246;", "ö");
        metin = metin.replace("&#220;", "Ü");
        metin = metin.replace("&#252;", "ü");
        metin = metin.replace("&#199;", "Ç");
        metin = metin.replace("&#231;", "ç");
        metin = metin.replace("&#286;", "Ğ");
        metin = metin.replace("&#287;", "ğ");
        metin = metin.replace("&#350;", "Ş");
        metin = metin.replace("&#351;", "ş");
        metin = metin.replace("%c4%9e", "Ğ");
        metin = metin.replace("%c4%9f", "ğ");
        metin = metin.replace("%c3%9c", "Ü");
        metin = metin.replace("%c3%bc", "ü");
        metin = metin.replace("%c5%9e", "Ş");
        metin = metin.replace("%c5%9f", "ş");
        metin = metin.replace("%c4%b0", "İ");
        metin = metin.replace("%c4%b1", "ı");
        metin = metin.replace("%c3%96", "Ö");
        metin = metin.replace("%c3%b6", "ö");
        metin = metin.replace("%c3%87", "Ç");
        metin = metin.replace("%c3%a7", "ç");

        metin = metin.replace(" ", "-");
        metin = metin.replace("?", "-");
        metin = metin.replace("%", "-");
        metin = metin.replace("½", "-");
        metin = metin.replace("$", "-");
        metin = metin.replace("#", "-");
        metin = metin.replace("£", "-");
        metin = metin.replace("!", "-");
        metin = metin.replace("^", "-");
        metin = metin.replace("'", "-");
        metin = metin.replace("&", "-");
        metin = metin.replace("/", "-");
        metin = metin.replace("*", "-");
        metin = metin.replace("\\", "-");
        metin = metin.replace("[", "-");
        metin = metin.replace("]", "-");
        metin = metin.replace("{", "-");
        metin = metin.replace("}", "-");
        metin = metin.replace("+", "-");
        metin = metin.replace("é", "-");
        metin = metin.replace("\"", "-");
        metin = metin.replace(",", "-");
        metin = metin.replace(".", "-");
        metin = metin.replace("~", "-");
        metin = metin.replace(";", "-");
        metin = metin.replace(":", "-");
        metin = metin.replace("<", "-");
        metin = metin.replace(">", "-");
        metin = metin.replace("|", "-");
        metin = metin.replace("@", "-");
        metin = metin.replace("æ", "-");
        metin = metin.replace("ß", "-");
        metin = metin.replace("¨", "-");

        metin = metin.replace("Ğ", "G");
        metin = metin.replace("ğ", "g");
        metin = metin.replace("Ü", "U");
        metin = metin.replace("ü", "u");
        metin = metin.replace("Ş", "S");
        metin = metin.replace("ş", "s");
        metin = metin.replace("İ", "I");
        metin = metin.replace("ı", "i");
        metin = metin.replace("Ö", "O");
        metin = metin.replace("ö", "o");
        metin = metin.replace("Ç", "C");
        metin = metin.replace("ç", "c");
        metin = metin.replace("â", "a");
        metin = metin.replace("Â", "a");

        return metin;
    }

    public static String WebAdresDuzelt(String adres) {
        adres = adres.replace("localhost", "10.0.2.2");
        adres = adres.replace("<br>", "\n");
        adres = adres.replace("<br/>", "\n");
        adres = adres.replace("<br />", "\n");
        adres = adres.replace("<b>", "");
        adres = adres.replace("</b>", "");
        adres = LabelaYaz(adres);

        return adres;
    }

    public static String LabelaYaz(String metin) {
        metin = metin.replace("&amp;", "");
        metin = metin.replace("&#304;", "İ");
        metin = metin.replace("&#305;", "ı");
        metin = metin.replace("&#214;", "Ö");
        metin = metin.replace("&#246;", "ö");
        metin = metin.replace("&#220;", "Ü");
        metin = metin.replace("&#252;", "ü");
        metin = metin.replace("&#199;", "Ç");
        metin = metin.replace("&#231;", "ç");
        metin = metin.replace("&#286;", "Ğ");
        metin = metin.replace("&#287;", "ğ");
        metin = metin.replace("&#350;", "Ş");
        metin = metin.replace("&#351;", "ş");
        metin = metin.replace("%c4%9e", "Ğ");
        metin = metin.replace("%c4%9f", "ğ");
        metin = metin.replace("%c3%9c", "Ü");
        metin = metin.replace("%c3%bc", "ü");
        metin = metin.replace("%c5%9e", "Ş");
        metin = metin.replace("%c5%9f", "ş");
        metin = metin.replace("%c4%b0", "İ");
        metin = metin.replace("%c4%b1", "ı");
        metin = metin.replace("%c3%96", "Ö");
        metin = metin.replace("%c3%b6", "ö");
        metin = metin.replace("%c3%87", "Ç");
        metin = metin.replace("%c3%a7", "ç");

        metin = metin.replace("&#39;", "'");
        metin = metin.replace("&ouml;", "ö");
        metin = metin.replace("&Ouml;", "Ö");
        metin = metin.replace("&uuml;", "ü");
        metin = metin.replace("&Uuml;", "Ü");
        metin = metin.replace("&ccedil;", "ç");
        metin = metin.replace("&Ccedil;", "Ç");
        metin = metin.replace("&acirc;", "â");
        metin = metin.replace("&Acirc;", "Â");

        metin = metin.replace("\\n", "");
        metin = metin.replace("\\r", "");

        return metin;
    }

    public static boolean InternetVarmi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static Object KaynakDegerDon(Context context, int id, KaynakTip kaynakTip) {
        Resources res = context.getResources();

        switch (kaynakTip) {
            case Boolean:
                return res.getBoolean(id);
            case Integer:
                return res.getInteger(id);
            case String:
                return res.getString(id);
            default:
                return res.getString(id);
        }
    }

    public enum KaynakTip {
        Boolean,
        Integer,
        String
    }

    public static URL URLDon(String url) {
        URL returnURI = null;
        try {
            returnURI = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return returnURI;
    }

    public static Activity AktiviteDon(Context context) {
        if (context == null)
            return null;
        else if (context instanceof Activity)
            return (Activity) context;
        else if (context instanceof ContextWrapper)
            return AktiviteDon(((ContextWrapper) context).getBaseContext());

        return null;
    }

    public static void Kapat(Context context) {
        TDAraclar.AktiviteDon(context).finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean CihazKucukmu(Context context) {
        if (((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) || ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL)) {
            return true;
        } else {
            return false;
        }
    }

    public static void BellegeYaz(Activity aktivite, String baslik, String anahtar, Object deger) {
        SharedPreferences.Editor editor = aktivite.getSharedPreferences(baslik, MODE_PRIVATE).edit();

        if (deger instanceof String) {
            editor.putString(anahtar, (String) deger);
        } else if (deger instanceof Integer) {
            editor.putInt(anahtar, (Integer) deger);
        } else if (deger instanceof Boolean) {
            editor.putBoolean(anahtar, (Boolean) deger);
        } else if (deger instanceof Float) {
            editor.putFloat(anahtar, (Float) deger);
        } else if (deger instanceof Long) {
            editor.putLong(anahtar, (Long) deger);
        }

        editor.commit();
    }

    public static Object BellektenOku(Activity aktivite, String baslik, String anahtar, Class donenSinif) {
        SharedPreferences prefs = aktivite.getSharedPreferences(baslik, MODE_PRIVATE);

        if (donenSinif == String.class) {
            return prefs.getString(anahtar, null);
        } else if (donenSinif == Integer.class) {
            return prefs.getInt(anahtar, 0);
        } else if (donenSinif == Boolean.class) {
            return prefs.getBoolean(anahtar, false);
        } else if (donenSinif == Float.class) {
            return prefs.getFloat(anahtar, 0);
        } else if (donenSinif == Long.class) {
            return prefs.getLong(anahtar, 0);
        } else {
            return null;
        }
    }

    // IntegerYap - String değeri int'e çevirmeye çalışır. Çeviremezse -1 döner.
    public static int IntegerYap(String deger) {
        try {
            return Integer.parseInt(deger);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // TekYap - Burada 2 tane yanyana aynı string geliyorsa tekleyene kadar döner. ("---" -> "-") veya ("alialialiali" -> "ali")
    public static String TekYap(String metin, String karakter) {
        do {
            metin = metin.replace(karakter + karakter, karakter);
        } while (metin.contains(karakter + karakter));

        return metin;
    }

    // MetinKarsilastir - 2 yazıyı kıyaslar. doMD5Compare true giderse ilgili text'i MD5'e çevirip compareText ile kıyaslar ve bool değer döner.
    public static boolean MetinKarsilastir(String metin, String karsitMetin) {
        if (metin == karsitMetin)
            return true;
        else
            return false;
    }

    // IsNull - String Boş mu değil mi kontrol eder
    public static boolean IsNull(String metin) {
        return metin == null ? true : metin.equals("") ? true : false;
    }

    // MetinKisalt - Gönderilen metni böler
    public static String MetinKisalt(String metin, int baslangic, int harfSayisi, int noktaSayisi) {
        if (metin.length() > harfSayisi) {
            metin = metin.substring(baslangic, harfSayisi);

            if (noktaSayisi > 0) {
                for (int i = 0; i < noktaSayisi; i++) {
                    metin += ".";
                }
            }
            return metin;
        } else
            return metin;
    }

    // IlkHarfBuyuk - Stringin ilk harfini büyük yazar.
    public static String IlkHarfBuyuk(String metin) {
        return metin.substring(0, 1).toUpperCase() + metin.substring(1);
    }

    // IlkHarfKucuk - Stringin ilk harfini küçük yazar.
    public static String IlkHarfKucuk(String metin) {
        return metin.substring(0, 1).toLowerCase() + metin.substring(1);
    }

    // CokluIlkHarfBuyuk - Birden çok kelimeli yazının ilk harflerini büyük yazar.
    public static String CokluIlkHarfBuyuk(String metin, boolean gerisiKucuk) {
        if (gerisiKucuk == true) {
            metin = metin.toLowerCase();
        }

        String[] kelimeler = metin.split(" ");
        metin = "";

        for (String item : kelimeler) {
            metin += item.substring(0, 1).toUpperCase() + item.substring(1) + " ";
        }

        return metin.trim();
    }

    // CokluIlkHarfKucuk - Birden çok kelimeli yazının ilk harflerini küçük yazar.
    public static String CokluIlkHarfKucuk(String metin, boolean gerisiBuyuk) {
        if (gerisiBuyuk == true) {
            metin = metin.toUpperCase();
        }

        String[] kelimeler = metin.split(" ");
        metin = "";

        for (String item : kelimeler) {
            metin += item.substring(0, 1).toLowerCase() + item.substring(1) + " ";
        }

        return metin.trim();
    }

    // HarfSayisi - İlgili textteki belirtilen harf sayısını döner.
    public static int HarfSayisi(String metin, char harf) {
        int sayac = 0;

        for (char item : metin.toCharArray()) {
            if (item == harf) {
                sayac++;
            }
        }

        return sayac;
    }

    public static Bitmap BitMapFromUrl(String url) {
        Bitmap bitmap = null;
        URLConnection connection;
        InputStream input;
        URL webUrl;

        try {
            webUrl = new URL(url.replaceAll(" ", "%20"));
            connection = webUrl.openConnection();
            connection.setRequestProperty("User-agent", "Mozilla/4.0");
            connection.connect();
            input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap DrawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static Drawable BitmapToDrawable(Resources resources, Bitmap bitmap) {
        return new BitmapDrawable(resources, bitmap);
    }

    public static boolean KlasorOlustur(String path) {
        boolean kontrol = false;

        try {
            File dir = new File(path);

            if (!dir.exists()) {
                kontrol = dir.mkdirs();
            }
            else {
                kontrol = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return kontrol;
    }
}

