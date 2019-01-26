package projet.pylos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Score_View extends AppCompatActivity {


    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score = findViewById(R.id.nomscore);
        score.setText( " ID JOUEUR : "+android.os.Build.MODEL + "\n \n Score :"+ (Long.toString( 20000 -  PYLOS_View.scoore.getDureeMs() ) ) );


    }






}
