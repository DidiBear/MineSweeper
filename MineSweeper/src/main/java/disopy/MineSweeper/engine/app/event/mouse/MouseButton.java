package disopy.MineSweeper.engine.app.event.mouse;

public enum MouseButton {
	NONE, LEFT, RIGHT, BOTH, MIDDLE;
	
	public boolean isNONE(){
		return NONE.equals(this);		
	}
	public boolean isLEFT(){
		return LEFT.equals(this);		
	}
	public boolean isRIGHT(){
		return RIGHT.equals(this);		
	}
	public boolean isBOTH(){
		return BOTH.equals(this);		
	}
	public boolean isMIDDLE(){
		return MIDDLE.equals(this);		
	}
}
