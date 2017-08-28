package disopy.MineSweeper.game;

import java.util.ArrayList;
import java.util.List;

import disopy.MineSweeper.engine.utils.dev.ExceptionUtils;
import disopy.MineSweeper.engine.utils.math.Entry;
import disopy.MineSweeper.engine.utils.math.MathUtils;
import disopy.MineSweeper.engine.utils.math.Matrix;

public class Board extends Matrix<Cell>{
	
	private int nbMines;
	private boolean isGenerated;
	
	public Board(int nbLines, int nbColumns, int nbMines){
		super(nbLines, nbColumns, Cell.class);
		this.nbMines = nbMines;
		isGenerated = false;
	}

	public void generateBoard(Entry initialInput){
		ExceptionUtils.verifyNullParameters(initialInput);
		placeMines(initialInput);
		affectValues();
	}
	private void placeMines(Entry initialInput){
		Entry randomPoint = new Entry(0, 0);
		
		int placedMines = 0;
		
		while(placedMines < nbMines){
			do {
				randomPoint = new Entry(MathUtils.random(0, nbLines), MathUtils.random(0, nbColumns));
			} while (get(randomPoint).isMine() || randomPoint.neardy(initialInput));
			
			this.get(randomPoint).setType(Cell.Type.MINE);
			placedMines++;
		}
	}
	private void affectValues(){
		for (int i = 0; i < nbLines; i++) {
			for (int j = 0; j < nbColumns; j++) {
				int neardyMines = 0;
				for (Cell cell : neardyCells(i, j)) {
					if(cell.isMine())
						neardyMines++;
				}
				get(i, j).setValue(neardyMines);	
			}
		}
	}
	
	/** @return false if it's a mine	 */
	public boolean discoverCell(Entry input){
		if (!isGenerated){
			generateBoard(input);
			isGenerated = true;
		}
		
		boolean flagDiscover = false;
		
		Cell cell = get(input);
		switch (cell.getState()) {
		case HIDE:
			cell.open();
			if (cell.isMine())
				return false;
			break;
			
		case FLAGGED:
			return true;
			
		case OPEN:
			int nbNeardyFlag = 0;
			for (Cell neardyCell : neardyCells(input)){
				if (neardyCell.isFlagged())
					nbNeardyFlag++;
			}
			if (nbNeardyFlag != cell.getValue())
				return true;
			
			flagDiscover = true;
			
			break;
		default:
			break;
		}
		
		if (cell.getValue() == 0 || flagDiscover){
			for (Entry neardyEntry : neardyEntries(input)){
				if (get(neardyEntry).isHide())
					if (!discoverCell(neardyEntry))
						return false;
			}
		}
		
		return true;
	}
	
	public void dicoverMines(){
		for (int i = 0; i < nbLines; i++) {
			for (int j = 0; j < nbColumns; j++) {
				Cell cell = get(i, j);
				if (cell.isMine() && !cell.isFlagged()) {
					cell.open();
				}
			}
		}
	}
	
	
	public List<Cell> neardyCells(Entry entry){
		ExceptionUtils.verifyNullParameters(entry);
		return neardyCells(entry.i, entry.j);
	}
	private List<Cell> neardyCells(int i, int j){
		List<Cell> cells = new ArrayList<Cell>();
		for (Entry entry : neardyEntries(i, j)){
			cells.add(get(entry));
		}
		return cells;
	}
	
	
	public List<Entry> neardyEntries(Entry entry){
		ExceptionUtils.verifyNullParameters(entry);
		return neardyEntries(entry.i, entry.j);
	}
	private List<Entry> neardyEntries(int i, int j){
		List<Entry> entries = new ArrayList<Entry>();
		for (int m = -1; m <= 1; m++) {
			for (int n = -1; n <= 1; n++) {
				if (inBoard(i+m, j+n) && (m != 0 || n != 0))
					entries.add(new Entry(i+m, j+n));
			}
		}
		return entries;
	}
	
	public void resetCellEvent(){
		for (int i = 0; i < nbLines; i++) {
			for (int j = 0; j < nbColumns; j++) {
				get(i, j).setEvent(Cell.Event.NONE);
			}
		}
	}

	public boolean isGenerated(){
		return isGenerated;
	}
}
