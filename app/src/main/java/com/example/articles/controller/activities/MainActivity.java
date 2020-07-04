package com.example.articles.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.articles.R;
import com.example.articles.controller.ActionsManager;
import com.example.articles.controller.fragments.MainFragment;
import com.example.articles.model.Constants;
import com.example.articles.model.tasks.ArticlesDownloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new ArticlesDownloader(output -> ActionsManager.attachFragment(MainActivity.this, R.id.main_container, new MainFragment(), Constants.MAIN_FRAGMENT_TAG))
                    .execute(new URL(Constants.API_URL_ALL_ARTICLES));
        } catch (MalformedURLException e) {
            Log.e("Could not download article", Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.log_out) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
        getSupportFragmentManager().popBackStack();
    }
}
