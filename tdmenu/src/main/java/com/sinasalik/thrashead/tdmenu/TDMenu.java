package com.sinasalik.thrashead.tdmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TDMenu extends LinearLayout {
    private Context context = getContext();

    private final float scale = context.getResources().getDisplayMetrics().density;
    private final int pixel = (int) (1 * scale + 0.5f);
    final private LayoutParams params = new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
    );

    public Integer FontBuyuklugu;
    public Integer FontStili;
    public Integer BaslikAralikArtisOrani;
    public Integer MetinAralikOrani;
    public boolean AltMenuGoster;
    public boolean IciniBosalt;
    public String AsagiOk;
    public String YukariOk;
    public ArrayList<MenuNesne> MenuListe = new ArrayList<>();

    public TDMenu(Context context) {
        super(context);
        init(null);
    }

    public TDMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable final AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TDMenu, 0, 0);

        AltMenuGoster = a.getBoolean(R.styleable.TDMenu_AltMenuGoster, true);
        IciniBosalt = a.getBoolean(R.styleable.TDMenu_IciniBosalt, false);
        AsagiOk = a.getString(R.styleable.TDMenu_AsagiOk) == null ? " ▼" : " " + a.getString(R.styleable.TDMenu_AsagiOk);
        YukariOk = a.getString(R.styleable.TDMenu_YukariOk) == null ? " ▲" : " " + a.getString(R.styleable.TDMenu_YukariOk);
        FontStili = FontStilDon(a.getInt(R.styleable.TDMenu_FontStili, 0));
        FontBuyuklugu = a.getInt(R.styleable.TDMenu_FontBuyuklugu, 20);
        BaslikAralikArtisOrani = a.getInt(R.styleable.TDMenu_BaslikAralikArtisOrani, 5);
        MetinAralikOrani = a.getInt(R.styleable.TDMenu_MetinAralikOrani, 5);
    }

    public void MenuDoldur() {
        addView(MenuDon(null, MenuListe, 1));
    }

    private LinearLayout MenuDon(LinearLayout layout, ArrayList<MenuNesne> menuListe, int altSeviye) {
        if (layout == null) {
            layout = new LinearLayout(context);
        }

        layout.setOrientation(VERTICAL);

        for (final MenuNesne menu : menuListe) {
            LinearLayout satir;
            TextView lblMenu;
            ImageView imgMenu;

            if (IciniBosalt) {
                this.removeAllViews();
            }

            int padding = pixel * MetinAralikOrani;
            int solPadding = ((altSeviye) * pixel * (MetinAralikOrani + BaslikAralikArtisOrani));

            satir = new LinearLayout(context);
            satir.setOrientation(HORIZONTAL);
            satir.setBackgroundColor(menu.ArkaPlanRenk);
            satir.setLayoutParams(params);
            satir.setPadding(solPadding, padding, padding, padding);

            lblMenu = new TextView(context);
            lblMenu.setLayoutParams(params);
            lblMenu.setTypeface(null, FontStili);
            lblMenu.setTextSize(FontBuyuklugu);
            lblMenu.setBackgroundColor(menu.ArkaPlanRenk);
            lblMenu.setTextColor(menu.MetinRenk);

            if (menu.Ikon > 0) {
                imgMenu = new ImageView(context);

                LayoutParams paramsImg = new LayoutParams(
                        FontBuyuklugu * pixel,
                        FontBuyuklugu * pixel
                );
                paramsImg.setMargins(0, 0, 10 * pixel, 0);
                paramsImg.gravity = Gravity.CENTER_VERTICAL;

                imgMenu.setLayoutParams(paramsImg);
                imgMenu.setImageResource(menu.Ikon);

                satir.addView(imgMenu);
            }

            if (AltMenuGoster && menu.AltKategoriler != null) {
                if (menu.AltKategoriler.size() > 0) {
                    lblMenu.setText(menu.Baslik + AsagiOk);
                } else {
                    lblMenu.setText(menu.Baslik);
                }
            } else {
                lblMenu.setText(menu.Baslik);
            }

            if (menu.Baslik == null) {
                lblMenu.setVisibility(View.GONE);
            }

            satir.addView(lblMenu);

            satir.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View _satir) {
                    if (AltMenuGoster && menu.AltKategoriler != null) {
                        if (menu.AltKategoriler.size() > 0) {
                            boolean resimli = menu.Ikon > 0 ? true : false;
                            MenuAcKapa(_satir, resimli);
                        }

                        return;
                    }

                    Intent intent;
                    switch (menu.LinkTipi) {
                        case Sayfa:
                            if (menu.Sinif != null) {
                                intent = new Intent(context, menu.Sinif);
                                intent.putExtras(menu.Parametreler);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                break;
                            }
                        case Fragman:
                            Fragment fr = null;
                            try {
                                fr = (Fragment) menu.Sinif.newInstance();
                                fr.setArguments(menu.Parametreler);

                                switch (menu.FragmanTipi) {
                                    case Ekle:
                                        menu.FragmanMgr.beginTransaction().add(menu.FragmanLyt, fr, fr.getClass().getSimpleName()).commit();
                                        break;
                                    case Degistir:
                                        menu.FragmanMgr.beginTransaction().replace(menu.FragmanLyt, fr, fr.getClass().getSimpleName()).addToBackStack(fr.getClass().getSimpleName()).commit();
                                        break;
                                }
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case WebSayfasi:
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(menu.Ekstra));
                            context.startActivity(intent);
                            break;
                        case Cikis:
                            Cikis(menu.CikisIkon);
                            break;
                    }
                }
            });

            layout.addView(satir);

            if (AltMenuGoster && menu.AltKategoriler != null) {
                if (menu.AltKategoriler.size() > 0) {
                    altSeviye++;
                    LinearLayout altLayout = new LinearLayout(context);
                    altLayout.setLayoutParams(params);
                    altLayout.setOrientation(VERTICAL);
                    altLayout.setVisibility(GONE);
                    layout.addView(MenuDon(altLayout, menu.AltKategoriler, altSeviye));
                    altSeviye--;
                }
            }
        }

        return layout;
    }

    private void MenuAcKapa(View satir, boolean resimli) {
        int index = resimli == true ? 1 : 0;

        LinearLayout lytParent = (LinearLayout) satir.getParent();
        LinearLayout lytSatir = (LinearLayout) satir;

        int altParentPozisyon = AltParentPozisyon(lytParent, lytSatir);

        LinearLayout lytAltParent = (LinearLayout) (lytParent).getChildAt(altParentPozisyon);

        TextView lblMenu = ((TextView) (lytSatir).getChildAt(index));
        String menuText = lblMenu.getText().toString();

        if (lytAltParent instanceof LinearLayout) {
            if (lytAltParent.getVisibility() == View.GONE) {
                lblMenu.setText(menuText.substring(0, menuText.length() - AsagiOk.length()) + YukariOk);
                lytAltParent.setVisibility(View.VISIBLE);
            } else {
                lblMenu.setText(menuText.substring(0, menuText.length() - AsagiOk.length()) + AsagiOk);
                lytAltParent.setVisibility(View.GONE);
            }
        }
    }

    private Integer AltParentPozisyon(LinearLayout layout, LinearLayout satir) {
        Integer position;

        for (position = 0; position < layout.getChildCount() - 1; position++) {
            View child = layout.getChildAt(position);

            if (child.equals(satir)) {
                return position + 1;
            }
        }

        return -1;
    }

    private Activity AktiviteDon(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return AktiviteDon(((ContextWrapper) cont).getBaseContext());

        return null;
    }

    private void Cikis(int cikisLogo) {
        AlertDialog.Builder alertCikis = new AlertDialog.Builder(context);
        alertCikis.setTitle(R.string.alertCikisBaslik)
                .setMessage(R.string.alertCikisMesaj)
                .setPositiveButton(R.string.btnTamamText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AktiviteDon(context).finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.btnIptalText, null);
        if (cikisLogo > 0) {
            alertCikis.setIcon(cikisLogo);
        }

        alertCikis.show();
    }

    private Integer FontStilDon(int id) {
        switch (id) {
            case 0:
                return Typeface.NORMAL;
            case 1:
                return Typeface.BOLD;
            case 2:
                return Typeface.ITALIC;
            case 3:
                return Typeface.BOLD_ITALIC;
            default:
                return Typeface.NORMAL;
        }
    }

    public static class MenuNesne {
        public MenuNesne() {
            this.LinkTipi = LinkTip.Sayfa;
            this.FragmanTipi = FragmanTip.Ekle;
            this.Parametreler = new Bundle();
            this.Ikon = 0;
            this.CikisIkon = 0;
            this.ArkaPlanRenk = Color.BLACK;
            this.MetinRenk = Color.WHITE;
        }

        public String Baslik;
        public String Ekstra;
        public LinkTip LinkTipi;
        public Class Sinif;
        public FragmentManager FragmanMgr;
        public FragmanTip FragmanTipi;
        public int FragmanLyt;
        public Bundle Parametreler;
        public int Ikon;
        public int CikisIkon;
        public int ArkaPlanRenk;
        public int MetinRenk;
        public ArrayList<MenuNesne> AltKategoriler;

        public enum LinkTip {
            Sayfa,
            Fragman,
            WebSayfasi,
            Cikis
        }

        public enum FragmanTip {
            Ekle,
            Degistir
        }
    }
}