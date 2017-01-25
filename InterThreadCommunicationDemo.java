package com.dash.abinash.InterThreadCommunication;

public class InterThreadCommunicationDemo {
	public static void main(String args[]){
		OddEvenIdentification obj = new OddEvenIdentification();
		OddThread ref = new OddThread(obj);
		EvenThread ref1=new EvenThread(obj);
		Thread t1=new Thread(ref);
		Thread t2=new Thread(ref1);
		t1.start();
		t2.start();
	}
}

class OddEvenIdentification {

	public OddEvenIdentification() {
		super();
	}

	public synchronized void printEvenNumbers() throws InterruptedException {
		for (int i = 0; i <= 20; i++) {
			if (i % 2 == 0) {
				System.out.println("Even Number :" + i);
				notify();
			} else {
				wait();
			}
		}
	}

	public synchronized void printOddNumbers() throws InterruptedException {
		for (int i = 0; i <= 20; i++) {
			if (i % 2 != 0) {
				System.out.println("Odd Number :" + i);
				wait();
			} else {
				notify();
			}
		}
	}
}

class OddThread implements Runnable {
	OddEvenIdentification ref;

	public OddThread(OddEvenIdentification ref) {
		this.ref = ref;
	}

	public void run() {
		try {
			ref.printOddNumbers();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class EvenThread implements Runnable {
	OddEvenIdentification ref;

	EvenThread(OddEvenIdentification ref) {
		this.ref = ref;
	}

	public void run() {
		try {
			ref.printEvenNumbers();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}