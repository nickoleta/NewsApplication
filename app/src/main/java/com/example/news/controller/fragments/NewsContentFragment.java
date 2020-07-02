package com.example.news.controller.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.news.NewsManager;
import com.example.news.R;
import com.example.news.model.NewsDto;
import com.example.news.model.tasks.DownloadContentImage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NewsContentFragment extends Fragment {

    private TextView title;
    private TextView content;
    private TextView author;
    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_content, container, false);

        initViews(view);
        setContent(getArguments());

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initViews(View view) {
        this.title = view.findViewById(R.id.content_title);
        this.content = view.findViewById(R.id.content_content);
        this.author = view.findViewById(R.id.content_author);
        this.image = view.findViewById(R.id.content_image);
    }

    private void setContent(Bundle bundle) {
        if (bundle != null) {
            int position = (int) bundle.get("position");
            NewsDto item = NewsManager.getInstance().getNews().get(position);
            title.setText(item.getTitle());
            content.setText(item.getContent());
            author.setText(item.getAuthor());
            final String imageUrl = item.getImageURL();
            image.setImageBitmap(downloadImage(imageUrl));
        }
    }

    private Bitmap downloadImage(String imageUrl) {
        try {
            final URL url = new URL(imageUrl);
            return new DownloadContentImage().execute(url).get();
        } catch (ExecutionException | MalformedURLException | InterruptedException e) {
            return null;
        }
    }
}
