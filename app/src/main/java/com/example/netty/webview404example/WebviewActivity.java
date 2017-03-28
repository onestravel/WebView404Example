package com.example.netty.webview404example;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;


public class WebviewActivity extends Activity {
	private String loadUrl = "";
	private WebView mWebView;
	private LinearLayout layoutEmpty;
	private LinearLayout layoutLoading;
	private boolean isLoadError = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		loadUrl = getIntent().getStringExtra("loadUrl");
		initView();
		mWebView.loadUrl(loadUrl);
	}

	private void initView() {
		mWebView = (WebView) findViewById(R.id.wv_webview);
		layoutEmpty = (LinearLayout) findViewById(R.id.layout_empty);
		layoutLoading = (LinearLayout) findViewById(R.id.layout_loading);
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setAllowContentAccess (true);
		settings.setAppCacheMaxSize (1024);
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
				super.onReceivedError(view, request, error);
				isLoadError = true;
				if (isLoadError) {
					layoutEmpty.setVisibility(View.VISIBLE);
				} else {
					layoutEmpty.setVisibility(View.GONE);
				}
			}

			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
				isLoadError = true;
				if (isLoadError) {
					layoutEmpty.setVisibility(View.VISIBLE);
				} else {
					layoutEmpty.setVisibility(View.GONE);
				}
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				layoutLoading.setVisibility(View.GONE);
				if (isLoadError) {
					layoutEmpty.setVisibility(View.VISIBLE);
				} else {
					layoutEmpty.setVisibility(View.GONE);
				}
			}
		});

		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				if (!TextUtils.isEmpty(title) && title.toLowerCase().contains("error")) {
					layoutLoading.setVisibility(View.GONE);
					isLoadError = true;
					if (isLoadError) {
						layoutEmpty.setVisibility(View.VISIBLE);
					} else {
						layoutEmpty.setVisibility(View.GONE);
					}
				}
			}
		});

		layoutEmpty.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mWebView.reload();
			}
		});
	}


}
