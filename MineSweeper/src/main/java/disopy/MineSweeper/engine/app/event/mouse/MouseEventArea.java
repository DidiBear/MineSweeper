package disopy.MineSweeper.engine.app.event.mouse;

import java.util.ArrayList;
import java.util.List;

import disopy.MineSweeper.engine.utils.dev.ExceptionUtils;
import disopy.MineSweeper.engine.utils.geometry.Point;
import disopy.MineSweeper.engine.utils.geometry.Quad;

/**
 * Represent an area where a mouse can click / hover
 * Launch a method for interact with this area.
 */
public class MouseEventArea {
	private Quad surface;
	private int priority;
	private MouseEventPerformer performer;
	
	public MouseEventArea(int priority, Quad surface, MouseEventPerformer performer) {
		ExceptionUtils.verifyNullParameters(surface, performer);
		
		this.surface = surface;
		this.priority = priority;
		this.performer = performer;
	}
		
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public MouseEventPerformer getPerformer(){
		return performer;
	}
	
	/** return a list of the Areas (of the domain) containing the point (with the higher priority) */
	public static List<MouseEventArea> get(List<MouseEventArea> areas, Point point){
		ExceptionUtils.verifyNullParameters(areas, point);
		
		List<MouseEventArea> resultAreas = new ArrayList<>();
		int highPriority = 0;
		
		for(MouseEventArea area : areas){
			if (area.contain(point)){
				if (area.priority > highPriority){
					highPriority = area.priority;
					resultAreas.clear(); // Remove those which have a lower priority
				}
				resultAreas.add(area);
			}
		}
		
		return resultAreas;
	}
	
	public boolean contain(Point point){
		ExceptionUtils.verifyNullParameters(point);
		int posX = surface.getPosition().getX(), posY = surface.getPosition().getY();
		int x = point.getX(), y = point.getY();
		return (posX <= x) && (x <= posX + surface.getWidth()) && (posY <= y) && (y <= posY + surface.getHeigth());
	}
}
