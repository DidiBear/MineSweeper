package disopy.MineSweeper.engine.utils.math;

import disopy.MineSweeper.engine.utils.dev.ExceptionUtils;

/**
 * Represent a point of a matrix
 * (i, j) represent the line and the column indexes
 */
public class Entry {
	public final int i, j;
	
	public Entry(int i, int j){
		this.i = i;
		this.j = j;
	}

	public boolean neardy(Entry entry, int distance){
		ExceptionUtils.verifyNullParameters(entry);
		
		int i2 = entry.i, j2 = entry.j;
		
		boolean isInVerticalRange = i - distance <= i2 && i2 <= i + distance;
		boolean isInHorizontalRange = j - distance <= j2 && j2 <= j + distance;
		
		return isInVerticalRange && isInHorizontalRange;
	}
	public boolean neardy(Entry entry){
		return neardy(entry, 1);
	}
}
