package com.qbsm.service.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.qbsm.service.exception.ParseException;
import com.qbsm.util.PropertiseUtil;

public class DBbackupRecoverUtil {

	private static String userName;
	private static String password;
	private static String database;
	private static String binPath;
	private static String backupLocation;
	private static Properties pro;
	
	static{
		try {
			pro = PropertiseUtil.getProperties("/dbBackUp.properties");
			userName = pro.getProperty("username");
			password = pro.getProperty("password");
			database = pro.getProperty("databaseName");
			binPath = pro.getProperty("mysqlBinPath");
			backupLocation = pro.getProperty("backupLocation");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String backup() {
		String backupFile = backupLocation
				+ database
				+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
		.format(new Date()) + ".sql";
		try {
			Runtime rt = Runtime.getRuntime();
			// 调用 调用mysql的安装目录的命令
			Process child = rt.exec(binPath + "//mysqldump -h localhost -u" + userName + " -p" + password + " " + database);
			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
			InputStreamReader isr = new InputStreamReader(in, "utf-8");
			// 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(isr);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			FileOutputStream fout = new FileOutputStream(backupFile); // 要用来做导入用的sql目标文件
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			isr.close();
			br.close();
			writer.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(backupFile+"备份成功-----");
		return backupFile;
	}

	public  void recover(String filePath) {
		try {
			Runtime rt = Runtime.getRuntime();
			// 调用 mysql的cmd
			Process child = rt.exec(binPath + "//mysql.exe -u" + userName + " -p" + password + " " + database);
			OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}