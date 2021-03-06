package durnek.bakalarka.geography.kviz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;

public class Otazka extends Activity {
    private ArrayList<String> otazky = new ArrayList<>();
    ImageView obr;
    int pocRiadkov = 0;
    int spravneOdpovede = 0;
    int pocOtazok = 0;
    int aktOtazka = 0;
    int typ_kvizu;
    int[] cislaOtazok,odpovede;
    int konstanta = 5;
    boolean[] poleSpravnychOdpovedi;
    int spravne;
    Button hlMenu,potvrd;
    RadioButton r0,r1,r2,r3;
    TextView otazka;
    RadioGroup group;
    String nazov_kvizu;
    DataBaseHelper db = null;

    final CharSequence[] questions = { " 5 ", " 10 ", " 15 ", " 20 "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){//ak nie je null = nastala zmena stavu, napr. rotacia obrazovky
            //obnovenie z ulozeneho stavu
            typ_kvizu = savedInstanceState.getInt("typ_kvizu",1);

        } else {//ide nove spustenie
            //nacitanie zo spustajuceho intentu
            Bundle b = getIntent().getExtras();
            typ_kvizu = b.getInt("typ_kvizu");
        }

        //nafuknutie content view
        if(typ_kvizu == 1) {
            setContentView(R.layout.activity_hl_mesta);
            nazov_kvizu = "hlmesto";
        }else{
            setContentView(R.layout.kviz_staty);
            nazov_kvizu = "staty";
        }

        hlMenu = (Button) findViewById(R.id.btnDoMenu);
        hlMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potvrdzovacieOkno();
            }
        });
        potvrd = (Button) findViewById(R.id.btnConfirm);
        potvrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(group.getCheckedRadioButtonId() == -1){

                }else {
                    dalsiaOtazka();
                }
            }
        });

            otazky = Nastroje.nacitajOtazkuZoSuboru(this, nazov_kvizu);
            pocRiadkov = Nastroje.pocetOtazok(this, nazov_kvizu);

            nastavPocOtazok();


        //spravna odpoved je ta, kde hodnota v poli je 4
        otazka = (TextView)findViewById(R.id.txtOtazka);
        r0 = (RadioButton)findViewById(R.id.radio0);
        r1 = (RadioButton)findViewById(R.id.radio1);
        r2 = (RadioButton)findViewById(R.id.radio2);
        r3 = (RadioButton)findViewById(R.id.radio3);
        group = (RadioGroup)findViewById(R.id.radioGroup1);

        otazka.setVisibility(View.INVISIBLE);
        group.setVisibility(View.INVISIBLE);
        potvrd.setVisibility(View.INVISIBLE);
        hlMenu.setVisibility(View.INVISIBLE);

        if(typ_kvizu == 1) {
            obr = (ImageView) findViewById(R.id.imgVlajka);
            obr.setVisibility(View.INVISIBLE);
            konstanta = 6;
        }
    }

    /**
     * metóda na potrvdenie ukončenia kvízu
     */
    public void potvrdzovacieOkno(){
        new AlertDialog.Builder(this).setTitle("Koniec kvízu").setMessage("Určite sa chcete vrátiť do menu?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        koniecAktivity();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

            }
        })
                .show();
    }

    /**
     * metóda, pomocou ktorej sa prejde na nasledujúcu otázku
     */
    public void dalsiaOtazka(){
        kontrola();
        aktOtazka++;
        if(aktOtazka == cislaOtazok.length){

        Intent i = new Intent(this, Vyhodnotenie.class);
            i.putExtra("typ_kvizu",nazov_kvizu);
            i.putExtra("poc_otazok",pocOtazok);
            i.putExtra("cisla_otazok",cislaOtazok);
            i.putExtra("spravnost_odpovedi",poleSpravnychOdpovedi);
            i.putExtra("konstanta_na_ocislovanie_zloziek",konstanta);
            koniecAktivity();

            startActivity(i);

        }else{
            polozOtazku();
        }
    }

    /**
     * metóda kontroluje či bol označený správny radiobutton
     */
    public void kontrola(){
        int radioButtonID = group.getCheckedRadioButtonId();
        View radioButton = group.findViewById(radioButtonID);
        int index = group.indexOfChild(radioButton);
        if(spravne == index){
            Nastroje.zobrazSpravne(this);
            poleSpravnychOdpovedi[aktOtazka] = true;
            spravneOdpovede++;
        } else {
            Nastroje.zobrazNespravne(this);
            poleSpravnychOdpovedi[aktOtazka] = false;
        }
    }

    /**
     * metóda nastaví počet otázok na začiatku kvízu
     */
    public void nastavPocOtazok(){
        new AlertDialog.Builder(this).setTitle("Vyber si počet otázok").setCancelable(false).setSingleChoiceItems(questions, -1,
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int question){
                        switch (question){
                            case 0:
                                pocOtazok = 5;
                                generujANastavViditelnost();
                                dialog.dismiss();
                                break;

                            case 1:
                                pocOtazok = 10;
                                generujANastavViditelnost();
                                dialog.dismiss();
                                break;

                            case 2:
                                pocOtazok = 15;
                                generujANastavViditelnost();
                                dialog.dismiss();
                                break;

                            case 3:
                                pocOtazok = 20;
                                generujANastavViditelnost();
                                dialog.dismiss();
                                break;
                        }
                    }
                }).show();
    }

    /**
     * metóda nastaví otazku, radiobuttony, vlajky
     */
    public void polozOtazku() {
        odpovede = Nastroje.generujCisla(4, 4);

        if (typ_kvizu == 1) {

             String pom = String.valueOf(otazky.get((cislaOtazok[aktOtazka] * konstanta) - 6));  //vlajka
            if (pom == otazky.get(0))
                pom = "f_1";

            int resID = getResources().getIdentifier(pom, "drawable", getPackageName());

            obr.setImageDrawable(getResources().getDrawable(resID));
        }
        otazka.setText(otazky.get((cislaOtazok[aktOtazka] * konstanta) - 5));   //otazka

            r0.setText("A:  " + otazky.get((cislaOtazok[aktOtazka] * konstanta) - odpovede[0]));
            r1.setText("B:  " + otazky.get((cislaOtazok[aktOtazka] * konstanta) - odpovede[1]));
            r2.setText("C:  " + otazky.get((cislaOtazok[aktOtazka] * konstanta) - odpovede[2]));
            r3.setText("D:  " + otazky.get((cislaOtazok[aktOtazka] * konstanta) - odpovede[3]));

            group.clearCheck();

        for(int k = 0; k < 4; k++){   //spravna odpoved je ta kde index je rovny 4
            if(odpovede[k] == 4)
                spravne = k;
        }

    }

    /**
     * metóda nastaví viditeľnosť radiobuttonov a nastaví pole s číslami
     */
    public void generujANastavViditelnost(){
        cislaOtazok = new int[pocOtazok];
        poleSpravnychOdpovedi = new boolean[pocOtazok];
        odpovede = new int[4];
        cislaOtazok = Nastroje.generujCisla(pocOtazok, pocRiadkov);
        if(typ_kvizu == 1)
            obr.setVisibility(View.VISIBLE);
        otazka.setVisibility(View.VISIBLE);
        group.setVisibility(View.VISIBLE);
        potvrd.setVisibility(View.VISIBLE);
        hlMenu.setVisibility(View.VISIBLE);
        polozOtazku();
    }

    public void koniecAktivity(){
        super.finish();
    }

    public void onBackPressed(){
       potvrdzovacieOkno();
    }
}
