package projet.pylos;
import java.util.ArrayList;
import java.util.List;

public class Etage {
	
	private int numero;
	private static int cpt=0;
	private int x;
	private int y;
	private Boule[][] tableau;
	
	public Etage(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.numero = num;//Comme sa on ne cr�e pas bcp d'etage que necessaire ( pour les test)
		cpt++;
		tableau = new Boule[x][y];
		
		this.remplir();
		//System.out.println("  JE PASSE PAR constructeur de Etage ");

	}
	
	public void remplir() {
		//System.out.println("  JE PASSE PAR remplir de Etage ");

		for(int i=0; i < x ; i++) {
			for(int j=0; j < y ; j++) {
				//might become a problem
				tableau[i][j] = Boule.none;
				
			}
		}
	}
	
	
	public void put(Boule b, Coordonne c) {// cette methode va parcourir un tableau 2D et deposer les boules a la case souhaite
		//System.out.println("  JE PASSE PAR put de Etage x : "+c.getx()+" y:"+c.gety()+" id etage: "+this.getNumero());
		
		for(int i=0; i < x; i++) {
			for(int j=0; j < y; j++) {
				if(c.getx() == i && c.gety() == j) {
					this.tableau[i][j] = b;
					b.setCoordonne(c);
					b.setPlaced(true);
					return;
				}
			}
		}
	}
	public boolean support(Coordonne pos) {
			if(cpt == this.getNumero() && !this.EtageRempli() )// SI ON EST AU SOMMET
				return false;
			
			if(pos.getx() == 0 && pos.gety() == 0 )// PAS SOMMET  A DROITE
				return   
						tableau[pos.getx()+1][pos.gety()].getcouleur() != "none" &&	  // CASE A DROITE DE LA POS
						tableau[pos.getx()][pos.gety() + 1].getcouleur() != "none";   // CASE EN BAS DE LA POS;  */
			
			if(pos.getx() == 0)// CAS A COTE DU MUR DE DROITE => X = 0 // PAS DE X - 1 SINON OUT OF RANGE
				return  
						tableau[pos.getx()][pos.gety() -1].getcouleur() != "none" &&
						tableau[pos.getx()+1][pos.gety()].getcouleur() != "none" &&	  // CASE A DROITE DE LA POS
				// tableau[pos.getx() - 1][pos.gety()].getcouleur() != "none" && // CASE A GAUCHE DE LA POS
						tableau[pos.getx()][pos.gety() + 1].getcouleur() != "none";   // CASE EN BAS DE LA POS;  */
			
			
			if(pos.getx() == this.getX()) // CAS A COTE DU MUR DE GAUCHE=> X = XMAX // PAS DE X + 1 
				return 
						tableau[pos.getx()][pos.gety() -1].getcouleur() != "none" &&   // CASE EN HAUT DE LA POS
				//tableau[pos.getx()+1][pos.gety()].getcouleur() != "none" &&	  // CASE A DROITE DE LA POS
						tableau[pos.getx() - 1][pos.gety()].getcouleur() != "none" && // CASE A GAUCHE DE LA POS
						tableau[pos.getx()][pos.gety() + 1].getcouleur() != "none";   // CASE EN BAS DE LA POS;  */
			
			
			if(pos.gety() == 0)	// CAS A COTE DU MUR DE HAUT  Y = 0 // PAS DE Y - 1 
				return //tableau[pos.getx()][pos.gety() -1].getcouleur() != "none" &&   // CASE EN HAUT DE LA POS
						tableau[pos.getx()+1][pos.gety()].getcouleur() != "none" &&	  // CASE A DROITE DE LA POS
						tableau[pos.getx() - 1][pos.gety()].getcouleur() != "none" && // CASE A GAUCHE DE LA POS
						tableau[pos.getx()][pos.gety() + 1].getcouleur() != "none";   // CASE EN BAS DE LA POS;  */
			
			
			if(pos.gety() == this.getY())// CAS A COTE DU MU DU BAS  Y = Ymax ==== PAS DE Y +1 
				return tableau[pos.getx()][pos.gety() -1].getcouleur() != "none" &&   // CASE EN HAUT DE LA POS
						tableau[pos.getx()+1][pos.gety()].getcouleur() != "none" &&	  // CASE A DROITE DE LA POS
						tableau[pos.getx() - 1][pos.gety()].getcouleur() != "none" && // CASE A GAUCHE DE LA POS
						tableau[pos.getx()][pos.gety() + 1].getcouleur() != "none";   // CASE EN BAS DE LA POS;  */
			
			
			
			return tableau[pos.getx()][pos.gety() -1].getcouleur() != "none" &&   // CASE EN HAUT DE LA POS
					tableau[pos.getx()+1][pos.gety()].getcouleur() != "none" &&	  // CASE A DROITE DE LA POS
					tableau[pos.getx() - 1][pos.gety()].getcouleur() != "none" && // CASE A GAUCHE DE LA POS
					tableau[pos.getx()][pos.gety() + 1].getcouleur() != "none";   // CASE EN BAS DE LA POS;  */
					
				
		
		
	}
	
	
	
