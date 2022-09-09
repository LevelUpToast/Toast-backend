package com.levelUpToast.levelUpToast.rest.search;

import com.levelUpToast.levelUpToast.domain.dataForm.responseForm.ResponseForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    @GetMapping("/search")
    public ResponseForm<Object> signUp() {

        return new ResponseForm<>(-1, "", null);
    }

    @GetMapping("/search/{SearchKeyword}")
    public ResponseForm<Object> signUp(@PathVariable("SearchKeyword") String userMail) {
        return new ResponseForm<>(9999, "", null);
    }
}
