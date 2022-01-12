//Shashank Eeda
//Period 3
//Mr.Jan
package checkers;

public class Piece {
	char color;
	boolean alive;
	boolean king;
	
	public Piece(char c) {
		color = c;
		alive = true;
		king = false;
	}
	
	public void setColor(char c) {
		color = c;
	}
	public char getColor() {
		return color;
	}
	public boolean isKing() {
		return king;
	}
	public boolean isalive() {
		return alive;
	}
	public void setKing() {
		king = true;
	}
	public void setDead() {
		alive = false;
	}
}
