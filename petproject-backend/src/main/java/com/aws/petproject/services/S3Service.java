package com.aws.petproject.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 212476263 on 2016.10.05..
 */
@Service
public class S3Service {

    @Autowired
    private ResourceLoader resourceLoader;

    public String uploadResource( MultipartFile multipartFile) throws IOException {
        String resourcePath = "s3://petproject-bucket/";
        String resourceUrl = resourcePath + multipartFile.getOriginalFilename();

        Resource resource = this.resourceLoader.getResource(resourceUrl);
        WritableResource writableResource = (WritableResource) resource;
        try (OutputStream outputStream = writableResource.getOutputStream()) {
            outputStream.write(multipartFile.getBytes());
        }

        return resourceUrl;
    }

    public InputStream downloadResource( String profilePicturePath) throws IOException {
        Resource resource = this.resourceLoader.getResource(profilePicturePath);
        return resource.getInputStream();
    }

}
