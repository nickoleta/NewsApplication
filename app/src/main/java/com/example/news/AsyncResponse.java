package com.example.news;

import com.example.news.model.NewsDto;

import java.util.List;

public interface AsyncResponse {

    void getNews(List<NewsDto> output);
}
