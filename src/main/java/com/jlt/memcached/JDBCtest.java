package com.jlt.memcached;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class JDBCtest {
	protected static int threadnum = 0;

	protected static MemCachedClient mcc = new MemCachedClient();

	protected static SockIOPool sUpool = null;
	static {
		// 可使用的memcached 服务列表
		String[] servers = { "127.0.0.1:11211" };

		// 服务器权重,在服务器性能差异不大的情况下也可以省略
		Integer[] weights = { new Integer(3) };

		// 获取连接池的单态方法，这个方法有一个重载的方法getInstance(String poolname)用于创建不同的连接池
		sUpool = SockIOPool.getInstance();

		sUpool.setServers(servers);
		sUpool.setWeights(weights);

		// 设置连接池维护线程的睡眠时间，单位秒；设置为0的话则维护线程不启动
		// 维护线程通过log输出socket运行状况，监测连接数目等参数
		sUpool.setMaintSleep(30);

		// 是否激活TCP的Nagle算法，缺省为true；Nagle算法通过减少必须发送包的个数（合并小包）来增加网络软件系统的效率；
		// 如果应用要求即时性高且所处环境中带宽充裕，则建议设置为false;
		sUpool.setNagle(false);

		// 连接memcache服务的超时时间，单位毫秒
		// 经测试，servers列表中如果存在不能被连接的多台server，其timeout是并发计算的，不是一台timeout后等第二台timeout这样；故severlist长不会带来问题；
		sUpool.setSocketConnectTO(100);

		// 连接上memcache服务后处理的超时时间，单位毫秒
		sUpool.setSocketTO(100);

		// 设置每个memcache服务的连接池的初始连接数，最小连接数和最大连接数；
		sUpool.setInitConn(1);
		sUpool.setMinConn(1);
		sUpool.setMaxConn(100);

		// 设置连接池的最大空闲时间和繁忙时间，单位毫秒
		// sUpool.setMaxIdle(1000);
		// sUpool.setMaxBusyTime(1000);

		sUpool.setHashingAlg(2);
		// 设置hash算法。0-2采用hash算法，查找cache服务使用余数方法，其中2既兼容别的客户端且效率高；
		// 3查找cache服务使用consistent方法，在大规模动态部署cache服务时有用，但会降低内存的利用效率；

		// 当当前socket不可用时，程序自动查找可用连接并返回，默认true，建议保持默认
		sUpool.setFailover(true);

		// 当网络闪断恢复后，这个socket连接还可以继续使用；
		sUpool.setFailback(true);

		// 默认为false，设置为true时在每次通信时都要进行连接有效性测试，造成通信次数倍增，经内网实测几乎不影响处理速度。在HA要求较高的场合建议设置为true
		sUpool.setAliveCheck(true);

		//
		sUpool.initialize();

		// 压缩设置，超过指定大小(KB)的数据会被压缩，不赞成使用
		// mcc.setCompressEnable(true);
		// mcc.setCompressThreshold(64);

	}

	public static void main(String[] args) {

		/*
		 * cache数据写入操作方法
		 * set方法：将数据保存到cache服务器，如果保存成功则返回true,如果cache服务器存在同样的key，则替换之
		 * add方法：将数据添加到cache服务器,如果保存成功则返回true，如果cache服务器存在同样key，则返回false
		 * replace方法
		 * ：将数据替换cache服务器中相同的key,如果保存成功则返回true，如果cache服务器不存在同样key，则返回false
		 */

		// mcc.set("testKey", "This is a test String", new Date(new Date()
		// .getTime() + 10000));// 过期时间为10秒

		/*
		 * cache数据读取操作方法
		 * 使用get方法从cache服务器获取一个数据，如果写入时是压缩的或序列化的，则get的返回会自动解压缩及反序列化
		 * 使用getMulti方法从cache服务器获取一组数据，是get方法的数组实现，输入参数keys是一个key数组，返回是一个map
		 */

		// String bar = mcc.get("testKey").toString();

		// System.out.println("testKey-->" + bar);
		// System.out.println(new Date(1330587732));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@10.25.18.23:1510:gbs01dev";
			String user = "asopr";
			String password = "Paic1234";
			String sql = "select * from ag_mon_info a, ag_mon_info b";
			System.out.println(sql);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.setFetchSize(1000);
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			System.out.println(new Date());
			while (rs.next()) {
				rs.getString(1);
				i++;
				if (i % 10000 == 0) {
					System.out.println(i);
					System.out.println(new Date());
				}

			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}