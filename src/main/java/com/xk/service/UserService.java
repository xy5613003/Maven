package com.xk.service;

import java.util.List;

import com.xk.domain.AUser;
import com.xk.vo.ProjectTreeVo;
import com.xk.vo.ReturnObj;
import com.xk.vo.User;

public interface UserService {
User login(User user);

List<User> getUserListByProjectId(String id);
ReturnObj saveuser(AUser user);
List<ProjectTreeVo>	getProjectTreeUser(String id);
}
