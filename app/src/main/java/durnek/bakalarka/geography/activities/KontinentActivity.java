package durnek.bakalarka.geography.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.fragments.KontinentDetailFragment;
import durnek.bakalarka.geography.fragments.KontinentListFragment;

public class KontinentActivity
    extends ActionBarActivity
    implements KontinentListFragment.OnKontinentSelectedListener {

    private boolean mDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontinent);

        if (findViewById(R.id.kontinent_detail_container) == null)
            mDualPane = false; //Portrait ||
        else
            mDualPane = true; //Landscape |  |
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onKontinentSelected(int id) {
        if (mDualPane) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.kontinent_detail_container, KontinentDetailFragment.newInstance(id));
            transaction.commit();

        } else {
            Intent intent = new Intent(this, KontinentDetailActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }
}
