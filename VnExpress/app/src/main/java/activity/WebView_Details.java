package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.vnexpress.R;

/**
 * Created by Joyboy on 9/20/2016.
 */

public class WebView_Details extends AppCompatActivity  implements ObservableScrollViewCallbacks{
    ObservableWebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_details);
        webView = (ObservableWebView) findViewById(R.id.webview_details);
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
            webView.setScrollViewCallbacks(this);
        }


    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (ab == null) {
            return;
        }
        if (scrollState == scrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == scrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
}
