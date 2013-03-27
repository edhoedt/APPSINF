package game.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;

public class Resolution
{
  private boolean fullscreen;
  private int width;
  private int height;
  private static HashMap<String, Resolution> resolutionMap = new HashMap();
  
  static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  static { resolutionMap.put("640,480", new Resolution(320, 240));
    resolutionMap.put("800,600", new Resolution(640, 480));
    resolutionMap.put((int)screenSize.getWidth()+","+(int)screenSize.getHeight(), new Resolution(800, 600));
  }

  public Resolution(int width, int height)
  {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public static HashMap<String, Resolution> getResolutionMap() {
    return resolutionMap;
  }

  public static void setResolutionMap(HashMap<String, Resolution> resolutionMap) {
    resolutionMap = resolutionMap;
  }

  public boolean isFullscreen() {
    return this.fullscreen;
  }

  public void setFullscreen(boolean fullscreen) {
    this.fullscreen = fullscreen;
  }

  public static Resolution getMappedResolution(Resolution resolution) {
    return (Resolution)resolutionMap.get(new String(resolution.getWidth() + "," + resolution.getHeight()));
  }
}