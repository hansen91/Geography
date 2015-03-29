package durnek.bakalarka.geography.activities;

import android.app.FragmentTransaction;
        import android.content.Intent;
        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;

        import durnek.bakalarka.geography.R;
        import durnek.bakalarka.geography.classes.Kontinent;
        import durnek.bakalarka.geography.fragments.KontinentDetailFragment;

public class KontinentDetailActivity extends FragmentActivity {
    public Kontinent kont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontinent_detail);
        //Vytiahnutie dat(Kontinent) z prijateho intentu
        Intent i = getIntent();
        kont = (Kontinent) i.getParcelableExtra("kontinent");

        //Vytvorenie noveho fragmentu
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, KontinentDetailFragment.newInstance(kont));
        transaction.commit();

    }
}
