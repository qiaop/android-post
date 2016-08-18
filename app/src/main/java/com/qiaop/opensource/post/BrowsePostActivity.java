package com.qiaop.opensource.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qiaop.opensource.post.view.ImagePagerActivity;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class BrowsePostActivity extends Activity{

    private WebView webview;
    private String url = "file:///android_asset/js.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        webview = (WebView) findViewById(R.id.post_webView);
        webview.getSettings().setUseWideViewPort(true); // 设置加载进来的页面自适应手机屏幕（可缩放）
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setWebViewClient(client);
        webview.setWebChromeClient(wvcc);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JsInteration(), "demo");
        webview.loadUrl(url);
    }

    private WebChromeClient wvcc = new WebChromeClient(){
        public void onReceivedTitle(WebView view, String title) {};
        public void onProgressChanged(WebView view, int newProgress) {};
    };

    private WebViewClient client = new WebViewClient(){
        public void onPageFinished(WebView view, String url) {};
    };

    public class JsInteration{
        @JavascriptInterface
        public void showBigView(String[] urls,int index){
            Intent intent = new Intent(BrowsePostActivity.this, ImagePagerActivity.class);
            intent.putExtra("urls", urls);
            intent.putExtra("position", index);
            startActivity(intent);
        }
    }
}
