package durnek.bakalarka.geography.kviz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;

import durnek.bakalarka.geography.R;

public class Otazka extends Activity {
    private ArrayList<String> otazky = new ArrayList<>();
    ImageView obr,correctObr;
    int pocRiadkov = 0;
    int spravneOdpovede = 0;
    int pocOtazok = 0;
    int aktOtazka = 0;
    int spravne = 0;
    Button hlMenu,potvrd;
    RadioGroup group;

    final CharSequence[] questions = { " 5 ", " 10 ", " 15 "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otazka);
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

    }

    public void potvrdzovacieOkno(){
        new AlertDialog.Builder(this).setTitle("Koniec kvizu").setMessage("Určite sa chcete vrátiť do menu?")
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

    public void dalsiaOtazka(){

    }

    public void kontrola(){
        int radioButtonID = group.getCheckedRadioButtonId();
        View radioButton = group.findViewById(radioButtonID);
        int index = group.indexOfChild(radioButton);
        if(spravne == index){

        }
    }

    public void nastavPocOtazok(){
        new AlertDialog.Builder(this).setTitle("Vyber si počet otázok").setCancelable(false).setSingleChoiceItems(questions, -1,
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int question){
                        switch (question){
                            case 0:
                                pocOtazok = 5;
                                break;

                            case 1:
                                pocOtazok = 10;
                                break;

                            case 2:
                                pocOtazok = 15;
                                break;
                        }
                    }
                }).show();
    }

    public void koniecAktivity(){
        super.finish();
    }

    public void onBackPressed(){
       potvrdzovacieOkno();
    }
}
