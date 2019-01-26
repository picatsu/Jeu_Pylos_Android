package projet.pylos;

import java.util.ArrayList;
public class Model implements IModel {
	
	private int largeurG;// largeur grille, donc les X, qui commence en haut a gauche et avance vers la droite
	private int hauteurG;//hauteur == Y, commence en haut a 0 et descend vers le bras de facon croissante
	private int profondeurG;//a priori il doit etre == a X, mais on sait jamais. ou le numero d'etage
	
	
	// ici on aura une grille compos� de pusieurs arraylist qui eux meme sont compos� d'un tableau a deux dimensions.
	// imaginez une boite de fromage la vache qui rie, mais au lieu qu'elle soit circulaire, y aura plusieurs etages carr� en commencant de celui du bas jusqu'a celui tout en haut quisera une seule boule
	
	private ArrayList<Etage> grille;
	
	public Model( int largeurG, int hauteurG, int profondeurG, ArrayList<Etage> grille) {
		this.largeurG = largeurG;
		this.hauteurG = hauteurG;
		this.profondeurG = profondeurG;
		this.grille = grille; 
		// Creation du cube a 3 demensions rempli de cases, dans chaque case y a une boule et un coordonne de la case (x,y,z)
//		this.RemplirEtages(); 
		// on limite les cases qui doivent etre transparentes = remplir avec boule transparente
		
		//System.out.println("  JE PASSE PAR LE CONSTRUCTEUR ");
	}
	
	public Model( int largeurG, int hauteurG, int profondeurG) {
		this.largeurG = largeurG;
		this.hauteurG = hauteurG;
		this.profondeurG = profondeurG;
		grille = new ArrayList<Etage>(hauteurG);
		// Creation du cube a 3 demensions rempli de cases, dans chaque case y a une boule et un coordonne de la case (x,y,z)
		this.RemplirEtages(); 
		// on limite les cases qui doivent etre transparentes = remplir avec boule transparente
		
		//System.out.println("  JE PASSE PAR LE CONSTRUCTEUR ");
	}
	
	/// LES GETTEURS 
	public int getlargeurG(){
		return largeurG ;
	}
	public int gethauteurG(){
		return hauteurG ;
	}
	public int getprofondeurG(){
		return  profondeurG;
	}
	
	
	/// LES SETTEURS
	public void setlargeurG(int l){
		this.largeurG = l ;
	}
	public void sethauteurG(int h){
		this.hauteurG  = h ;
	}
	public void setprofondeurG(int p){
		this.profondeurG =  p ;
	}
	
	
	
	
	
	public void RemplirEtages() {
		//REMPLIR LES CASES VIDES1
		//System.out.println("  JE PASSE PAR LE RemplirEtages de model ");
		for(int y =0; y < hauteurG; y++) {
			Etage a = new Etage(profondeurG- y, profondeurG-y, y);// ON DONNE LA TAILLE DE LETAGE (X.Y) ET LE NUMERO DE L4ETAGE QUI EST LA VARIABLE LOCALE Y
 			grille.add(a);
		}
		
	}
		 
	public boolean valid(Coordonne pos){// CASE VALIDE - DANS LA GRILLE
		//System.out.println("  JE PASSE PAR LE valid de model  ");
		return pos.getx() >= 0 && pos.gety() >= 0 && pos.getz() >= 0 &&
				pos.getx() < largeurG && pos.gety() < hauteurG &&
				pos.getz() < profondeurG;
	}
	
	
	
	
	 public boolean free(Coordonne pos){// Case LIBRE
		// System.out.println("  JE PASSE PAR LE free de model ");
		 if ( !valid(pos))
			 return false;
		 
		 for(Etage a : grille) {// ON PARCOUR ETAGE PAR ETAGE
			// SI ON EST DANS LE BON ETAGE ET QUE L'ETAGE CONTIENT DEJA CETTE BOULE == NE CONTIENT PAS UNE BOULE DIFFERENTE DE BOULE.NONE
				 if( a.getNumero() ==pos.getz()  && a.containss(pos) ) 
					 return false;
		 }
		 return true;
	 }
	 
	 
	 public boolean canPut(Boule id, Coordonne pos){
		 //System.out.println("  JE PASSE PAR canPut de model  ");
		 if(id.isPlaced()) return false;
		 if(!free(pos))
			 return false;
		 
		 
		 Etage avant = grille.get(0);
		 for(Etage a: grille) {
			 if(a.getNumero() == pos.getz() && !a.containss(pos)) {// SI ON EST DANS LE BON ETAGE ET QUE LA POSE EST LIBRE
				 if(a.getNumero() == 0)
					 return true;
				 //might have to remove the assigning of boolean values
				 if(avant.hasPillar(pos)) {
						id.setHasPillar(true);
					 	return true;
				 }
					 //very messy way to go about it
					 //but id isnt pos

				 
			 }
			avant = a;
		 }
		 //System.out.println("  JE NE PEUX PAS DEPOSER MA BOULE ICI "+avant.getNumero()+avant.getX()+avant.getY());
		 return false;
		 
	 }
	 
	 
	 public void put(Boule id, Coordonne pos){
		 // GERER LES CONDITIONS DU JEU 
		 //System.out.println("  JE PASSE PAR put DE MODEL ");
		 if(!canPut(id,pos)) {
			 put(id,rudimentaryAI());
			 return;
		 }
			
		 
//		 for(Etage a: grille) {
			 //System.out.println("  JE PASSE PAR Remove de Model et numero etage  =  "+a.getNumero());
//			 if(a.getNumero() == pos.getz()) {// SA VEUT DIRE QU'ON EST DANS LE BON ETAGE
//				 if(!canPut(id,pos))
//					 return;
				 if(pos.getz() == 0)   grille.get(pos.getz()).put(id, pos);
				 
				 else {
					 grille.get(pos.getz()).put(id, pos);
					 grille.get(pos.getz()-1).changePillarState(true, id.getCoordonne());
				 }
				 return;
				 
//			 }
//		 }
		 
		 
	 }
	 	 	 
