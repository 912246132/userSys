package cn.edu.wgf.dto;

public class VIPDTO {

	private String vname;
	private String vid;
	private String password;
	private int superuser;
	public String getvname() {
		return vname;
	}
	public void setvname(String vname) {
		this.vname = vname;
	}
	public String getvid() {
		return vid;
	}
	public void setvid(String vid) {
		this.vid = vid;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public int getsuperuser() {
		return superuser;
	}
	public void setsuperuser(int superuser) {
		this.superuser = superuser;
	}

}
