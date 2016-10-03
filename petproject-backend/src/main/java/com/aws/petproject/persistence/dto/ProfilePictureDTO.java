package com.aws.petproject.persistence.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by 212476263 on 2016.10.03..
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
public class ProfilePictureDTO {

    private String picturePath;

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath( String picturePath ) {
        this.picturePath = picturePath;
    }
}
