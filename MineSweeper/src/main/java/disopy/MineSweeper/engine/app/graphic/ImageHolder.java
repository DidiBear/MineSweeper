package disopy.MineSweeper.engine.app.graphic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageHolder {

	private final Map<String, Image> images = new HashMap<>();
	
	private String path;
	
	public ImageHolder(String path){
		this.path = path;
	}

	public void add(String name, String filename) throws IOException{
		images.put(name, new Image(path + "/"+ filename));
	}
	
	public Image get(String name){
		if (!images.containsKey(name))
			throw new IllegalArgumentException("Image " + name + " doesn't exist");
		return images.get(name);
	}
}
