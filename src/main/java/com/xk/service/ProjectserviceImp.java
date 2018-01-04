package com.xk.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.dao.AProjectMapper;
import com.xk.dao.ProjectDao;
import com.xk.domain.AProject;
import com.xk.domain.AProjectExample;
import com.xk.util.UUIDBuild;
import com.xk.vo.Project;
import com.xk.vo.ReturnObj;

@Service
public class ProjectserviceImp extends BaseService<AProjectMapper, AProjectExample, AProject> implements Projectservice{
@Resource
private ProjectDao projectdao;
@Autowired
public void setAProjectMapper(AProjectMapper promapper) {
	super.setMapper(promapper);
}

@Override
public Project getByid(String id) {
	// TODO Auto-generated method stub
	return projectdao.getByid(id);
}

@Override
@Transactional
public List<Project> queryAllpro() {
	// TODO Auto-generated method stub
	return projectdao.queryAllpro();
}

@Override
public ReturnObj saveproject(AProject project) {
	ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.SAVE_SUCCESS_MSG,null);
	try{
		if(project.getId()!=null&&project.getId().length()>0){ //id存在，做更新操作
			this.updateSelective(project);
			result.setMsg(ReturnObj.UPDATE_SUCCESS_MSG);
		}else{  //做保存操作
			project.setId(UUIDBuild.getUUID());
			project.setCreatetime(new Date());
			this.save(project);
			result.setMsg(ReturnObj.SAVE_SUCCESS_MSG);
		}
	}catch(Exception e){
		result.setSuccess(ReturnObj.FAIL);
		if(project.getId()!=null&&project.getId().length()>0){
			result.setMsg(ReturnObj.UPDATE_FAIL_MSG);
		}else{
			result.setMsg(ReturnObj.SAVE_FAIL_MSG);
		}
		e.printStackTrace();
	}
	return result;
}





}
