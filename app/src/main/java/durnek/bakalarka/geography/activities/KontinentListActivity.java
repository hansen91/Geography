package durnek.bakalarka.geography.activities;

/**
 * Created by Lukas on 12. 3. 2015.
 */
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import durnek.bakalarka.geography.R;

public class KontinentListActivity extends FragmentActivity {
    private static final String VYBRANY_KONTINENT = "vybrany_kontinent";
    private static final String backName = "detail";
    private boolean mTwoPane;
    private long vybrany_kontinent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontinent);

        if (findViewById(R.id.kontinent_detail_container) != null) {
            mTwoPane = true;

            ((KontinentListFragment)getSupportFragmentManager().findFragmentById(
                    R.id.kontinent_list)).setActivateOnItemClick(true);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* switch (item.getItemId()) {
            case R.id.oprograme:
                Intent i1 = new Intent(this, OPrograme.class);
                startActivity(i1);
                return true;
        }
        return super.onOptionsItemSelected(item);*/
        return true;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong(VYBRANY_KONTINENT, vybrany_kontinent);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null)
            vybrany_kontinent = savedInstanceState.getLong(VYBRANY_KONTINENT);
    }


}
