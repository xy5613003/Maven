package com.xk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.domain.AUser;
import com.xk.domain.AUserExample;
import com.xk.vo.ProjectTreeVo;
import com.xk.vo.User;

public interface UserDao extends BaseMapper<AUser, AUserExample, String>{
	User login(@Param("user")User user);
	List<User> getUserListByProjectId(String id);
	int updateSelective(User user);
	List<User> findUserPageList(User user,PageBounds pb);
	int findUserPageCount(User user);
	List<ProjectTreeVo> getProjectTreeUser(String id);
}
