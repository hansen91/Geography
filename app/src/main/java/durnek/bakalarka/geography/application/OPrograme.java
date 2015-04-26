package durnek.bakalarka.geography.application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import durnek.bakalarka.geography.R;

/**
 * Created by Lukas on 12. 3. 2015.
 */
public class OPrograme extends ActionBarActivity implements View.OnClickListener{
    TextView link;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oprograme);
        link = (TextView)findViewById(R.id.download);
        findViewById(R.id.zdroj).setOnClickListener(this);
        link.setText("Obrázky z aplikácie sú stiahnuté z tejto stránky: ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {   //chceme nastavit vysledok nasej operacie od resultu
            setResult(RESULT_OK,new Intent().putExtra("download","OPrograme"));
            finish();   //ukonci danu aktivitu a spätne sa vrati na prvu aktivitu

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.zdroj){
            Uri uri = Uri.parse("http://www.dreamstime.com/royalty-free-stock-image-travel-world-monuments-concept-image34562046");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);  //uri-resource, s ktorymi sa naraba
            startActivity(intent);
        }
    }
}