	 public boolean achieved(String couleur1, String couleur2){ // JEU FINI 
		 //System.out.println("  JE PASSE PAR LE achieved de model  ");
		 if(grille.get(profondeurG -1 ).EtageRempli()) {//si l'etage tout en haut est rempli, on return true;
			 System.out.println(" LE GAGNANT EST :"+this.gagnant(couleur1,couleur2));
			 return true;
			 }
		 
		 for(Etage a : grille) 
			 if(a.EtageRempli() == false)// si un des etages n'est pas totalement rempli on sort
				 return false;
		 //System.out.println(" LE GAGNANT EST :"+this.gagnant(couleur1,couleur2));
         return true; 
	 }
	 
	 
	 
	 public String gagnant(String couleur1, String couleur2) {// ON CALCULE LES BORD DE L ETAGE
		 
		int cpta=0;
		int cptb=0;
		
		for(Etage cpt: grille) {
			if(cpt.gagnant(couleur1, couleur2) == couleur1)
				cpta++;
			else
				cptb++;
		}
		 
		if(cpta == cptb)
			return " EGALITE " ;
		
		
		if(cpta>cptb)
			return couleur1;
		
		return couleur2;
	 }
	 //problem de remove de balle pillier qui marche apres deux fois.
	public void remove(Boule id){
		Etage etage = grille.get(id.getCoordonne().getz());
//		if(etage.getNumero() == id.getCoordonne().getz()) return;
		if(etage.remove(id) == 2) {
			Etage avant = grille.get(0);
			for(Etage a: grille) {
					if(avant.getNumero()>id.getCoordonne().getz()) break;
					 if(a.getNumero() == 0)
						 continue;
					 if(avant.hasPillar(id.getCoordonne())) {
						 etage.removePillar(id);
					 	 avant.changePillarState(false, id.getCoordonne());
					 	 for(Boule b : etage.getGoodNeighbors(id.getCoordonne())) {
					 		 if(b.equals(id)) continue;
					 		 avant.changePillarState(true, b.getCoordonne());
					 	 }
					 }
						 
					 	 
				avant = a;
			 }
		}	
	}
	 

	 public void reset(){
		 this.grille.clear();
	 }
	
	 
	 
	 
	 public boolean testregle(Boule id, Coordonne pos, Etage a) {
		 // ICI ON VA VERIFIE SI SOUS LA BOULE Y A PAS DES BOULES DE LA MEME COULEUR
		 	Etage avant = grille.get(0);
		 	
		 	for(Etage b: grille) {
		 		if ( b == a) {
		 			// APPEL A ETAGE 
		 			return avant.testregle2(pos, id);
		 		}
		 		avant = a;
			
		 	}
		 
		 
		 return false;
	 }
	 
	 public boolean oneSquareColorPillar(Boule id) {
		 if(id.getCoordonne().getz() == 0) return false;
		 Etage avant = grille.get(id.getCoordonne().getz()-1);
		 Etage suivant = grille.get(id.getCoordonne().getz());
		 for(Coordonne b : suivant.allLevelCoordonne()) {
			 if(sameColor(avant.getPillars(b).snd())) return true;
			
		 }
		 return false;
	 }
	 private boolean validColor(Boule[] table) {
		 for(Boule b : table) {
			 if(b.getcouleur().equals("none")) return false;
		 }
		 return true;
	 }
	 private boolean sameColor(Boule[] table) {
		 if(!validColor(table)) return false;
		 String color = table[0].getcouleur();
		 for(Boule b : table) {
			 if(!b.getcouleur().equals(color)) return false;
		 }
		 return true;
	 }
	 //checks if we can deplace a ball to a superior level
	 //need to add a condition to verify that the superior position has pillars
	 public Boolean canBeDeplacedTo(Boule id, Coordonne pos) {
			if((id != Boule.none) || (pos.gety() != 0 
					&& pos.gety() > id.getCoordonne().gety())) return !id.isPillar();
			return false;
	 }
	public Coordonne rudimentaryAI() {
		Coordonne pos = null;
		for(Etage etage : grille) {
			if(etage.freePosition() == null) continue;
			else {
				pos = etage.freePosition();
				break;
			}

		}
		System.out.println(pos);
		return pos;
	}
	 public String toString(){
		 //System.out.println("  JE PASSE PAR toString de Model ");
		 String a ="P : Player  M : Machine \n y = Horizontal x = Vertical \n ";
		 
		 for(Etage b : grille) {
			 a = a + b.toString();
			 a+="\n";
			 a+="\n";
			 a+="\n";
			 a+="\n";
		 }
		 return a;
	 }
	 
	public Boule getBouleParPosParEtage(Coordonne pos) {
		return grille.get(pos.getz()).getBouleByPos(pos);
	}
	 
	public ArrayList<Etage> getGrille() {
		return grille;
	}

	public void setGrille(ArrayList<Etage> grille) {
		this.grille = grille;
	}
	
	

}
