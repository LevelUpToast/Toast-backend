package com.levelUpToast.levelUpToast.bodyForm.requestForm.img;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImgRequestForm {
    private MultipartFile imgFile;
}
