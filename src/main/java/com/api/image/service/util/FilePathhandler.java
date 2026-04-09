package com.api.image.service.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class FilePathhandler {

    @Value("${image.upload.dir:opt/upload}")
    private String uploadDir;

    @Value("${image.upload.folder:uploads}")
    private String assignedFolder;

    public String copyContent(MultipartFile multipartFile, String folder, String name){

        Path folderPath = Paths.get(uploadDir, folder);

        try {
            //Creamos Directorio del path de la imagen
            if( !Files.exists(folderPath) ) {
                Files.createDirectories(folderPath);
            }

            //Copiamos el contenido del multipartFile dentro del directorio de la imagen
            Files.copy(multipartFile.getInputStream(),
                    folderPath.resolve(name),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Hay un HORROR en COPYCONTENT-FILEPATHHANDLER");
            throw new RuntimeException(e);
        }

        return getUrl(folder, name);
    }

    public void deleteFile(String path, String fileName){
        try {
            Path folderPath = Paths.get(path);
            if( Files.exists(folderPath.resolve(fileName)) ){
                log.info("EXISTE la carpeta: {} deberia eliminarse", folderPath);
            }

            Files.deleteIfExists( folderPath.resolve(fileName) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String getUrl(String folder, String name) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/"+this.assignedFolder)
                .path("/"+folder)
                .path("/"+name)
                .toUriString();
    }

}
