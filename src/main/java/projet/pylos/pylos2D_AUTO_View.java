package projet.pylos;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;


public class pylos2D_AUTO_View extends Activity implements AdapterView.OnItemSelectedListener {
    Dialog myDialog;
    TheApplication app;
    TextView tostring;
    AdView mAdview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2d_auto);
        myDialog = new Dialog(this);
        app = new TheApplication(2);

        tostring = findViewById(R.id.tostring);
        // Spinner element
        final Spinner spinnerX = findViewById(R.id.spinner);
        final Spinner spinnerY = findViewById(R.id.spinnerY);
        final Spinner spinnerETAGE = findViewById(R.id.spinnerETAGE);

        Button button= findViewById(R.id.buttonspinner);

        // Spinner click listener
        spinnerX.setOnItemSelectedListener(this);
        spinnerY.setOnItemSelectedListener(this);
        spinnerETAGE.setOnItemSelectedListener(this);

        MobileAds.initialize(this);



        mAdview = findViewById(R.id.adView2d);



        AdRequest adrequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build() ;


        mAdview.loadAd(adrequest);

        // Spinner Drop down elements
        List<String> posx = new ArrayList<String>();
        posx.add("0");
        posx.add("1");
        posx.add("2");
        posx.add("3");

        List<String> posy = new ArrayList<String>();
        posy.add("0");
        posy.add("1");
        posy.add("2");
        posy.add("3");

        List<String> posETAGE = new ArrayList<String>();
        posETAGE.add("0");
        posETAGE.add("1");
        posETAGE.add("2");
        posETAGE.add("3");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterX = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posx);
        ArrayAdapter<String> dataAdapterY = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posy);
        ArrayAdapter<String> dataAdapterETAGE = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posETAGE);

        // Drop down layout style - list view with radio button
        dataAdapterX.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterETAGE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerX.setAdapter(dataAdapterX);
        spinnerY.setAdapter(dataAdapterY);
        spinnerETAGE.setAdapter(dataAdapterETAGE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TheApplication.moteur2d.achieved("#FF0000","#FFFF00")){
                    Toast.makeText(v.getContext(), "Game Fini!  le gagnant est : "+
                            TheApplication.moteur2d.gagnant("#FF0000","#FFFF00"), Toast.LENGTH_SHORT - 1).show();
                }

                if( TheApplication.moteur2d.canPut( new Boule("#FF0000", 10), new Coordonne(
                        Integer.parseInt(spinnerX.getSelectedItem().toString() ) ,
                        Integer.parseInt( spinnerY.getSelectedItem().toString() ),
                        Integer.parseInt( spinnerETAGE.getSelectedItem().toString())
                )) ) {

                    TheApplication.moteur2d.put(new Boule("#FFFF00", 10),
                            new Coordonne(
                                    Integer.parseInt(spinnerX.getSelectedItem().toString() ) ,
                                    Integer.parseInt( spinnerY.getSelectedItem().toString() ),
                                    Integer.parseInt( spinnerETAGE.getSelectedItem().toString())
                            )
                    );

                    Toast.makeText(v.getContext(), "Boule Deposer !  ", Toast.LENGTH_SHORT - 1).show();
                    int x=0;
                    int y=0;
                    int z = 0;

                    boolean bool = true;
                    while(bool){
                            x =  0 + (int)(Math.random() * ((3 -0 ) + 1));
                            y =  0 + (int)(Math.random() * ((3 -0 ) + 1));
                            z =  0 + (int)(Math.random() * ((3 -0 ) + 1));

                            if(TheApplication.moteur2d.canPut( new Boule("#FF0000", 10),  new Coordonne(x,y,z) ) ){
                                TheApplication.moteur2d.put( new Boule("#FF0000", 10),  new Coordonne(x,y,z) );
                                        bool =false;
                            }
                    }

                }
                else{
                    Toast.makeText(v.getContext(), "Action refusé !  ", Toast.LENGTH_SHORT - 1).show();
                }

                tostring.setText(TheApplication.moteur2d.toString());


            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT - 1).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }





}