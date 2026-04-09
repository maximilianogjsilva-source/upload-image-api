package com.api.image.mapper;

import com.api.image.controller.dto.ImageEntityDTO;
import com.api.image.domain.entity.ImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ImageEntityMapper {

    public ImageEntityDTO imageEntityDTO(ImageEntity imageEntity) {
        return ImageEntityDTO.builder()
                .originalName(imageEntity.getOriginalName())
                .fileName(imageEntity.getFileName())
                .url(imageEntity.getUrl())
                .exp(imageEntity.getExp())
                .contentType(imageEntity.getContentType())
                .extension(imageEntity.getExtension())
                .build();
    }

}
