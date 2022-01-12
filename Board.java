//Shashank Eeda
//Period 3
//Mr.Jan
package checkers;

public class Board {
	int rows;
	int cols;
	int pcnt;
	boolean killed;
	
	Box bx[][] = new Box[8][8];
	Piece p1[] = new Piece[12];
	Piece p2[] = new Piece[12];
	
	public boolean validBox(int x, int y) {
//		System.out.println("validBox ? values "+x+", "+y);
		if (bx[y][x] == null)
			return false;
		else
			return true;
	}
	
	public boolean validPlayer(char p, int x, int y) {
//		System.out.println("validPlayer ? values p="+p+", x="+x+", y="+y);

		if (bx[y][x] == null)
			return false;
//		System.out.println("p= "+p+", c = "+bx[y][x].getColor());
		if (bx[y][x].getColor() != p)
			return false;
		
		return true;
	}
	
	public boolean validDirection(char player, int x1, int y1, int x2, int y2) {
		Box tb;
		Piece p;
		tb = bx[y1][x1];
		p = tb.getPiece();
//		System.out.println("validDirection ? values p="+player+", x1="+x1+", y1="+y1+", x2="+x2+", y2="+y2);
				
		if (p.isKing() == true)
			return true;
	
		if (player == 'W') {
			if (y2 <= y1)
				return false;
		} else {
			if (y1 <= y2) {
				return false;
			}
		}
		return true;
	}

	public void resetKill() {
		killed = false;
	}
	
	public boolean canKill(char player, int x, int y) {
		Box tb;
		Piece p;
		System.out.println("canKill ? values x="+x+", y="+y);

		tb = bx[y][x];
		p = tb.getPiece();
		if (p == null)
			return false;
		
		if (p.getColor() == player)
			return false;
		
		if (p.getColor() == ' ')
			return false;
		if (p.getColor() == '.')
			return false;
		if (p.isalive() == false)
			return false;
		
		return true;
	}
	
	public boolean killPlayer(char player, int x, int y) {
		Box tb;
		Piece p;
		System.out.println("killplayer ? values x="+x+", y="+y);

		tb = bx[y][x];
		p = tb.getPiece();
		if (p == null)
			return false;
		
		if (p.getColor() == player)
			return false;
		
		if (p.getColor() == ' ')
			return false;
		if (p.getColor() == '.')
			return false;
		if (p.isalive() == false)
			return false;
		
		p.setColor('.');
		p.setDead();
		killed = true;
		return true;
	}
	
	public boolean isKing(int x, int y) {
		Box tb;
		Piece p;
		
		tb = bx[y][x];
		p = tb.getPiece();
		return p.isKing();
	}
	
	public boolean validMove(char player, int x1, int y1, int x2, int y2, boolean kill) {
		Box tb;
		Piece p;
		tb = bx[y1][x1];
		p = tb.getPiece();
		int dx, dy;
		System.out.println("validMove ? values pl="+player+", x1="+x1+", y1="+y1+", x2="+x2+", y2="+y2);

		if (player == 'W')
			dy = y2 - y1;
		else
			dy = y1 - y2;

		if (p.isKing()) {
			if (dy < 0)
				dy = dy * -1;
		}
		
		if (dy == 1)
			return true;
		
		dx = x2 - x1;
		if (dx < 0)
			dx = dx * -1;
		
		if ((dx <= 0) || (dy <= 0))
			return false;
		if ((dy > 2) || (dx > 2))
			return false;
		System.out.println("valid move dx="+dx+", dy="+dy);
		if (dy == 2) {
			if (y1 < y2)
				dy = y1 + 1;
			else
				dy = y1 - 1;
			if (x1 < x2)
				dx = x1 + 1;
			else
				dx = x1 - 1;
			if (kill == true) {
				if (killPlayer(player, dx, dy) == false)
					return false;
			} else {
				if (canKill(player, dx, dy) == false)
					return false;
				return true;
			}
		}
		if (player == 'W' && y2 == 7)
			p.setKing();
		if (player == 'B' && y2 == 0)
			p.setKing();
		
		return true;
	}

	public void swap_piece(int x1, int y1, int x2, int y2) {
		Box tb;
		Piece p;

		tb = bx[y1][x1];
		bx[y1][x1] = bx[y2][x2];
		bx[y2][x2] = tb;
		tb = bx[y2][x2];
		p = tb.getPiece();
		if (p.getColor() == 'W' && y2 == 7)
			p.setKing();
		if (p.getColor() == 'B' && y2 == 0)
			p.setKing();		
	}
	
	public boolean destEmpty(int x, int y) {
//		System.out.println("dst Empty? x="+x+", y="+y);
		if (bx[y][x].getColor() == ' ')
			return true;
		if (bx[y][x].getColor() == '.')
			return true;
		
		return false;
	}
	
	public boolean gameDone() {
		int i, x, y, wcnt, bcnt;

		bcnt = wcnt = 0;
		for (i = 0; i < pcnt/2; i++) {
			if (p1[i].isalive())
				wcnt = wcnt + 1;
			if (p2[i].isalive())
				bcnt = bcnt + 1;
		}
		if (wcnt == 0) {
			System.out.println("Black piece player Won!");
			return true;
		}
		if (bcnt == 0) {
			System.out.println("White piece player Won!");
			return true;
		}
		return false;
	}
	
	public void display() {
		int i, x, y, wcnt, bcnt;

		bcnt = wcnt = 0;
		for (i = 0; i < pcnt/2; i++) {
			if (p1[i].isalive())
				wcnt = wcnt + 1;
			if (p2[i].isalive())
				bcnt = bcnt + 1;
		}
	
		System.out.print("Checkers Game! White("+wcnt+") Black("+bcnt+")\n\n");
		for (y = 0; y < rows; y++) {
			if (y == 3)
				System.out.print(" Y "+y+"|");
			else
				System.out.print("   "+y+"|");
			for (x = 0; x < cols; x++) {
				if (bx[y][x] == null) {
					System.out.print(" - "); 
					continue;
				}
				bx[y][x].display();
			}
			System.out.println();
		}
		System.out.print("     ");
		for (x = 0; x < cols+1; x++) {
			System.out.print("---");
		}
		System.out.print("\n     ");

		for (y = 0; y < rows; y++) {
			System.out.print(" "+ y + " ");
		}

		System.out.print("<-X\n\n");
	}
	
	public void reset() {
		int x, y;
		int n;
		
		n = 0;
		for (y = 0; y < 3; y++) {
			for (x = 0; x < 8; x++) {
				if ((x+y) % 2 == 1) {
					bx[y][x].setPiece(p1[n]);
					n = n + 1;
				}
			}
		}
		
		n = 0;
		for (y = 5; y < 8; y++) {
			for (x = 0; x < 8; x++) {
				if ((x+y) % 2 == 1) {
					bx[y][x].setPiece(p2[n]);
					n = n + 1;
				}
			}
		}		
	}
	
	public Board(int tx, int ty, int tp)
	{
		int i, x, y;
		
		rows = tx;
		cols = ty;
		pcnt = tp;
		
		for (i = 0; i < pcnt/2; i++) {
			p1[i] = new Piece('W');
			p2[i] = new Piece('B');
		}
	
		for (y = 0; y < rows; y++) {
			for (x = 0; x < cols; x++) {
				if ((x+y) % 2 == 1) {
					bx[y][x] = new Box(y, x, null);
				} else {
					bx[y][x] = null;
				}
			}
		}
	}	
}
