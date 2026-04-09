package com.api.image.domain.repository;

import com.api.image.domain.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageEntityRepository extends JpaRepository<ImageEntity, Long> {

    Optional<ImageEntity> findByFileName(String fileName);

}
