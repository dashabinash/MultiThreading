package com.dash.abinash.ThreadSynchronization;

public class SynchronizationDemo {
	public static void main(String... arguments) {
		Account account = new Account(1000);
		Customer c1 = new Customer(account, "Abinash");
		Customer c2 = new Customer(account, "Sekhar");
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		t2.start();
		t1.start();	
	}
}

class Account {

	private int bal;

	public Account(int bal) {
		this.bal = bal;
	}

	public boolean isSufficientBalance(int w) {
		if (bal > w) {
			return(true);
		} else {
			return(false);
		}
	}

	public void withdrawAmount(int amt) {
		bal=bal-amt;
		System.out.println("Withdrawl amount :" + amt);
		System.out.println("Remaining balance :" + bal);
	}
}

class Customer implements Runnable {

	java.util.Scanner sc = new java.util.Scanner(System.in);
	private Account account;
	private String name;

	public Customer(Account account, String name) {
		this.account = account;
		this.name = name;
	}

	@Override
	public void run() {
		synchronized (account) {
			System.out.println(name + ", Enter amount to withdraw :");
			int amount = sc.nextInt();
			if (account.isSufficientBalance(amount)) {
				System.out.println("Name :" + name);
				account.withdrawAmount(amount);
			} else {
				System.out.println("Insufficient balance.");
			}
		}
	}

}
