package com.example.articles.model.tasks;

import com.example.articles.model.dto.Article;

import java.util.List;

public interface AsyncResponse {

    void getNews(List<Article> output);
}
