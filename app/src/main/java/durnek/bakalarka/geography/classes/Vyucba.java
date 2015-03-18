package durnek.bakalarka.geography.classes;

/**
 * Created by Lukas on 12. 3. 2015.
 */
import android.database.Cursor;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import durnek.bakalarka.geography.application.App;
import durnek.bakalarka.geography.DataBaseHelper;

/**
 * Created by Lukas on 5. 3. 2015.
 */
public class Vyucba {
    private static final String ID_KONTINENTU2 = "id_kontinentu";
    private static final String NAZOV2 = "nazov";
    private static final String ZLOZENIE = "zlozenie";
    private static final String ROZLOHA = "rozloha";
    private static final String POPULACIA = "populacia";

    /**
     * static {
     * Resources res = App.getContext().getResources();
     * }   //pracuje sa tu s polom retazcov, ktore su ulozene vo values tam co je strings.xml
     */

    private static Vyucba instance = new Vyucba();
    private DataBaseHelper db;

  /*  public Vyucba() {
        try {
            db = new DataBaseHelper(App.getContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * vrati zoznam kontinentov
     *
     * @return
     */
   /* public List<Kontinent> dajKontinenty() {
        Svet pole = new Svet();
}*/


   /* public Kontinent dajKontinent(int id){
        Cursor nazov = db.getZoznamKontinentov();

        List<Kontinent> pole = new ArrayList<Kontinent>();
        if(nazov.moveToFirst()){
            do{
                int kont_id = nazov.getInt(nazov.getColumnIndex(ID_KONTINENTU2));
                String naz = nazov.getString(nazov.getColumnIndex(NAZOV2));
                String zloz = nazov.getString(nazov.getColumnIndex(ZLOZENIE));
                long rozl = nazov.getLong(nazov.getColumnIndex(ROZLOHA));
                long pop = nazov.getLong(nazov.getColumnIndex(POPULACIA));

                pole.add(new Kontinent(naz,zloz,rozl,pop));
            } while (nazov.moveToNext());
        }
        return new Kontinent(pole.toArray(new Kontinent[pole.size()]));
    }*/

    public static Vyucba getInstance() {
        return instance;
    }

}