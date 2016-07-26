package com.qbsm.dao.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.annotatioin.AnnotationUtil;
import com.qbsm.dao.help.BuildResult;
import com.qbsm.dao.help.ResultSetToBeans;
import com.qbsm.dao.help.SQLBuildUtil;
import com.qbsm.dao.help.SQLUtil;
import com.qbsm.dao.util.BeansXmlUtil;
import com.qbsm.dao.util.ResetBeanUtils;
import com.qbsm.dao.util.VoResolve;
import com.qbsm.db.DBUtil;

@SuppressWarnings("rawtypes")
public class GladiolusDaoImpl implements com.qbsm.dao.GladiolusDao {

	private SQLUtil sqlUtil = new SQLBuildUtil();

	@Override
	public <T> T getById(Integer id, Class PoClass) {
		List<T> result = getByIds(new Integer[] { id }, PoClass);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public <T> List<T> getByIds(Integer[] ids, Class PoClass) {
		BuildResult buildResult = sqlUtil.buildQuerySQL(PoClass, ids);
		return queryForStip(buildResult.getSql(), buildResult.getParams(), PoClass, DBUtil.getTransactionConnection(), true);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T queryForObject(Object Po) {
		BuildResult buildResult = sqlUtil.buildQuerySQL(Po);
		System.out.println(buildResult);
		List result = queryForStip(buildResult.getSql(), buildResult.getParams(), Po.getClass(), DBUtil.getTransactionConnection(), true);
		if (result != null && !result.isEmpty()) {
			return (T) result.get(0);
		}
		return null;
	}

	@Override
	public Object queryForObject(String sql, Object[] params) {
		System.out.println(sql+"5576576");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = DBUtil.getTransactionConnection();
		Object obj = null;
		try {
			ps = conn.prepareStatement(sql);
			bindingParameters(params, ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				obj = rs.getObject(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQL语句构建失败！sql为:" + sql, e);
		} finally {
			DBUtil.releaseAll(rs, ps, null);
			try {
				DBUtil.releaseConnection(conn);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return obj;
	}

	@Override
	public <T> T queryForObject(String sql, Object[] params, Class encapClass) {
		
		System.out.println(sql+"5576576");

		List<T> result = queryForStip(sql, params, encapClass, DBUtil.getTransactionConnection(), true);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public <T> List<T> queryForListByPo(Object po) {
		return queryForListByPo(po, null, null);
	}

	@Override
	public <T> List<T> queryForListByPo(Object po, Integer start, Integer MaxSize) {
		
		
		BuildResult SqlResult = sqlUtil.buildQuerySQL(po);
		String sql = SqlResult.getSql();
		if (start != null && MaxSize != null) {
			sql = sql + " limit " + start + "," + MaxSize;
		}
		return queryForStip(sql, SqlResult.getParams(), po.getClass(), DBUtil.getTransactionConnection(), true);
	}

	@Override
	public <T> List<T> queryForListByVo(Object Vo) {
		return queryForListByVo(Vo, null, null);
	}

	@Override
	public <T> List<T> queryForListByVo(Object Vo, Integer start, Integer MaxSize) {
		VoResolve voResolve = BeansXmlUtil.getBeansXmlUtil().getVoResolve(Vo.getClass().getSimpleName());
		Object[] pos = new Object[voResolve.getPos().size()];
		try {
			for (int i = 0; i < voResolve.getPos().size(); i++) {
				Object po = voResolve.getPos().get(i).newInstance();
				ResetBeanUtils.copy(Vo, po);// 为po赋值
				pos[i] = po;
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		BuildResult SqlResult = sqlUtil.buildQuerySQL(pos);
		System.out.println(SqlResult);
		String sql = SqlResult.getSql();
		if (start != null && MaxSize != null) {
			sql = sql + " limit " + start + "," + MaxSize;
		}
		return queryForStip(sql, SqlResult.getParams(), Vo.getClass(), DBUtil.getTransactionConnection(), true);
	}

	@Override
	public <T> List<T> queryForList(String sql, Class encapClass) {
		System.out.println(sql+"-------------------------");
		return queryForStip(sql, null, encapClass, DBUtil.getTransactionConnection(), true);
	}

	@Override
	public <T> List<T> queryForList(String sql, Object[] params, Class encapClass) {
System.out.println(sql);
		return queryForStip(sql, params, encapClass, DBUtil.getTransactionConnection(), true);
	}

	@Override
	public <T> List<T> queryForList(Class encapClass, Object... Po) {
		BuildResult buildResult = sqlUtil.buildQuerySQL(Po);
		return queryForStip(buildResult.getSql(), buildResult.getParams(), encapClass, DBUtil.getTransactionConnection(), true);
	}

	@Override
	public <T> List<T> queryForList(Class encapClass, Integer start, Integer MaxSize, Object... Po) {
		BuildResult buildResult = sqlUtil.buildQuerySQL(Po);
		String sql = buildResult.getSql();
		if (start != null && MaxSize != null) {
			sql = sql + " limit " + start + "," + MaxSize;
		}
		return queryForStip(sql, buildResult.getParams(), encapClass, DBUtil.getTransactionConnection(), true);
	}

	private <T> List<T> queryForStip(String sql, Object[] params, Class encapClass, Connection conn, boolean closeConn) {
		System.out.println(sql+"12312312312");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			bindingParameters(params, ps);
			rs = ps.executeQuery();
			return ResultSetToBeans.bindDataToBeans(rs, encapClass);
		} catch (SQLException e) {
			throw new RuntimeException("SQL语句构建失败！sql为:" + sql, e);
		} finally {
			DBUtil.releaseAll(rs, ps, null);
			if (closeConn) {
				try {
					DBUtil.releaseConnection(conn);
				} catch (SQLException e) {
					throw new RuntimeException("DBUtil.releaseConnection()方法出现异常");
				}
			}
		}
	}

	@Override
	public int getDataCount(Object... po) {
		if (po == null) {
			throw new RuntimeException("po不能为空！");
		}
		BuildResult result = sqlUtil.buildQueryDataCountSQL(po);
		return getDataCount(result.getSql(), result.getParams(), DBUtil.getTransactionConnection(), true);
	}

	@Override
	public int getDataCount(String sql, Object[] params) {
System.out.println(sql);
		return getDataCount(sql, params, DBUtil.getTransactionConnection(), true);
	}

	private int getDataCount(String sql, Object[] params, Connection conn, boolean closeConn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			bindingParameters(params, ps);
			rs = ps.executeQuery();
			int rowCount = 0;
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.releaseAll(rs, ps, null);
			if (closeConn) {
				try {
					DBUtil.releaseConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}

	@Override
	public boolean containsEntity(Object Po) {
		BuildResult buildResult = sqlUtil.buildQuerySQL(Po);
		List result = queryForStip(buildResult.getSql(), buildResult.getParams(), Po.getClass(), DBUtil.getTransactionConnection(), true);
		if (!result.isEmpty()) {
			return true;
		}
		return false;
	}

	/*
	 * ----------------------------------事务方法------------------------------------
	 */
	@Override
	public void beginTransaction() {
		try {
			DBUtil.beginTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rollbackTransaction() {
		try {
			DBUtil.rollbackTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commitTransaction() {
		try {
			DBUtil.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * ----------------------------------更新方法------------------------------------
	 */
	@Override
	public boolean save(Object po) {
		BuildResult SqlResult = sqlUtil.buildSaveSQL(po);
		System.out.println(SqlResult);
		return update(SqlResult.getSql(), SqlResult.getParams(), DBUtil.getTransactionConnection(), true);
	}

	@Override
	public boolean saveByVo(Object vo) {
		VoResolve voResolve = BeansXmlUtil.getBeansXmlUtil().getVoResolve(vo.getClass().getSimpleName());
		beginTransaction();
		Connection conn = DBUtil.getTransactionConnection();
		BuildResult SqlResult = null;
		boolean flag = false;
		try {
			for (int i = 0; i < voResolve.getPos().size(); i++) {
				Object po = voResolve.getPos().get(i).newInstance();
				ResetBeanUtils.copy(vo, po);// 为po赋值
				SqlResult = sqlUtil.buildSaveSQL(po);// 构建SQL语句
				String tableName = AnnotationUtil.getAnnotationTable(voResolve.getPos().get(i));// 获取数据库表名
				if (isAutoIncrement(conn, tableName)) {
					int key = getGeneratedKeyByInsert(SqlResult.getSql(), SqlResult.getParams(), conn, false);
					if (ResetBeanUtils.isHaveFiled(vo.getClass(), AnnotationUtil.getPrimaryField(voResolve.getPos().get(i)).getName())) {
						ResetBeanUtils.setProperty(vo, AnnotationUtil.getPrimaryField(voResolve.getPos().get(i)).getName(), key);
					}
					flag = true;
				} else {
					flag = update(SqlResult.getSql(), SqlResult.getParams(), conn, false);
				}
			}
		} catch (Exception e) {
			rollbackTransaction();
			throw new RuntimeException("saveByVo方法出现异常！事务已回滚！  最后一个po的SQL构建结果为:" + SqlResult, e);
		} finally {
			try {
				DBUtil.releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		commitTransaction();
		return flag;
	}

	@Override
	public boolean save(String sql, Object[] params) {
		return update(sql, params, DBUtil.getTransactionConnection(), true);
	}

	@Override
	public boolean delete(Object po) {
		BuildResult SqlResult = sqlUtil.buildDeleteSQL(po);
		return update(SqlResult.getSql(), SqlResult.getParams(), DBUtil.getTransactionConnection(), true);
	}

	@Override
	public boolean delete(Integer id, Class PoClass) {
		Integer[] ids = { id };
		return delete(ids, PoClass);
	}

	@Override
	public boolean delete(Integer[] ids, Class PoClass) {
		BuildResult SqlResult = sqlUtil.buildDeleteSQL(PoClass, ids);
		return update(SqlResult.getSql(), SqlResult.getParams(), DBUtil.getTransactionConnection(), true);
	}

	@Override
	public boolean update(Object po) {
		BuildResult SqlResult = sqlUtil.buildUpdateSQL(po);
		return update(SqlResult.getSql(), SqlResult.getParams(), DBUtil.getTransactionConnection(), true);
	}

	@Override
	public boolean updateByVo(Object vo) {
		beginTransaction();
		BuildResult SqlResult = null;
		boolean flag = false;
		List<Object> pos = getPos(vo);
		try {
			for (int i = 0; i < pos.size(); i++) {
				SqlResult = sqlUtil.buildUpdateSQL(pos.get(i));
				flag = update(SqlResult.getSql(), SqlResult.getParams(), DBUtil.getTransactionConnection(), true);
			}
		} catch (Exception e) {
			rollbackTransaction();
			throw new RuntimeException("updateByVo方法出现异常,操作已回滚！最后一个po的SQL构建结果为：" + SqlResult, e);
		}
		commitTransaction();
		return flag;
	}

	@Override
	public boolean update(String sql, Object[] params) {
		return update(sql, params, DBUtil.getTransactionConnection(), true);
	}

	private boolean update(String sql, Object[] params, Connection conn, boolean closeConn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int flag = 0;
		try {
			ps = conn.prepareStatement(sql);
			bindingParameters(params, ps);
			flag = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.releaseAll(rs, ps, null);
			if (closeConn) {
				try {
					DBUtil.releaseConnection(conn);
				} catch (SQLException e) {
					throw new RuntimeException("DBUtil.releaseConnection()方法出现异常");
				}
			}
		}
		return flag > 0 ? true : false;
	}

	@Override
	public boolean updateBatch(String sql, Object[][] params, int batchCount) {
		return updateBatch(sql, params, batchCount, DBUtil.getTransactionConnection(), true);
	}

	private boolean updateBatch(String sql, Object[][] params, int batchCount, Connection conn, boolean closeConn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int[] flag = null;
		int count = 0;// 统计累计插入记录数量
		try {
			ps = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for (int i = 0; i < params.length; i++) {
				bindingParameters(params[i], ps);
				ps.addBatch();
				if (++count % batchCount == 0)
					ps.executeBatch();
			}
			flag = ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.releaseAll(rs, ps, null);
			if (closeConn) {
				try {
					DBUtil.releaseConnection(conn);
				} catch (SQLException e) {
					throw new RuntimeException("DBUtil.releaseConnection()方法出现异常");
				}
			}
		}
		return flag != null && flag.length > 0 ? true : false;
	}

	@Override
	public Integer getGeneratedKeyByInsert(Object po) {
		BuildResult SqlResult = sqlUtil.buildSaveSQL(po);
		return getGeneratedKeyByInsert(SqlResult.getSql(), SqlResult.getParams(), DBUtil.getTransactionConnection(), true);
	}

	@Override
	public Integer getGeneratedKeyByInsert(String sql, Object[] params) {
		return getGeneratedKeyByInsert(sql, params, DBUtil.getTransactionConnection(), true);
	}

	private Integer getGeneratedKeyByInsert(String sql, Object[] params, Connection conn, boolean closeConn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer generatedKeys = null;
		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			bindingParameters(params, ps);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while (rs.next()) {
				generatedKeys = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.releaseAll(rs, ps, null);
			if (closeConn) {
				try {
					DBUtil.releaseConnection(conn);
				} catch (SQLException e) {
					throw new RuntimeException("DBUtil.releaseConnection()方法出现异常");
				}
			}
		}
		return generatedKeys;
	}

	/**
	 * 绑定参数
	 * 
	 * @param params
	 */
	private void bindingParameters(Object[] params, PreparedStatement ps) {
		if (params != null) {
			for (int i = 1; i < params.length + 1; i++) {
				try {
					ps.setObject(i, params[i - 1]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断主键是否自增
	 * 
	 * @param conn
	 * @param tableName
	 * @return
	 */
	private boolean isAutoIncrement(Connection conn, String tableName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from " + tableName + " where 1=2");// 获取表结构
			rs = ps.executeQuery();
			ResultSetMetaData rsme = rs.getMetaData();
			int columnCount = rsme.getColumnCount();
			for (int i = 1; i < columnCount; i++) {
				if (rsme.isAutoIncrement(i)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getDataCountByVo(Object vo) {
		List<Object> pos = getPos(vo);
		BuildResult result = sqlUtil.buildQueryDataCountSQL(pos.toArray());
		return getDataCount(result.getSql(), result.getParams(), DBUtil.getTransactionConnection(), true);
	}

	private List<Object> getPos(Object vo) {
		List<Object> pos = new ArrayList<Object>();
		VoResolve voResolve = BeansXmlUtil.getBeansXmlUtil().getVoResolve(vo.getClass().getSimpleName());
		try {
			for (int i = 0; i < voResolve.getPos().size(); i++) {
				Object po = voResolve.getPos().get(i).newInstance();
				ResetBeanUtils.copy(vo, po);// 为po赋值
				pos.add(po);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return pos;
	}
}
