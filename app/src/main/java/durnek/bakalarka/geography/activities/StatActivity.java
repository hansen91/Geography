package durnek.bakalarka.geography.activities;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.fragments.StatListFragment;



public class StatActivity
    extends ActionBarActivity{

    private boolean mDualPane;
    private int idKontinentu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_stat);
        setContentView(R.layout.activity_stat);

        idKontinentu = getIntent().getExtras().getInt("idKontinentu", 1);

        if (findViewById(R.id.stat_detail_container) == null) {
            mDualPane = false; //Portrait ||

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.stat_list_container, StatListFragment.newInstance(idKontinentu));
            transaction.commit();

        } else {
            mDualPane = true; //Landscape |  |
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
