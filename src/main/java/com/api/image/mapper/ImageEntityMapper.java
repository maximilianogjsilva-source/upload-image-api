package com.api.image.mapper;

import com.api.image.domain.entity.ImageEntity;
import com.api.image.domain.repository.ImageEntityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;

@Component
public class ImageEntityMapper {

    @Value("${image.upload.dir}")
    private String uploadDir;

    @Value("${image.upload.folder:uploads}")
    private String folder;

    public ImageEntity toImageEntity(MultipartFile multipartFile){

        //El nombre original del archivo
        String originalName = multipartFile.getOriginalFilename();

        //Primero verificamos que no sea nulo el nombre y luego obtenemos la extension
        assert originalName != null;
        String extension = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();

        //Asignamos nuevo nombre con randonUUID
        String filename = UUID.randomUUID() + extension;

        //fecha que se creo y fecha de expiracion en Instant
        Long create = Instant.now().toEpochMilli();
        Long exp = Instant.now().plusMillis(7884000000L).toEpochMilli();

        //URL publica segun Contexto(localhost, dominio-propio.com, prueba-server-test.host)
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/"+folder)
                .path("/image/")
                .path(filename)
                .toUriString();

        return ImageEntity.builder()
                .id( ImageEntityRepository.count() )
                .originalName( originalName )
                .fileName(filename)
                .create( create )
                .exp( exp )
                .path( Paths.get(uploadDir, "image") )
                .url( url )
                .contentType( multipartFile.getContentType() )
                .extension(extension)
                .build();
    }

}
