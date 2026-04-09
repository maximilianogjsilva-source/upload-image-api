package com.api.image.controller;

import com.api.image.controller.dto.ImageEntityDTO;
import com.api.image.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/image")
public class ImgStorageController {


    @Autowired
    private ImageStorageService imageStorageService;


    //Falta corregir parámetros de entrada
    @PostMapping("/upload/v1")
    public ResponseEntity<ImageEntityDTO> uploadImageV1(
            @Validated @RequestParam("image") MultipartFile image,
            @RequestParam("folder") String folder) {
        return this.imageStorageService.saveImage(image, folder).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    //Ambos tipos de parámetros están permitidos tanto si quiere parametrizar como si no
    @PostMapping("/upload/v2")
    public ResponseEntity<ImageEntityDTO> uploadImageV2(
            @Validated @RequestParam("image") MultipartFile image) {
        return this.imageStorageService.saveImage(image).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/get/v1/{id}")
    public ResponseEntity<ImageEntityDTO> getImage(@PathVariable Long id){
        return this.imageStorageService.getImageById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //El unico que funciona bien por el momento - falta retocar para que devuelva DTO
    @GetMapping("/list-all/v1")
    public ResponseEntity<List<ImageEntityDTO>> listAll(){
        return ResponseEntity.ok(this.imageStorageService.getAll());
    }

    //Falta completar para eliminar los datos en base de datos y las carpetas con las imagenes
    @DeleteMapping("/delete-all/v1")
    public ResponseEntity<?> deleteAll(){
        return ResponseEntity.noContent().build();
    }

    //Falta completar para eliminar uno solo dependiendo del dato recibido
    @DeleteMapping("/delete/v1")
    public ResponseEntity<?> delete(@RequestParam("fileName") String fileName) {
        return this.imageStorageService.deleteImage(fileName)
                .map( (image)->ResponseEntity.noContent().build() )
                .orElse(ResponseEntity.notFound().build());
    }



}
