package com.levelUpToast.levelUpToast.rest.img;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.img.ImgRequestForm;
import com.levelUpToast.levelUpToast.domain.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.data.img.ImgDownload;
import com.levelUpToast.levelUpToast.domain.data.img.ImgItem;
import com.levelUpToast.levelUpToast.service.img.ImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImgController {
    private final ImgService imgService;


    @GetMapping("/img/{imgName}") // img resource 요청
    public ResponseEntity<Resource> resImg(@PathVariable String imgName) throws IOException {
        Path path = Paths.get(imgService.getImg(imgName));
        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/img") // img 저장 (미완성)
    public ResponseForm<String> reqImg(ImgRequestForm imgRequestForm) {
        Map<String, String> data = new HashMap<>();
        try {
            // 이미지 저장
            ImgItem imgItem = imgService.saveImg(imgRequestForm.getImgFile());
            data.put("imgURL", imgItem.getStoreFileName());
            log.info("[ImgController log] : 이미지 저장 성공 originalImgName = {}, uploadImgName = {}", imgItem.getUploadFileName(), imgItem.getStoreFileName());
            return new ResponseForm<>(-1, "img 이름 : " + imgItem.getUploadFileName() + " 저장완료", data);
        } catch (Exception e) {
            log.info("Ex = {}", e.toString());
            return new ResponseForm<>(112, e.getMessage(), null);
        }

    }


}
