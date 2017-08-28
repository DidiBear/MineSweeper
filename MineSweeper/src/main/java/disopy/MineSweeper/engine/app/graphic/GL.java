package disopy.MineSweeper.engine.app.graphic;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import disopy.MineSweeper.engine.utils.geometry.Point;
import disopy.MineSweeper.engine.utils.geometry.Quad;

public final class GL {
	private GL(){}
	
	private static final Map<Image, Texture> textures = new HashMap<>();
	private static final TrueTypeFont font;
	private static final int fontSize;
	static {
		// TODO GL Font 
		fontSize = 20;
		Font awtFont = new Font("Arial", Font.BOLD, fontSize);
		font = new TrueTypeFont(awtFont, true);
	}
	
	public static void initGL(int width, int height) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);  
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0 , 0, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW); 
	}
	
	public static void clear(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();	
	}
	
	private static void bindColor(Color color){
		GL11.glColor4d(color.getPercentR(), color.getPercentG(), color.getPercentB(), color.getPercentA());		
	}
	
	public static void drawQuad(Color color, Quad surface){
		drawQuad(color, surface.getPosition(), surface.getWidth(), surface.getHeigth());
	}
	public static void drawQuad(Color color, Point position, int width, int height){
		int x = position.getX(), y = position.getY();
		
		bindColor(color);
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + width, y);
			GL11.glVertex2f(x + width, y + height);
			GL11.glVertex2f(x, y + height);
		GL11.glEnd();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static void drawImage(Image image, Point position){
		drawImage(image, position, Color.WHITE);
	}	
	public static void drawImage(Image image, Point position, Color filter){
		drawTexture(getTexture(image), position, filter);
	}
	private static void drawTexture(Texture texture, Point position, Color filter){
		drawTexture(texture, new Quad(position, texture.getImageWidth(), texture.getImageHeight()), filter);
	}
	public static void drawImage(Image image, Quad surface){
		drawImage(image, surface, Color.WHITE);
	}
	public static void drawImage(Image image, Quad surface, Color filter){
		drawTexture(getTexture(image), surface, filter);
	}
	private static void drawTexture(Texture texture, Quad surface, Color filter){
		bindColor(filter);
		texture.bind();
	
		int x = surface.getPosition().getX(), y = surface.getPosition().getY();
		
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glTexCoord2f(0, 0);
		    GL11.glVertex2f(x, y);
		 
		    GL11.glTexCoord2f(texture.getWidth(), 0);
		    GL11.glVertex2f(x + surface.getWidth(), y);
		 
		    GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
		    GL11.glVertex2f(x + surface.getWidth(), y + surface.getHeigth());
		 
		    GL11.glTexCoord2f(0, texture.getHeight());
		    GL11.glVertex2f(x, y + surface.getHeigth());
		GL11.glEnd();
	}

	private static Texture getTexture(Image image){
		if (!textures.containsKey(image))
			loadTexture(image);
	
		return textures.get(image);
	}	
	private static void loadTexture(Image image){
		try {
			textures.put(image, TextureLoader.getTexture(image.getType(), ResourceLoader.getResourceAsStream(image.getPath())));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void drawString(int x, int y, String string, Color color){
		font.drawString(x - font.getWidth(string)/2, y - font.getHeight(string)/2, string, getSlickColor(color));
	}
	private static org.newdawn.slick.Color getSlickColor(Color color) {
		float r = (float)color.getPercentR();
		float g = (float)color.getPercentG();
		float b = (float)color.getPercentB();
		float a = (float)color.getPercentA();
		return new org.newdawn.slick.Color(r, g, b, a);
	}
	
	
}