	public boolean testregle2(Coordonne pos, Boule bol) {
		// ICI ON VA VERIFIE QUIL Y A �PES DE BOULES SOUS LES CASE EN DESSOUS
		
		if(pos.getx() == 0 && pos.gety() == 0 )// PAS SOMMET  A DROITE
			return   
					tableau[pos.getx()+1][pos.gety()].getcouleur() == bol.getcouleur() &&	  // CASE A DROITE DE LA POS
					tableau[pos.getx()][pos.gety() + 1].getcouleur() == bol.getcouleur();   // CASE EN BAS DE LA POS;  */
		
		if(pos.getx() == 0)// CAS A COTE DU MUR DE DROITE => X = 0 // PAS DE X - 1 SINON OUT OF RANGE
			return  
					tableau[pos.getx()][pos.gety() -1].getcouleur() == bol.getcouleur() &&
					tableau[pos.getx()+1][pos.gety()].getcouleur() == bol.getcouleur() &&	  // CASE A DROITE DE LA POS
			// tableau[pos.getx() - 1][pos.gety()].getcouleur() != "none" && // CASE A GAUCHE DE LA POS
					tableau[pos.getx()][pos.gety() + 1].getcouleur() == bol.getcouleur();   // CASE EN BAS DE LA POS;  */
		
		
		if(pos.getx() == this.getX()) // CAS A COTE DU MUR DE GAUCHE=> X = XMAX // PAS DE X + 1 
			return 
					tableau[pos.getx()][pos.gety() -1].getcouleur() == bol.getcouleur() &&   // CASE EN HAUT DE LA POS
			//tableau[pos.getx()+1][pos.gety()].getcouleur() != "none" &&	  // CASE A DROITE DE LA POS
					tableau[pos.getx() - 1][pos.gety()].getcouleur() == bol.getcouleur() && // CASE A GAUCHE DE LA POS
					tableau[pos.getx()][pos.gety() + 1].getcouleur() == bol.getcouleur();   // CASE EN BAS DE LA POS;  */
		
		
		if(pos.gety() == 0)	// CAS A COTE DU MUR DE HAUT  Y = 0 // PAS DE Y - 1 
			return //tableau[pos.getx()][pos.gety() -1].getcouleur() != "none" &&   // CASE EN HAUT DE LA POS
					tableau[pos.getx()+1][pos.gety()].getcouleur() == bol.getcouleur() &&	  // CASE A DROITE DE LA POS
					tableau[pos.getx() - 1][pos.gety()].getcouleur() == bol.getcouleur() && // CASE A GAUCHE DE LA POS
					tableau[pos.getx()][pos.gety() + 1].getcouleur() == bol.getcouleur();   // CASE EN BAS DE LA POS;  */
		
		
		if(pos.gety() == this.getY())// CAS A COTE DU MU DU BAS  Y = Ymax ==== PAS DE Y +1 
			return tableau[pos.getx()][pos.gety() -1].getcouleur() == bol.getcouleur() &&   // CASE EN HAUT DE LA POS
					tableau[pos.getx()+1][pos.gety()].getcouleur() == bol.getcouleur() &&	  // CASE A DROITE DE LA POS
					tableau[pos.getx() - 1][pos.gety()].getcouleur() == bol.getcouleur() && // CASE A GAUCHE DE LA POS
					tableau[pos.getx()][pos.gety() + 1].getcouleur() == bol.getcouleur();   // CASE EN BAS DE LA POS;  */
		
		
		
		return tableau[pos.getx()][pos.gety() -1].getcouleur() == bol.getcouleur() &&   // CASE EN HAUT DE LA POS
				tableau[pos.getx()+1][pos.gety()].getcouleur() == bol.getcouleur() &&	  // CASE A DROITE DE LA POS
				tableau[pos.getx() - 1][pos.gety()].getcouleur() == bol.getcouleur() && // CASE A GAUCHE DE LA POS
				tableau[pos.getx()][pos.gety() + 1].getcouleur() == bol.getcouleur();   // CASE EN BAS DE LA POS;  */
		

		
	}
	
	
	/*
	public boolean containss(Boule a, Coordonne c) {//RETOURNE TRUE SI LA BOULE EST DANS LA CASE DONNE 
		System.out.println("  JE PASSE PAR containss1 de Etage ");
		for(int i=0; i < x; i++) {
			for(int j=0; j < y; j++) {
				if (tableau[i][j].getcouleur() == a.getcouleur() && tableau[i][j].getId() == a.getId() && i == c.getx() && j == c.gety()) {
					return true;
				}
			}
		}
		return false;
	}
	
	*/
	
