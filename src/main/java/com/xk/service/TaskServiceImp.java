package com.xk.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.dao.ATaskMapper;
import com.xk.dao.TaskDao;
import com.xk.domain.ATask;
import com.xk.domain.ATaskExample;
import com.xk.util.UUIDBuild;
import com.xk.vo.Page;
import com.xk.vo.ReturnObj;
import com.xk.vo.Task;

@Service
public class TaskServiceImp extends BaseService<ATaskMapper, ATaskExample, ATask> implements TaskService{
	@Resource
	private TaskDao td;
	@Autowired
	public void setATaskMapper(ATaskMapper aTaskMapper) {
		super.setMapper(aTaskMapper);
	}
	
	public Page findTaskPage(Task task, PageBounds pb) {
		List list= td.findTaskPageList(task, pb);
		int total = td.findTaskPageCount(task);
		Page page = new Page(pb,list,total);
		return page;
	}
	public ReturnObj savetask(Task task) {
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.SAVE_SUCCESS_MSG,null);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(task.getId()!=null&&task.getId().length()>0){
				ATask tab = mapper.selectByPrimaryKey(task.getId());
				tab.setName(task.getName());
				tab.setJhstarttime(sdf.parse(task.getStarttime())); //转换时间
				tab.setJhendtime(sdf.parse(task.getEndtime()));
				tab.setUserid(task.getUserid());
				tab.setDescription(task.getDescription());
				this.update(tab);
				result.setMsg(ReturnObj.UPDATE_SUCCESS_MSG);
			}else{
				ATask tab = new ATask();
				BeanUtils.copyProperties(task, tab);
				tab.setId(UUIDBuild.getUUID());
				tab.setJhstarttime(sdf.parse(task.getStarttime())); //转换时间
				tab.setJhendtime(sdf.parse(task.getEndtime()));
				tab.setState(0);//初始化状态
				this.save(tab);
				result.setMsg(ReturnObj.SAVE_SUCCESS_MSG);
			}
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			if(task.getId()!=null&&task.getId().length()>0){
				result.setMsg(ReturnObj.UPDATE_FAIL_MSG);
			}else{
				result.setMsg(ReturnObj.SAVE_FAIL_MSG);
			}
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ATask> getEchartdata(Integer month) {
		// TODO Auto-generated method stub
		return mapper.getEchartdata(month);
	}

	


}
