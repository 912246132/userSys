package cn.edu.wgf.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import cn.edu.wgf.dto.VIPDTO;
import cn.edu.wgf.util.DataAccess;

public class VipDAO {
			//��ѯ
		public Vector<VIPDTO> findAllVipInfo() {
			Vector<VIPDTO> v=new Vector<VIPDTO>();
			Connection conn=null;
			Statement stat=null;
			ResultSet rs=null;
			try {
				conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
				String sql="select * from vip";
				rs=stat.executeQuery(sql);
				while(rs.next()){//��������
					VIPDTO s=new VIPDTO();
					s.setvname(rs.getString("vname"));
					s.setvid(rs.getString("vid"));
					s.setpassword(rs.getString("password"));
					s.setsuperuser(rs.getInt("superuser"));
					v.add(s);
				}
				
			}catch (SQLException e) {
				System.out.println("����SQL���ʱ���ִ���");
				e.printStackTrace();
			}finally{
				try {
					if(rs!=null){
					  rs.close();
					  rs=null;
					}
					if(stat!=null){
					  stat.close();
					  stat=null;
					}
					if(conn!=null){
					  conn.close();
					  conn=null;
					}
				} catch (SQLException e) {
					System.out.println("�ر����ӡ���估�����ʱ���ִ���");
					e.printStackTrace();
				}
			}
			return v;
		}
		//ɾ��
		//��һ�棺
//		public boolean deleteStudentBySno(String sno){
//			boolean flag1=false;
//			Connection conn=null;
//			Statement stat=null;
//			try {
//				conn=conn=DataAccess.getConnection();
//				stat=conn.createStatement();//����SQL������ִ��
//				String sql="delete  from student where sno='"+sno+"'";
//				stat.executeUpdate(sql);
//				flag1=true;
//			}catch (SQLException e) {
//				System.out.println("����SQL���ʱ���ִ���");
//				e.printStackTrace();
//			}finally{
//				try {
//					if(stat!=null){
//					  stat.close();
//					  stat=null;
//					}
//					if(conn!=null){
//					  conn.close();
//					  conn=null;
//					}
//				} catch (SQLException e) {
//					System.out.println("�ر����ӡ���估�����ʱ���ִ���");
//					e.printStackTrace();
//				}
//			}
//			return flag1;
//		}
		
		
		
