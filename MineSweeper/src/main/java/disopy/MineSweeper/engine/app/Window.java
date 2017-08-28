package disopy.MineSweeper.engine.app;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import disopy.MineSweeper.engine.app.event.mouse.ButtonState;
import disopy.MineSweeper.engine.app.event.mouse.MouseButton;
import disopy.MineSweeper.engine.app.event.mouse.MouseEventArea;
import disopy.MineSweeper.engine.utils.geometry.Point;

public class Window {
	
	protected int width, heigth;
	
	protected Point cursorPosition;
	private final List<MouseEventArea> mouseAreas = new ArrayList<>();
	protected boolean leftButtonDown, rightButtonDown;
	
	
	public Window(int width, int heigth){
		this.width = width;
		this.heigth = heigth;
		cursorPosition = new Point(0, 0);
	}
	
	public void start(){
		createDisplay(width, heigth);
	}
	private void createDisplay(int width, int heigth){
		try {
			Display.setDisplayMode(new DisplayMode(width, heigth));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/** Update the display */
	public void update() {
		Display.update();
		Display.sync(60);
	}
	
	/** Update the event */
	public void event(int delta){
		mouseEvent();
		keyboardEvent();
	}

	protected void addMouseEventArea(MouseEventArea area){
		mouseAreas.add(area);
	}
	private void mouseEvent(){
		cursorPosition.set(Mouse.getX(), heigth - Mouse.getY());

		// A button is pressed or released
		while (Mouse.next()) {
			if (Mouse.getEventButton() == -1)
				continue;
			
			boolean pressed = Mouse.getEventButtonState();
			ButtonState state = pressed ? ButtonState.PRESSED : ButtonState.RELEASED;

			MouseButton button = MouseButton.NONE;
			if (Mouse.getEventButton() == 0){
				button = rightButtonDown ? MouseButton.BOTH : MouseButton.LEFT;
				leftButtonDown = pressed;
			}
			else if (Mouse.getEventButton() == 1){
				button = leftButtonDown ? MouseButton.BOTH : MouseButton.RIGHT;
				rightButtonDown = pressed;
			}
			
			// Perform action (when a change was made)
			for(MouseEventArea area : MouseEventArea.get(mouseAreas, cursorPosition)){
				if (!button.isNONE())
					area.getPerformer().performClick(button, state);
			}
		}
		
		// Perform action (on static state)
		for (MouseEventArea area : MouseEventArea.get(mouseAreas, cursorPosition)){
			area.getPerformer().mouseHover();

			MouseButton button = (leftButtonDown && rightButtonDown) ? MouseButton.BOTH
					: (leftButtonDown) ? MouseButton.LEFT
					: (rightButtonDown) ? MouseButton.RIGHT
					: MouseButton.NONE;
			
			if (!button.isNONE())
				area.getPerformer().performClick(button, ButtonState.DOWN);
		}
	}
	private void keyboardEvent(){
		// TODO KeyboardEvent
	}
	
	public boolean isFinish(){
		return Display.isCloseRequested();
	}
	public void close(){
		Display.destroy();
	}
}
