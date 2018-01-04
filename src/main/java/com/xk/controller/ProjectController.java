package com.xk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xk.domain.AProject;
import com.xk.domain.AProjectExample;
import com.xk.domain.AUserRelPro;
import com.xk.domain.AUserRelProExample;
import com.xk.service.ProUserRelProServiceImp;
import com.xk.service.ProjectserviceImp;
import com.xk.service.UserServiceImp;
import com.xk.util.UUIDBuild;
import com.xk.vo.ProjectTreeVo;
import com.xk.vo.ReturnObj;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Resource
	private ProjectserviceImp pserv;
	@Resource
	private UserServiceImp userv;
	
	@Autowired
	private ProUserRelProServiceImp userrelpro;
	
	/**
	 * 跳转到用户管理页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manageproject")
	public ModelAndView manageproject()throws Exception{
		ModelAndView mv = new ModelAndView("/jsp/project/manageproject");
		return mv;
	}
	
	/**
	 * 查询项目
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryprojecttree")
	@ResponseBody
	public Object queryprojecttree(String name)throws Exception{
		List<ProjectTreeVo> list = new ArrayList<ProjectTreeVo>();
		AProjectExample example = new AProjectExample();
		example.setOrderByClause("createtime desc");
		AProjectExample.Criteria cri = example.createCriteria();
		if(name!=null&&name.length()>0){
			cri.andNameLike("%"+name+"%");
		}
		List<AProject> projectList = pserv.findList(example);
		ProjectTreeVo vo = null;
		for(AProject project:projectList){
			vo = new ProjectTreeVo();
			vo.setId(project.getId());
			vo.setName(project.getName());
			vo.setMemo(project.getDescription());
			List<ProjectTreeVo> children = userv.getProjectTreeUser(vo.getId());
			vo.setChildren(children);
			list.add(vo);
		}
		return list;
	}
	
	/**
	 * 跳转到用户新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toaddproject")
	public ModelAndView toaddproject()throws Exception{
		ModelAndView view = new ModelAndView("/jsp/project/addproject");
		return view;
	}
	/**
	 * 跳转到用户新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveproject",method={RequestMethod.POST})
	@ResponseBody
	public Object saveproject(AProject project)throws Exception{
		ReturnObj result = pserv.saveproject(project);
		return result;
	}
	
	/**
	 * 跳转到用户修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toeditproject")
	public ModelAndView toeditproject(String id)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/project/editproject");
		try {
			AProject project = pserv.get(id);
			view.addObject("project", project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	/**
	 * 删除项目或用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteproject",method={RequestMethod.GET})
	@ResponseBody
	public Object deleteproject(HttpServletRequest request)throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.DELETE_SUCCESS_MSG,null);
		try {
			String idStr = request.getParameter("ids");
			String[] ids = idStr.split(",");
			int i = 0;

			AUserRelProExample example = new AUserRelProExample();
			AUserRelProExample.Criteria cri = null;
			
			for(String id:ids){
				i = pserv.delete(id);
				if(i==1){  //如果删除项目成功，则要删除项目角色关系表里的关系
					cri = example.createCriteria();
					cri.andProjectidEqualTo(id);
					userrelpro.deleteByExample(example);
				}else{//如果删除项目不成功，则id为项目用户关系表的id，直接删除关系即可
					userrelpro.delete(id);
				}
			}
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.DELETE_FAIL_MSG);
		}
		return result;
	}

	/**
	 * 跳转添加用户页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toaddprojectuser")
	public ModelAndView toaddprojectuser(String projectId)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/project/addprojectuser");
		view.addObject("projectId", projectId);
		return view;
	}
	@RequestMapping("/addprojectuser")
	@ResponseBody
	public Object addprojectuser(String userId,String projectId)throws Exception{
		ReturnObj ret = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.ADD_SUCCESS_MSG,null);
		try {
			AUserRelPro tab = null;
			String[] ids = userId.split(",");
			for(String id:ids){
				List<AUserRelPro> list = userrelpro.getProUserRelList(projectId,id); //判断项目和用户关系是否存在
				if(list!=null&&list.size()>0){
					continue;
				}
				tab = new AUserRelPro();
				tab.setId(UUIDBuild.getUUID());
				tab.setUserid(id);
				tab.setProjectid(projectId);
				tab.setRole(0);
				userrelpro.save(tab);
			}
		} catch (Exception e) {
			ret.setSuccess(ReturnObj.FAIL);
			ret.setMsg(ReturnObj.ADD_FAIL_MSG);
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 判断项目中是否存在组长，因为，一个组只能有一个组长
	 */
	@RequestMapping("/existsLeader")
	@ResponseBody
	public Object existsLeader(String projectid)throws Exception{
		ReturnObj ret = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.SET_SUCCESS_MSG,null);
		try {
			AUserRelProExample example = new AUserRelProExample();
			AUserRelProExample.Criteria cri = example.createCriteria();
			cri.andProjectidEqualTo(projectid);
			cri.andRoleEqualTo(2);//2表示组长
			List<AUserRelPro> list = userrelpro.findList(example);
			if(list==null||list.size()==0){ //表示不存在
				ret.setSuccess(ReturnObj.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 设置组长
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public Object updateRole(String id,String projectid,Integer role)throws Exception{
		ReturnObj ret = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.SET_SUCCESS_MSG,null);
		try {
			if(role==2){  //如果是设置组长，则需要先取消原有组长，然后再设置为新组长
				userrelpro.setLeader(id,projectid);
			}else{
				AUserRelPro tab = userrelpro.get(id);
				tab.setRole(role);
				userrelpro.updateSelective(tab);
			}
		} catch (Exception e) {
			ret.setSuccess(ReturnObj.FAIL);
			ret.setMsg(ReturnObj.SET_FAIL_MSG);
			e.printStackTrace();
		}
		return ret;
	}
}
