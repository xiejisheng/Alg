package com.xjs.alg;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
	final static int size = 4;
	final static Map<Integer, ArrayBlockingQueue<String>> msgList = new HashMap<>();
	{
		for (int i = 0; i < 4; i++) {
			msgList.put(i, new ArrayBlockingQueue<String>(100));
		}
	}

	public void producer(String msg) {
		int index = msg.hashCode() % size;
		msgList.get(index).offer(msg);
	}

	// 前提，可以把指定线程挂在指定的core上
	public void consumer() {
		new Thread(new Runnable() {
			BlockingQueue<String> q0 = msgList.get(0);

			@Override
			public void run() {
				while (true) {
					String msg = q0.poll();
					// handle(msg)
				}
				
			}
		}).start();
		new Thread(new Runnable() {
			BlockingQueue<String> q1 = msgList.get(1);

			@Override
			public void run() {
				while (true) {
					String msg = q1.poll();
					// handle(msg)
				}
				
			}
		}).start();
		new Thread(new Runnable() {
			BlockingQueue<String> q2 = msgList.get(2);

			@Override
			public void run() {
				while (true) {
					String msg = q2.poll();
					// handle(msg)
				}
				
			}
		}).start();
		new Thread(new Runnable() {
			BlockingQueue<String> q3 = msgList.get(3);

			@Override
			public void run() {
				while (true) {
					String msg = q3.poll();
					// handle(msg)
				}
				
			}
		}).start();
	}
}
