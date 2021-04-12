package com.zsc.example.nobody.thred;


public class Main {
	public static void main(String[] args) {
		MyThread mTh1=new MyThread("A");
		MyThread mTh2=new MyThread("B");
		mTh1.start();
		mTh2.start();

	}

}