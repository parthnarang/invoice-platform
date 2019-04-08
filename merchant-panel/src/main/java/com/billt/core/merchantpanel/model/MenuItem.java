package com.billt.core.merchantpanel.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuItem {
    Long id;
    String name;
    Long categoryId;
    String message;
    String category;
    Boolean isError;
    Integer price;
    MultipartFile file;
}
