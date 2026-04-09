package com.api.image.controller.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ImageEntityDTO(String originalName, String fileName,
                             String url, String extension,
                             String contentType, Instant exp) {
}
