package durnek.bakalarka.geography.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import durnek.bakalarka.geography.R;
import durnek.bakalarka.geography.fragments.StatDetailFragment;

public class StatDetailActivity extends FragmentActivity {

    public int idStatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_detail);
        //Vytiahnutie dat(Stat) z prijateho intentu
        Intent i = getIntent();
        idStatu = i.getIntExtra("idStatu", 1);

        //Vytvorenie noveho fragmentu
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container_stat, StatDetailFragment.newInstance(idStatu));
        transaction.commit();
    }
}
