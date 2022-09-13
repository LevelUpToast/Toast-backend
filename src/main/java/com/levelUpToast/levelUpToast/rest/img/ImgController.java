package com.levelUpToast.levelUpToast.rest.img;

import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.img.ImgRequestForm;
import com.levelUpToast.levelUpToast.domain.dataForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.model.img.ImgItem;
import com.levelUpToast.levelUpToast.service.img.imgServiceInf.ImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImgController {
    private final ImgService imgService;


    @GetMapping("/img/{imgName}") // img resource 요청
    public Resource resImg(@PathVariable String imgName) throws MalformedURLException {
        log.info("[ImgController log] : 이미지 요청 성공 uploadImgName = {}", imgName);
        return new UrlResource("file:" + imgService.getFullPath(imgName));
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
