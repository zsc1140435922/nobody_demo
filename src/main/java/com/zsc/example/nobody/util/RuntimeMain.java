package com.zsc.example.nobody.util;

public class RuntimeMain {
	public static void main(String[] args) {
		int a = Runtime.getRuntime().availableProcessors();
		System.out.println(a);
	}
}
