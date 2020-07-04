package com.example.articles.model;

import com.example.articles.model.dto.Article;

import java.util.ArrayList;
import java.util.List;

public final class ArticlesManager {

    private static ArticlesManager instance;
    private List<Article> articles;

    private ArticlesManager() {
        this.articles = new ArrayList<>();
    }

    public static ArticlesManager getInstance() {
        if (instance == null) {
            instance = new ArticlesManager();
        }
        return instance;
    }

    public void addAllArticles(List<Article> list) {
        articles.addAll(list);
    }

    public List<Article> getArticles() {
        return this.articles;
    }
}
