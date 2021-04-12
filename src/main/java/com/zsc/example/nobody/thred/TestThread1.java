package com.zsc.example.nobody.thred;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestThread1 {

   public static void main(final String[] arguments){

      Map<String,String> map = new ConcurrentHashMap<String, String>();

      map.put("1", "One");
      map.put("2", "Two");
      map.put("3", "Three");
      map.put("5", "Five");
      map.put("6", "Six");

      System.out.println("Initial ConcurrentHashMap: "+map);
      Iterator<String> iterator = map.keySet().iterator();

      try{ 
         while(iterator.hasNext()){
            String key = iterator.next();
            if(key.equals("3")) {
               map.put("4", "Four");
            }
         }
      }catch(ConcurrentModificationException cme){
         cme.printStackTrace();
      }
      System.out.println("ConcurrentHashMap after modification: "+map);

      map = new HashMap<String, String>();

      map.put("1", "One");
      map.put("2", "Two");
      map.put("3", "Three");
      map.put("5", "Five");
      map.put("6", "Six");

      System.out.println("Initial HashMap: "+map);
      iterator = map.keySet().iterator();

      try{
         while(iterator.hasNext()){
            String key = iterator.next();
            if(key.equals("3")) { 
               map.put("4", "Four");
            }
         }
         System.out.println("HashMap after modification: "+map);
      }catch(ConcurrentModificationException cme){
         cme.printStackTrace();
      }
   }  
}
/*
Initial ConcurrentHashMap: {1=One, 5=Five, 6=Six, 3=Three, 2=Two}
ConcurrentHashMap after modification: {1=One, 5=Five, 6=Six, 3=Three, 4=Four, 2=Two}
Initial HashMap: {3=Three, 2=Two, 1=One, 6=Six, 5=Five}*/