		//�ڶ��棺���ɾ��
		public boolean deleteVipByVname(String vname){//����vnameɾ��һ��vip��¼
			boolean flag1=false;
			Connection conn=null;
			Statement stat=null;
			Statement stat1=null;
			ResultSet rs = null;
			ResultSet rs1 = null;
			try {
				conn=conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
				stat1=conn.createStatement();//����SQL������ִ��
				//��ôɾ��û�˿��ĵ�Ӱ
				String sql2 = "select * from code where vname='"+vname+"'";
				String sql3 = null;
				rs = stat.executeQuery(sql2);
				String mname = null;//cid ��Ϊ��Ӱ���
				while(rs.next()){
					mname = rs.getString(2);
					sql3 = "select count(*) as num from code where mname = '"+mname+"'";
					rs1 = stat1.executeQuery(sql3);
					rs1.next();
					

					if(rs1.getInt("num")==1)
					{
						stat1.executeUpdate("delete from code where mname ='"+mname+"'");
					    stat1.executeUpdate("delete from movie where mname ='"+mname+"'");
					}
					
					}

				stat1.executeUpdate("delete  from code where vname='"+vname+"'");
				stat1.executeUpdate("delete  from vip where vname='"+vname+"'");
				flag1=true;
			}catch (SQLException e) {
				System.out.println("����SQL���ʱ���ִ���");
				e.printStackTrace();
			}finally{
				try {
					if(stat!=null){
					  stat.close();
					  stat=null;
					}
					if(conn!=null){
					  conn.close();
					  conn=null;
					}
				} catch (SQLException e) {
					System.out.println("�ر����ӡ���估�����ʱ���ִ���");
					e.printStackTrace();
				}
			}
			return flag1;
		}
//		��һ�棺VIP��������
		public boolean insertVIPByInfo(VIPDTO vdto){
			boolean flag=false;
			String vid = vdto.getvid();
			String vname = vdto.getvname();
			String password = vdto.getpassword();
			int superuser = vdto.getsuperuser();
			String sql = "insert into Vip values"+
			"('"+vname+"','"+vid+"','"+password+"','"+superuser+"')";
			Connection conn=null;
			Statement stat=null;
			try {
				conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
                stat.executeUpdate(sql);
                flag = true;
			}catch (SQLException e) {
				System.out.println("����SQL���ʱ���ִ���");
				e.printStackTrace();
			}finally{
				try {
					if(stat!=null){
					  stat.close();
					  stat=null;
					}
					if(conn!=null){
					  conn.close();
					  conn=null;
					}
				} catch (SQLException e) {
					System.out.println("�ر����ӡ���估�����ʱ���ִ���");
					e.printStackTrace();
				}
			}
			return flag;
		}
		
		
		//�ڶ��棺VIP�������
		public boolean insertVipByVname(String vname){
			boolean flag2=false;
			Connection conn=null;
			Statement stat=null;
			try {
				conn=conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
				String sql1="insert into code (vname,mname,vipcode)"+"values('"+vname+"','������������5��','6666')";
				String sql="insert into vip (vname,vid,password,superuser)"+"values('"+vname+"','04','444','0')";
				stat.executeUpdate(sql);
				stat.executeUpdate(sql1);
				
				flag2=true;
			}catch (SQLException e) {
				System.out.println("����SQL���ʱ���ִ���");
				e.printStackTrace();
			}finally{
				try {
					if(stat!=null){
					  stat.close();
					  stat=null;
					}
					if(conn!=null){
					  conn.close();
					  conn=null;
					}
				} catch (SQLException e) {
					System.out.println("�ر����ӡ���估�����ʱ���ִ���");
					e.printStackTrace();
				}
			}
			return flag2;
		}
		
		
		
		
		
		//VIP����
		public boolean updateVipByVname(VIPDTO vv){
			boolean flag3=false;
			Connection conn=null;
			Statement stat=null;
			String vid = vv.getvid();
			String vname = vv.getvname();
			String password = vv.getpassword();
			int superuser = vv.getsuperuser();
			try {
				conn=conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
				String sql="update Vip set vname= '"+vname+"',password= '"
				            +password+"',superuser='"+superuser+"' where vid='"+vid+"'";
				stat.executeUpdate(sql);
				flag3=true;
			}catch (SQLException e) {
				System.out.println("����SQL���ʱ���ִ���");
				e.printStackTrace();
			}finally{
				try {
					if(stat!=null){
					  stat.close();
					  stat=null;
					}
					if(conn!=null){
					  conn.close();
					  conn=null;
					}
				} catch (SQLException e) {
					System.out.println("�ر����ӡ���估�����ʱ���ִ���");
					e.printStackTrace();
				}
			}
			return flag3;
		}
		
	    //VIP��ѯ�ض��û�
		public int findVipByVnameAndPassword(String _vname,String _password){	
			int superValue = 0;
			Connection conn=null;
			java.sql.PreparedStatement prep=null;
			ResultSet rs=null;
			try {
				conn=DataAccess.getConnection();
			    prep=conn.prepareStatement
			    		("select * from Vip where vname=? and password=?");
			    System.out.println("123123123");
			    prep.setString(2, _password);
			    prep.setString(1, _vname);
			    rs=prep.executeQuery();
			    if(rs.first()){
			    	superValue=Integer.parseInt(rs.getString("superuser"));
			    	System.out.println("---1---"+superValue);
			    }
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rs!=null){
					  rs.close();
					}
					if(prep!=null){
					  prep.close();
					}
					if(conn!=null){
					  conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return superValue;
		 }
}
