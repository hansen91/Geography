package durnek.bakalarka.geography;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import durnek.bakalarka.geography.activities.KontinentActivity;
import durnek.bakalarka.geography.application.OPrograme;
import durnek.bakalarka.geography.kviz.Otazka;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    Context context = this;
    private boolean mDualPane;
    private TextView txtOprograme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.kontinent_detail_container) == null)
            mDualPane = false; //Portrait ||
        else
            mDualPane = true; //Landscape |  |


        Button vyucba = (Button)findViewById(R.id.btnVyucba);
        vyucba.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent myIntent = new Intent(context,KontinentActivity.class);
                startActivityForResult(myIntent,0);
            }
        });



        Button kvizHlMesto = (Button)findViewById(R.id.btnKviz);
        kvizHlMesto.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), Otazka.class);
                intent.putExtra("typ_kvizu", 1);
                startActivityForResult(intent,0);
            }
        });


        Button kvizStat = (Button)findViewById(R.id.btnKvizStaty);
        kvizStat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(),Otazka.class);
                intent.putExtra("typ_kvizu",0);
                startActivityForResult(intent,0);
            }
        });

        Button koniec = (Button)findViewById(R.id.btnKoniec);
        koniec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ukonciGeografiu();
            }
        });
    }

    public void ukonciGeografiu(){
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,OPrograme.class);
            intent.putExtra("download","download");
            startActivityForResult(intent, 222);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
