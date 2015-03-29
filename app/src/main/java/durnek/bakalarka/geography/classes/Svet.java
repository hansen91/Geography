package durnek.bakalarka.geography.classes;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.application.App;

/**
 * Created by Lukas on 13. 3. 2015.
 */
public class Svet {
    private ArrayList<Kontinent> kontinenty;
    private DataBaseHelper db;

    private static Svet instance = new Svet();

    public Svet(){
        db = new DataBaseHelper(App.getContext());
    }

    public List<Kontinent> getKontinenty() {
        ArrayList<Kontinent> list = new ArrayList<Kontinent>(6);

        Cursor kont = (Cursor) db.getAllContinents();

        if(kont.moveToFirst()){

        }

        return kontinenty;
    }
}
