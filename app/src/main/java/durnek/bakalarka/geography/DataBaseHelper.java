package durnek.bakalarka.geography;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import durnek.bakalarka.geography.classes.Kontinent;

/**
 * Created by Lukas on 28. 2. 2015.
 */
public class DataBaseHelper
        extends SQLiteOpenHelper {

    //Cesta k databaze
   public String DB_PATH;
    //InputStream desc = getResources.getAssets().open("db.sqlite")
    public String DB_NAME;
    public static SQLiteDatabase myDataBase;
    public final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context,String menoDatabazy){

        super(context, menoDatabazy, null, 1);
        this.myContext = context;
        String packageName = context.getPackageName();
        DB_PATH = String.format("//data//data//%s//databases//",packageName);
        DB_NAME = menoDatabazy;
        try {
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SQLiteDatabase getDb(){
        return myDataBase;
    }

    /**
     * Vytvori prazdnu databazu a prepise ju nami vytvorenou databazou
     * */
    public void createDataBase() throws IOException{

         boolean dbExist = checkDataBase();

        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(this.getClass().toString(), "Copying error");
                throw new Error("Error copying database!");
            }
        } else {
            Log.i(this.getClass().toString(), "Database already exists");
        }
    }


    private boolean checkDataBase(){

        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.close();
        myInput.close();

    }

    public SQLiteDatabase openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        if(myDataBase == null) {
            try {
                createDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
            return myDataBase;

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}




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

    public Cursor getKontintent(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query("Kontinent",new String[]{"nazov"},null,null,null,null,"nazov");
    }

    public ArrayList<String> dajKontinenty(String select){
        ArrayList<String> list = new ArrayList<String>();

        Cursor kontKurzor = myDataBase.rawQuery(select, null);
        kontKurzor.moveToFirst();

        if(!kontKurzor.isAfterLast()){
            do{
                list.add(kontKurzor.getString(0));
            } while(kontKurzor.moveToNext());
        }
        kontKurzor.close();
        return list;
    }


    /*SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
            R.layout.listitemlayout,
            c, from, to);
    ListView lv = (ListView)findViewById(R.id.contactListView);
    lv.setAdapter(adapter);*/

}
