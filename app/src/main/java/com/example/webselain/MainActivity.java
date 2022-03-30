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
    private ArrayList<Page> pageHistory = new ArrayList<>();
    private String url;
    private int position;

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
        pageHistory.add(new Page(url));
        position = 0;

    }

    // Search
    public void loadAddress(View v) {

        // Gets the address from search bar
        url = etAddress.getText().toString();

        // Loading the page
        String[] urlArray = url.split("www", 2);
        if (urlArray[0].length() == 0) {
            url = "http://www" + url;
        }
        else if (urlArray[0].compareTo("index.html") == 0) {
            url = "file:///android_asset/" + url;
        }
        wvWeb.loadUrl(url);

        // Managing page history
        addPageToHistory(new Page(url));
    }

    private void addPageToHistory(Page page) {

        // If page history is full, remove the oldest page
        if (position > 9) {
            pageHistory.remove(0);
        }
        // Otherwise move the selected position
        else {
            position++;
        }

        // If user searches for new page after going back to previous page,
        // remove the pages after that page
        if (position < pageHistory.size() - 1) {
            for (int i = position; i < pageHistory.size(); i++) {
                pageHistory.remove(position);
            }
        }

        // Adds the page to the list
        pageHistory.add(page);
    }

    public void nextPage(View v) {
        if (position < pageHistory.size() - 1) {
            position++;
            wvWeb.loadUrl(pageHistory.get(position).getUrl());
        }
    }

    public void previousPage(View v) {
        if (position > 0) {
            position--;
            wvWeb.loadUrl(pageHistory.get(position).getUrl());
        }
    }



    public void refreshPage(View v) {
        wvWeb.loadUrl(wvWeb.getUrl());
    }

    public void executeJavaScript(View v) {
        wvWeb.evaluateJavascript("javascript:shoutOut()", null);
    }
}