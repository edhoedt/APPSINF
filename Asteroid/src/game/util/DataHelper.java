package game.util;


import java.awt.Dimension;
import java.awt.Toolkit;

public abstract class DataHelper
{
  private static int windowWidth;
  private static int windowHeight;

  public static int getWidth()
  {
    return windowWidth;
  }

  public static void setWidth(int w) {
    windowWidth = w;
  }

  public static int getHeight() {
    return windowHeight;
  }

  public static void setHeight(int h) {
    windowHeight = h;
  }

  public static int getCenterX() {
    return windowWidth / 2;
  }

  public static int getCenterX(Resolution resolution) {
    return resolution.getWidth() / 2;
  }

  public static int getCenterX(Resolution resolution, int offset) {
    return getCenterX(resolution) - offset / 2;
  }

  public static int getCenterY() {
    return windowHeight / 2;
  }
  public static int getCenterY(Resolution resolution) {
    return resolution.getHeight() / 2;
  }

  public static int getCenterY(Resolution resolution, int offset) {
    return getCenterY(resolution) - offset / 2;
  }
  public static Resolution getMonitorResolution() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension scrnsize = toolkit.getScreenSize();
    return new Resolution((int)scrnsize.getWidth(), (int)scrnsize.getHeight());
  }
}