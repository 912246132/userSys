package cn.edu.wgf.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.edu.wgf.dto.movieDTO;
import cn.edu.wgf.util.DataAccess;

public class movieDAO {
	//movie查询
	public Vector<movieDTO> findAllMovieInfo() {
		Vector<movieDTO> v=new Vector<movieDTO>();
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		try {
			conn=DataAccess.getConnection();
			stat=conn.createStatement();//创建SQL语句对象并执行
			String sql="select * from movie";
			rs=stat.executeQuery(sql);
			while(rs.next()){//处理结果集
				movieDTO s=new movieDTO();
				s.setMname(rs.getString("mname"));
				s.setMtime(rs.getString("mtime"));
				s.setMprice(rs.getString("mprice"));
				v.add(s);
			}
			
		}catch (SQLException e) {
			System.out.println("运行SQL语句时出现错误");
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
				System.out.println("关闭连接、语句及结果集时出现错误");
				e.printStackTrace();
			}
		}
		return v;
	}
	//movie多表删除
	public boolean deleteMovieByMname(String mname){
		boolean flag1=false;
		Connection conn=null;
		Statement stat=null;
		try {
			conn=conn=DataAccess.getConnection();
			stat=conn.createStatement();//创建SQL语句对象并执行
			String sql="delete  from code where mname='"+mname+"'";
			String sql1="delete  from movie where mname='"+mname+"'";
			stat.executeUpdate(sql);
			stat.executeUpdate(sql1);
			flag1=true;
		}catch (SQLException e) {
			System.out.println("运行SQL语句时出现错误");
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
				System.out.println("关闭连接、语句及结果集时出现错误");
				e.printStackTrace();
			}
		}
		return flag1;
	}
	//movie多表增加
	public boolean insertMovieByMname(movieDTO mdto){
		boolean flag2=false;
		Connection conn=null;
		Statement stat=null;
		String mname = mdto.getMname();
		String mtime = mdto.getMtime();
		String mprice = mdto.getMprice();
		String sql = "insert into movie values"+
				"('"+mname+"','"+mtime+"','"+mprice+"')";
		try {
			conn=conn=DataAccess.getConnection();
			stat=conn.createStatement();//创建SQL语句对象并执行
			stat.executeUpdate(sql);
			flag2=true;
		}catch (SQLException e) {
			System.out.println("运行SQL语句时出现错误");
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
				System.out.println("关闭连接、语句及结果集时出现错误");
				e.printStackTrace();
			}
		}
		return flag2;
	}
	//movie更新
	public boolean updateMovieByMname(movieDTO mm){
		boolean flag3=false;
		Connection conn=null;
		Statement stat=null;
		String mname = mm.getMname();
		String mtime = mm.getMtime();
		String mprice = mm.getMprice();
		try {
			conn=conn=DataAccess.getConnection();
			stat=conn.createStatement();//创建SQL语句对象并执行
			String sql="update movie set mname='"+mname+"',mtime='"+mtime+"',mprice='"+mprice+"' where mname='"+mname+"'";
			stat.executeUpdate(sql);
			flag3=true;
		}catch (SQLException e) {
			System.out.println("运行SQL语句时出现错误");
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
				System.out.println("关闭连接、语句及结果集时出现错误");
				e.printStackTrace();
			}
		}
		return flag3;
	}
	//movie查询特定用户
			public boolean findMovieByMnameAndMtime(String _mname,String _mtime){
				boolean flag=false; 
				Connection conn=null;
				java.sql.PreparedStatement prep=null;
				ResultSet rs=null;
				try {
					conn=DataAccess.getConnection();
				    prep=conn.prepareStatement
				    		("select * from movie where mname=? and mtime=?");
				    prep.setString(1, _mname);
				    prep.setString(2, _mtime);
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