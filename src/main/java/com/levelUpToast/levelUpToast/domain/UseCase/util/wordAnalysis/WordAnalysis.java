package com.levelUpToast.levelUpToast.domain.UseCase.util.wordAnalysis;

import java.util.ArrayList;
import java.util.List;

public interface WordAnalysis {
    List<String> extractionNouns(String text) throws Exception;

    String wordCount(List<String> pList, int index) throws Exception;

    String wordAnalysis(String text, int index) throws Exception;
}
