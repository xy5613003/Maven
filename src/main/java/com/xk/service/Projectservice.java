package com.xk.service;

import java.util.List;

import com.xk.domain.AProject;
import com.xk.vo.Project;
import com.xk.vo.ReturnObj;

public interface Projectservice {
Project getByid(String id);
List<Project> queryAllpro();
ReturnObj saveproject(AProject project); 
}