	public boolean containss(Coordonne c) {
		// SI LA BOULE EST NULL ET QUE LES COORDONNE DONNE EN PARAMETRE SONT LES MEMES QUE LA CASE ALORS LA CASE EST VIDE DONC ON RETOURNE TRUE
		
		//System.out.println("  JE PASSE PAR containss de Etage ");
		for(int i=0; i < x; i++) {
			for(int j=0; j < y; j++) {
				if (tableau[i][j] != Boule.none && i == c.getx() && j == c.gety() ) {
					return true;
				}
			}
		}
		return false;
	}

	
	
	
	//method that removes a given id ball
	//works on 2 conditions and gives 2 different results
	//the ball shouldnt be a pillar to a ball in a superior level
	//if the ball has pillars, we switch their state to false, then
	//switch the state of the ones used elsewhere to true
	public int remove(Boule id){
		//weird  = Boule.none, might have to change it later
		if(id.isPillar()) return 1;
		if(id.isHasPillar()) return 2;
		tableau[id.getCoordonne().getx()][id.getCoordonne().gety()].setPlaced(false);
		tableau[id.getCoordonne().getx()][id.getCoordonne().gety()]= Boule.none;
		
		return 3;
		
	}
	//mightve been better to work with enums here
	public boolean removePillar(Boule id){
		//System.out.println("  JE PASSE PAR remove de Etage ");
		if(id.isPillar()) return false;
//		System.out.println("ball is not a pillar");
		if(id.isHasPillar()) {
//			System.out.println("ball is a pillar");
			tableau[id.getCoordonne().getx()][id.getCoordonne().gety()].setPlaced(false);
			tableau[id.getCoordonne().getx()][id.getCoordonne().gety()]= Boule.none;
//			System.out.println("ball deleted");
			id.setHasPillar(false);
			changePillarState(false, id.getCoordonne());			

		}
		return true;
	}

	public Boule[][] getTab(){
		return tableau;
	}
	
	
	
	
	
	
	public boolean EtageRempli() {// verifie si tout l'etage est rempli
		//System.out.println("  JE PASSE PAR rempli de Etage ");
		if(tableau.length == 0)
			return false;
		for(int i=0; i < x; i++) 
			for(int j=0; j < y; j++) 
				if(tableau[i][j] == Boule.none) 
					return false;
				
			
		
		return true;
	}
	
	
	
	
	public String gagnant(String couleur1, String couleur2) {
		if(this.getNumero() == Etage.cpt)
			return tableau[0][0].getcouleur();
		int cpta=0;
		int cptb=0;
		for(int i=0; i < x; i++) {
			for(int j=0; j < y; j++) {
				
				if(tableau[i][j].getcouleur() == couleur1 && ( i == 0 || i == x || j == 0 || j == y))
					cpta++;
				if(tableau[i][j].getcouleur() == couleur2 && ( i == 0 || i == x || j == 0 || j == y))
					cptb++;
				else
					return " PROBLEME COULEUR 3";
				
			}
		}
		
		if(cpta>cptb)
			return couleur1;
		
		return couleur2;
	}
	
	

