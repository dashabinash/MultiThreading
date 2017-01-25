package com.dash.abinash.ThreadSynchronizationSolution;

/*
 * Following program will do the synchronization with synchronized block.
 */

public class CopySolutionWithSynchronization {
	public static void main(String[] args) {
		System.out.println("Execution of CopySolutionWithSynchronization program..");
		Chair c = new Chair();
		MyThread m = new MyThread(c, 5, "Thread-1");
		MyThread m1 = new MyThread(c, 100, "Thread-2");
		Thread t1 = new Thread(m);
		Thread t2 = new Thread(m1);
		t2.start();
		t1.start();
	}
}

class Chair {
	public void printChair(int n) throws InterruptedException {
		for (int i = 1; i <= 5; i++) {
			try {
				System.out.println(n * i);
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
	}
}

class MyThread implements Runnable {

	private Chair c;
	private int n;
	private String name;

	MyThread(Chair c, int n, String name) {
		this.c = c;
		this.n = n;
		this.name = name;
	}

	@Override
	public void run() {
		synchronized (c) {
			try {
				System.out.println(name);
				c.printChair(n);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}