package com.qbsm.bean;

import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;


//预警通报表t_alarm

@Table("t_alarm")
public class Alarm{
	
    // Fields    
	@PrimaryColumn("alarm_id")
    private Integer alarm_id;			//表id
    private Integer alarm_tombstone;	//逻辑删除标志
    private String alarm_period;		//预警通报周期
    private Integer alarm_isdel;		//  没有用，可删除
    
     
	public Integer getAlarm_id() {
		return alarm_id;
	}
	public void setAlarm_id(Integer alarm_id) {
		this.alarm_id = alarm_id;
	}
	public Integer getAlarm_tombstone() {
		return alarm_tombstone;
	}
	public void setAlarm_tombstone(Integer alarm_tombstone) {
		this.alarm_tombstone = alarm_tombstone;
	}
	public String getAlarm_period() {
		return alarm_period;
	}
	public void setAlarm_period(String alarm_period) {
		this.alarm_period = alarm_period;
	}
	public Integer getAlarm_isdel() {
		return alarm_isdel;
	}
	public void setAlarm_isdel(Integer alarm_isdel) {
		this.alarm_isdel = alarm_isdel;
	}  
}