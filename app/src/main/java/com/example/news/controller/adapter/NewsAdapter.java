package com.example.news.controller.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.ActionsManager;
import com.example.news.R;
import com.example.news.controller.fragments.NewsContentFragment;
import com.example.news.model.NewsDto;

import java.util.List;

import static com.example.news.Constants.NEWS_CONTENT_FRAGMENT_TAG;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private static final String POSITION_BUNDLE_KEY = "position";

    private List<NewsDto> data;
    private LayoutInflater inflater;

    public NewsAdapter(Context context, List<NewsDto> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsDto dto = data.get(position);
        holder.setData(dto);

        NewsContentFragment newsContentFragment = new NewsContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION_BUNDLE_KEY, position);
        newsContentFragment.setArguments(bundle);

        holder.getCardView().setOnClickListener((View view) -> ActionsManager.replaceFragment(view.getContext(), R.id.main_container, newsContentFragment, NEWS_CONTENT_FRAGMENT_TAG));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
