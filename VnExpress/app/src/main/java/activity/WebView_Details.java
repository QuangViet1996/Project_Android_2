package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.vnexpress.R;

/**
 * Created by Joyboy on 9/20/2016.
 */

public class WebView_Details extends AppCompatActivity {
    WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_details);
        webView = (WebView) findViewById(R.id.webview_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getBundleExtra("data");
            String link = bundle.getString("link");

            webView.getSettings().setJavaScriptEnabled(true);
//            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//            webView.getSettings().setSupportMultipleWindows(true);
//            webView.getSettings().setSupportZoom(true);
//            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.loadUrl(link);
            getSupportActionBar().setTitle("Nội dung chi tiết");
        }
    }
}
