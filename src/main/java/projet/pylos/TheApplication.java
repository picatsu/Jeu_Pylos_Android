package projet.pylos;

import android.app.Application;

import java.util.ArrayList;

public class TheApplication extends Application {

   static Model moteur;
   static Model moteur2d;
   int nb;
   public static Player player1;
   public  static Player player2;
   static Player currentPlayer;
   static ArrayList<Boule> listebouleplayer1;
    static ArrayList<Boule>  listebouleplayer2;

   public TheApplication() {
       moteur = new Model(4, 4, 4);
       player1 = new Player(15, "#FF0000");
       player2 = new Player(15, "#FFFF00");
       listebouleplayer1 = new ArrayList<>(15);
       listebouleplayer1 = new ArrayList<>(15);

       for (int i = 0; i < listebouleplayer1.size(); i++) {
           listebouleplayer1.add(new Boule("#FF0000", 10));
           listebouleplayer2.add(new Boule("#FFFF00", 10));

       }
   }
       public TheApplication(int nb){
           moteur2d = new Model(4, 4, 4);
       this.nb = nb;

           player1 = new Player(15, "#FF0000");
           player2 = new Player(15,"#FFFF00");
           listebouleplayer1 = new ArrayList<>(15);
           listebouleplayer1 = new ArrayList<>(15);

           for(int i=0; i< listebouleplayer1.size(); i++){
               listebouleplayer1.add(new Boule("#FF0000",10));
               listebouleplayer2.add(new Boule("#FFFF00",10));

           }




   }









}

