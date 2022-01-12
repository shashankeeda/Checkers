//Shashank Eeda
//Period 3
//Mr.Jan
package checkers;

import java.util.Scanner;

public class Checkers {
	static char player = 'W';
	
	
	public static int validateMove(Board b, char p, int x1, int y1, int x2, int y2) {
		int v = 1;
		
		if ((x1 == x2) && (y1 == y2)) {
			System.out.println("source/destination can't be same. Invalid move");
			return 0;
		}
		
		if (b.validBox(x1, y1) == false) {
			System.out.println("Picked invalid piece");
			return 0;
		}
		
		if (b.validBox(x2, y2) == false) {
			System.out.println("Invalid Destination");
			if (player == 'W')
				player = 'B';
			else
				player = 'W';
			return 0;
		}
		
		if (b.validPlayer(p, x1, y1) == false) {
			System.out.println("Moving wrong piece. Your turn is lost");
			if (player == 'W')
				player = 'B';
			else
				player = 'W';
			return 0;
		}
	
		if (b.destEmpty(x2, y2) == false) {
			System.out.println("Destination not empty. Your turn is lost");
			if (player == 'W')
				player = 'B';
			else
				player = 'W';
			return 0;
		}
		
		if (b.validDirection(p, x1, y1, x2, y2) == false)  {
			System.out.println("Wrong Direction. Your turn is lost");
			if (player == 'W')
				player = 'B';
			else
				player = 'W';
			return 0;
		}	

		if (b.validMove(p, x1, y1, x2, y2, true) == false)  {
			System.out.println("Invalid Move. Your turn is lost");
			if (player == 'W')
				player = 'B';
			else
				player = 'W';
			return 0;
		}
	
//		System.out.println("Valid move\n\n\n");
		
		return 1;
	}
	
	public static boolean killAnother(Board b, char p, int x1, int y1, int x2, int y2) {
		int v = 1;
		
		if (x2 < 0 || x2 > 7)
			return false;
		if (y2 < 0 || y2 > 7)
			return false;
					
		if ((x1 == x2) && (y1 == y2)) {
			return false;
		}
		
		if (b.validBox(x1, y1) == false) {
			return false;
		}
		
		if (b.validBox(x2, y2) == false) {
			return false;
		}
		
		if (b.validPlayer(p, x1, y1) == false) {
			return false;
		}
	
		if (b.destEmpty(x2, y2) == false) {
			return false;
		}
		
		if (b.validDirection(p, x1, y1, x2, y2) == false)  {
			return false;
		}	

		if (b.validMove(p, x1, y1, x2, y2, false) == false)  {
			return false;
		}		
		System.out.println("p="+p+", x1="+x1+", y1="+y1+", x2="+x2+", y2="+y2);
		return true;
	}
	
	public static void move_piece(Board b, char p, int x1, int y1, int x2, int y2) {
		b.swap_piece(x1, y1, x2, y2);
		
		if (b.killed == true) {
			b.resetKill();
			if (p == 'W' || b.isKing(x2, y2)) {
				if (killAnother(b, p, x2, y2, x2+2, y2+2))
					return;
				if (killAnother(b, p, x2, y2, x2-2, y2+2))
					return;
			}
			if (p == 'B' || b.isKing(x2, y2)) {
				if (killAnother(b, p, x2, y2, x2+2, y2-2))
					return;
				if (killAnother(b, p, x2, y2, x2-2, y2-2))
					return;
			}
		}
		b.resetKill();
		if (player == 'W')
			player = 'B';
		else
			player = 'W';
	}
	
	public static void play_turn(Board b) {
		Scanner inp = new Scanner(System.in);
		int x1, y1, x2, y2, ret;
		
		
		System.out.print("Player '"+player+"', please select your piece(x y) : ");
		x1 = inp.nextInt();
		y1 = inp.nextInt();
		if (x1 < 0 || y1 < 0 || x1 > 7 || y1 > 7) {
			System.out.println("No piece at that location. Try again");
			return;
		}
		System.out.print("   Please select destination : ");
		x2 = inp.nextInt();
		y2 = inp.nextInt();
		if (x2 < 0 || y2 < 0 || x2 > 7 || y2 > 7) {
			System.out.println("No piece at that location. Try again");
			return;
		}
		for (int i=0;i<100;i++)
			System.out.println();
		ret = validateMove(b, player, x1, y1, x2, y2);
		if (ret == 0)
			return;
		move_piece(b, player, x1, y1, x2, y2);
	}
	
	public static void main(String[] args) {
		int i, j;
		int rows = 8;
		int cols = 8;
		int pieces = 24;
		int gameon = 1;
		
		Board b = new Board(rows, cols, pieces);
		b.reset();
		for (i=0;i<100;i++)
			System.out.println();
		while (gameon == 1) {
			b.display();
			play_turn(b);
			if (b.gameDone() == true) {
				b.display();
				gameon = 0;
			}
		}
	}
}
