package com.example.articles.model.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.articles.model.dto.Article;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ImagesDownloader extends AsyncTask<Integer, Void, Void> {

    private List<Article> articles;

    ImagesDownloader(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        int position = params[0];
        String url = "";

        for (int i = position; i < articles.size(); i++) {
            if (isCancelled()) {
                return null;
            }

            if (i >= articles.size()) {
                return null;
            }

            if (articles.get(i).getImage() != null) {
                continue;
            }

            Bitmap bitmap = null;
            url = articles.get(i).getImageURL();
            try (InputStream inputStream = new URL(url).openStream()) {
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                // set default image
            }
            if (bitmap != null) {
                articles.get(i).setImage(Bitmap.createScaledBitmap(bitmap, 360, 360, false));
            }
        }
        return null;
    }
}
