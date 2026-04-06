package com.api.image.domain.repository;

import com.api.image.domain.entity.ImageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ImageEntityRepository {

    private final List<ImageEntity> imageEntityList = new ArrayList<ImageEntity>();
    private static Long countId = 0L;

    public static Long count(){
        return ++countId;
    }

    public List<ImageEntity> listAll(){
        return this.imageEntityList;
    }

    public Optional<ImageEntity> getById(Long id){
        return this.imageEntityList.stream()
                .filter(imageEntity -> imageEntity.getId().equals(id))
                .findFirst();
    }

    public Optional<ImageEntity> save(ImageEntity imageEntity){
        this.imageEntityList.add(imageEntity);
        return Optional.of(imageEntity);
    }

    public void deleteById(Long id){
        this.imageEntityList.removeIf(imageEntity -> imageEntity.getId().equals(id));
    }

}
