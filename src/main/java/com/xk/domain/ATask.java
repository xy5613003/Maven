package com.xk.domain;

import java.util.Date;

public class ATask {
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
    private int count;
    

    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug == null ? null : bug.trim();
    }

    public Date getJhstarttime() {
        return jhstarttime;
    }

    public void setJhstarttime(Date jhstarttime) {
        this.jhstarttime = jhstarttime;
    }

    public Date getJhendtime() {
        return jhendtime;
    }

    public void setJhendtime(Date jhendtime) {
        this.jhendtime = jhendtime;
    }

    public Date getSjstarttime() {
        return sjstarttime;
    }

    public void setSjstarttime(Date sjstarttime) {
        this.sjstarttime = sjstarttime;
    }

    public Date getSjendtime() {
        return sjendtime;
    }

    public void setSjendtime(Date sjendtime) {
        this.sjendtime = sjendtime;
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
        this.userid = userid == null ? null : userid.trim();
    }

    public String getTesterid() {
        return testerid;
    }

    public void setTesterid(String testerid) {
        this.testerid = testerid == null ? null : testerid.trim();
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

	@Override
	public String toString() {
		return "ATask [id=" + id + ", name=" + name + ", description=" + description + ", bug=" + bug + ", jhstarttime="
				+ jhstarttime + ", jhendtime=" + jhendtime + ", sjstarttime=" + sjstarttime + ", sjendtime=" + sjendtime
				+ ", state=" + state + ", userid=" + userid + ", testerid=" + testerid + ", projectid=" + projectid
				+ ", count=" + count + "]";
	}
    
}