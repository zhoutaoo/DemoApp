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
		// ��ʹ�õ�memcached �����б�
		String[] servers = { "127.0.0.1:11211" };

		// ������Ȩ��,�ڷ��������ܲ��첻��������Ҳ����ʡ��
		Integer[] weights = { new Integer(3) };

		// ��ȡ���ӳصĵ�̬���������������һ�����صķ���getInstance(String poolname)���ڴ�����ͬ�����ӳ�
		sUpool = SockIOPool.getInstance();

		sUpool.setServers(servers);
		sUpool.setWeights(weights);

		// �������ӳ�ά���̵߳�˯��ʱ�䣬��λ�룻����Ϊ0�Ļ���ά���̲߳�����
		// ά���߳�ͨ��log���socket����״�������������Ŀ�Ȳ���
		sUpool.setMaintSleep(30);

		// �Ƿ񼤻�TCP��Nagle�㷨��ȱʡΪtrue��Nagle�㷨ͨ�����ٱ��뷢�Ͱ��ĸ������ϲ�С�����������������ϵͳ��Ч�ʣ�
		// ���Ӧ��Ҫ��ʱ�Ը������������д����ԣ����������Ϊfalse;
		sUpool.setNagle(false);

		// ����memcache����ĳ�ʱʱ�䣬��λ����
		// �����ԣ�servers�б���������ڲ��ܱ����ӵĶ�̨server����timeout�ǲ�������ģ�����һ̨timeout��ȵڶ�̨timeout��������severlist������������⣻
		sUpool.setSocketConnectTO(100);

		// ������memcache�������ĳ�ʱʱ�䣬��λ����
		sUpool.setSocketTO(100);

		// ����ÿ��memcache��������ӳصĳ�ʼ����������С�������������������
		sUpool.setInitConn(1);
		sUpool.setMinConn(1);
		sUpool.setMaxConn(100);

		// �������ӳص�������ʱ��ͷ�æʱ�䣬��λ����
		// sUpool.setMaxIdle(1000);
		// sUpool.setMaxBusyTime(1000);

		sUpool.setHashingAlg(2);
		// ����hash�㷨��0-2����hash�㷨������cache����ʹ����������������2�ȼ��ݱ�Ŀͻ�����Ч�ʸߣ�
		// 3����cache����ʹ��consistent�������ڴ��ģ��̬����cache����ʱ���ã����ή���ڴ������Ч�ʣ�

		// ����ǰsocket������ʱ�������Զ����ҿ������Ӳ����أ�Ĭ��true�����鱣��Ĭ��
		sUpool.setFailover(true);

		// ���������ϻָ������socket���ӻ����Լ���ʹ�ã�
		sUpool.setFailback(true);

		// Ĭ��Ϊfalse������Ϊtrueʱ��ÿ��ͨ��ʱ��Ҫ����������Ч�Բ��ԣ����ͨ�Ŵ���������������ʵ�⼸����Ӱ�촦���ٶȡ���HAҪ��ϸߵĳ��Ͻ�������Ϊtrue
		sUpool.setAliveCheck(true);

		//
		sUpool.initialize();

		// ѹ�����ã�����ָ����С(KB)�����ݻᱻѹ�������޳�ʹ��
		// mcc.setCompressEnable(true);
		// mcc.setCompressThreshold(64);

	}

	public static void main(String[] args) {

		/*
		 * cache����д���������
		 * set�����������ݱ��浽cache���������������ɹ��򷵻�true,���cache����������ͬ����key�����滻֮
		 * add��������������ӵ�cache������,�������ɹ��򷵻�true�����cache����������ͬ��key���򷵻�false
		 * replace����
		 * ���������滻cache����������ͬ��key,�������ɹ��򷵻�true�����cache������������ͬ��key���򷵻�false
		 */

		// mcc.set("testKey", "This is a test String", new Date(new Date()
		// .getTime() + 10000));// ����ʱ��Ϊ10��

		/*
		 * cache���ݶ�ȡ��������
		 * ʹ��get������cache��������ȡһ�����ݣ����д��ʱ��ѹ���Ļ����л��ģ���get�ķ��ػ��Զ���ѹ���������л�
		 * ʹ��getMulti������cache��������ȡһ�����ݣ���get����������ʵ�֣��������keys��һ��key���飬������һ��map
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