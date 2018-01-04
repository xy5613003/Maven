package com.xk.dao;

import java.util.List;

import com.xk.domain.ATask;
import com.xk.domain.ATaskExample;

public interface ATaskMapper extends BaseMapper<ATask, ATaskExample, String>{
	
	List<ATask> getEchartdata(Integer month);
	
}