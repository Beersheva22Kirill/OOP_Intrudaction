package telran.shapes;

import java.util.Iterator;

public class SquareTriangle extends Square {

	private boolean isLeftDiagonal;
		
	public SquareTriangle(int width, boolean isLeftDiagonal) {
		super(width);
		this.isLeftDiagonal = isLeftDiagonal;
	}
		
	public String[] presentation(int offset) {
		int width = getWidth();
		String[] res = new String [width];
			String line = getLine(offset);
			res[width - 1] = line;
			if (isLeftDiagonal) {
				for (int i = width - 2; i > 0; i --) {
					res[i] = getLegtLeft(offset,i-1);
				}
				res[0] = " ".repeat(offset) + super.getSymbol();			
			} else {
				for (int i = 1; i < width - 1; i ++) {
					res[i] = getLegRight(offset,i);
				}
				res[0] = " ".repeat(offset + width - 1) + super.getSymbol();
			}
			
		return res;
	}

	private String getLegtLeft(int offset, int widht) {
		
		return " ".repeat(offset) + super.getSymbol() + " ".repeat(widht) + super.getSymbol();			
	}
	
	private String getLegRight(int offset, int width) {
		
		return " ".repeat(offset + getWidth()  - width - 1)  + super.getSymbol() + " ".repeat(width-1) +  super.getSymbol() ;			
	}
}
