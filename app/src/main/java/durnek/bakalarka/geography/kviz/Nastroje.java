package durnek.bakalarka.geography.kviz;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.classes.Stat;

/**
 * Created by Lukas on 18. 4. 2015.
 */
public class Nastroje extends Activity{

    public static ArrayList<String> nacitajOtazkuZoSuboru(Context context,String subor){
        ArrayList<String> zoznam = new ArrayList<String>();
        String riadok;
        int resID = context.getResources().getIdentifier(subor, "raw" ,context.getPackageName());
        InputStream in = context.getResources().openRawResource(resID);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try{
            while ((riadok = reader.readLine()) != null){
                if((riadok.isEmpty() == false && riadok.trim().equals("") == false
                                              && riadok.trim().equals("\n") == false)){
                    zoznam.add(riadok);
                }
            }
            in.close();
        }catch(IOException e){

        }

        return zoznam;
    }

    static class Stat
    {
        private String nazov;
        private int id_statu;

        public String getNazov() {
            return nazov;
        }

        public int getIdStatu() {
            return id_statu;
        }

        Stat(int idStatu, String nazov) {
            this.id_statu = idStatu;
            this.nazov = nazov;

        }
    }

    public static List<Stat> vyberStatov(Context context){
        List<Stat> aktOdpovede = new ArrayList<Stat>();
        Cursor c = null;
        DataBaseHelper db = new DataBaseHelper(context);

        String select = "SELECT id_statu,nazov FROM Stat";
        c = db.getReadableDatabase().rawQuery(select,null);
        while(c.moveToNext()){
            aktOdpovede.add(new Stat(c.getInt(0),c.getString(1)));
        }
        return aktOdpovede;
    }

    /**
     * metóda, ktorá vráti počet otázok
     * @param context
     * @param subor
     * @return
     */
    public static int pocetOtazok(Context context,String subor){
        int pom;
        int pocetRiadkov = 0;
        if(subor == "hlmesto")
            pom = 6;
        else
            pom = 5;
        String riadok;
        int resID = context.getResources().getIdentifier(subor, "raw",
                context.getPackageName());
        InputStream in = context.getResources().openRawResource(resID);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {

            while ((riadok = reader.readLine()) != null) {

                if ((riadok.isEmpty() == false && riadok.trim().equals("") == false
                                               && riadok.trim().equals("\n") == false)) {

                    pocetRiadkov++;
                }
            }
            in.close();

        } catch (IOException e) {
        }

        return (pocetRiadkov / pom);
    }

    /**
     * metóda,ktorá vygeneruje pole s číslami otázok
     * @param velkost
     * @param hornaHranica
     * @return
     */
    public static int[] generujCisla(int velkost, int hornaHranica){
        int pole[] = new int[velkost];

        int poc = 0; //pomocna premenna pocitadlo
        int nahodneCislo = 0;

        boolean opakujeSa = false;

        Random rnd = new Random();

        do{
            nahodneCislo = rnd.nextInt(hornaHranica) + 1;
            for(int i = 0; i < poc; i++){
                if(pole[i] == nahodneCislo){
                    opakujeSa = true;
                    break;
                }
            }
            if(opakujeSa == false){

                pole[poc] = nahodneCislo;
                poc++;

            } else {
                opakujeSa = false;
            }
        }while(poc < velkost);

        return pole;
    }

   /* public static int[] generujPoleOdpovedi(int velkostPola, int hornaHranica){
        int[] poleOdpovedi = new int[velkostPola];

        int poc = 3;
        int nahCislo = 0;
        int prvaOdpoved = 0;

        boolean opakujeSa = false;

        Random rnd = new Random();

        do{
            nahCislo = rnd.nextInt(hornaHranica)+1;
            for(int i=0; i < poc; i++){

                if(poleOdpovedi[i] == nahCislo){
                    opakujeSa = true;
                    break;
                }
            }
            if(opakujeSa == false){
                poleOdpovedi[poc] = nahCislo;
                poc++;
            }else
                    opakujeSa = false;

        }while(poc < velkostPola);
        return poleOdpovedi;
    }*/



    public static void zobrazSpravne(Context context){
        final Toast toast = new Toast(context.getApplicationContext());
        ImageView correctObr = new ImageView(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        Handler handler = new Handler();
        correctObr.setImageResource(R.drawable.correct);
        toast.setView(correctObr);
        toast.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        },500);
    }

    public static void zobrazNespravne(Context context){
        final Toast toast = new Toast(context.getApplicationContext());
        ImageView incorrectObr = new ImageView(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        Handler handler = new Handler();
        incorrectObr.setImageResource(R.drawable.incorrect);
        toast.setView(incorrectObr);
        toast.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        },500);
    }
}
