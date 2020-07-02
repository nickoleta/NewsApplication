package com.example.news.model.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.news.AsyncResponse;
import com.example.news.NewsManager;
import com.example.news.model.Article;

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

public class DownloadNews extends AsyncTask<URL, Integer, List<Article>> {

    private static final String ARTICLES_JSON_FIELD = "articles";
    private static final String TITLE_JSON_FIELD = "title";
    private static final String AUTHOR_JSON_FIELD = "author";
    private static final String DESCRIPTION_JSON_FIELD = "description";
    private static final String CONTENT_JSON_FIELD = "content";
    private static final String IMAGE_URL_JSON_FIELD = "urlToImage";
    private static final String PUBLISHED_AT_JSON_FIELD = "publishedAt";

    private AsyncResponse delegate;

    public DownloadNews(AsyncResponse asyncResponse) {
        this.delegate = asyncResponse;
    }

    @Override
    protected List<Article> doInBackground(URL... urls) {
        HttpURLConnection connection = connect(urls[0]);

        StringBuilder jsonText = readData(connection);

        List<Article> news = new ArrayList<>();
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

    private List<Article> parseData(StringBuilder jsonText) {
        JSONObject jsonObject;
        JSONArray articles;
        try {
            jsonObject = new JSONObject(jsonText.toString());
            articles = jsonObject.getJSONArray(ARTICLES_JSON_FIELD);
        } catch (JSONException e) {
            return null;
        }

        List<Article> items = new ArrayList<>();
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
                Article item = new Article(article.getString(TITLE_JSON_FIELD),
                        article.getString(AUTHOR_JSON_FIELD),
                        article.getString(DESCRIPTION_JSON_FIELD),
                        article.getString(CONTENT_JSON_FIELD),
                        article.getString(IMAGE_URL_JSON_FIELD),
                        article.getString(PUBLISHED_AT_JSON_FIELD));
                items.add(item);
            } catch (JSONException e) {
                return null;
            }
        }
        NewsManager.getInstance().addAllNews(items);
        downloadImages(items);
        return items;
    }

    private void downloadImages(List<Article> news) {
        for (int i = 0; i < news.size(); i += 10) {
            DownloadImages downloadImagesTask = new DownloadImages(news);
            downloadImagesTask.execute(0);
        }
    }

    @Override
    protected void onPostExecute(List<Article> news) {
        super.onPostExecute(news);
        delegate.getNews(news);
    }
}
