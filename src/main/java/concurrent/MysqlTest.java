package concurrent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class MysqlTest {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	private static final String USER = "root";
	private static final String PASS = "123456";

	@Test
	public void testDeadLock() throws ClassNotFoundException, SQLException, InterruptedException {
		Class.forName(JDBC_DRIVER);
//		test1();
		ExecutorService pool = Executors.newFixedThreadPool(100);
			
		for (int i = 0; i < 100; i++) {
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("start");
					try {
						try {
							test();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("end");

				}
			});
		}
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void test() throws ClassNotFoundException, SQLException, InterruptedException {
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		String sql = "delete from dltask where a='a' and b='b' and c='c'";
		stmt.executeUpdate(sql);
//		TimeUnit.SECONDS.sleep(new Random(10).nextInt()+1);
//		conn.commit();
//		stmt.close();
//		conn.close();
	}
	
	private void test1() throws ClassNotFoundException, SQLException, InterruptedException {
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		String sql = "delete from dltask where a='a' and b='b' and c='c'";
		stmt.executeUpdate(sql);
		TimeUnit.SECONDS.sleep(2);
//		conn.commit();
//		stmt.close();
//		conn.close();
	}
	
	@Test
	public void testJSONObject() {
		String jsonString = new com.alibaba.fastjson.JSONObject().fluentPut("address", null).toJSONString();
		System.out.println(jsonString);
	}

}
