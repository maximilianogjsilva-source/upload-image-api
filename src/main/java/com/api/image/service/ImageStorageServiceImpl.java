package com.api.image.service;

import com.api.image.domain.entity.ImageEntity;
import com.api.image.domain.repository.ImageEntityRepository;
import com.api.image.mapper.ImageEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class ImageStorageServiceImpl implements ImageStorageService{


    @Autowired
    private ImageEntityRepository imageEntityRepository;

    @Autowired
    private ImageEntityMapper imageEntityMapper;

    @Override
    public Optional<ImageEntity> getImageById(Long id) {
        return this.imageEntityRepository.getById(id);
    }

    @Override
    public Optional<ImageEntity> saveImage(MultipartFile multipartFile) throws IOException {

        //Mappeamos a un tipo ImageEntity
        ImageEntity imageEntity = imageEntityMapper.toImageEntity(multipartFile);

        //Creamos Directorio del path de la imagen
        if( !Files.exists(imageEntity.getPath()) ) {
            Files.createDirectories(imageEntity.getPath());
        }
        //Se crea el directorio de la imagen y se copia el contenido del multipartFile dentro
        Files.copy(multipartFile.getInputStream(),
                imageEntity.getPath().resolve(imageEntity.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);

        return this.imageEntityRepository.save(imageEntity);
    }

    @Override
    public Optional<ImageEntity> deleteImage(ImageEntity imageEntity) throws IOException {

        Files.deleteIfExists(imageEntity.getPath());

        this.imageEntityRepository.deleteById(imageEntity.getId());

        return Optional.of(imageEntity);
    }
}
