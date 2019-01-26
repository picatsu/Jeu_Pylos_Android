package projet.pylos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



public class Connexion extends AppCompatActivity implements View.OnClickListener {



            private EditText input_email;
            private EditText input_password;

            private Button btn_connexion;
            private Button btn_inscription;

            private ProgressDialog progressDialog;

            private FirebaseAuth mAuth;
            private FirebaseAuth.AuthStateListener mAuthListener;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.content_connexion);

                btn_connexion = findViewById(R.id.btn_connexion);
                btn_inscription = findViewById(R.id.btn_inscription);

                input_email = findViewById(R.id.input_mail);
                input_password = findViewById(R.id.input_password);

                btn_inscription.setOnClickListener(this);
                btn_connexion.setOnClickListener(this);

                progressDialog = new ProgressDialog(this);

                //FirebaseApp.initializeApp(this);
                mAuth = FirebaseAuth.getInstance();

                // Verify if user is logged
                if(mAuth.getCurrentUser() != null){
                    //closing this activity
                    finish();
                    //starting login activity
                    startActivity(new Intent(this, User.class));
                }
            }

            @Override
            public void onClick(View view) {
                if (view == btn_connexion){
                    connexion();

                    // Verify if user is logged
                    if(mAuth.getCurrentUser() != null){
                        //closing this activity
                        finish();
                        //starting login activity
                        startActivity(new Intent(this, User.class));
                    }
                } else if (view == btn_inscription) {
                    // mAuth.createUserWithEmailAndPassword();
                    inscription();

                    // Verify if user is logged
                    if(mAuth.getCurrentUser() != null){
                        //closing this activity
                        finish();
                        //starting login activity
                        startActivity(new Intent(this, User.class));
                    }
                }
            }

            private void inscription(){

                //getting email and password from edit texts
                String email = input_email.getText().toString().trim();
                String password = input_password.getText().toString().trim();

                //checking if email and passwords are empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
                    return;
                }

                //if the email and password are not empty
                //displaying a progress dialog

                progressDialog.setMessage("Registering Please Wait...");
                progressDialog.show();

                //creating a new user
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            Toast.makeText(Connexion.this,"Successfully registered",Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                        } else {
                            //display some message here
                            Toast.makeText(Connexion.this,"Registration Error",Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                        }
                    }
                });

            }

            private void connexion(){
                //getting email and password from edit texts
                String email = input_email.getText().toString().trim();
                String password = input_password.getText().toString().trim();

                //checking if email and passwords are empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
                    return;
                }

                //if the email and password are not empty
                //displaying a progress dialog

                progressDialog.setMessage("Connexion Please Wait...");
                progressDialog.show();

                // Logging
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithEmail:success");
                            // FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(Connexion.this,"Successfully connected",Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            // Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            Toast.makeText(Connexion.this,"Connexion failed",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });


            }
}
