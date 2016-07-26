package com.qbsm.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	private static String USERNAME = null;
	private static String PASSWORD = null;
	private static String DRIVER = null;
	private static String URL = null;

	private static ComboPooledDataSource ds = null;
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static boolean isC3p0 = false;

	static {
		// 判断有没有c3p0配置文件,如果有,则启用连接池,否则用内部连接
		InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("c3p0-config.xml");
		// System.out.println(inputStream);
		// 说明有c3p0配置文件
		if (inputStream != null) {
			ds = new ComboPooledDataSource();
			isC3p0 = true;
		} else {// 没有则加载驱动
			// 判断有没有db.properties配置文件,如果有,则加载
			inputStream = DBUtil.class.getResourceAsStream("/db.properties");
			if (inputStream != null) {
				Properties dbproperties = new Properties();
				try {
					dbproperties.load(inputStream);
				} catch (IOException e) {
					throw new RuntimeException("db.properties文件读取异常！请确认配置是否正确！");
				}
				USERNAME = dbproperties.getProperty("User").toString();
				PASSWORD = dbproperties.getProperty("Password").toString();
				DRIVER = dbproperties.getProperty("DriverClass").toString();
				URL = dbproperties.getProperty("URL").toString();
				try {
					Class.forName(DRIVER);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("driverClass-->" + DRIVER + "<--未找到！请确认配置是否正确！");
				}
			}
		}
	}

	private static DataSource getDataSource() {
		return ds;
	}

	/**
	 * 从连接池中得到数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static Connection getNewConnection() {
		try { // 如果使用c3p0连接池,直接返回池中的连接
			if (isC3p0) {
				return getDataSource().getConnection();
			} else {
				return DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 为事务专门开一个连接,内部使用连接
	 * 
	 * @return connection
	 */
	public static Connection getTransactionConnection() {
		Connection con = threadLocal.get();
		// 当con不等于null，说明已经调用过beginTransaction(),表示开启了事务！
		if (con != null)
			return con;
		return getNewConnection();
	}

	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		Connection con = threadLocal.get();
		if (con != null)
			throw new SQLException("已经开启了事务,就不要重复开启了!\n");
		con = getNewConnection();// 给con赋值，表示事务已经开始了
		con.setAutoCommit(false);
		threadLocal.set(con);// 把当前线程的连接保存起来！
	}

	/**
	 * 事物回滚
	 * 
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection con = threadLocal.get();
		if (con == null)
			throw new SQLException("还没有开启事务,不能回滚!\n");
		con.rollback();
		con.close();
		threadLocal.remove();
	}

	/**
	 * 事物提交
	 * 
	 * @throws SQLException
	 */
	public static void commitTransaction() throws SQLException {
		Connection con = threadLocal.get();// 获取当前线程的专用连接
		if (con == null)
			throw new SQLException("还没有开启事务,不能提交!\n");
		con.commit();
		con.close();
		// 把它设置为null，表示事务已经结束了！下次再去调用getConnection()返回的就不是con了
		threadLocal.remove();// 从threadLocal中移除连接
	}

	/**
	 * 释放资源
	 * 
	 * @throws SQLException
	 */
	public static void releaseConnection(Connection connection) throws SQLException {
		Connection con = threadLocal.get();
		// 如果con == null，说明现在没有事务，那么connection一定不是事务专用的！
		if (con == null)
			connection.close();
		// 如果con != null，说明有事务，那么需要判断参数连接是否与con相等，若不等，说明参数连接不是事务专用连接
		if (con != connection)
			connection.close();
	}

	/**
	 * 释放所有
	 * 
	 * @param rs
	 *            ResultSet
	 * @param ps
	 *            PreparedStatement
	 * @param conn
	 *            Connection
	 */
	public static void releaseAll(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new RuntimeException("ResultSet关闭出错！" + e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement关闭出错！" + e);
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						throw new RuntimeException("Connection 关闭出错！" + e);
					}
			}
		}
	}
}
