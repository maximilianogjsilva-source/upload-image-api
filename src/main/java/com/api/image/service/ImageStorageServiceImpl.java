package com.api.image.service;

import com.api.image.controller.dto.ImageEntityDTO;
import com.api.image.domain.entity.ImageEntity;
import com.api.image.domain.repository.ImageEntityRepository;
import com.api.image.mapper.ImageEntityMapper;
import com.api.image.service.util.FilePathhandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ImageStorageServiceImpl implements ImageStorageService{

    @Value("${image.upload.dir:opt/upload}")
    private String uploadDir;

    @Autowired
    private ImageEntityRepository imageEntityRepository;

    @Autowired
    private ImageEntityMapper mapper;

    @Autowired
    private FilePathhandler filePathhandler;


    @Override
    public List<ImageEntityDTO> getAll() {
        return this.imageEntityRepository.findAll().stream().map(mapper::imageEntityDTO).toList();
    }

    @Override
    public Optional<ImageEntityDTO> getImageById(Long id) {
        return this.imageEntityRepository.findById(id).map(mapper::imageEntityDTO);
    }

    @Override
    public Optional<ImageEntityDTO> saveImage(MultipartFile multipartFile) {
        return this.saveImage(multipartFile, "image");
    }

    @Override
    public Optional<ImageEntityDTO> saveImage(MultipartFile multipartFile, String fold) {

        //El nombre original del archivo
        String originalName = multipartFile.getOriginalFilename();

        //Primero verificamos que no sea nulo el nombre y luego obtenemos la extension
        assert originalName != null;
        String extension = originalName.substring( originalName.lastIndexOf(".") )
                .toLowerCase();

        //Asignamos nuevo nombre con randomUUID
        String filename = UUID.randomUUID() + extension;

        //URL publica segun Contexto(localhost, dominio-propio.com, prueba-server-test.host)
        //Y copiamos el contenido del multipartFile dentro del directorio creado
        String url = this.filePathhandler.copyContent(multipartFile, fold, filename);

        //fecha que se creo y fecha de expiracion en Instant
        Instant create = Instant.now();
        Instant exp = Instant.now().plusMillis(7884000000L);

        //Mappeamos a un tipo ImageEntity
        ImageEntity imageEntity = ImageEntity.builder()
                .originalName( originalName )
                .fileName(filename)
                .extension(extension)
                .path(uploadDir+fold)
                .contentType( multipartFile.getContentType() )
                .url(url)
                .create( create )
                .exp( exp )
                .build();

        return Optional.of(
                mapper.imageEntityDTO(this.imageEntityRepository.save(imageEntity))
        );
    }

    @Override
    public Optional<ImageEntityDTO> deleteImage(String fileName){

        //Recupero el imageEntity
        return this.imageEntityRepository.findByFileName(fileName).map((image)->{

            //Elimino el archivo
            this.filePathhandler.deleteFile(image.getPath(), image.getFileName());

            //Elimino el imageEntity de la BD
            this.imageEntityRepository.delete(image);
            return mapper.imageEntityDTO(image);
        });

    }


}
