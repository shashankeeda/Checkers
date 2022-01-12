//Shashank Eeda
//Period 3
//Mr.Jan
package checkers;

public class Box {
	int x;
	int y;
	Piece p;
	
	public Box(int tx, int ty, Piece tp)
	{
		x = tx;
		y = ty;
		p = tp;
	}
	
	public void display() {
		if (p == null)
			System.out.print(" . ");
		else
			System.out.print(" "+p.getColor()+" ");
	}
	
	public void setPiece(Piece tp)
	{
		p = tp;
	}
	public Piece getPiece()
	{
		return p;
	}
	public boolean isValid() {
		if (p == null)
			return false;
		return true;
	}
	
	public char getColor() {
		if (p == null)
			return ' ';
//		System.out.println("getColor p="+p.getColor()+", x="+x+", y="+y);
		return p.getColor();
	}
}
