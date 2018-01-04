package com.xk.dao;

import java.util.List;

import com.xk.domain.AUserRelPro;
import com.xk.domain.AUserRelProExample;
import com.xk.vo.Project;

public interface ProjectDao extends BaseMapper<AUserRelPro, AUserRelProExample, String>{
	Project getByid(String id);
	List<Project> queryAllpro();
}
