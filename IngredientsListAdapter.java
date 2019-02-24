package com.example.peeyushrai.healthscanner;

import java.util.List;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.ListViewHolder>{
    private List<String> listValues;

    public class ListViewHolder extends RecyclerView.ViewHolder{
        public TextView ingredient;
        public ImageView icon;
        public View viewLayout;

        public ListViewHolder (View v){
            super(v);
            viewLayout = v;
            ingredient = v.findViewById(R.id.ingredient);
            icon = v.findViewById(R.id.icon);
        }
    }

    public void add(int position, String item) {
        listValues.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        listValues.remove(position);
        notifyItemRemoved(position);
    }

    public IngredientsListAdapter(List<String> ingredientsList) {
        listValues = ingredientsList;
    }

    @Override
    public IngredientsListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.ingredients_rows, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ListViewHolder vh = new ListViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = listValues.get(position);
        holder.ingredient.setText(name);
        //System.out.println(holder.ingredient);

        //System.out.println("allBadIngredient SIZE:" + getData.allBadIngredients.size());

        if (getData.allBadIngredients.size() != 0)
        {
            //System.out.println("ingredient: "  +  holder.ingredient.getText().toString());
            //System.out.println("Bad Ingredient List" + (java.util.ArrayList) getData.returnBadIngredientlist());

            boolean isBad = isBadIngredientForCondition(holder.ingredient.getText().toString(), (java.util.ArrayList<String>)getData.allBadIngredients);
            //System.out.println("IS BAD RETURN:" + isBad);
            if (isBadIngredientForCondition(holder.ingredient.getText().toString(), (java.util.ArrayList<String>)getData.allBadIngredients))
            {
                holder.icon.setImageResource(R.mipmap.sadface);
                holder.ingredient.setTextColor(Color.RED);
            }
            else
            {
                //System.out.println("Printing Smiley Face");
                holder.icon.setImageResource(R.mipmap.smiley_face);
                holder.ingredient.setTextColor(Color.BLACK);
            }
            holder.ingredient.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Code to call action when item is clicked
                }
            });
        }

        //holder.itemTextFooter.setText("Footer: " + name);
    }

    public static boolean isBadIngredientForCondition(String ingredient, ArrayList<String> badIngredients) {
        //System.out.println("Product Ingredient :" + ingredient);
        //System.out.println("Bad Ingredient :" + badIngredients);
        for (int i = 0; i < badIngredients.size(); i++) {
            if  (ingredient.toLowerCase().contains(badIngredients.get(i).toLowerCase())) {
                //System.out.println("Returning TRUE");
                return true;
            }
        }
        return false;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listValues.size();
    }

}
