package com.mfkcel.mynlp.util.easyexcel;

/**
 * @author mc1381288@163.com
 * @date 2019/11/7 14:28
 */
public class DemoData{
    private String word;
    private String wordAttr;
    private Long wordFreq;

    public DemoData() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordAttr() {
        return wordAttr;
    }

    public void setWordAttr(String wordAttr) {
        this.wordAttr = wordAttr;
    }

    public Long getWordFreq() {
        return wordFreq;
    }

    public void setWordFreq(Long wordFreq) {
        this.wordFreq = wordFreq;
    }

    @Override
    public String toString() {
        return  word + "\t" + wordFreq;
    }
}
