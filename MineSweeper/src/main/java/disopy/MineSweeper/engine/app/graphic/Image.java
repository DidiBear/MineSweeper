package disopy.MineSweeper.engine.app.graphic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Image {
	private String path;
	private String type;
	
	public Image(String pathFile) throws IOException{
		if (!(new File(pathFile)).exists())
			throw new FileNotFoundException("File " + pathFile +" doesn't exist");
		
		this.path = pathFile;
		this.type = pathFile.substring(pathFile.lastIndexOf(".")).toUpperCase();
	}

	public String getPath(){
		return path;
	}

	public String getType(){
		return type;
	}	
}
