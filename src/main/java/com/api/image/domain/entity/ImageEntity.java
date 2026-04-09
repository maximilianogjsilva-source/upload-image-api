package com.api.image.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String url;
    @Column(name = "original_name")
    private String originalName;
    @Column(name = "file_name")
    private String fileName;
    private String extension;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "time_create")
    private Instant create;
    private Instant exp;

}
