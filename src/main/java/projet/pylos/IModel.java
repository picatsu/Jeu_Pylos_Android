package projet.pylos;

public interface IModel {
	
			/* Largeur du cube � construire */
			int getlargeurG();
	        /* Hauteur du cube � construire */
			int gethauteurG();
	        
	        /* Profondeur du cube � construire */
			int getprofondeurG();

	   
	   

	        /* Donne true si la position pass�e en argument est valide
	           dans le cube � construire, qu'elle soit vide ou non */
			boolean valid(Coordonne pos);

	        
	        
	        
	        
	        /* Donne true si la position donn�e en argument est une position
	           valide du cube � construire non couverte par une boule.
	           On dit alors que la position est libre. */
			boolean free(Coordonne pos);

	        
	        
	        
	        
	        
	        /* Donne true si le cube � construire est achev�
	           et false sinon. */
			boolean achieved(String couleur1, String couleur2);

	       
	        
	        
	        

	        /* Donne true si la boule pass�e en argument peut �tre plac�e �
	           la position donn�e en second argument et false sinon.
	           
	           Une pi�ce peut �tre plac�e si et seulement si:
	            a) Le joueur n'a pas encore jouer son tour
	            b) la position est valide;
	            c) toutes les positions que recouvrira la pi�ce sont libres. 
	            d) s'il va pos� puis recuperer des boules */
			boolean canPut(Boule id, Coordonne pos);
	        
	        
	        
	        
	        

	        /* Rajoute sur le cube a construire la boule qu'on vient de poser a la position donnee
	         en parametre  */
			void put(Boule id, Coordonne pos) throws Exception;

	        
	        
	        
	        
	        /* Retire la pi�ce donn�e en argument du cube en construction.
	           Ne fait rien si la pi�ce n'est pas plac�e. ! Une seule chance a la fois dans un 
	           delai precis  */
			void remove(Boule id);

	        
	        
	        
	        
	        /* Donne la liste des Boule plac�es associ�es, chacune, � la liste
	           des positions qu'elles couvrent dans un tableau 3D  
	        public Pair<Boule, Coordonne>[][][] Bouleplacer();*/


	    }



