package durnek.bakalarka.geography.kviz;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import durnek.bakalarka.geography.R;

/**
 * Created by Lukas on 18. 4. 2015.
 */
public class Nastroje extends Activity{

    public static ArrayList<String> nacitajOtazkuZoSuboru(Context context,String subor){
        ArrayList zoznam = new ArrayList<String>();
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

    /**
     * metóda, ktorá vráti počet otázok
     * @param context
     * @param subor
     * @return
     */
    public static int pocetOtazok(Context context,String subor){
        int pom;
        subor = "hlmesto";
        pom = 6;
        int pocetRiadkov = 0;
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
    public static int[] generujPoleOtazok(int velkost,int hornaHranica){
        int pole[] = new int[velkost];

        int poc = 0; //pomocna premenna pocitadlo
        int nahodneCislo = 0;

        boolean opakujeSa = false;

        Random rnd = new Random();

        do{
            nahodneCislo = rnd.nextInt(hornaHranica) + 1;
            for(int i = 0; i < velkost; i++){
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
