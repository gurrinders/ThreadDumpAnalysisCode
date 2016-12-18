package com.deadlock;

public class TestCase1 {

	String str1 = "Java";
	String str2 = "UNIX";

	Thread trd1 = new Thread("My Thread 1") {
		public void run() {
			while (true) {
				// Here the deadlock will occur
				synchronized (str1) {
					synchronized (str2) {
						System.out.println(str1 + str2);
					}
				}
			}
		}
	};

	Thread trd2 = new Thread("My Thread 2") {
		public void run() {
			while (true) {
				// here the deadlock occurs
				synchronized (str2) {
					synchronized (str1) {
						System.out.println(str2 + str1);
					}
				}
			}
		}
	};

	public static void main(String a[]) {
		TestCase1 mdl = new TestCase1();
		mdl.trd1.start();
		mdl.trd2.start();
	}
}