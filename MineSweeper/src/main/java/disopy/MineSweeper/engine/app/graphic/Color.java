package disopy.MineSweeper.engine.app.graphic;

public class Color {
	private static final int CODING = 255; 
	private final int r, g, b, a;
	
	public Color(int r, int g, int b, int a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	public Color(int r, int g, int b){
		this(r, g, b, CODING);
	}
	
	public Color(Color color){
		this(color.r, color.g, color.b, color.a);
	}
	public Color merge(Color color, int degree){
		Color result = new Color(this);
		for (int i = 0; i < degree; i++)
			result = result.merge(color);
		return result;
	}
	public Color merge(Color color){
		int r = (int) ((this.r + color.r)/2);
		int g = (int) ((this.g + color.g)/2);
		int b = (int) ((this.b + color.b)/2);
		int a = (int) ((this.a + color.a)/2);
		
		return new Color(inBorne(r), inBorne(g), inBorne(b), inBorne(a));
	}
	private int inBorne(int val){
		return (val < 0) ? 0 : (val > CODING) ? CODING : val;
	}
	
	public int getR(){
		return r;
	}
	public double getPercentR(){
		return percentValue(r);
	}
	
	public int getG(){
		return g;
	}
	public double getPercentG(){
		return percentValue(g);
	}
	public int getB(){
		return b;
	}
	public double getPercentB(){
		return percentValue(b);
	}
	public int getA(){
		return a;
	}
	public double getPercentA(){
		return percentValue(a);
	}
	
	private double percentValue(int val){
		return (double)val/(double)CODING;
	}
	
	@Override
	public String toString(){
		return "Color [" + r + ", " + g + ", " + b + ", " + a + "]";
	}

	public static Color getNumber(int num){
		switch (num) {
		case 1:
			return BLUE.merge(GREY);
		case 2:
			return GREEN.merge(BLACK);
		case 3:	case 7:	case 8:
			return new Color(180,5, 5);
		case 4:
			return BLUE;
		case 5:
			return RED.merge(BLACK);
		case 6:
			return BLUE.merge(GREEN);
		default:
			break;
		}
		return BLACK;
	}

	public static final Color WHITE = new Color(CODING, CODING, CODING, CODING);
	public static final Color GREY = new Color(CODING/2, CODING/2, CODING/2, CODING);
	public static final Color BLACK = new Color(0, 0, 0, CODING);
	
	
	public static final Color RED = new Color(CODING, 0, 0, CODING);
	public static final Color GREEN = new Color(0, CODING, 0, CODING);
	public static final Color BLUE = new Color(0, 0, CODING, CODING);
		
	public static final Color HIDE_CELL = new Color(170, 200, 255);
	public static final Color OPEN_CELL = new Color(190, 190, 210);
	
	public static final Color MINE_CELL = new Color(50, 50, 50);
	public static final Color NUMBER = new Color(100, 255, 100);
	
	public static final Color FLAGGED_CELL = new Color(255, 75, 75);
	public static final Color HOVER_FILTER = new Color(255, 255, 255, 100);
}
