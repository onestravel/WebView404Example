package com.example.netty.webview404example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void onclick(View view){
		String url = ((EditText)findViewById(R.id.et_url)).getText().toString();
		Intent intent = new Intent(this,WebviewActivity.class);
		intent.putExtra("loadUrl",url);
		startActivity(intent);
	}
}
