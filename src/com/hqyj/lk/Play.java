package com.hqyj.lk;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-11-19:32
 */
public class Play {
    public static void main(String[] args) throws IOException {
        BufferedImage image =new BufferedImage(1920,  1080 ,
                BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, 1920, 1080);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, 1920, 1080);

        FileOutputStream fos   =   new   FileOutputStream("img.jpg");
        JPEGImageEncoder encoder   =   JPEGCodec.createJPEGEncoder(fos);
        encoder.encode(image);
        fos.close();
    }
}
