package com.sinasalik.thrashead.tdframework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Connection extends SQLiteOpenHelper {
    private String _dbPath;
    private String _dbName;

    private final Context _context;

    public Connection(Context context, int dbVer, String dbPath, String dbName) {
        super(context, dbPath + "/" + dbName, null, dbVer);
        this._context = context;
        this._dbPath = dbPath;
        this._dbName = dbName;

        CreateDatabase();
    }

    public void CreateDatabase() {
        boolean dbExist = CheckDatabase();

        if (!dbExist) {
            try {
                CopyDatabase();
            } catch (IOException e) {
                throw new Error("Veritabanı kopyalanamadı...");
            }
        }
    }

    private boolean CheckDatabase() {
        SQLiteDatabase DB;
        Boolean checkDB = false;

        try {
            String myPath = this._dbPath + "/" + this._dbName;
            DB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            DB = null;
            checkDB = false;
        }

        if (DB != null) {
            checkDB = true;

            if (DB.isOpen()) {
                DB.close();
            }

            DB = null;
        }
        else {
            checkDB = false;
        }

        return checkDB;
    }

    private void CopyDatabase() throws IOException {
        InputStream myInput = this._context.getAssets().open(this._dbName);

        String outFileName = this._dbPath + "/" + this._dbName;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
