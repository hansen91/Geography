package durnek.bakalarka.geography.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import durnek.bakalarka.geography.R;

/**
 * Created by Lukas on 18. 4. 2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private List<Drawable> _listObrazky;
    private List<Boolean> _spravneOdpovede;
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context _context, List<String> listDataHeader,
                                 List<Drawable> listObrazky, List<Boolean> spravneOdpovede,
                                 HashMap<String, List<String>> listDataChild) {
        this._context = _context;
        this._listDataHeader = listDataHeader;
        this._listObrazky = listObrazky;
        this._spravneOdpovede = spravneOdpovede;
        this._listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    public Object getObrazok(int groupPosition){
        return this._listObrazky.get(groupPosition);
    }

    public boolean getSpravnost(int groupPosition){
        return this._spravneOdpovede.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group,null);
        }

        boolean spravne = getSpravnost(groupPosition);

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        if(!spravne)
            lblListHeader.setTextColor(Color.RED);
        else
            lblListHeader.setTextColor(Color.parseColor("#008000"));
        Drawable drawable = (Drawable) getObrazok(groupPosition);

        ImageView lblListHeaderObrazok = (ImageView) convertView
                .findViewById(R.id.list_obrazok);
        lblListHeaderObrazok.setImageDrawable(drawable);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition,final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
