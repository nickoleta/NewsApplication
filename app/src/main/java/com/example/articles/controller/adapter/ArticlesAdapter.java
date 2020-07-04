package com.example.articles.controller.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.articles.R;
import com.example.articles.controller.ActionsManager;
import com.example.articles.controller.fragments.ArticlesContentFragment;
import com.example.articles.model.Constants;
import com.example.articles.model.dto.Article;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {

    private static final String POSITION_BUNDLE_KEY = "position";

    private List<Article> data;
    private LayoutInflater inflater;

    public ArticlesAdapter(Context context, List<Article> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_article, parent, false);
        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        Article dto = data.get(position);
        holder.setData(dto);

        ArticlesContentFragment articlesContentFragment = new ArticlesContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION_BUNDLE_KEY, position);
        articlesContentFragment.setArguments(bundle);

        holder.getCardView().setOnClickListener((View view) -> ActionsManager.replaceFragment(view.getContext(), R.id.main_container, articlesContentFragment, Constants.ARTICLES_CONTENT_FRAGMENT_TAG));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
