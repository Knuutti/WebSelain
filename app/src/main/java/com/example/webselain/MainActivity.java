package com.example.webselain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView wvWeb;
    EditText etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wvWeb = findViewById(R.id.wvWeb);
        etAddress = findViewById(R.id.etAddress);

        wvWeb.getSettings().setJavaScriptEnabled(true);
        wvWeb.setWebViewClient(new WebViewClient());
        wvWeb.loadUrl("http://www.lut.fi");


    }

    public void loadAddress(View v) {
        String url = etAddress.getText().toString();
        System.out.println(url);
        wvWeb.loadUrl(url);

    }
}