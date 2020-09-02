package com.zsc.example.nobody.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-19 15:00
 **/
@Service
public class ExcelOptionsService {
    /**
     * 根据excel输入流，读取excel文件
     *
     * @param inputStream exece表格的输入流
     * @return 返回双重list的集合
     **/
    public List<List<String>> writeWithoutHead(InputStream inputStream) {
        StringExcelListener listener = new StringExcelListener();
        ExcelReader excelReader = EasyExcelFactory.read(inputStream, null, listener).headRowNumber(0).build();
        List<List<String>> datas = listener.getDatas();
        excelReader.finish();
        return datas;
    }

    public List<ExcelModel> writeWithoutHead1(InputStream inputStream) {
        try{
            ExcelModelListener listener = new ExcelModelListener();
            Sheet sheet = new Sheet(1,1,ExcelModel.class);
            EasyExcelFactory.readBySax(inputStream,sheet,listener);
            List<ExcelModel> datas = listener.getList();
            return datas;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * StringList 解析监听器
     *
     * @author zhangcanlong
     * @since 2019-10-21
     */
    private static class StringExcelListener extends AnalysisEventListener {

        /**
         * 自定义用于暂时存储data
         * 可以通过实例获取该值
         */
        private List<List<String>> datas = new ArrayList<>();

        /**
         * 每解析一行都会回调invoke()方法
         *
         * @param object  读取后的数据对象
         * @param context 内容
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            @SuppressWarnings("unchecked") Map<String, String> stringMap = (HashMap<String, String>) object;
            //数据存储到list，供批量处理，或后续自己业务逻辑处理。
            datas.add(new ArrayList<>(stringMap.values()));
            //根据自己业务做处理
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
            //注意不要调用datas.clear(),否则getDatas为null
        }

        /**
         * 返回数据
         *
         * @return 返回读取的数据集合
         **/
        public List<List<String>> getDatas() {
            return datas;
        }

        /**
         * 设置读取的数据集合
         *
         * @param datas 设置读取的数据集合
         **/
        public void setDatas(List<List<String>> datas) {
            this.datas = datas;
        }
    }


    public class ExcelModelListener extends AnalysisEventListener<ExcelModel> {

        /**
         * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
         */
        private static final int BATCH_COUNT = 100;
        List<ExcelModel> list = new ArrayList<ExcelModel>();
        public volatile int count = 1;
        @Override
        public void invoke(ExcelModel data, AnalysisContext context) {
            System.out.println("解析到一条数据:{ "+ data.toString() +" }");
            list.add(data);
            count ++;
            if (list.size() >= BATCH_COUNT) {
                saveData( count );
                list.clear();
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            saveData( count );
            System.out.println("所有数据解析完成！");
            System.out.println(" count ：" + count);
        }

        /**
         * 加上存储数据库
         */
        private void saveData(int count) {
            System.out.println("{ "+ count +" }条数据，开始存储数据库！" + list.size());
            System.out.println("存储数据库成功！");
        }

        /**
         * 获取list
         *
         * @return the list
         */
        public List<ExcelModel> getList() {
            return list;
        }
    }
}
