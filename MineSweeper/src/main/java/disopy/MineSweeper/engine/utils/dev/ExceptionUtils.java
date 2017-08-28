package disopy.MineSweeper.engine.utils.dev;

public final class ExceptionUtils {
	private ExceptionUtils(){}
	
	public static void verifyNullParameters(Object... parameters){
		int i = 0;
		for(Object parameter : parameters){
			if(parameter == null)
				throw new IllegalArgumentException("Null parameter : " + i);
			i++;
		}
	}
}
