package net.generationfuture.game;

import java.awt.*;
import java.awt.image.*;

public class TransparentFilter extends RGBImageFilter
{
 private final int transparentRGB;

 public TransparentFilter( Color color )
 {
  this.transparentRGB = color.getRGB();
 }

 @Override
 public int filterRGB( int x, int y, int rgb )
 {
  if ( rgb != transparentRGB )
    return rgb | 0xff000000;

  return rgb & 0xffffff;    //transparent
 }
}