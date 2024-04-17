package com.mybatis.springminiproject.controller;
import com.mybatis.springminiproject.model.File;
import com.mybatis.springminiproject.model.dto.response.ApiResponse;
import com.mybatis.springminiproject.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/files")
public class FileController {
    private final FileService fileService;
    @PostMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String fileName = fileService.saveFile(file);
        String fileUrl = "http://localhost:8080/files?fileName=" + fileName;
        File fileResponse = new File(fileName, fileUrl, file.getContentType(), file.getSize());
        ApiResponse<File> response = ApiResponse.<File>builder()
                .message("successfully uploaded file")
                .status(HttpStatus.OK)
                .payload(fileResponse).dateTime(LocalDateTime.now()).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getFile(@RequestParam String fileName) throws IOException {
        Resource resource = fileService.getFileByFileName(fileName);
        MediaType mediaType;
        if (fileName.endsWith(".pdf")) {
            mediaType = MediaType.APPLICATION_PDF;
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
            mediaType = MediaType.IMAGE_PNG;
        } else {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .contentType(mediaType).body(resource);
    }
}

