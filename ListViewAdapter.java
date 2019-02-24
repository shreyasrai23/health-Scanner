package com.example.peeyushrai.healthscanner; /**
 * Created by shreyasrai on 7/18/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.peeyushrai.healthscanner.ConditionNames;
import com.example.peeyushrai.healthscanner.ConditionSetup;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<ConditionNames> arraylist;

    public ListViewAdapter(Context context ) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<ConditionNames>();
        this.arraylist.addAll(ConditionSetup.movieNamesArrayList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return ConditionSetup.movieNamesArrayList.size();
    }

    @Override
    public ConditionNames getItem(int position) {
        return ConditionSetup.movieNamesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(ConditionSetup.movieNamesArrayList.get(position).getConditionName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ConditionSetup.movieNamesArrayList.clear();
        if (charText.length() == 0) {
            ConditionSetup.movieNamesArrayList.addAll(arraylist);
        } else {
            for (ConditionNames wp : arraylist) {
                if (wp.getConditionName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    ConditionSetup.movieNamesArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
