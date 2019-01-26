package projet.pylos;
public class Boule {
	private Coordonne a=new Coordonne(0,3,0);
	private String couleur;
	private static int cpt  =0;
	private int id;
	private int rayon;
	private boolean isPillar;
	private boolean hasPillar;
	private boolean isPlaced;
	
	public final static Boule none = new Boule("none",0);

	
	public Boule(String couleur,int rayon) {
		this.couleur = couleur;
		this.rayon=rayon;
		id = cpt;
		cpt ++;
	}
	
	public Boule(String couleur, Coordonne a, int rayon) {
		this(couleur,rayon);
		this.a = a;
	}
	
	public String getcouleur() {
		return couleur;
	}
	
	public Coordonne getCoordonne() {
		return a;
	}
	
	@Override
	public boolean equals(Object obj) {
		 if (obj == null) {
	            return false;
	        }
		 final Boule boule = (Boule) obj;
		return this.couleur.equals(boule.getcouleur())
				&& this.getCoordonne() == boule.getCoordonne();

	}
	public void setCoordonne( Coordonne a) {
		this.a = a;
	}
	public int getrayon() {
		return rayon;
	}
	
	public void setrayon(int a) {
		this.rayon=a;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isPillar() {
		return isPillar;
	}

	public void setPillar(boolean isPillar) {
		this.isPillar = isPillar;
	}

	public boolean isHasPillar() {
		return hasPillar;
	}

	public void setHasPillar(boolean hasPillar) {
		this.hasPillar = hasPillar;
	}

	public boolean isPlaced() {
		return isPlaced;
	}

	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}
	
	
}
