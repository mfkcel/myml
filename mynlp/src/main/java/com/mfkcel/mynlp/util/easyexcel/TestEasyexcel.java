package com.mfkcel.mynlp.util.easyexcel;

//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelReader;
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import com.alibaba.excel.read.metadata.ReadSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * 为表格建立的POJO应该是public class类型的，不能是默认的访问类型
 */

/**
 * @author mc1381288@163.com
 * @date 2019/11/7 13:42
 */
public class TestEasyexcel {
    public static void main(String[] args) {
        String fileName = "D:\\myfile\\myml\\mynlp\\src\\main\\resources\\Comprehensive Thesaurus of chinese.xlsx";

        // 写法1
        // 需要指定使用哪个class去读，然后读取第一个sheet  文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

        // 写法2
//        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        //使用后，一定要记得关闭,读的时候会创建临时文件，到时磁盘会崩溃的
//        excelReader.finish();
    }
}

