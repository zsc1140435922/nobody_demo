package com.zsc.example.nobody.poi;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

public class QueryExcel {
	public static void main(String[] args) {  
        File file = new File("d:/koubeishop_20180412.xls");  
        ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);  
        for(int i = 0 ;i < result.size() ;i++){  
        	//第i行
            ArrayList<Object> row = result.get(i);
//            String name = row.get(0).toString();
//            String category_id = row.get(1).toString();
//            String parent_id_1 = row.get(3).toString();
//            String parent_id_2 = row.get(5).toString();
//            String parent_id_3 = row.get(7).toString();
//            
//            
//            StringBuffer sql = new StringBuffer("insert into yz_business_category values ");
//			sql.append("('")
//			.append(category_id)
//			.append("','")
//			.append(name)
//			.append("','");
//			if(StringUtils.isNotBlank(parent_id_1) && 
//					StringUtils.isBlank(parent_id_2) && 
//					StringUtils.isBlank(parent_id_3)){
//				sql.append(0);
//			}else if(StringUtils.isNotBlank(parent_id_1) && 
//					StringUtils.isNotBlank(parent_id_2) &&
//					StringUtils.isBlank(parent_id_3)
//					){
//				sql.append(parent_id_1);
//			}else if(StringUtils.isNotBlank(parent_id_1) && 
//					StringUtils.isNotBlank(parent_id_2) &&
//					StringUtils.isNotBlank(parent_id_3)
//					){
//				sql.append(parent_id_2);
//			}
//	
//			sql.append("',");
//			if(StringUtils.isNotBlank(parent_id_1) && 
//					StringUtils.isBlank(parent_id_2) && 
//					StringUtils.isBlank(parent_id_3)){
//				sql.append(1);
//			}else if(StringUtils.isNotBlank(parent_id_1) && 
//					StringUtils.isNotBlank(parent_id_2) &&
//					StringUtils.isBlank(parent_id_3)){
//				sql.append(2);
//			}else if(StringUtils.isNotBlank(parent_id_1) && 
//					StringUtils.isNotBlank(parent_id_2) &&
//					StringUtils.isNotBlank(parent_id_3)){
//				sql.append(3);
//			}
//			sql.append(",0,0");
//			sql.append(");");
//			System.out.println(sql.toString());
            
            
           
          String category_id = row.get(0).toString();
          String name = row.get(1).toString();
          String parent_id_1 = row.get(2).toString();
          String parent_name = row.get(3).toString();
          int level = Integer.valueOf(row.get(4).toString());
          
          
          StringBuffer sql = new StringBuffer("insert into yz_business_category values ");
			sql.append("('")
			.append(category_id)
			.append("','")
			.append(name)
			.append("','");
			if(StringUtils.isBlank(parent_id_1) ){
				sql.append(0);
			}else if(StringUtils.isNotBlank(parent_id_1) ){
				sql.append(parent_id_1);
			}
			sql.append("',");
			sql.append(level);
			sql.append(",0,0");
			sql.append(");");
			System.out.println(sql.toString());
        }  
//        ExcelUtil.writeExcel(result,"F:/excel/bb.xls");  
    } 
}