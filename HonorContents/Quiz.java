
/**
 * Write a description of Quiz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class Quiz {
    public final static int invertedIndex = 255;
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    public void saveNewGrayImage() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGray(inImage);
            String fname = inImage.getFileName();
            String newName = "gray-" + fname;
            outImage.setFileName(newName);
            outImage.draw();
            outImage.save();
        }
    }
    
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(invertedIndex - inPixel.getRed());
            pixel.setGreen(invertedIndex - inPixel.getGreen());
            pixel.setBlue(invertedIndex - inPixel.getBlue());
        }
        return outImage;
    }
    
    public void saveNewInvertedImage() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeInversion(inImage);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            outImage.setFileName(newName);
            outImage.draw();
            outImage.save();
        }
    }
}
