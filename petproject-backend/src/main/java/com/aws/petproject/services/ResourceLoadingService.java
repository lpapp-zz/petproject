package com.aws.petproject.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

/**
 * Created by 212476263 on 2016.09.28..
 */
@Service
public class ResourceLoadingService {

    @Autowired
    private ResourceLoader resourceLoader;

    public void uploadResource() throws IOException {
        Resource resource = this.resourceLoader.getResource("s3://myBucket/rootFile.log");
        WritableResource writableResource = (WritableResource) resource;
        try (OutputStream outputStream = writableResource.getOutputStream()) {
            outputStream.write("test".getBytes());
        }
    }

    public void downloadResource() throws IOException {
        Resource resource = this.resourceLoader.getResource("s3://myBucket/rootFile.log");
        Resource secondResource = this.resourceLoader.getResource("s3://myBucket/rootFolder/subFile");

        InputStream inputStream = resource.getInputStream();
    }
}
