package durnek.bakalarka.geography;

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
import durnek.bakalarka.geography.classes.Stat;

/**
 * Created by Lukas on 28. 2. 2015.
 */
public class DataBaseHelper
        extends SQLiteOpenHelper {

    public static String DB_PATH = "/data/data/durnek.bakalarka.geography/databases/";
    public static String DB_NAME = "svetDB.sqlite";
    public static final int DB_VERSION = 1;

    //TABULKY
    public static final String TB_KONTINENT = "Kontinent";
    public static final String TB_STAT = "Stat";

    //ATRIBUTY KONTINENTU
    private static final String KEY_ID = "id_kontinentu";
    private static final String KEY_NAZOV = "nazov";
    private static final String KEY_ROZLOHA = "rozloha";
    private static final String KEY_POC_STATOV = "poc_statov";
    private static final String KEY_POC_UZEMI = "zavisle_uzemia";
    private static final String KEY_POPULACIA = "poc_obyv";

    //ATRIBUTY STATU
    private static final String KEY_ID_STAT = "id_statu";
    private static final String KEY_NAZOV_STAT = "nazov";
    private static final String KEY_HL_MESTO = "hl_mesto";
    private static final String KEY_ROZLOHA_STAT = "rozloha";
    private static final String KEY_JEDNOTKA = "jednotka";
    private static final String KEY_POPULACIA_STAT = "populacia";
    private static final String KEY_MESTA = "zname_mesta";
    private static final String KEY_JAZYK = "uradny_jazyk";
    private static final String KEY_MENA = "mena";


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

    public List<Kontinent> getContinentsWithStatesNumber(){
        List<Kontinent> listContinents = new ArrayList<Kontinent>();
        SQLiteDatabase db = getWritableDatabase();

        String select = "SELECT " + KEY_NAZOV + ", " + KEY_POC_STATOV + " FROM " + TB_KONTINENT;

        //vysledok z curzora
        Cursor c = getReadableDatabase().rawQuery(select,null);

        if(c != null){
            c.moveToFirst();
            do{
                listContinents.add(new Kontinent(
                        c.getString(0),
                        c.getInt(1))
                );

            } while (c.moveToNext());
            c.close();
        }
        return listContinents;
    }

   /* public ArrayList<Stat> staty(String company, String orderNumber) throws SQLException {


        ArrayList<Stat> nieco = new ArrayList<Stat>();
        Cursor mCursor =


                myDB.query(true, TB_STAT, new String[] {
                                KEY_NAZOV_STAT,},
                        KEY_ID + "=?",
                        new String[] {company,orderNumber},
                        null, null, null , null);


        if (mCursor.moveToFirst()) {
            do {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setItem(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_ITEM)));
                orderDetail.setDescription(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_DESCRIPTION)));
                orderDetail.setQuantity(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_QUANTITY)));
                orderDetail.setPrice(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_PRICE)));
                orderDetailList.add(orderDetail);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        return orderDetailList;


    }*/

    /*public Kontinent getContinent(int id){
        SQLiteDatabase db = getReadableDatabase();

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
    }*/

    public Kontinent dajKontinent(int id){
        Kontinent kontinent = null;

        String select = "SELECT " + KEY_NAZOV + ", " + KEY_POC_STATOV + ", " +  KEY_POC_UZEMI + ", " +
                KEY_ROZLOHA + ", "+ KEY_POPULACIA + " FROM " + TB_KONTINENT +
                " WHERE " + KEY_ID + " = " + id;

        //vysledok z curzora
        Cursor c = getReadableDatabase().rawQuery(select,null);
        if(c != null){
            c.moveToFirst();

            /*
            kontinent = new Kontinent(Integer.parseInt(c.getString(0)),
                    c.getString(1), Integer.parseInt(c.getString(2)),Integer.parseInt(c.getString(3)),Long.parseLong(c.getString(4)),
                    Long.parseLong(c.getString(5)));*/
            kontinent = new Kontinent(c.getString(0),
                    c.getInt(1), //Integer.parseInt(c.getString(1)),
                    c.getInt(2), //Integer.parseInt(c.getString(2)),
                    c.getLong(3), //Long.parseLong(c.getString(3)),
                    c.getLong(4)); //Long.parseLong(c.getString(4)));
            c.close();
        }
        return kontinent;
    }


    public List<Stat> getAllStates(int idKontinentu){
        List<Stat> listStates = new ArrayList<Stat>();
        SQLiteDatabase db = this.getReadableDatabase();

        //String select = "SELECT " + KEY_NAZOV_STAT + " FROM " + TB_STAT;
        /*String select = "SELECT  s.nazov "
                + "FROM Stat s "
                + "INNER JOIN Kontinent k ON k.id_kontinentu = s.id_kontinentu "
                + " WHERE k.id_kontinentu = "
                + idKontinentu
                + " "
                + " order BY s.nazov";
*/
        String select2 = "SELECT s." + KEY_NAZOV_STAT + ", s." + KEY_ID_STAT + " FROM " + TB_STAT + " s JOIN " + TB_KONTINENT +
                " k ON " + "k." + KEY_ID + " = " + "s." +KEY_ID + " WHERE k." +KEY_ID + " = " + "'" + idKontinentu + "'";

        //vysledok z curzora
        Cursor c = db.rawQuery(select2,null);

        //Log.w("select",select2);

        if(c != null){
            c.moveToFirst();
            do{
                listStates.add( new Stat(c.getInt(1), c.getString(0)) );

            } while (c.moveToNext());
            c.close();
        }
        return listStates;
    }



   /* public List<Stat> getStaty(String idKon) {
        List<Stat> stat_list = new ArrayList<Stat>();

        String selection = DataBaseHelper.KEY_ID + "=?";
        String[] selectionArgs = { idKon };

        Cursor cursor = myDB.query(TB_STAT,
                new String[] { KEY_NAZOV_STAT }, selection,
                selectionArgs, null, null, KEY_NAZOV_STAT);

        while (cursor.moveToNext()) {
            Stat stat_bin = new Stat();
            stat_bin.setNazov(cursor.getString(0));
            stat_list.add(stat_bin);
        }
        return stat_list;
    }*/

   public Stat dajStat(int id){
        Stat stat = null;

        String select = "SELECT " + KEY_NAZOV_STAT + ", " + KEY_HL_MESTO + ", " +  KEY_ROZLOHA_STAT + ", " + //" " + KEY_JEDNOTKA + ", " +
                KEY_POPULACIA_STAT + ", "+ KEY_MESTA + ", " + KEY_JAZYK + ", " + KEY_MENA +  " FROM " + TB_STAT +
                " WHERE " + KEY_ID_STAT + " = " + id;

        //vysledok z curzora
        Cursor c = getReadableDatabase().rawQuery(select,null);
        if(c != null){
            c.moveToFirst();

            stat = new Stat(c.getString(0),
                    c.getString(1),
                    c.getLong(2), //Long.parseLong(c.getString(2)),
                    //c.getString(3),
                    c.getLong(3),  //Long.parseLong(c.getString(4)));
                    c.getString(4),
                    c.getString(5),
                    c.getString(6));

            c.close();
        }
        return stat;
    }
}
