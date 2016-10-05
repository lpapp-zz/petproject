package com.aws.petproject.resizer.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.coobird.thumbnailator.Thumbnails;

/**
 * Created by 212476263 on 2016.10.05..
 */
@Component
public class ResizerService {

    @Autowired
    private S3Service s3Service;

    public void resizeImage(String imagePath) throws IOException {
        InputStream inputStream = s3Service.downloadResource( imagePath );

        File thumbnailImage = null;

        Thumbnails.of(inputStream)
          .size(64, 48)
          .useOriginalFormat()
          .toFile( thumbnailImage );

        String thumbnailS3Path = s3Service.uploadResource( thumbnailImage );

        System.out.println(thumbnailS3Path);
    }

}
