package durnek.bakalarka.geography;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import durnek.bakalarka.geography.activities.KontinentListActivity;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KontinentListActivity kl = new KontinentListActivity();



        Button vyucba = (Button)findViewById(R.id.btnVyucba);
        vyucba.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){




                //Toast.makeText(context,"Vyucba kliknuta",Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(context,KontinentListActivity.class);
                startActivity(myIntent);


            }
            //Intent myIntent = new Intent(view.getContext(),Kontinent.class);
            //startActivity(myIntent);
             /*  AssetDatabaseOpenHelper adb = new AssetDatabaseOpenHelper(context);
               SQLiteDatabase db = adb.openDatabase();
               Cursor c = db.rawQuery("SELECT * FROM Kontinent;", null);
               Log.d("MyApp", "cnt: " + c.getCount());*/

        });



        Button kviz = (Button)findViewById(R.id.btnKviz);
        kviz.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });

        Button pexeso = (Button)findViewById(R.id.btnPexeso);
        pexeso.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });

        Button koniec = (Button)findViewById(R.id.btnKoniec);
        koniec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ukonciGeografiu();
            }
        });
    }

    public void spustiVyucbu(){
        Toast.makeText(this,"Vyucba kliknuta",Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(this,KontinentListActivity.class);
        startActivity(myIntent);
    }



    public void ukonciGeografiu(){
        this.finish();
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

    @Override
    public void onClick(View v) {

    }
}
