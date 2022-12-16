package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import telran.shapes.*;

class ShapeTests {

	@Test
	@Disabled
	void rectanglTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}
	
	@Test
	@Disabled
	void squareTest() {
		Square square = new Square(8);
		displayStrings(square.presentation(0));
		square.setSymbol("#");
		displayStrings(square.presentation(0));
	}
		
	
	@Test
	@Disabled
	void squareTriangleTest() {
		SquareTriangle triangle = new SquareTriangle(8, false);
		displayStrings(triangle.presentation(5));
	}
	
	@Test
	@Disabled
	void squareLeftTriangleTest() {
		SquareLeftTriangle lTriangle = new SquareLeftTriangle(10);
		displayStrings(lTriangle.presentation(5));
	}
	
	@Test
	@Disabled
	void squareRightTriangleTest() {
		SquareRightTriangle rTriangle = new SquareRightTriangle(10);
		displayStrings(rTriangle.presentation(5));
	}
	
	@Test
	void canvasTest() {
		Shape rectangle = new Rectangle(20, 5);
		Shape triangleLeft = new SquareLeftTriangle(8);
		Shape triangleRight = new SquareRightTriangle(3);
		Canvas canvas = new Canvas(3, 3, new Shape[] {rectangle, triangleLeft});
		//canvas.setDirection("columns");
		Canvas canvas2 = new Canvas (15, 10, new Shape[] {canvas, rectangle, triangleLeft,triangleRight});
		canvas2.setDirection("columns");
		displayStrings(canvas2.presentation(4));
				
	}
	
	
	@Test
	private void displayStrings(String[] strings) {
		for(String str:strings) {
			System.out.println(str);
		}
	}
	
	 


}
