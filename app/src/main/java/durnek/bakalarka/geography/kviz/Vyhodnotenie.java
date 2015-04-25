package durnek.bakalarka.geography.kviz;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.adapters.ExpandableListAdapter;

/**
 * Created by Lukas on 19. 4. 2015.
 */
public class Vyhodnotenie extends Activity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ArrayList<String> list_all = new ArrayList<>();
    List<String> arr;   //pole do ktoreho pridavam correct alebo incorrect odpovede
    List <Drawable> listDataPictures;
    List<List<String>> dataList;

    boolean[] poleSoSpravnymiOdpovedami;
    List<Boolean> spravneOdpovede;
    int pocetSpravnychOdpovedi=0;
    int konst;
    int[] pole;
    String  nazovObrazka;
    TextView percento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vyhodnotenie);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        percento = (TextView) findViewById(R.id.percenta);

        Bundle bund = getIntent().getExtras();
        double pocOtazok = bund.getInt("poc_otazok");
        pole = new int[bund.getInt("poc_otazok")];   // pole s vybratým počtom otázok


        Bundle bund2 = getIntent().getExtras();
        pole = bund2.getIntArray("cisla_otazok");  //do poľa vložíme čísla otázok


        Bundle bund3 = getIntent().getExtras();
        konst = bund3.getInt("konstanta_na_ocislovanie_zloziek");  //vyberieme si konštantu


        poleSoSpravnymiOdpovedami = new boolean[bund.getInt("poc_otazok")];
        Bundle bund4 = getIntent().getExtras();
        poleSoSpravnymiOdpovedami = bund4.getBooleanArray("spravnost_odpovedi");

        spravneOdpovede = new ArrayList<Boolean>();  //tu sa nachadzaju len správne odpovede
        for (int s = 0; s < pole.length; s++) {
            if (poleSoSpravnymiOdpovedami[s] == true) spravneOdpovede.add(true);
            else spravneOdpovede.add(false);
        }

        //priprava dat do expandableListView
        dataVZozname();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataPictures, spravneOdpovede, listDataChild);

        //nastavime list adapter
        expListView.setAdapter(listAdapter);

        //Listview group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        Button hlMenu = (Button)findViewById(R.id.buttonDoMenu);
        hlMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                koniecAktivity();
            }
        });

        for(int i=0; i < pocOtazok; i++){ //pocet spravnych odpovedi
            if(poleSoSpravnymiOdpovedami[i]) pocetSpravnychOdpovedi++;

           percento.setText(String.format("%.0f" , pocetSpravnychOdpovedi/pocOtazok*100) + "%");
        }
    }
        private void dataVZozname(){
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();
            listDataPictures = new ArrayList<Drawable>();

            //nacitaj do ArrayListu otazku
            list_all = Nastroje.nacitajOtazkuZoSuboru(this,"hlmesto");
            dataList = new ArrayList<List<String>>();

            for(int r = 0; r < pole.length; r++){
                arr = new ArrayList<>();


                nazovObrazka = String.valueOf(list_all.get(pole[r]*konst - 6));
                if (nazovObrazka==list_all.get(0)) nazovObrazka="f_1";
                int resID = getResources().getIdentifier(nazovObrazka , "drawable", getPackageName());
                Drawable draw = getResources().getDrawable(resID);
                listDataPictures.add(draw);
                listDataHeader.add((r+1) + ") " + list_all.get(pole[r]*konst - 5));
                arr.add(" ✔" + list_all.get(pole[r]*konst - 4));
                arr.add(" ✘" + list_all.get(pole[r]*konst - 3));
                arr.add(" ✘" + list_all.get(pole[r]*konst - 2));
                arr.add(" ✘" + list_all.get(pole[r]*konst - 1));
                dataList.add(arr);
            }

            for(int q=0; q < pole.length; q++){
                listDataChild.put(listDataHeader.get(q), dataList.get(q));
            }
        }


       public void koniecAktivity(){
           list_all.clear();
           super.finish();


    }
}
