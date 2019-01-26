package projet.pylos;
public class Coordonne {
	private int x;
	private int y;
	private int z;
	
	
	public Coordonne(int x,int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;}

	
	
	public void setX(int x) {
		this.x = x;}
	public void setY(int y) {
		this.y = y;}
	public void setZ(int z) {
		this.z = z;}
	
	
	public int getx() {
		return x;}
	public int gety() {
		return y;}
	public int getz() {
		return z;}
	
	public boolean containss(Coordonne c) {
		return this.x == c.getx() &&
				this.y == c.gety() &&
				this.z == c.getz();
	}
	
		
	

}
