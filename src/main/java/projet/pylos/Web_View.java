package projet.pylos;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;


public class Web_View extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        final WebView webview;

        webview = findViewById(R.id.webview1);



        webview.getSettings().setJavaScriptEnabled(true);

        webview.loadUrl("http://pylos.dx.am");

    }
}
