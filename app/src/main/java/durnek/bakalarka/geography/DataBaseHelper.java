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
import java.util.List;

/**
 * Created by Lukas on 28. 2. 2015.
 */
public class DataBaseHelper
        extends SQLiteOpenHelper {

    public static String DB_PATH = "/data/data/durnek.bakalarka.geography/databases/";
    public static String DB_NAME = "db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TB_KONTINENT = "Kontinent";

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




    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.
   /* public Cursor getZoznamKontinentov(){
        Cursor c = null;

            c = getReadableDatabase().rawQuery("SELECT nazov from Kontinent ORDER BY nazov", new String[0]);
        return c;
    }*/


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


     /*public Cursor fetchCountriesByName(String inputText) throws SQLException {
      //Log.w(TAG, inputText);
      Cursor mCursor = myDataBase.query("Kontinent", new String[] {"id_kontinentu",
                          "nazov"},
                  null, null, null, null, "nazov");


      return mCursor;
  }*/

  /*  public Cursor getKontinent(){
        SQLiteDatabase db = getReadableDatabase();
        return  db.query("Kontinent", new String[]{"nazov"}, null, null, null, null, "nazov");
    }*/

}
