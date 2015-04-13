package durnek.bakalarka.geography.classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.application.App;

/**
 * Created by Lukas on 13. 3. 2015.
 */
public class Svet {
    private DataBaseHelper db;




    private static Svet instance = new Svet();

    public Svet() {
            db = new DataBaseHelper(App.getContext());
    }




}
