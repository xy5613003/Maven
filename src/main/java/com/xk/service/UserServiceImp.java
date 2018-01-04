package com.xk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.dao.AUserMapper;
import com.xk.dao.UserDao;
import com.xk.domain.AUser;
import com.xk.domain.AUserExample;
import com.xk.util.PinyinUtil;
import com.xk.util.UUIDBuild;
import com.xk.vo.Page;
import com.xk.vo.ProjectTreeVo;
import com.xk.vo.ReturnObj;
import com.xk.vo.User;

@Service
public class UserServiceImp extends BaseService<AUserMapper, AUserExample, AUser> implements UserService{
	@Resource
	private UserDao ud;
	@Autowired
	public void setAUserMapper(AUserMapper aUsermapper) {
		super.setMapper(aUsermapper);
	}
	@Override
	public User login(User user) {
		
		return ud.login(user);
	}

	
	@Override
	public List<User> getUserListByProjectId(String id) {
		// TODO Auto-generated method stub
		return ud.getUserListByProjectId(id);
	}
	
	public Page findUserPage(User vo, PageBounds pb) {
		if(vo.getName()!=null&&!"".equals(vo.getName())){
			vo.setName("%"+vo.getName()+"%");
		}
		List list= ud.findUserPageList(vo, pb);
		int total = ud.findUserPageCount(vo);
		Page page = new Page(pb,list,total);
		return page;
	}
	@Override
	public ReturnObj saveuser(AUser user) {
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.SAVE_SUCCESS_MSG,null);
		try{
			String name = user.getName();
			if(name!=null&&!"".equals(name)){
				user.setPinyin(PinyinUtil.getFullSpell(name));
				user.setJianpin(PinyinUtil.getFirstSpell(name));
			}
			if(user.getId()!=null&&user.getId().length()>0){ //id存在，做更新操作
				this.update(user);
				result.setMsg(ReturnObj.UPDATE_SUCCESS_MSG);
			}else{  //做保存操作
				user.setId(UUIDBuild.getUUID());
				user.setPwd("123456");
				this.save(user);
				result.setMsg(ReturnObj.SAVE_SUCCESS_MSG);
			}
		}catch(Exception e){
			result.setSuccess(ReturnObj.FAIL);//异常 抛出失败.
			if(user.getId()!=null&&user.getId().length()>0){
				result.setMsg(ReturnObj.UPDATE_FAIL_MSG);
			}else{
				result.setMsg(ReturnObj.SAVE_FAIL_MSG);
			}
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<ProjectTreeVo> getProjectTreeUser(String id) {
		// TODO Auto-generated method stub
		return ud.getProjectTreeUser(id);
	}
	


}
