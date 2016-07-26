package com.qbsm.service.menberInfoService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.IndividualPerformFormBean;

public class MenberKPI {
   private GladiolusDao dao = new GladiolusDaoImpl();
   
   public static List<String> conv(String date){
	  	
	  String[] a  = date.split(" - ");
	  String[] b = new String[2];
	  for(int i = 0;i<a.length;i++){
		 b[i] = a[i].replace("/", "-");
	  }
	  String[] c = b[0].split("-");
	  String[] d = b[1].split("-");
	  String befer = c[2]+"-"+c[1]+"-"+c[0];
	  String after = d[2]+"-"+d[1]+"-"+d[0];
	  
	  List<String> convdate = new ArrayList<String>();
	  convdate.add(after);
	  convdate.add(befer);
	  //System.out.println(convdate.get(0));//after
	  //System.out.println(convdate.get(1));//before
	  return convdate;
	}
   
   /**
    * 个人绩效详细信息
    * @param individualPerformFormBean
    * @param beforeDate
    * @param afterDate
    * @param pc
    * @param ps
    * @param filters
    * @return
    */
   protected List<IndividualPerformFormBean> queryIndividualPerformFormBean(IndividualPerformFormBean individualPerformFormBean,String beforeDate,String afterDate,int pc,int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
        sql.append("select go.goods_id as goods_id,go.goods_name as goods_name, sum(apg.apply_goods_count) as apply_goods_count, sum(vg.voucher_goods_count) as voucher_goods_count from t_voucher vo,t_voucher_goods vg,t_goods go,t_apply ap ,t_apply_goods apg,t_user u  where go.goods_id=vg.goods_id_fk and vo.voucher_type= '领用凭证单' and vo.user_id_fk=u.user_id and vo.voucher_id=vg.voucher_id_fk and ap.user_id_fk=u.user_id  and ap.Apply_id=apg.apply_id_fk  and u.user_id=?  and ap.apply_type=2  and ap.Apply_time between ? and ? and vo.voucher_createtime  BETWEEN ? and ? GROUP BY go.goods_id");
        return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{individualPerformFormBean.getUser_id(),beforeDate,afterDate,beforeDate,afterDate},IndividualPerformFormBean.class);
   }
   
   /**
    * 个人绩效数量
    * @param individualPerformFormBean
    * @param beforeDate
    * @param afterDate
    * @param pc
    * @param ps
    * @param filters
    * @return
    */
   protected List<Integer> queryCount(IndividualPerformFormBean individualPerformFormBean,String beforeDate,String afterDate,Filters filters){
	   List<Integer> list = new ArrayList<Integer>(1);
		String sql = "select count(*) from (select go.goods_id as goods_id,go.goods_name as goods_name, sum(apg.apply_goods_count) as apply_goods_count, sum(vg.voucher_goods_count) as voucher_goods_count from t_voucher vo,t_voucher_goods vg,t_goods go,t_apply ap ,t_apply_goods apg,t_user u  where go.goods_id=vg.goods_id_fk and vo.voucher_type= '领用凭证单' and vo.user_id_fk=u.user_id and vo.voucher_id=vg.voucher_id_fk and ap.user_id_fk=u.user_id  and ap.Apply_id=apg.apply_id_fk  and u.user_id=?  and ap.apply_type=2  and ap.Apply_time between ? and ? and vo.voucher_createtime  BETWEEN ? and ? GROUP BY go.goods_id)anas"+filters.toAndSql();
		Object individualPerformCount = dao.queryForObject(sql, new Object[]{individualPerformFormBean.getUser_id(),beforeDate,afterDate,beforeDate,afterDate});
	    list.add(Integer.parseInt(String.valueOf(individualPerformCount)));
		return list;
   }
   
   public static void main(String[] args) {
	   List<IndividualPerformFormBean> we = new ArrayList<IndividualPerformFormBean>();
	   MenberKPI ad = new MenberKPI();
	   IndividualPerformFormBean in = new IndividualPerformFormBean();
	   in.setUser_id(151);
//	   in.setGoods_id(10);

	  List<IndividualPerformFormBean> l =  ad.queryIndividualPerformFormBean(in, "2016-01-02" , "2016-12-09", 1,10, new Filters());
	   
	   System.out.println();
	   for(IndividualPerformFormBean ew : l){
		   System.out.println(ew);
	   }
	   
	  System.out.println( ad.queryCount(in, "2016-01-02" , "2016-12-09",  new Filters()));
}
   
}
