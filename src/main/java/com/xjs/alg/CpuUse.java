package com.xjs.alg;

public class CpuUse {
	public static void main(String[] args) {
		int busyTime = 500;
		int idleTime = busyTime;
		long startTime = 0;
		while (true) {
			startTime = System.currentTimeMillis();
			// busy loop
			while ((System.currentTimeMillis() - startTime) <= busyTime)
				;
			// idle loop
			try {
				Thread.sleep(idleTime);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}
