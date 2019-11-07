package com.mfkcel.mynlp.util.easyexcel;

//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mc1381288@163.com
 * @date 2019/11/7 14:28
 *
 * easyexcel的简单使用
 */
//public class DemoDataListener extends AnalysisEventListener<DemoData> {
//    List<DemoData> list = new ArrayList<DemoData>();
//
//    public DemoDataListener() {
//
//    }
//
//    /**
//     * 每一条数据解析时都会调用
//     * @param data
//     * @param analysisContext
//     */
//    @Override
//    public void invoke(DemoData data, AnalysisContext analysisContext) {
//        list.add(data);
//        // 这里已取得数据，可以按照业务逻辑对数据做相应的处理
////        System.out.println(data);
//    }
//
//    /**
//     * 所有数据解析完成了 会来调用
//     * @param analysisContext
//     */
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        Writer out = null;
//        try {
//            out = new FileWriter("chinese_word.dict");
//            for(DemoData data : list) {
//                out.write(data.toString());
//                out.write('\n');
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(out != null) {
//                try {
//                    out.close();
//                } catch (Exception e) {
//
//                }
//            }
//
//            System.out.println("end!");
//        }
//    }
//}
