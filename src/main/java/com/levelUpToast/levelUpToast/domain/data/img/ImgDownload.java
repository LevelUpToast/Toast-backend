package com.levelUpToast.levelUpToast.domain.data.img;

import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import javax.mail.Header;

@Data
public class ImgDownload {
    Resource resource;
    HttpHeaders header;

    public ImgDownload(Resource resource, HttpHeaders headers) {
        this.resource = resource;
        this.header = headers;
    }
}
