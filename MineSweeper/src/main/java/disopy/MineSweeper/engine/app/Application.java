package disopy.MineSweeper.engine.app;

import org.lwjgl.Sys;

public class Application implements Runnable {
	private Window window;

	public Application(Window window){
		this.window = window;
	}

	@Override
	public void run(){
		window.start();
		while (!window.isFinish()) {
			window.event(getDelta());
			window.update();
		}
		window.close();
	}

	/** time at last frame */
	private static long lastFrame;

	/** @return milliseconds passed since last frame */
	private static int getDelta(){
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/** @return The system time in milliseconds */
	private static long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}
