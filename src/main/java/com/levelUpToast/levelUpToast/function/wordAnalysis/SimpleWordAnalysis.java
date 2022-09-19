package com.levelUpToast.levelUpToast.function.wordAnalysis;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;

import java.util.*;

/**
 * SimpleWordAnalysis 입력 받은 데이터를 받아 명사만 추출하고,
 * 명사 사용횟수를 Map 형식으로 담아서 Return 하는 객체
 * Return 형식은 ArrayList<String> 이다.
 */
public class SimpleWordAnalysis implements WordAnalysis {
    Komoran NLP = new Komoran(DEFAULT_MODEL.LIGHT);

    /**
     * extractionNouns 자연어 처리 - 형태소 분석하여 명사만 추출하기
     *
     * @param keyWord 분석 대상 단어를 입력을 받는 파라미터
     * @return 입력받은 keyWord 분석후 특수문자, 한국어, 영어, 숫자 제외 단어 모두 한 칸으로 변환시키고, 분석할 문장의 앞, 뒤에 존재할 수 있는 필요없는 공백 제거하고 명사만 추출하고 Return
     */
    @Override
    public List<String> extractionNouns(String keyWord) {
        return NLP.analyze(keyWord.replace("[^가-힣a-zA-Z0-9", " ").trim()).getNouns();
    }

    /**
     * wordCount 입력받는 단어 빈도수 분석하고 Return 메소드
     *
     * @param keyWord 분석 대상 단어를 입력을 받는 param, keyWord_Set 변수에 Keyword 넣어 중복되지 않은 단어만 저장하고 나머지는 자동 삭제합니다.
     * @return 분석된 KeyWord 단어가 중복 저장되어 있는 keyWordSet 단어의 빈도수 측정후 입력 받은 index 보다 같거나 크면 넣고 계산하고 조건에 맞는 단어 Return,
     */
    @Override
    public ArrayList<String> wordCount(List<String> keyWord, int index) {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> keyWordSet = new HashSet<>(keyWord);

        for (String text : keyWordSet)
            if (Collections.frequency(keyWord, text) >= index) arrayList.add(text);
        return arrayList;
    }

    /**
     * wordAnalysis 분석 대상의 텍스트를 입력 받아 명사와 사용된 갯수를 분석하는 메소드
     *
     * @param text 분석 대상의 Text 입력받는 param
     * @return 분석이 완료된 데이터를 Return
     */
    @Override
    public ArrayList<String> wordAnalysis(String text, int index) {
        return wordCount(extractionNouns(text), index);
    }

}
