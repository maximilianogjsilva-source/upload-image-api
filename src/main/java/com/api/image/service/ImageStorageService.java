package com.api.image.service;

import com.api.image.domain.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageStorageService {

    Optional<ImageEntity> getImageById(Long id);

    Optional<ImageEntity> saveImage(MultipartFile multipartFile) throws IOException;

    Optional<ImageEntity> deleteImage(ImageEntity imageEntity) throws IOException;

}
