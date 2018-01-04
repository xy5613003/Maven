package com.xk.vo;

import java.util.Date;

public class Task {
	private String id;
    private String name;
    private String description;
    private String bug;
    private Date jhstarttime;
    private Date jhendtime;
    private Date sjstarttime;
    private Date sjendtime;
    private Integer state;
    private String userid;
    private String testerid;
    private String projectid;
    //以下mapper里未映射
    
    private String jhst;
    private String jhet;
    private String sjst;
    private String sjet;
    private String user;
    private String tester;
    private String projectname;
    private String starttime;
    private String endtime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBug() {
		return bug;
	}
	public void setBug(String bug) {
		this.bug = bug;
	}
	public Date getJhstarttime() {
		return jhstarttime;
	}
	public void setJhstarttime(Date jhstarttime) {
		this.jhstarttime = jhstarttime;
	}
	public String getJhst() {
		return jhst;
	}
	public void setJhst(String jhst) {
		this.jhst = jhst;
	}
	public Date getJhendtime() {
		return jhendtime;
	}
	public void setJhendtime(Date jhendtime) {
		this.jhendtime = jhendtime;
	}
	public String getJhet() {
		return jhet;
	}
	public void setJhet(String jhet) {
		this.jhet = jhet;
	}
	public Date getSjstarttime() {
		return sjstarttime;
	}
	public void setSjstarttime(Date sjstarttime) {
		this.sjstarttime = sjstarttime;
	}
	public String getSjst() {
		return sjst;
	}
	public void setSjst(String sjst) {
		this.sjst = sjst;
	}
	public Date getSjendtime() {
		return sjendtime;
	}
	public void setSjendtime(Date sjendtime) {
		this.sjendtime = sjendtime;
	}
	public String getSjet() {
		return sjet;
	}
	public void setSjet(String sjet) {
		this.sjet = sjet;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTesterid() {
		return testerid;
	}
	public void setTesterid(String testerid) {
		this.testerid = testerid;
	}
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
    
}
