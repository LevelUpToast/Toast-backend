package com.levelUpToast.levelUpToast.rest.search;

import com.levelUpToast.levelUpToast.domain.dataForm.search.SearchResponseForm;
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
    public SearchResponseForm signUp() {

        return new SearchResponseForm(-1, "", null);
    }

    @GetMapping("/search/{SearchKeyword}")
    public SearchResponseForm signUp(@PathVariable("SearchKeyword") String userMail) {

        return new SearchResponseForm(9999, "", null);
    }
}
