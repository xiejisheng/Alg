package concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

/**
 * 需要等待数据的到来
 * 轮询还是阻塞
 * @author xjs
 *
 */
public class LinkWaitNode {
	private final static ConcurrentHashMap<String, Object> reqDatas = new ConcurrentHashMap<String, Object>();
	private final static ConcurrentHashMap<String, LatchSupport> latchs = new ConcurrentHashMap<String, LatchSupport>();
	private final static ConcurrentHashMap<String, Object> resDatas = new ConcurrentHashMap<String, Object>();
	
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() ->  {
			reqDatas.clear();
			resDatas.clear();
			latchs.clear();
		}));
	}
	
	private static void addLatch(String key) {
		
		LatchSupport ls = LatchSupport.create();
		latchs.putIfAbsent(key, ls);
		ls.await();
	}
	
	
	private static void removeLatch(String key) {
		if (!latchs.containsKey(key)) 
			throw new UnsupportedOperationException("key not exist");
		
		LatchSupport ls = latchs.get(key);
		ls.countDown();
		System.out.println(ls.elapsed());
		latchs.remove(key);
	}
	
	public static Object getForWait(String key) {
		addLatch(key);
		return resDatas.get(key);
	}
	
	public static void putForNotify(String key) {
		resDatas.putIfAbsent(key, new Object());
		removeLatch(key);
	}
	
	protected static class LatchSupport {
		private CountDownLatch latch;
		private Stopwatch stopwatch;
		private LatchSupport() {
			this.latch = new CountDownLatch(1);
			this.stopwatch = Stopwatch.createUnstarted();
		}
		
		public static LatchSupport create() {
			return new LatchSupport();
		}
		
		public void countDown() {
			this.latch.countDown();
			this.stopwatch.stop();
		}
		
		public void await() {
			this.stopwatch.start();
			try {
				this.latch.await();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		public long elapsed() {
			return this.stopwatch.elapsed(TimeUnit.MILLISECONDS);
		}
	}

}
