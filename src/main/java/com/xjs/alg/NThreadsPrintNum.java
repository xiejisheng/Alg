package com.xjs.alg;

import java.util.concurrent.atomic.AtomicInteger;

public class NThreadsPrintNum {
	
	public static void main(String[] args) {
		// busy wait
//		printNum(3, 100);
		// wait notify
		 printNum1(3, 100);
	}

	private static void printNum(int nthreads, int n) {
		// corner case

		AtomicInteger ai = new AtomicInteger(0);
		int[] arr = new int[nthreads];
		arr[0] = 1;
		for (int i = 0; i < nthreads; i++) {
			int num = i;

			new Thread(new Runnable() {

				@Override
				public void run() {
					for (;;) {
						// TODO spin
						while (arr[num] != 1) {
							Thread.yield();
						}
						
						int nextNum = num+1 == nthreads ? 0 : num+1;
						if (ai.get() > n) {
							arr[num] = 0;
							arr[nextNum] = 1;
							break;
						}
						System.out.println("thread" + num + ": " + ai.getAndIncrement());
						
						arr[num] = 0;
						arr[nextNum] = 1;
					}
				}
			}).start();
		}
	}

	private static void printNum1(int nthreads, int n) {
		// corner case

		AtomicInteger ai = new AtomicInteger(0);
		WaitNode[] warr = new WaitNode[nthreads];
		for (int i = 0; i < nthreads; i++) {
			warr[i] = new WaitNode();
		}
		warr[0].released = true;
		for (int i = 0; i < nthreads; i++) {
			int num = i;

			new Thread(new Runnable() {

				@Override
				public void run() {
					for (;;) {
						// signal
						WaitNode currw = warr[num];
						int nextNum = num+1 == nthreads ? 0 : num+1;
						WaitNode nextw = warr[nextNum];
						
						wait(currw);
						
						if (ai.get() > n) {
							warr[num].inter();
							release(currw, nextw);
							break;
						}
						
						System.out.println("thread" + num + ": " + ai.getAndIncrement());
						
						release(currw, nextw);
					}
				}

				private void wait(WaitNode wn) {
					try {
						wn.doWait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}

				private void release(WaitNode currw, WaitNode nextw) {
					currw.released = false;
					try {
						nextw.doNotify();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}).start();
		}
	}

	static class WaitNode {
		public boolean released = false;

		public synchronized void doWait() throws InterruptedException {
			try {
				while (!released)
					wait();
			} catch (InterruptedException ie) {
				if (!released) {
					released = true;
					throw ie;
				} else {
					Thread.currentThread().interrupt();
				}
			}
		}

		public synchronized void doNotify() throws InterruptedException {
			released = true;
			notify();
		}
		
		public void inter() {
			Thread.currentThread().interrupt();
		}
	}
}
