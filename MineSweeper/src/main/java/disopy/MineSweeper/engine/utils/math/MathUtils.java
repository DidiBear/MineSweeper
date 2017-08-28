package disopy.MineSweeper.engine.utils.math;

public final class MathUtils {
	private MathUtils(){}
	
	/** random in : [min, max[ */
	public static int random(int min, int max){
		return (int)(Math.random()*(max-min)) + min;
	}
}
