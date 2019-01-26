package projet.pylos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



public class PYLOS_View extends Activity {
    Dialog myDialog;

    View view;
    static EditText email;
    static EditText mdp;

    public static int  scoremission1 =0;
    public static int  scoremission2 =0;
    public static int  scoremission3 =0;

    public static Button boulesjoueur1;
    public static Button boulesjoueur2;




    public static TextView txtclose;
    public static TextView valeurscore;
    public static TextView valeurmission1;
    public static TextView valeurmission2;
    public static TextView valeurmission3;
    public static SharedPreferences pref; // 0 - for private mode
    public static SharedPreferences.Editor editor;

    public static Temps scoore = new Temps(); // Calcule du score via une classe a part

    MediaPlayer musictoogle;

    public int musicEncours=0;
    MediaPlayer musicfond;

    public static Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pylos);
        myDialog = new Dialog(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pylos);

        this.c= this;

        PYLOS_View.scoore.start();
        GLSurfaceView glView = findViewById(R.id.boardSurface);

        boulesjoueur1 = findViewById(R.id.button22);
        boulesjoueur2 = findViewById(R.id.button21);

        //boulesjoueur1.setText(  Integer.toString( GameBoardPylos.app.player1.getNombreDeBoules() ) );
       // boulesjoueur2.setText(Integer.toString( GameBoardPylos.app.player2.getNombreDeBoules()  ) );

        OpenGlRenderer renderer = new OpenGlRenderer();
        glView.setRenderer(renderer);
        glView.requestFocus();
        glView.setFocusableInTouchMode(true);

        GameBoardPylos.init(this);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        ////// CHECK F
        if(GameBoardPylos.checkEnd()){
            if(GameBoardPylos.app.player1.getNombreDeBoules() > GameBoardPylos.app.player2.getNombreDeBoules()) {
                Toast.makeText( this, "GAME FINI, Tu as gagné, REGARD TON SCORE  !  ", Toast.LENGTH_SHORT - 1).show();
            }
            else{
                Toast.makeText( this, "GAME FINI, Tu as perdu, REGARD TON SCORE  !  ", Toast.LENGTH_SHORT - 1).show();

            }


        }//////////////////////////////// FIN DE GAME 3D






        valeurscore = findViewById(R.id.valeurscore);
        valeurmission1 = findViewById(R.id.valeurmission1);
        valeurmission2 = findViewById(R.id.valeurmission2);
        valeurmission3 = findViewById(R.id.valeurmission3);


        scoremission1 = pref.getInt("mission1", 0); // getting Integer

        glView.setOnTouchListener(new SwipeControls(this));

        Button b1 = findViewById(R.id.haut);
        Button b2 = findViewById(R.id.bas);
        Button b3 = findViewById(R.id.droit);
        Button b4 = findViewById(R.id.gauche);



        ToggleButton toggle = findViewById(R.id.button17);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                  if (isChecked) {
                                                      // The toggle is enabled
                                                      musictoogle = MediaPlayer.create(PYLOS_View.this, R.raw.regles);
                                                      musictoogle.setVolume(50,50);
                                                      musictoogle.start();
                                                      musicEncours=1;

                                                  } else {
                                                      // The toggle is disabled
                                                      musictoogle.pause();
                                                      musicEncours=0;
                                                  }
                                              }
                                          }
        );

        ButtonControls bc = new ButtonControls(this);
        b1.setOnTouchListener(bc);
        b2.setOnTouchListener(bc);
        b3.setOnTouchListener(bc);
        b4.setOnTouchListener(bc);







        //final TextView tv = (TextView) findViewById(R.id.tvGameOver);
        Thread timer = new Thread() {
            @Override
            public void run() {
                super.run();
                int time = 0;
                while (true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    time++;
                    final int temp = time;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }
        };
        timer.start();

        musicfond = new MediaPlayer();
        musicfond.start();
    }


    public static void showfin(){

        if(TheApplication.player1.getNombreDeBoules() == 0 && (TheApplication.player2.getNombreDeBoules() > 0) ){
            Toast.makeText( PYLOS_View.c, "GAME FINI, Tu as perdu ... REGARD TON SCORE  !  ", Toast.LENGTH_SHORT - 1).show();


        }
        else {
            if(TheApplication.player2.getNombreDeBoules() == 0 && (TheApplication.player1.getNombreDeBoules() > 0)) {
                Toast.makeText( PYLOS_View.c, "GAME FINI, Tu as gagné, REGARD TON SCORE  !  ", Toast.LENGTH_SHORT - 1).show();

            }
        }


    }





    public static void changescore(Long newscore){
        if(newscore > scoore.getDureeMs()){

            editor.putLong("score", newscore); // Storing long

            editor.commit(); // commit changes
        }
    }

    //public static void changetextboutton(){
      //  PYLOS_View.boulesjoueur1.setText(GameBoardPylos.app.player1.getNombreDeBoules() );
       // PYLOS_View.boulesjoueur2.setText(GameBoardPylos.app.player2.getNombreDeBoules() );
    //}

    public void changemission(int nb){

            if(nb > scoremission1 ){
                PYLOS_View.scoremission1 =  nb;
                editor.putInt("mission1", nb); // Storing integer

            }

            if( nb > 100 && nb < 1000){
                PYLOS_View.scoremission2 = PYLOS_View.scoremission1 + nb;
                editor.putInt("mission2", nb); // Storing integer

            }

            if(nb >1000){
                PYLOS_View.scoremission3 = PYLOS_View.scoremission2 + nb;
                editor.putInt("mission2", nb); // Storing integer
            }


    }



    public void ShowPopup(View v){ // BOUTTON PARAMETRE LANCE SA
        scoore.pause();
        this.view = v;




        Button btnlogin;
        Button btngenerate;

        myDialog.setContentView(R.layout.parametre_pop_up);
        txtclose = myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");

        valeurscore = myDialog.findViewById(R.id.valeurscore);
        valeurmission1 = myDialog.findViewById(R.id.valeurmission1);
        valeurmission2 = myDialog.findViewById(R.id.valeurmission2);
        valeurmission3 = myDialog.findViewById(R.id.valeurmission3);


        //  https://www.journaldev.com/9412/android-shared-preferences-example-tutorial
        changescore(PYLOS_View.scoore.getDureeMs());
        valeurscore.setText(Long.toString( 20000 - pref.getLong("score", 0) ));
        this.changemission( PYLOS_View.scoremission1 );

        valeurmission1.setText(scoremission1+" % reussite ");
        valeurmission2.setText(scoremission2+" % reussite ");
        valeurmission3.setText(scoremission3+" % reussite ");

        btnlogin = myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoore.resume(); //// REPRISE DU SCORE
                myDialog.dismiss();
            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(PYLOS_View.this, Connexion.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


                myDialog.dismiss();
            }
        });

        btngenerate = myDialog.findViewById(R.id.btncertificat);

        btngenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PYLOS_View.this, Score_View.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }



    public void openDialog() { /////// BOUTON LOGIN DANS MENU PARAMETRE
        // Creation du SharedPreferences HIGH_SCORE




        SharedPreferences settings = this.view.getContext().getSharedPreferences("HIGH_SCORE", Context.MODE_PRIVATE);
        long highScore = settings.getLong("HIGH_SCORE", 2000000);// La premiere sera evidement le premier score
        AlertDialog.Builder builder = new AlertDialog.Builder(this.view.getContext()); // creation du dialog box
        LayoutInflater inflater = LayoutInflater.from(this.view.getContext()); // on le met sur la fenetre du screen 2
        View dialogLayout = inflater.inflate(R.layout.login_pop_up, null); // on lui associe un xml
        builder.setView(dialogLayout);
        // CReation du Dialog Box
        // Titre du Dialog Box
        builder.setTitle("Fin de Partie ! ");// Titre du dialog box
        // Initialisation des deu bouttons
        Button buttonok = dialogLayout.findViewById(R.id.dialog_connexion);
        Button buttonexit = dialogLayout.findViewById(R.id.dialog_inscription);






        buttonok.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Si new game est activé, alors on lance une nouvelle game direction sur la meme activity
            //c.connexion(PYLOS_View.email.getText().toString(), PYLOS_View.mdp.getText().toString());

            }
        });
        buttonexit.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Si le Exit est activé, on revient au main activity
           // c.inscription(PYLOS_View.email.getText().toString(), PYLOS_View.mdp.getText().toString());

            }
        });
        // Ici on choisi quel message affiché
        if(highScore < scoore.getDureeSec() ){// On compare le score actuel avec le high score enregistré
            builder.setMessage(" Score : "+ scoore.getDureeSec() );
        }
        else{
            // on rentre ici si c'est un nouveau score en seconde
            builder.setMessage("Congratulation New Record ! \n Score : "+ scoore.getDureeSec() +"\n");
            settings.edit().putLong("HIGH_SCORE", scoore.getDureeSec() ).apply();
            // on modifie la valeur
        }
        // Create the alert dialog and display it
        AlertDialog theAlertDialog = builder.create();
        theAlertDialog.show();
    }



/*
    private void createPdf(){
        // create a new document
        PdfDocument document = new PdfDocument();


        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        File a = new File(docsFolder.getAbsolutePath(),"test.pdf");
        String targetPdf = "/sdcard/test.pdf";
         File filePath = new File("/storages/newFile.pdf");
        //File filePath = a;

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(100, 100, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);



        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawText(" TEXT ICI ", (float) 10.0, (float ) 10.0, paint);
        //canvas.drawCircle(50, 50, 30, paint);


        // finish the page
        document.finishPage(page);

        // Create Page 2
        pageInfo = new PdfDocument.PageInfo.Builder(500, 500, 2).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200, 100, paint);
        document.finishPage(page);



        // write the document content

        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
    }


    */
}

