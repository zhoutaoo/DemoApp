package com.selenium;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.InputSource;

public class DbunitCase {
	private static Connection jdbcConnection = null;
	private static IDatabaseConnection connection = null;

	@BeforeClass
	public static void beforeClass() throws Exception {
		// 加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 获取数据库连接
		jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/test?useUnicode=false&characterEncoding=utf-8", "root", "root");
		connection = new DatabaseConnection(jdbcConnection);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		// 关闭连接
		jdbcConnection.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void exportPartData() throws Exception {
		// 包装数据库连接为一个查询结果集
		QueryDataSet partialDataSet = new QueryDataSet(connection);
		// partialDataSet.addTable("users", "SELECT * FROM users");
		partialDataSet.addTable("authoritys");
		FlatXmlDataSet.write(partialDataSet, new FileOutputStream("authoritys.xml"));
	}

	@Test
	@Ignore
	public void exportAllData() throws Exception {
		IDataSet fullDataSet = connection.createDataSet();
		FlatXmlDataSet.write(fullDataSet, new FileOutputStream("jltms.xml"));
	}

	@Test
	@Ignore
	public void exportDependentData() throws Exception {
		// 依赖表导出：导出X表以及所有X表的关联表，同时已正确的次序来保证插入操作
		String[] depTableNames = TablesDependencyHelper.getAllDependentTables(connection, "users");
		IDataSet depDataset = connection.createDataSet(depTableNames);
		FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));

	}

	@Test
	@Ignore
	public void importData() throws Exception {
		// 导入数据
		File[] files = new File("D:/Work/Workspaces/MyEclipse/jltms/db/dml").listFiles();
		for (File f : files) {
			if (f.isFile()) {
				System.out.println(f.getAbsolutePath());
				IDataSet xmlDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(f.getAbsolutePath())));
				DatabaseOperation.TRUNCATE_TABLE.execute(connection, xmlDataSet);
				DatabaseOperation.INSERT.execute(connection, xmlDataSet);
			}
		}
	}
}
