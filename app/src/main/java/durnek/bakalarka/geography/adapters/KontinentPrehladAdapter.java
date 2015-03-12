package durnek.bakalarka.geography.adapters;

import java.util.List;

import durnek.bakalarka.geography.classes.Kontinent;


/**
 * Created by Lukas on 11. 3. 2015.
 */
public class KontinentPrehladAdapter {
    private static interface Item{
        public int id();
    }

    private static class Kontinenty implements Item{
        public Kontinent nazov;
        public int id;

        @Override
        public int id(){
            return id;
        }
    }

    private List<Item> items;



    public int getCount() {
        return items.size();
    }


}