	public String toString() {
		//System.out.println("  JE PASSE PAR toString de Etage ");
		String a="";
		
		for(int i=0; i < x; i++) {
			
			for(int j=0; j < y; j++) {
				
				if(tableau[i][j] == Boule.none ) {
					a+=" * ";
				}
				if(tableau[i][j].getcouleur() == "#FF0000" ) {
					a+=" M ";
				}
				if(tableau[i][j].getcouleur() == "#FFFF00" ) {
					a+=" P ";
				}

			}
			a+="\n";
			
			}
	return a+"\tetage num :"+this.getNumero();
	}
	
	
	
	public boolean hasPillar(Coordonne boule) {
		if(cpt == this.getNumero() && !this.EtageRempli())// SI ON EST AU SOMMET
			return false;
		if(getPillars(boule).fst() == false)
			return false;
		for(Boule b : getPillars(boule).snd()) {
			if (b.getcouleur().equals("none")) return false;
		}
		return true;
		
	}
	//methode qui retourne les boules en dessous
	public Pair<Boolean, Boule[]> getPillars(Coordonne boule) {

		Boule[] pillars = new Boule[4];
		if(boule.getx() +2  > this.x || boule.gety() +2 > this.y || this.canPutRemastered(boule) == false)
			return new Pair(false,pillars);

		//System.out.print(" lolo"+ boule.getx() + "zkfhaeujfhé"+ boule.gety());

		Boule b1 = tableau[boule.getx()][boule.gety()];
		Boule b2 = tableau[boule.getx()+1][boule.gety()];
		Boule b3 = tableau[boule.getx()][boule.gety()+1];
		Boule b4 = tableau[boule.getx()+1][boule.gety()+1];
		pillars[0] =b1;
		pillars[1] =b2;
		pillars[2] =b3;
		pillars[3] =b4;


		return new Pair(true,pillars);
	}
	//change letat des pilliers
	public void changePillarState(boolean state, Coordonne boule) {
		if(getPillars(boule).fst() == false)
			return;
		for(Boule b : getPillars(boule).snd()) {
			b.setPillar(state);
		}
	}
	public void refreshPillarState(boolean state, Coordonne boule) {
		for(Boule b : getPillars(boule).snd()) {
			if(b.getCoordonne().equals(boule))
			b.setPillar(state);
		}
	}
	public List<Boule> getGoodNeighbors(Coordonne boule) {
		List<Boule> goodNeighbors = new ArrayList<>();
		for(int i=0; i < x; i++) 
			for(int j=0; j < y; j++) 
				if(!(tableau[i][j].getcouleur().equals("none"))) goodNeighbors.add(tableau[i][j]);
					
		return goodNeighbors;
	}
	//modifier la méthode pour être compatible a un board de n'importe quelle taille
	
	 public boolean canPutRemastered(Coordonne coordinate) {
		System.out.print("eafhaefh"+coordinate.toString());
		 return tableau[coordinate.getx()][coordinate.gety()] != Boule.none;
	 }
	 public List<Coordonne> allLevelCoordonne(){
		 List<Coordonne> list = new ArrayList<>();
		 for(int i=0; i < x; i++) 
				for(int j=0; j < y; j++) 
					list.add(new Coordonne(i,j,this.getNumero()));
		 return list;
	 }
	 public List<Boule> allLevelBalls(){
		 List<Boule> list = new ArrayList<>();
		 for(int i=0; i < x; i++) 
				for(int j=0; j < y; j++) 
					if(!tableau[i][j].getcouleur().equals("none"))
					list.add(tableau[i][j]);
		 return list;
	 }
	public Coordonne freePosition() {
		for(int i=0; i < x; i++)
			for(int j=0; j < y; j++)
				if(tableau[i][j]== Boule.none) {
//						System.out.println(i + " " + j);
					return new Coordonne(i, j, this.getNumero());
				}

		return null;
	}
	 
	public Boule getBouleByPos(Coordonne pos) {
		return tableau[pos.getx()][pos.gety()];
	}
	 
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
	
	

}
