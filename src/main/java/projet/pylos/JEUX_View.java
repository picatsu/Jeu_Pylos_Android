package projet.pylos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class JEUX_View extends AppCompatActivity {
    AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);

        MobileAds.initialize(this);



        mAdview = findViewById(R.id.adView);



        AdRequest adrequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build() ;


        mAdview.loadAd(adrequest);
    }


    public void onClickPylos(View v) {

        Intent intent2 = new Intent(JEUX_View.this, PYLOS_View.class);
        startActivity(intent2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void onClickSiteWeb(View v) {

        Intent intent2 = new Intent(JEUX_View.this, Web_View.class);
        startActivity(intent2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
}

    public void onClick2d(View v) {

        Intent intent2 = new Intent(JEUX_View.this, pylos2D_AUTO_View.class);
        startActivity(intent2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void onClickinsctructiuons(View v) {

        Intent intent2 = new Intent(JEUX_View.this, Insctrutions_View.class);
        startActivity(intent2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }



}
