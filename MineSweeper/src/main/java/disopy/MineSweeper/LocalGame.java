package disopy.MineSweeper;

import java.io.IOException;

import disopy.MineSweeper.engine.app.Window;
import disopy.MineSweeper.engine.app.event.mouse.ButtonState;
import disopy.MineSweeper.engine.app.event.mouse.MouseButton;
import disopy.MineSweeper.engine.app.event.mouse.MouseEventArea;
import disopy.MineSweeper.engine.app.event.mouse.MouseEventPerformer;
import disopy.MineSweeper.engine.app.graphic.Color;
import disopy.MineSweeper.engine.app.graphic.GL;
import disopy.MineSweeper.engine.app.graphic.ImageHolder;
import disopy.MineSweeper.engine.utils.geometry.Point;
import disopy.MineSweeper.engine.utils.geometry.Quad;
import disopy.MineSweeper.engine.utils.math.Entry;
import disopy.MineSweeper.game.Board;
import disopy.MineSweeper.game.Cell;

public class LocalGame extends Window{
	
	private final Board board;
	private int sizeCell = 24;
	private boolean gameLost = false;
	private boolean gameFinish = false;
	
	private final ImageHolder images;
	
	public LocalGame(int width, int heigth){
		super(width, heigth);
		
		board = new Board(16, 30, 99);
		
		images = new ImageHolder("res/image");
		try {
			images.add("hideCell", "cell.png");
			images.add("openCell", "openCell.png");
			images.add("flag", "flag.png");
			images.add("mine", "mine.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		for (int i = 0; i < board.getNbLines(); i++) {
			for (int j = 0; j < board.getNbColumns(); j++) {
				Quad surface = new Quad(new Point(j*sizeCell + j, i*sizeCell + i), sizeCell, sizeCell);
				
				Cell cell = board.get(i, j);
				Entry entry = new Entry(i, j);
				addMouseEventArea(new MouseEventArea(0, surface, new MouseEventPerformer(){
					@Override
					public void mouseHover(){
						cell.setHoverEvent();
					}
					
					@Override
					public void performClick(MouseButton button, ButtonState state){
						
						if(!gameLost){
							if (button.isRIGHT() && state.isPRESSED()){
								cell.flag();
							}
							
							if (button.isLEFT() && state.isRELEASED()){
								if(!board.discoverCell(entry)){
									board.dicoverMines();
									gameLost = true;
								}
							}
						}
						else{
							if (state.isPRESSED()){
								gameFinish = true;
							}
						}
					}
				}));
			}
		}
	}
	
	@Override
	public void start() {
		super.start();
		GL.initGL(width, heigth);
	}
	
	@Override
	public boolean isFinish(){
		if(gameFinish)
			return true;
		return super.isFinish();
	}
	
	@Override
	public void event(int delta) {
		board.resetCellEvent();
		
		super.event(delta);
	};
	
	@Override
	public void update(){
		GL.clear();
		
		for (int i = 0; i < board.getNbLines(); i++) {
			for (int j = 0; j < board.getNbColumns(); j++) {				
				Cell cell = board.get(i, j);
				
				int x = j*sizeCell + j, y = i*sizeCell + i;
				Quad surface = new Quad(new Point(x, y), sizeCell, sizeCell);
				
				if (cell.isHide() || cell.isFlagged()){
					GL.drawImage(images.get("hideCell"), new Point(x, y), Color.HIDE_CELL);
					if (cell.isFlagged())
						GL.drawImage(images.get("flag"), new Point(x, y));	
				}
				else if(cell.isOpen()){
					GL.drawImage(images.get("openCell"), new Point(x, y));
				
					if(cell.isNumber() && cell.getValue() != 0)
						GL.drawString(x + sizeCell/2, y + sizeCell/2, ""+cell.getValue(), Color.getNumber(cell.getValue()));
					else if (cell.isMine())
						GL.drawImage(images.get("mine"), new Point(x, y));
				}
				
				if (cell.isHover())
					GL.drawQuad(Color.HOVER_FILTER, surface);
			}
		}
		super.update();
	}
}
