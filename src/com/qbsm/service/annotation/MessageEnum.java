package com.qbsm.service.annotation;

public enum MessageEnum {
	S00000,   	//系统操作成功
	S00001,		//添加用户成功，并成功发送信息到指定邮箱中
	S00002,		//添加用户成功，但邮箱发送失败，请主动与用户取得联系
	
	E11111,		//系统繁忙，请稍后再试
	
	E20001,		//两次密码填写不同
	E20002,		//账号填写失败（长度为6--11,由非中文组成）
	E20003,		//邮箱格式不正确
	E20004,		//不合法的图片大小
	E20005,		//不合法的文件类型
	E20006,		//请填写完整的信息
	
	E30001,		//查找信息不存在
	E30002,  	//添加信息失败
	E30003,		//更新信息失败
	E30004,		//删除信息失败
	
	E40001,		//申请数量大于库存数量
	E40002,		//申请采购物资还有库存
	E40003, 	//仓库里面没有该物资
	E40004,		//没有要查找的信息
	E40005		//申请数量为0
}
