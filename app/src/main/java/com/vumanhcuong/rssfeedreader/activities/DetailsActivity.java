package com.vumanhcuong.rssfeedreader.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vumanhcuong.rssfeedreader.R;

public class DetailsActivity extends AppCompatActivity {
    private WebView mDetailsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDetailsWebView = (WebView) findViewById(R.id.wvDetails);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        mDetailsWebView.loadUrl(link);
        mDetailsWebView.setWebViewClient(new WebViewClient());

    }
}
