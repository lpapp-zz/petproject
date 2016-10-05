package com.aws.petproject.resizer.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        String resourcePath = imagePath.substring(0, imagePath.lastIndexOf( "/" ) + 1);

        String resourceUrl = resourcePath + "thumbnail." + fileName;

        Resource resource = this.resourceLoader.getResource(resourceUrl);
        WritableResource writableResource = (WritableResource) resource;
        OutputStream outputStream = writableResource.getOutputStream();

        Thumbnails.of( inputStream )
          .size( 64, 48 )
          .useOriginalFormat()
          .toOutputStream( outputStream );

        System.out.println( resourceUrl );
    }

}
