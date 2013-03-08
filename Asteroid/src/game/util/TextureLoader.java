package game.util;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader {

	// La table des textures qui ont été chargées sur ce Loader.
	private HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	// La couleur du model y compris l'alpha pour l'image de la bibliothèque graphique.
	private ColorModel alphaColor;
	
	// La couleur du model pour l'image de la bibliothèque graphique.
	private ColorModel color;
	
	// Buffer pour les ID des textures.
	private IntBuffer textureIDBuffer = BufferUtils.createIntBuffer(1);
	
	public TextureLoader(){
		alphaColor = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
																	new int[] {8,8,8,8},
																	true,
																	false,
																	ComponentColorModel.TRANSLUCENT,
																	DataBuffer.TYPE_BYTE);
		color = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
																new int[] {8,8,8,0},
																true,
																false,
																ComponentColorModel.OPAQUE,
																DataBuffer.TYPE_BYTE);
	}
	
	// Crée une nouvelle texture ID
	private int createTextureID(){
		glGenTextures(textureIDBuffer);
		return textureIDBuffer.get(0);
	}
	
	// Charge une texture
	public Texture getTexture(String ressource) throws IOException {
		Texture tex = textures.get(ressource);
		
		if(tex != null){
			return tex;
		}
		
		tex = getTexture(ressource, GL_TEXTURE_2D, GL_RGBA, GL_LINEAR, GL_LINEAR);
		textures.put(ressource, tex);
		
		return tex;
	}
	
	// Charge une texture dans OpenGL à partir d'une image sur le disque
	public Texture getTexture(String ressource, int target, int dstPixelFormat, int minFilter, int magFilter) throws IOException {
		int srcPixelFormat;
		
		// Crée une texture ID pour cette texture
		int textureID = createTextureID();
		Texture texture = new Texture(target, textureID);
		
		// Lie cette texture
		glBindTexture(target, textureID);
		
		BufferedImage bufferedImage = loadImage(ressource);
		texture.setWidth(bufferedImage.getWidth());
		texture.setHeight(bufferedImage.getHeight());
		
		if(bufferedImage.getColorModel().hasAlpha()){
			srcPixelFormat = GL_RGBA;
		}
		else{
			srcPixelFormat = GL_RGB;
		}
		
		// Converti l'image en buffer de byte
		ByteBuffer textureBuffer = convertImageData(bufferedImage, texture);
		
		if(target == GL_TEXTURE_2D){
			glTexParameteri(target, GL_TEXTURE_MIN_FILTER, minFilter);
			glTexParameteri(target, GL_TEXTURE_MAG_FILTER, magFilter);
		}
		
		// Produit une texture à partir d'un buffer de byte
		glTexImage2D(target,
						0,
						dstPixelFormat,
						get2Fold(bufferedImage.getWidth()),
						get2Fold(bufferedImage.getHeight()),
						0,
						srcPixelFormat,
						GL_UNSIGNED_BYTE,
						textureBuffer);
		
		return texture;
	}

	private int get2Fold(int fold) {
		int ret = 2;
		while (ret < fold){
			ret *= 2;
		}
		return ret;
	}

	private ByteBuffer convertImageData(BufferedImage bufferedImage, Texture texture) {
		ByteBuffer imageBuffer;
		WritableRaster raster;
		BufferedImage texImage;
		
		int texWidth = 2;
		int texHeight = 2;
		
		// Cherche la puissance de 2 la plus proche de la largeur et hauteur de
		// la texture produite.
		while(texWidth < bufferedImage.getWidth()){
			texWidth *= 2;
		}
		while(texHeight < bufferedImage.getHeight()){
			texHeight *= 2;
		}
		
		texture.setTexHeight(texHeight);
		texture.setTexWidth(texWidth);
		
		// Crée une trame qui peut être utilisée par OpenGL comme source pour une texture
		if(bufferedImage.getColorModel().hasAlpha()){
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 4, null);
			texImage = new BufferedImage(alphaColor, raster, false, new Hashtable());
		}
		else{
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 3, null);
			texImage = new BufferedImage(color, raster, false, new Hashtable());
		}
		
		// Copie la source de l'image dans l'image produite
		Graphics g = texImage.getGraphics();
		g.setColor(new Color(0f,0f,0f,0f));
		g.fillRect(0, 0, texWidth, texHeight);
		g.drawImage(bufferedImage, 0, 0, null);
		
		// Crée un buffer de byte à partir de l'image temporaire
		// qui va être utilisé par OpenGL pour produire une texture.
		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();
		
		imageBuffer = ByteBuffer.allocateDirect(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		imageBuffer.flip();
		
		return imageBuffer;
	}

	private BufferedImage loadImage(String ref) throws IOException {
		URL url = TextureLoader.class.getClassLoader().getResource(ref);
		
		if(url == null){
			throw new IOException("Ne trouve pas : " + ref);
		}
		
		Image img = new ImageIcon(url).getImage();
		BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		return bufferedImage;
	}
}
