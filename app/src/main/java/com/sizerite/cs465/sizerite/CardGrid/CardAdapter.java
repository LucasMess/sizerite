package com.sizerite.cs465.sizerite.CardGrid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sizerite.cs465.sizerite.R;

import java.util.ArrayList;

/**
 * CardAdapter used to convert an array of cards into a grid view.
 */
public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

    ArrayList<Card> cards;
    Context context;
    RecyclerView recyclerView;
    Class target;

    public CardAdapter(ArrayList<Card> cards, Context context){
        this.cards = cards;
        this.context = context;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_card, parent, false);

        CardHolder cardHolder = new CardHolder(view);

        // Set up the on click listener.
        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if (recyclerView != null) {
                    // TODO: Track the selection of the user.
                    CardHolder clickedCard = (CardHolder) recyclerView.findContainingViewHolder(view);
                    if (target != null) {
                        Intent intent = new Intent(context, target);
                        context.startActivity(intent);
                    }
                }
            }
        });

        return cardHolder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView){
        this.recyclerView = null;
    }

    /**
     * Set the activity that the app will transition to once an item is clicked on.
     * @param target The activity to go to.
     */
    public void onItemClickTransitionTo(Class target){
        this.target = target;
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        cards.get(position).populateCardHolder(holder, context);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


}
