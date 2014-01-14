package com.cangshudoudou.msgbox.wrapper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://www.cangshudoudou.com/msgbox-m/");
        
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        myWebView.setWebViewClient(new WebViewClient());
    }

    public void onBackPressed() {
    	WebView myWebView = (WebView) findViewById(R.id.webview);
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
