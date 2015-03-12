package durnek.bakalarka.geography.classes;

/**
 * Created by Lukas on 12. 3. 2015.
 */
import android.database.Cursor;

import java.io.IOException;
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

    /**
     * static {
     * Resources res = App.getContext().getResources();
     * }   //pracuje sa tu s polom retazcov, ktore su ulozene vo values tam co je strings.xml
     */

    private static Vyucba instance = new Vyucba();
    private DataBaseHelper db;

    public Vyucba() {
        try {
            db = new DataBaseHelper(App.getContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * vrati zoznam kontinentov
     *
     * @return
     */
    public List<SimpleKontinent> dajKontinenty() {
        ArrayList<SimpleKontinent> pole = new ArrayList<SimpleKontinent>(6);

        SimpleKontinent sk = new SimpleKontinent(1);

        Cursor nazov = db.getZoznamKontinentov();

        if (nazov.moveToFirst()) {
            do {
                int id = nazov.getInt(nazov.getColumnIndex(ID_KONTINENTU2));
                String naz = nazov.getString(nazov.getColumnIndex(NAZOV2));

                if (sk.id_kontinentu == id)
                    sk.pridajKontinent(naz);
                else {
                    pole.add(sk);
                    sk = new SimpleKontinent(id);
                    sk.pridajKontinent(naz);
                }
            } while (nazov.moveToNext());
            pole.add(sk);
        }
        nazov.close();

        return pole;

    }

    public static Vyucba getInstance() {
        return instance;
    }

}