package cn.edu.wgf.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.edu.wgf.dto.codeDTO;
import cn.edu.wgf.util.DataAccess;

public class codeDAO {
	//��ѯ
		public Vector<codeDTO> findAllcodeInfo(String username) {
			Vector<codeDTO> v=new Vector<codeDTO>();
			Connection conn=null;
			Statement stat=null;
			ResultSet rs=null;
			Statement stat1=null;
			Statement stat2=null;
			try {
				conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
				//���vname = admin,���ѯ���ж�Ʊ��Ϣ
				String sql1="select * from code";
				String sql2="select * from code where vname = '"+username+"'";
				if(username=="admin")
				    rs=stat.executeQuery(sql1);
				//��������û�����ȥѡ���ѯ�Ķ�Ʊ��Ϣ
				else
					rs=stat.executeQuery(sql2);
				while(rs.next()){//��������
					codeDTO s=new codeDTO();
					s.setFlowid(rs.getInt("Flowid"));
					s.setVname(rs.getString("vname"));
					s.setMname(rs.getString("mname"));
					s.setvipCode(rs.getInt("vipcode"));
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
	//code���ɾ��(1)
		public boolean deletecodeByVnameAndMname (String _vname,String _mname){
			boolean flag1=false;
			Connection conn=null;
			Statement stat=null;
			Statement stat1=null;
			ResultSet rs=null;
			ResultSet rs1=null;
			try {
				conn=conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
				stat1=conn.createStatement();//����SQL������ִ��
				//���ɾ��booking�е���Ϣ����ô�����ⲿ��Ӱ����û���˿���û�о���movie���¼ܣ�����movie����
				String sql2="select * from code where mname ='"+_mname+"'";
				String sql3=null;
				rs = stat.executeQuery(sql2);
				String mname=null;
				while(rs.next()){
					mname = rs.getString(2);
					sql3="select count(*) as num from booking where mname ='"+_mname+"'";
				    rs1 = stat1.executeQuery(sql3);
					rs1.next();
					if(rs1.getInt("num")==1){
						stat1.executeUpdate("delete from booking where mname ='"+_mname+"'");
						stat1.executeUpdate("delete from movie where mname ='"+_mname+"'");	
					}
				}
				    stat.executeUpdate("delete  from code where vname='"+_vname+"' and mname='"+_mname+"'");
				    flag1 = true;	
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
		//codeɾ����2��
				public boolean deleteCodeByFlowid(int flowid){
					boolean flag3=false;
					Connection conn=null;
					Statement stat=null;
					try {
						conn=conn=DataAccess.getConnection();
						stat=conn.createStatement();//����SQL������ִ��
						String sql="delete from code where flowid='"+flowid+"'";
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
	//code�������
		public boolean insertCodeInfo(codeDTO sdto){
			boolean flag2=false;
			Connection conn=null;
			Statement stat=null;
			String vname = sdto.getVname();
			String mname = sdto.getMname();
			int vipcode = sdto.getvipCode();
			String sql1="insert into code(vname,mname,vipcode) values"
			            +"('"+vname+"','"+mname+"','"+vipcode+"')";
			try {
				conn=conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
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
		//code ���£�2��
		public boolean updateCodeInfo(codeDTO sdto){
			boolean flag2=false;
			Connection conn=null;
			Statement stat=null;
			String vname = sdto.getVname();
			String mname = sdto.getMname();
			int vipcode = sdto.getvipCode();
			String sql1="update code set vname='"+vname+"',mname='"+mname+"',vipcode='"+vipcode+"'";
			try {
				conn=conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
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
		//code���£�1��
		public boolean updateBookingByVname(String Vname){
			boolean flag3=false;
			Connection conn=null;
			Statement stat=null;
			try {
				conn=conn=DataAccess.getConnection();
				stat=conn.createStatement();//����SQL������ִ��
				String sql="update booking set code='123456' where vname='����'";
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
		//code��ѯ�ض��û�
		public boolean findBookingByVname(String _vname){
			boolean flag=false; 
			Connection conn=null;
			java.sql.PreparedStatement prep=null;
			ResultSet rs=null;
			try {
				conn=DataAccess.getConnection();
			    prep=conn.prepareStatement
			    		("select * from booking where vname=?");
			    prep.setString(1, _vname);
			    rs=prep.executeQuery();
			    if(rs.first())
			    	flag=true;
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
			return flag;
		 }
}
