package com.deadlock;

class Resource1 {

	public void getValue() {
		System.out.println("--- In Resource 1 ----");

	}
}

class Resource2 {

	public void getValue() {
		System.out.println("--- In Resource 2 ---- ");
	}
}

class Printer1 implements Runnable {

	Resource1 r1;
	Resource2 r2;

	Printer1(Resource1 r1, Resource2 r2) {
		this.r1 = r1;
		this.r2 = r2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		while (true) {
			synchronized (r1) {
				r1.getValue();
//
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

				synchronized (r2) {
					r2.getValue();
				}
//			}
		}
	}

}

class Printer2 implements Runnable {

	Resource1 r1;
	Resource2 r2;

	Printer2(Resource1 r1, Resource2 r2) {
		this.r1 = r1;
		this.r2 = r2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	//	while (true) {
			synchronized (r2) {
				r2.getValue();

				synchronized (r1) {
					r1.getValue();
				}
			}
	//	}
	}

}

public class CheckDeadLockTest {

	public CheckDeadLockTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		Resource1 res1 = new Resource1();
		Resource2 res2 = new Resource2();

		new Thread(new Printer1(res1, res2)).start();
		new Thread(new Printer2(res1, res2)).start();

	}

}
