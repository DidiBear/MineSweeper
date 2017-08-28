package disopy.MineSweeper.engine.app.event.mouse;

public enum ButtonState {
	UP, DOWN, PRESSED, RELEASED;
	
	public boolean isUP(){
		return UP.equals(this);		
	}
	public boolean isDOWN(){
		return DOWN.equals(this);		
	}
	public boolean isPRESSED(){
		return PRESSED.equals(this);		
	}
	public boolean isRELEASED(){
		return RELEASED.equals(this);		
	}
}
