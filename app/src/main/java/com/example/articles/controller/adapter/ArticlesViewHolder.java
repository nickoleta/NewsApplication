package com.example.articles.controller.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.articles.R;
import com.example.articles.model.dto.Article;

class ArticlesViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private ImageView image;
    private TextView title;
    private TextView author;

    ArticlesViewHolder(@NonNull View itemView) {
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
