package com.example.news.model.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class DownloadContentImage extends AsyncTask<URL, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(URL... urls) {
        try (InputStream is = new URL(urls[0].toString()).openStream()) {
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            Log.e("Could not download image", Objects.requireNonNull(e.getMessage()));
            return null;
        }
    }
}
