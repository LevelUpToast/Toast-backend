package com.levelUpToast.levelUpToast.service.function.wordAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface WordAnalysis {
    List<String> extractionNouns(String text) throws Exception;

    ArrayList<String> wordCount(List<String> pList, int index) throws Exception;

    ArrayList<String> wordAnalysis(String text, int index) throws Exception;
}
