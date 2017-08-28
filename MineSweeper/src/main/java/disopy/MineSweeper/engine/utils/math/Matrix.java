package disopy.MineSweeper.engine.utils.math;

import java.util.ArrayList;
import java.util.List;

import disopy.MineSweeper.engine.utils.dev.ExceptionUtils;

public abstract class Matrix<T> {

	protected final int nbLines, nbColumns;
	private final List<List<T>> matrix;

	public Matrix(int nbLines, int nbColumns, Class<T> classT){
		ExceptionUtils.verifyNullParameters(classT);
		
		this.nbLines = nbLines;
		this.nbColumns = nbColumns;

		matrix = new ArrayList<List<T>>();
		for (int i = 0; i < nbLines; i++) {
		
			List<T> column = new ArrayList<T>();
			for (int j = 0; j < nbColumns; j++) {
				column.add(null);
				
				try {
					column.set(j, classT.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
				}
			}
			matrix.add(column);
		}
	}
	
	public T get(int i, int j){
		return matrix.get(i).get(j);
	}
	public T get(Entry entry){
		ExceptionUtils.verifyNullParameters(entry);
		return get(entry.i, entry.j);
	}
	
	public T set(int i, int j, T element){
		return matrix.get(i).set(j, element);
	}
	public T set(Entry entry, T element){
		ExceptionUtils.verifyNullParameters(entry);
		return set(entry.i, entry.j, element);
	}
	
	protected boolean inBoard(int i, int j){
		return (0 <= i && i < nbLines) && (0 <= j && j < nbColumns);
	}
	public boolean inBoard(Entry entry){
		ExceptionUtils.verifyNullParameters(entry);
		return inBoard(entry.i, entry.j);
	}
	
	public int getNbLines(){
		return nbLines;
	}

	public int getNbColumns(){
		return nbColumns;
	}
}
