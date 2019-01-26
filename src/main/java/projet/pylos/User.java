package projet.pylos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth mAuth;

    private Button btn_deconnexion;
    public static TextView txt_userMail;
    public static TextView txt_idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        //initializing firebase authentication object
        mAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(mAuth.getCurrentUser() == null){

            //closing this activity
            finish();
            //starting login_pop_up activity
            startActivity(new Intent(this, JEUX_View.class));

        } else {

            //getting current user
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            //initializing views
            txt_userMail = findViewById(R.id.txt_userMail);
            txt_idUser = findViewById(R.id.txt_idUser);
            btn_deconnexion = findViewById(R.id.btn_deconnexion);

            //displaying logged in user name
            txt_userMail.setText("Bonjour " + user.getEmail());
            txt_idUser.setText(user.getUid());


            //adding listener to button
            btn_deconnexion.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btn_deconnexion){
            logout();
        }
    }

    private void logout(){
        //logging out the user
        mAuth.signOut();
        //closing activity
        finish();
        //starting login_pop_up activity
        startActivity(new Intent(this, JEUX_View.class));
    }
}
