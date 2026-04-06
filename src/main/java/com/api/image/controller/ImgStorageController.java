package com.api.image.controller;

import com.api.image.domain.entity.ImageEntity;
import com.api.image.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/image")
public class ImgStorageController {


    @Autowired
    private ImageStorageService imageStorageService;


    @PostMapping("/upload/v1")
    public ResponseEntity<ImageEntity> uploadImageV1(@Validated @RequestParam("image") MultipartFile file) throws Exception {
        return this.imageStorageService.saveImage(file).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }



}
