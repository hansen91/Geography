package durnek.bakalarka.geography.activities;

/**
 * Created by Lukas on 12. 3. 2015.
 */
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import durnek.bakalarka.geography.DataBaseHelper;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.classes.Kontinent;
import durnek.bakalarka.geography.fragments.KontinentListFragment;

public class KontinentListActivity
    extends Activity
    implements KontinentListFragment.OnKontinentSelectedListener {

    private static final String VYBRANY_KONTINENT = "vybrany_kontinent";
    private static final String backName = "detail";
    private boolean mTwoPane;
    private long vybrany_kontinent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_kontinent);
        setContentView(R.layout.skuska);


            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.cointainer, KontinentListFragment.newInstance());
            transaction.commit();


      /*  DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
        try{
            dbHelper.createDataBase();
        } catch(IOException e){
            e.printStackTrace();
        }

        ListView lvContinents = (ListView)findViewById(R.id.list);
        ArrayList<String> listContinent = dbHelper.dajKontinenty();

        if(listContinent != null) {
            ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
                    R.layout.kontinent_item, R.id.kontinent, listContinent);
            lvContinents.setAdapter(adapter);
        }

        //FragmentTransaction transaction = getFragmentManager()

      /*  if (findViewById(R.id.kontinent_detail_container) != null) {
            mTwoPane = true;

            ((KontinentListFragment)getSupportFragmentManager().findFragmentById(
                    R.id.kontinent_list)).setActivateOnItemClick(true);
        }*/

    }

    @Override
    public void onKontinentSelected(Kontinent kontinent) {
        Intent intent  = new Intent();
    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

      /*  switch (item.getItemId()) {
            case R.id.oprograme:
                Intent i1 = new Intent(this, OPrograme.class);
                startActivity(i1);
                return true;
        }
        return super.onOptionsItemSelected(item);
        return true;
    }*/

   /* public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong(VYBRANY_KONTINENT, vybrany_kontinent);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null)
            vybrany_kontinent = savedInstanceState.getLong(VYBRANY_KONTINENT);
    }*/



}
