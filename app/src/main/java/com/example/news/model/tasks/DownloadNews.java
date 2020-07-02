package com.example.news.model.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.news.AsyncResponse;
import com.example.news.NewsManager;
import com.example.news.model.NewsDto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadNews extends AsyncTask<URL, Integer, List<NewsDto>> {

    private AsyncResponse delegate;

    public DownloadNews(AsyncResponse asyncResponse) {
        this.delegate = asyncResponse;
    }

    @Override
    protected List<NewsDto> doInBackground(URL... urls) {
        HttpURLConnection connection = connect(urls[0]);

        StringBuilder jsonText = readData(connection);

        List<NewsDto> news = new ArrayList<>();
        if (jsonText != null) {
            news = parseData(jsonText);
        }

        return news;
    }

    private HttpURLConnection connect(URL url) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            Log.e("CONNECTING", "Could not get a connection ", e);
            return null;
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            return null;
        }
        return connection;
    }

    private StringBuilder readData(HttpURLConnection connection) {
        StringBuilder jsonText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                if (isCancelled()) {
                    return null;
                }
                jsonText.append(inputLine);
            }
        } catch (IOException e) {
            Log.e("READING", "Could not read downloaded stream", e);
            return null;
        } finally {
            connection.disconnect();
        }
        return jsonText;
    }

    private List<NewsDto> parseData(StringBuilder jsonText) {
        JSONObject jsonObject;
        JSONArray articles;
        try {
            jsonObject = new JSONObject(jsonText.toString());
            articles = jsonObject.getJSONArray("articles");
        } catch (JSONException e) {
            return null;
        }

        List<NewsDto> items = new ArrayList<>();
        for (int i = 0; i < articles.length(); i++) {
            JSONObject article;
            try {
                article = articles.getJSONObject(i);
            } catch (JSONException e) {
                return null;
            }

            if (isCancelled()) {
                return null;
            }

            try {
                NewsDto item = new NewsDto(article.getString("title"),
                        article.getString("author"),
                        article.getString("description"),
                        article.getString("content"),
                        article.getString("urlToImage"),
                        article.getString("publishedAt"));
                items.add(item);
            } catch (JSONException e) {
                return null;
            }
        }
        NewsManager.getInstance().addAllNews(items);
        downloadImages(items);
        return items;
    }

    private void downloadImages(List<NewsDto> news) {
        for (int i = 0; i < news.size(); i += 5) {
            DownloadImages downloadImagesTask = new DownloadImages(news.subList(i, i + 5));
            downloadImagesTask.execute(i);
        }
    }

    @Override
    protected void onPostExecute(List<NewsDto> news) {
        super.onPostExecute(news);
        delegate.getNews(news);
    }
}
