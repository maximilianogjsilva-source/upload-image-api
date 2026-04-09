package com.api.image.service;

import com.api.image.controller.dto.ImageEntityDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageStorageService {

    List<ImageEntityDTO> getAll();

    Optional<ImageEntityDTO> getImageById(Long id);

    Optional<ImageEntityDTO> saveImage(MultipartFile multipartFile);

    Optional<ImageEntityDTO> saveImage(MultipartFile multipartFile, String folder);

    Optional<ImageEntityDTO> deleteImage(String fileName);

}
