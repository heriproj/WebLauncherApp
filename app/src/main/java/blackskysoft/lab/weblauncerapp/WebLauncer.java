package blackskysoft.lab.weblauncerapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebLauncer extends ActionBarActivity {
    WebView mainWebView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_launcer);
        mainWebView = (WebView) findViewById(R.id.webView);
        mainWebView.loadUrl("http://blackskysoft.net");
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,Window.PROGRESS_VISIBILITY_ON);
        mainWebView.setWebViewClient(new MainWebViewClient());
        mainWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                WebLauncer.this.setTitle("Loading...");
                WebLauncer.this.setProgress(progress * 100);
                if (progress == 100){
                    WebLauncer.this.setTitle(view.getTitle());
                    WebLauncer.this.setTitle("blackskysoft.NET");
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(mainWebView.canGoBack())
            mainWebView.goBack();
        else
            super.onBackPressed();
    }

    private class MainWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("Log", "loading: " + url);
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_launcer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
