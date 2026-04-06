package com.api.image.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    private Long id;
    private Path path;
    private String url;
    private String originalName;
    private String fileName;
    private String extension;
    private String contentType;
    private Long create;
    private Long exp;

}
