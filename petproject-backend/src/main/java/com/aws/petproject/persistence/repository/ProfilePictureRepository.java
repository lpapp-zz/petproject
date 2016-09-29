package com.aws.petproject.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aws.petproject.persistence.entitiy.ProfilePicture;

/**
 * Created by 212476263 on 2016.09.29..
 */
public interface ProfilePictureRepository  extends JpaRepository<ProfilePicture, Integer>{

}
