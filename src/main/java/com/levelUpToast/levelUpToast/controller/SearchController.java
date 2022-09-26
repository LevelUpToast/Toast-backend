package com.levelUpToast.levelUpToast.controller;

import com.levelUpToast.levelUpToast.domain.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.service.search.SimpleSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SimpleSearchService simpleSearchService;

    /**
     * 검색페이지 요청 하는 메소드, 데이터는
     * @return 요청이 끝나면 ResponseForm 반환한다.
     */
    @GetMapping("/search")
    public ResponseForm<Object> search() {

        return new ResponseForm<>(-1, "", null);
    }

    /**
     * 클라이언트로 부터 요청 받은 내용으로 데이터 범위와 검색내용을 받는다.
     * @param index 클라이언트로 요청 받은 인덱스 범위
     * @param SearchKeyword 사용자가 검색할 내용을 받는다.
     * @return 요청이 끝나면 ResponseForm 반환한다.
     */
    @GetMapping("/search/{index}/{SearchKeyword}")
    public ResponseForm<Object> Search(@PathVariable("index") int index,@PathVariable("SearchKeyword") String SearchKeyword) {
        Map<String, Object> data = new LinkedHashMap<>();
        log.info("[SearchController log] 입력받은 검색 요청 요청된 검색어 = \"{}\"\t 검색 index 범위 = {}", SearchKeyword, index);
        data.put("SearchProduct", simpleSearchService.SearchProduct(SearchKeyword, index));
        if (data.get("SearchProduct") == null)
            return new ResponseForm<>(-1, "검색 요청 데이터 없음", null);
        return new ResponseForm<>(-1, "검색내용 데이터 요청", data);
    }
}
