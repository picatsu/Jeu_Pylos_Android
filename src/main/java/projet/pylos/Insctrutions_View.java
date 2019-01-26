package projet.pylos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;


public class Insctrutions_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insctrutions);


        final WebView webview;
        webview = findViewById(R.id.webview2);



        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "https://www.gigamic.com/files/catalog/products/rules/pylos_rule-fr.pdf";
        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);

        //webview.loadUrl("https://www.gigamic.com/files/catalog/products/rules/pylos_rule-fr.pdf");
    }
}
