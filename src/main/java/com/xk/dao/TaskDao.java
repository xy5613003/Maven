package com.xk.dao;


import java.util.List;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.domain.ATask;
import com.xk.domain.ATaskExample;
import com.xk.vo.Task;

public interface TaskDao extends BaseMapper<ATask, ATaskExample, String>{
	List<Task> findTaskPageList(Task task,PageBounds pb);
	int findTaskPageCount(Task task);
}
