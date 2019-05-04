package com.billt.core.datasourcebase.services;


import com.billt.core.datasourcebase.Exceptions.FileStorageException;
import com.billt.core.datasourcebase.Exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl {

    private Path fileStorageLocation;

   // @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Autowired
    public FileStorageServiceImpl() {
        uploadDirectory = "./uploads";
        this.fileStorageLocation = Paths.get(uploadDirectory)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..") || file.isEmpty()) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName){
        try {
            Resource resource = null;
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            resource = new UrlResource(filePath.toUri());
            if(!resource.exists()) {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
            return resource;
        } catch (MalformedURLException ex) {
           throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}