package com.qbsm.service.systemManageService;
import com.qbsm.service.util.DBbackupRecoverUtil;
public class BackupAndRecoverManage {
	

	/*数据库备份*/
	public static String backup() throws Exception {
		DBbackupRecoverUtil dbutil = new DBbackupRecoverUtil();
		return dbutil.backup();
	}
	

	/*数据库备份*/
	public static void recover(String filePath) throws Exception {
		DBbackupRecoverUtil dbutil = new DBbackupRecoverUtil();
		dbutil.recover(filePath);
	}

}
