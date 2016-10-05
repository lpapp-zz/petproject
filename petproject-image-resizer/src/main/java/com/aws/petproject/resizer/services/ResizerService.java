package com.aws.petproject.resizer.services;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import net.coobird.thumbnailator.Thumbnails;

/**
 * Created by 212476263 on 2016.10.05..
 */
@Component
public class ResizerService {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ResourceLoader resourceLoader;

    public void resizeImage( String imagePath ) throws IOException {
        InputStream inputStream = s3Service.downloadResource( imagePath );

        String fileName = imagePath.substring(imagePath.lastIndexOf( "/" ) + 1, imagePath.length());
        String fileFormat = fileName.substring( fileName.lastIndexOf( "." ) + 1, fileName.length());
        String resourcePath = imagePath.substring(0, imagePath.lastIndexOf( "/" ) + 1);

        String resourceUrl = resourcePath + "thumbnail." + fileName;

        Resource resource = this.resourceLoader.getResource(resourceUrl);
        WritableResource writableResource = (WritableResource) resource;
        OutputStream outputStream = writableResource.getOutputStream();

        BufferedImage image = Thumbnails.of( inputStream )
          .size( 64, 48 )
          .useOriginalFormat()
          .asBufferedImage();

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write( image, fileFormat, baos );
//        baos.flush();
//        byte[] imageInByte = baos.toByteArray();
//        baos.close();

        outputStream.write( ((DataBufferByte) image.getData().getDataBuffer()).getData() );

        System.out.println( resourceUrl );
    }

    public void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight)
      throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                                                       scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                                                        .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public void resize(String inputImagePath,
                              String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

}
