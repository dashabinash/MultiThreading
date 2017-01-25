package com.dash.abinash.ThreadSynchronizationSolution;

/*
 * Following program will do the synchronization with synchronized block.
 */
public class SolutionWithSynchronization {
	public static void main(String[] args) {
		System.out.println("Execution of SolutionWithSynchronization program..");
		Table t = new Table();
		MyThread1 m1 = new MyThread1(t);
		MyThread2 m2 = new MyThread2(t);
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m2);
		t1.start();
		t2.start();
	}
}

class Table {
	public void printTable(int n) throws InterruptedException {
		for (int i = 1; i <= 5; i++) {
			try {
				System.out.println(n * i);
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
	}
}

class MyThread1 implements Runnable {

	private Table t;

	MyThread1(Table t) {
		this.t = t;
	}

	@Override
	public void run() {
		synchronized (t) {
			try {
				t.printTable(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	private Table t;

	MyThread2(Table t) {
		this.t = t;
	}

	@Override
	public void run() {
		synchronized (t) {
			try {
				t.printTable(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
