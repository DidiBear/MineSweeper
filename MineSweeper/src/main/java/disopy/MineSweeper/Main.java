package disopy.MineSweeper;

import disopy.MineSweeper.engine.app.Application;

public class Main {
	public static void main(String[] args){
		
		LocalGame game = new LocalGame(749, 399);
		
		new Application(game).run();
		
	}
}
