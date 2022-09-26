package com.levelUpToast.levelUpToast.domain.UseCase.wordAnalysis;

import java.util.ArrayList;
import java.util.List;

public interface WordAnalysis {
    List<String> extractionNouns(String text) throws Exception;

    ArrayList<String> wordCount(List<String> pList, int index) throws Exception;

    ArrayList<String> wordAnalysis(String text, int index) throws Exception;
}
