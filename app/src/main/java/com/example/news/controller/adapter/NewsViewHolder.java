package com.example.news.controller.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.model.Article;

class NewsViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private ImageView image;
    private TextView title;
    private TextView author;

    NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.cardView = itemView.findViewById(R.id.card_view_main);
        this.image = itemView.findViewById(R.id.card_image);
        this.title = itemView.findViewById(R.id.card_title);
        this.author = itemView.findViewById(R.id.card_author);
    }

    void setData(Article article) {
        this.author.setText(article.getAuthor());
        this.title.setText(article.getTitle());
        this.image.setImageBitmap(article.getImage());
    }

    CardView getCardView() {
        return cardView;
    }
}
