package com.xk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.dao.AUserRelProMapper;
import com.xk.dao.UserrelProjectDao;
import com.xk.domain.AUserRelPro;
import com.xk.domain.AUserRelProExample;



@Service
public class ProUserRelProServiceImp extends BaseService<AUserRelProMapper, AUserRelProExample, AUserRelPro> implements ProUserRelProService{
	@Autowired
	public void setAUserRelProMapper(AUserRelProMapper userrelpro) {
		super.setMapper(userrelpro);
	}
	@Resource
	private UserrelProjectDao usrp;

	@Override
	public void setLeader(String id, String projectid) {
		
		usrp.cancelLeader(projectid);
		usrp.setLeader(id);
	}

	@Override
	public List<AUserRelPro> getProUserRelList(String projectId, String id) {
		AUserRelProExample example = new AUserRelProExample();
		AUserRelProExample.Criteria cri = example.createCriteria();
		cri.andUseridEqualTo(id);
		cri.andProjectidEqualTo(projectId);
		return mapper.selectByExample(example, new PageBounds());
		
	}
}
