package telran.shapes;

import org.hamcrest.core.IsInstanceOf;

public class Canvas extends Shape {
	
	private Shape[] shapes;
	private String direction = "row";
	private int margin = 1;
	
	
	public Canvas ( int width, int height, Shape[] shapes) {
		super(width, height);	
		this.shapes = shapes;
	}

	@Override
	public String[] presentation(int offset) {
		int countFigure = shapes.length;
		int sizeCanvas = 0;
		String[] figure;
		
		for (Shape sh : shapes) {
			if (sh instanceof Canvas) {
				((Canvas)sh).setDirection(this.direction); // downcasting Shape to Canvas
			}
		}
		
		
		if (direction == "row") {
			sizeCanvas = this.getHeight();			
		} else if (direction == "columns") {
			for(int i = 0; i < countFigure; i++) {
				figure = shapes[i].presentation(offset); // polymorphism Shapes
				for (int j = 0; j < figure.length; j++) {
					sizeCanvas ++;
				}
				sizeCanvas += margin;
			}
		}
		
		String[] res = new String[sizeCanvas];	
		
		if (direction == "row") {
			shapes[0].setHeight(this.getHeight());
			figure = shapes[0].presentation(offset);
			for(int j = 0; j < figure.length; j++) {
				res[j] = figure[j];
			}
			for(int i = 1; i < countFigure; i++) {	
				shapes[i].setHeight(getHeight());
				figure = shapes[i].presentation(margin);
				for(int j = 0; j < figure.length; j++) {
					res[j] += figure[j];
				}
			}
		}
		else if (direction == "columns") {
			int counter = 0;
			for(int i = 0; i < countFigure; i++) {
				//shapes[i].setWidth(this.getWidth());
				figure = shapes[i].presentation(offset);
				for(int j = 0; j < figure.length;j++) {
					res[counter] = figure[j];
					counter++;
				}	
				for(int m = 0; m < margin;m++) {
					res[counter] = " ";
					counter++;
				}
			}
			
		}
		return res;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}
	


}
