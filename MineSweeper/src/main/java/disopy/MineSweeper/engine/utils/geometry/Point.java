package disopy.MineSweeper.engine.utils.geometry;

public class Point {
	private int x, y;

	public Point(int x, int y){
		set(x, y);
	}
	public Point(Point point){
		this(point.getX(), point.getY());
	}

	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public void set(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}
