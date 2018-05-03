package com.example.evan.languageapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by per6 on 3/23/18.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder>  {

    private Context context;
    private List<LanguageCard> languageCards;
    private LanguageCard languageCard;
    private int pos;


    public WordAdapter(Context context, List<LanguageCard> languageCards) {
        Log.d("languageCard", "WordAdapter: " + languageCards);
        this.context = context;
        this.languageCards = languageCards;
        this.pos = 0;
        this.languageCard = languageCards.get(pos);
    }

    //creates the ViewHolder by inflating the layout and returning it
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phrase, parent, false);

        return new MyViewHolder(itemView);
    }

    //assigns the appropriate information from the word object to each widget in the layout
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1. get the object at the position form the list
        LanguageCard languageCard = this.languageCards.get(position);
        //set the textviews/widgets in the viewholder
        holder.chinese.setText(languageCard.getChinese());
        holder.pinyin.setText(languageCard.getPinyin());
        holder.english.setText(languageCard.getEnglish());
    }

    @Override
    public int getItemCount() {
        Log.d("List size", "getItemCount: " + languageCards.size());
        return languageCards.size();
    }

    //inside, we wire widgets for our layout
    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView chinese, pinyin, english;

        public MyViewHolder(View itemView) {
            super(itemView);
            chinese = itemView.findViewById(R.id.chinese);
            pinyin = itemView.findViewById(R.id.pinyin);
            english = itemView.findViewById(R.id.english);

            final int id = itemView.getId();

            chinese.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("view id", "onClick: " + view.getId() + " " + R.id.chinese);
                    switch(view.getId()){
                        case R.id.chinese:
//                          Log.d("bundle for recyclerview", "onClick: " + bundle.getString("intro paragraph"));
                            if(languageCard.getEnglish().equals("hello")) {
                                Intent i = new Intent(view.getContext(), LessonOneRecyclerViewDetails.class);
                                i.putExtra("background info", "This here is an interesting phrase. Nǐ means you, and hǎo means good." +
                                        "However, when you put these two together, you get hello." +
                                        "This provides some insight into Chinese culture. Imagine a world where you say, \"You good\"." +
                                        "Kind of strange isn't it?");
                                context.startActivity(i);
                                languageCard = languageCards.get(pos++);
                            }
                            else if(languageCard.getEnglish().equals("long time no see")) {
                                Intent i = new Intent(view.getContext(), LessonOneRecyclerViewDetails.class);
                                context.startActivity(i);
                            }
                            break;
                    }
                }
            });

        }
    }
}
