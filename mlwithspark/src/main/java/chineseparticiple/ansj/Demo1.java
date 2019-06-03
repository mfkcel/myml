package chineseparticiple.ansj;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.Test;

import java.util.List;

/**
 * create by mfkcel on 2019/6/3 7:18
 * ansj 的基本使用
 */
public class Demo1 {
    @Test
    public void test1() {
        String sentence = "让战士们过一个欢乐祥和的新春佳节。";
        // 基本的分词
        Result parse1 = BaseAnalysis.parse(sentence);

        // 精准分词
        Result parse2 = ToAnalysis.parse(sentence);

        System.out.println(parse1);
        System.out.println(parse2);
    }

    @Test
    public void test2() {
        //nlp 分词用于对文本进行发现分析工作
        String sentence = "洁面仪配合洁面深层清洁毛孔 清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹 " +
                "脸颊毛孔修复的看不见啦 草莓鼻历史遗留问题没辙 脸和脖子差不多颜色的皮肤才是健康的" +
                " 长期使用安全健康的比同龄人显小五到十岁 28岁的妹子看看你们的鱼尾纹";
        Result result1 = NlpAnalysis.parse(sentence);
        Result result2 = ToAnalysis.parse(sentence);

        System.out.println(result1);
        System.out.println(result2);
    }

    @Test
    public void test3() {
        // 面向索引的分词，这个不是太理解
        String sentence = "主副食品";
        Result result = IndexAnalysis.parse(sentence);
        System.out.println(sentence);
    }

}
