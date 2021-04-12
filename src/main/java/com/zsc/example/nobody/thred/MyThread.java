package com.zsc.example.nobody.thred;

import java.util.Date;

public class MyThread extends Thread {
	public static int flg = 100;
	private String name;
    public MyThread(String name) {
       this.name=name;
    }
	private int ticket = 5;
	Date data = new Date();
	
	public void run(){
//		int ticket = 5;
//		for(int i=0;i<10;i++){
//			if (i>ticket){
//				System.out.println(name+"第"+i+"次");
//			}
//			
//		}
//		ticket++;
//		ticket = ticket+12;
//		System.out.println(name+"---------"+ticket);
//		
//		System.out.println(name+"---------"+data);
		
		if("A".equals(name)){
			ticket++;
			flg++;
			
		}
		System.out.println(name+"---------"+flg);
//		try {
//			Thread.sleep(10000);
//			data = new Date();
//			System.out.println(name+"---------"+data);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}

