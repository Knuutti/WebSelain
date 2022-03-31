package com.example.webselain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WebView wvWeb;
    private EditText etAddress;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wvWeb = findViewById(R.id.wvWeb);

        etAddress = findViewById(R.id.etAddress);

        wvWeb.getSettings().setJavaScriptEnabled(true);
        wvWeb.setWebViewClient(new WebViewClient());

        // Default page
        url = "http://www.lut.fi";
        wvWeb.loadUrl(url);

    }

    // Search
    public void loadAddress(View v) {

        // Gets the address from search bar
        url = etAddress.getText().toString();

        // Loading the page
        String[] urlArray = url.split("www.", 2);
        if (urlArray[0].compareTo("index.html") == 0) {
            url = "file:///android_asset/" + url;
        }
        else if (urlArray[0].length() == 0 || urlArray[0].length() == url.length()) {
            url = "http://www." + url;
        }
        wvWeb.loadUrl(url);
    }

    public void nextPage(View v) {
        if (wvWeb.canGoForward()) {
            wvWeb.goForward();
        }
    }

    public void previousPage(View v) {
        if (wvWeb.canGoBack()) {
            wvWeb.goBack();
        }
    }

    public void refreshPage(View v) {
        wvWeb.loadUrl(wvWeb.getUrl());
    }

    public void executeJavaScript(View v) {
        wvWeb.evaluateJavascript("javascript:shoutOut()", null);
    }
    public void executeJavaScript2(View v) {
        wvWeb.evaluateJavascript("javascript:initialize()", null);
    }

}