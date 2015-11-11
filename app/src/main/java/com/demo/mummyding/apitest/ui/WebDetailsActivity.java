package com.demo.mummyding.apitest.ui;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.demo.mummyding.apitest.R;
import com.demo.mummyding.apitest.cache.CONSTANT;

import im.delight.android.webview.AdvancedWebView;

public class WebDetailsActivity extends AppCompatActivity {

    private AdvancedWebView webView;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_details);
         final String url  = getIntent().getStringExtra("url");
        // final String url ="http://www.codeceo.com/article/android-webview-reader.html";
      //  final String url = "http://news.xinhuanet.com/local/2015-11/11/c_128416708.htm";
        Log.d("url", url);
        webView = (AdvancedWebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        String dbpath = getFilesDir().getAbsolutePath() + CONSTANT.APP_CACAHE_DIRNAME;
        webView.getSettings().setUserAgentString("Android");
        //webView.getSettings().setDatabasePath(dbpath);
        webView.getSettings().setAppCachePath(dbpath);
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                WebDetailsActivity.this.setTitle("正在加载....");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                WebDetailsActivity.this.setTitle("新闻");
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                WebDetailsActivity.this.setTitle("正在加载 "+newProgress+"%");
            }
        });
    }
}
