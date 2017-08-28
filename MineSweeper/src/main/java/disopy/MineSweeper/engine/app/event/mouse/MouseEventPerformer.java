package disopy.MineSweeper.engine.app.event.mouse;

public interface MouseEventPerformer {	
	public void mouseHover();
	public void performClick(MouseButton button, ButtonState state);
}
