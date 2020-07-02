package com.example.news;

import com.example.news.model.Article;

import java.util.ArrayList;
import java.util.List;

public final class NewsManager {

    private static NewsManager instance;
    private List<Article> news;

    private NewsManager() {
        this.news = new ArrayList<>();
    }

    public static NewsManager getInstance() {
        if (instance == null) {
            instance = new NewsManager();
        }
        return instance;
    }

    public void addAllNews(List<Article> list) {
        news.addAll(list);
    }

    public List<Article> getNews() {
        return this.news;
    }
}
