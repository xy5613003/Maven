package com.xk.dao;


import com.xk.domain.AUserRelPro;
import com.xk.domain.AUserRelProExample;

public interface UserrelProjectDao extends BaseMapper<AUserRelPro, AUserRelProExample, String>{
	 int  cancelLeader(String projectid);
	int setLeader(String id);
}
