package com.aws.petproject.resizer.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;

/**
 * Created by 212476263 on 2016.10.05..
 */
@Service
public class S3Service {

    @Autowired
    private ResourceLoader resourceLoader;

    public InputStream downloadResource( String profilePicturePath) throws IOException {
        Resource resource = this.resourceLoader.getResource(profilePicturePath);
        return resource.getInputStream();
    }

}
