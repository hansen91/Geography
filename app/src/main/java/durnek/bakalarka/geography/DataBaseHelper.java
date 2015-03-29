package durnek.bakalarka.geography;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import durnek.bakalarka.geography.classes.Kontinent;

/**
 * Created by Lukas on 28. 2. 2015.
 */
public class DataBaseHelper
        extends SQLiteOpenHelper {

    public static String DB_PATH = "/data/data/durnek.bakalarka.geography/databases/";
    public static String DB_NAME = "db.sqlite";
    public static final int DB_VERSION = 1;

    //TABULKY
    public static final String TB_KONTINENT = "Kontinent";
    public static final String TB_STAT = "Stat";

    //ATRIBUTY
    private static final String KEY_ID = "id";
    private static final String KEY_NAZOV = "nazov";
    private static final String KEY_POC_STATOV = "pocStatov";
    private static final String KEY_POC_UZEMI = "pocUzemi";
    private static final String KEY_ROZLOHA = "rozloha";
    private static final String KEY_POPULACIA = "populacia";

    private SQLiteDatabase myDB;
    private Context context;

    public DataBaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public synchronized void close(){
        if(myDB!=null){
            myDB.close();
        }
        super.close();
    }

    /***
     * Check if the database is exist on device or not
     * @return
     */
    private boolean checkDataBase() {
        SQLiteDatabase tempDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            Log.e("error", e.getMessage());
        }
        if (tempDB != null)
            tempDB.close();
        return tempDB != null ? true : false;
    }

    /***
     * Copy database from source code assets to device
     * @throws IOException
     */
    public void copyDataBase() throws IOException {
        try {
            InputStream myInput = context.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;

            while((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            Log.e("error - copyDatabase", e.getMessage());
        }

    }

    /***
     * Open database
     * @throws SQLException
     */
    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /***
     * Check if the database doesn't exist on device, create new one
     * @throws IOException
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("error - create", e.getMessage());
            }
        }
    }

    public List<String> getAllContinents(){
        List<String> listContinents = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;

        try{
            c = db.rawQuery("SELECT * FROM " + TB_KONTINENT,null);
            if(c == null) return null;

            String nazov;
            c.moveToFirst();
            do{
                nazov = c.getString(1);
                listContinents.add(nazov);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e){
            Log.e("chyba v selecte ", e.getMessage());
        }
        db.close();
        return listContinents;
    }

    public Kontinent getContinent(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query("Kontinent", new String[] { KEY_ID,
                        KEY_NAZOV, KEY_POC_STATOV, KEY_POC_UZEMI, KEY_ROZLOHA, KEY_POPULACIA }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (c != null)
            c.moveToFirst();

        Kontinent kontinent = new Kontinent(Integer.parseInt(c.getString(0)),
                c.getString(1), Integer.parseInt(c.getString(2)),Integer.parseInt(c.getString(3)),Long.parseLong(c.getString(4)),
                Long.parseLong(c.getString(5)));
        // return kontinent
        return kontinent;
    }

    public Kontinent dajKontinent(int id){
        Kontinent kontinent = null;
        String select = "SELECT " + KEY_NAZOV + ", "+ KEY_POC_STATOV + ", " +  KEY_POC_UZEMI + ", " +
                KEY_ROZLOHA + ", "+ KEY_POPULACIA + " FROM " + TB_KONTINENT +
                " WHERE " + KEY_ID + " = " + id;
        //vysledok z curzora
        Cursor c = getReadableDatabase().rawQuery(select,null);
        if(c != null){
            c.moveToFirst();

            kontinent = new Kontinent(Integer.parseInt(c.getString(0)),
                    c.getString(1), Integer.parseInt(c.getString(2)),Integer.parseInt(c.getString(3)),Long.parseLong(c.getString(4)),
                    Long.parseLong(c.getString(5)));
            c.close();
        }
        return kontinent;
    }


 /* public ArrayList<HashMap<String, String>> getAllContinents(){
        ArrayList<HashMap<String,String>> listContinents;
        listContinents = new ArrayList<HashMap<String,String>>();
        String select = "SELECT * FROM " + TB_KONTINENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(select,null);

        //presun na prvy zaznam
        if(c.moveToFirst()){
            do{
                HashMap<String,String> hm = new HashMap<String,String>();
                hm.put(KEY_NAZOV, c.getString(1));
                /*hm.put(KEY_POC_STATOV, c.getString(2));
                hm.put(KEY_POC_UZEMI, c.getString(3));
                hm.put(KEY_ROZLOHA, c.getString(4));
                hm.put(KEY_POPULACIA, c.getString(5));
                //listContinents.add(hm);
            }while(c.moveToNext()); //kurzor na dalsi zaznam
        }
        return listContinents;
    }

    public HashMap<String,String> getContinent(String id){
        HashMap<String,String> hm = new HashMap<String,String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + TB_KONTINENT + "WHERE KEY_ID ='"+id+"'";
        Cursor c = db.rawQuery(select,null);
        if(c.moveToFirst()){
            do{
                hm.put(KEY_NAZOV, c.getString(1));
                hm.put(KEY_POC_STATOV, c.getString(2));
                hm.put(KEY_POC_UZEMI, c.getString(3));
                hm.put(KEY_ROZLOHA, c.getString(4));
                hm.put(KEY_POPULACIA, c.getString(5));
            }while(c.moveToNext());
        }
        return hm;
    }


   /* public Kontinent getKontinent(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABULKA, new String[] {
                id_kontinentu,nazov
        }, id_kontinentu + "=?", new String[] {String.valueOf(id)},null,null,null );
        if(cursor != null) cursor.moveToFirst();

        Kontinent kontinent = new Kontinent(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        return kontinent;
    }*/



}
