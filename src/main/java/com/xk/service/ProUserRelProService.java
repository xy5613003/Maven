package com.xk.service;

import java.util.List;

import com.xk.domain.AUserRelPro;

public interface ProUserRelProService {
	void setLeader(String id ,String projectid);
	List<AUserRelPro> getProUserRelList(String projectId,String id);

}
