package projet.pylos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	private int actionPoints;
	private List<Boule> balls;
	private String color;
	private int nombreDeBoules;
	public Player(int numberOfBalls, String color) {
		balls = new ArrayList<>();
		this.color = color;
		nombreDeBoules = numberOfBalls;

		for(int i=0; i<numberOfBalls; i++) {
			Boule ball = new Boule(color, 10);
			ball.setId(i);
			balls.add(ball);
		}
	}
	public String action() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	public void decrementNumberOfBalls(){
	    nombreDeBoules--;
    }
    public int getNombreDeBoules(){
	    return nombreDeBoules;
    }
	public int getActionPoints() {
		return actionPoints;
	}
	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}
	public List<Boule> getBalls() {
		return balls;
	}
	public void setBalls(List<Boule> balls) {
		this.balls = balls;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
