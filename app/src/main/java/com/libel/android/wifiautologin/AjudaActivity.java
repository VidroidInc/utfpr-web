package com.libel.android.wifiautologin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.moshekaplan.android.wifiautologin.R;
import com.moshekaplan.android.wifiautologin.activities.EditConfiguration;

public class AjudaActivity extends ActionBarActivity {
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        webView = (WebView) findViewById(R.id.webViewAjuda);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.getAllowContentAccess();
        webSettings.getCacheMode();
        webView.loadUrl("http://forum.vidroid.com.br/d/7-utfprweb-login");
        webView.canGoBack();
        webView.setWebChromeClient(new WebChromeClient());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.ajuda_web_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent(this, EditConfiguration.class);
                startActivity(intent);
                finish();
                return true;
            }
            case R.id.menuVoltar:{
                webView.goBack();
                return true;
            }
            case  R.id.menuAvancar:{
                webView.goForward();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, EditConfiguration.class);
        startActivity(intent);
        finish();
    }
}
