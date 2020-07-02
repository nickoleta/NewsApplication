package com.example.news;

import com.example.news.model.NewsDto;

import java.util.ArrayList;
import java.util.List;

public final class NewsManager {

    private static NewsManager instance;
    private List<NewsDto> news;

    private NewsManager() {
        this.news = new ArrayList<>();
    }

    public static NewsManager getInstance() {
        if (instance == null) {
            instance = new NewsManager();
        }
        return instance;
    }

    public void addAllNews(List<NewsDto> list) {
        news.addAll(list);
    }

    public List<NewsDto> getNews() {
        return this.news;
    }
}
