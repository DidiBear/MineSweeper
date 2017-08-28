package disopy.MineSweeper.engine.utils.geometry;

public class Quad {
	
	private Point position;
	private int width, heigth;
	
	public Quad(Point position, int width, int heigth){
		this.position = position;
		this.width = width;
		this.heigth = heigth;
	}

	public Point getPosition(){
		return position;
	}
	public void setPosition(Point position){
		this.position = position;
	}
	public int getWidth(){
		return width;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public int getHeigth(){
		return heigth;
	}
	public void setHeigth(int heigth){
		this.heigth = heigth;
	}	
}
