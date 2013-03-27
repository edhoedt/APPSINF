package game.util;

import org.newdawn.slick.Image;

public abstract class DrawHelper
{
  public static void drawImageCentered(Image image, int x, int y, float alpha, float scale)
  {
    image.setAlpha(alpha);
    image.draw(x - image.getWidth() * scale / 2.0F, y - image.getHeight() * scale / 2.0F, scale);
  }

  public static void drawImageCentered(Image image, int x, int y)
  {
    drawImageCentered(image, x, y, 1.0F, 1.0F);
  }

  public static void drawImage(Image image, int x, int y)
  {
    image.draw(x, y);
  }
}