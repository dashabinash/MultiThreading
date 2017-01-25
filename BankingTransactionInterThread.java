package com.dash.abinash.InterThreadCommunication;


public class BankingTransactionInterThread {
	public static void main(String[] args) {
		TransactionLogic tl = new TransactionLogic(15000);
		WithdrawThread wt = new WithdrawThread(tl);
		DepositThread dt = new DepositThread(tl);
		Thread t1 = new Thread(wt);
		Thread t2 = new Thread(dt);
		t1.start();
		t2.start();

	}
}

class TransactionLogic{
	private double balance;
	
	TransactionLogic(double balance) {
		this.balance = balance;
	}

	public synchronized void withdrawMoney(double amount) throws InterruptedException {
		if (amount > balance) {
			System.out.println("Insufficient balance to withdraw...");
			wait();
			balance = balance - amount;
			System.out.println(" if part,Remaining balance :" + balance);
		} else {
			balance = balance - amount;
			System.out.println(" else part,Remaining balance :" + balance);
		}
	}

	public synchronized void depositMoney(double depositamount) throws InterruptedException {
		balance = balance + depositamount;
		System.out.println("Balance after deposit: " + balance);
		notify();
	}
}

class WithdrawThread implements Runnable {
	TransactionLogic tl;

	public WithdrawThread(TransactionLogic tl) {
		this.tl = tl;
	}

	@Override
	public void run() {
		try {
			tl.withdrawMoney(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class DepositThread implements Runnable {
	TransactionLogic tl;

	public DepositThread(TransactionLogic tl) {
		this.tl = tl;
	}

	@Override
	public void run() {
		try {
			tl.depositMoney(40000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
