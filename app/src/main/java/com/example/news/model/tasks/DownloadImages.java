package com.example.news.model.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.news.model.NewsDto;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class DownloadImages extends AsyncTask<Integer, Void, Void> {

    private List<NewsDto> news;

    DownloadImages(List<NewsDto> news) {
        this.news = news;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        int position = params[0];
        String url = "";

        for (int i = position; i <= position + 5; i++) {
            if (isCancelled()) {
                return null;
            }

            if (i >= news.size()) {
                return null;
            }

            if (news.get(i).getImage() != null) {
                continue;
            }

            Bitmap bitmap = null;
            url = news.get(i).getImageURL();
            try (InputStream inputStream = new URL(url).openStream()) {
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                // set default image
            }
            if (bitmap != null) {
                news.get(i).setImage(Bitmap.createScaledBitmap(bitmap, 360, 360, false));
            }
        }
        return null;
    }
}
