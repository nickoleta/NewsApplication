package com.example.news;

import com.example.news.model.Article;

import java.util.List;

public interface AsyncResponse {

    void getNews(List<Article> output);
}
