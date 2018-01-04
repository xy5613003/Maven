package com.xk.service;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.domain.ATask;
import com.xk.vo.Page;
import com.xk.vo.Task;

public interface TaskService {
	Page findTaskPage(Task vo, PageBounds pb);
	List<ATask> getEchartdata(Integer month);
}
