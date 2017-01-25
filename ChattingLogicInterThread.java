package com.dash.abinash.InterThreadCommunication;

public class ChattingLogicInterThread {
	public static void main(String[] args){
		ChattingLogic cl=new ChattingLogic();
		new SenderThread(cl);
		new RecevierThread(cl);
	}
}

class ChattingLogic{
	boolean flag=false;
	
	public synchronized void senderMsg(String message) throws InterruptedException{
		if(flag){
			wait();
		}
		else{
			System.out.println("Sender :"+message);
			flag=true;
			notify();
		}
	}
	
	public synchronized void recevierMsg(String message) throws InterruptedException{
		if(!flag){
			wait();
		}
		else{
			System.out.println("Recevier :"+message);
			flag=false;
			notify();
		}
		
	}
}

class SenderThread implements Runnable{
	ChattingLogic cl;
	String[] s={"Hi","How are you ?","I am also doing fine."};
	
	public SenderThread(ChattingLogic cl){
		this.cl=cl;
		new Thread(this,"Questions").start();
	}

	@Override
	public void run() {
		for(int i=0;i<s.length;i++){
			try {
				cl.senderMsg(s[i]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class RecevierThread implements Runnable{
	ChattingLogic cl;
	String[] r={"Hi","I am good,What about you ?","Great"};
	
	public  RecevierThread(ChattingLogic cl) {
		this.cl=cl;
		new Thread(this,"Answers").start();
	}

	@Override
	public void run() {
		for(int j=0;j<r.length;j++){
			try {
				cl.recevierMsg(r[j]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
