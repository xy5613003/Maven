package com.xk.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.domain.AUser;
import com.xk.domain.AUserRelProExample;
import com.xk.service.ProUserRelProServiceImp;
import com.xk.service.UserServiceImp;
import com.xk.vo.Page;
import com.xk.vo.ReturnObj;
import com.xk.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserServiceImp us;
	@Resource
	private ProUserRelProServiceImp userrelpro;
	/**
	 * 跳转到用户管理页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manageuser")
	public ModelAndView manageuser()throws Exception{
		ModelAndView mv = new ModelAndView("/jsp/user/manageuser");
		return mv;
	}
	
	/**
	 * 查询用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryuser")
	@ResponseBody
	public Object queryuser(User vo,Integer page,Integer rows)throws Exception{
		page = page==null?1:page;
		rows = rows==null?15:rows;
		PageBounds pb = new PageBounds(page,rows);
		Page p = us.findUserPage(vo,pb);
		return p;
	}
	
	/**
	 * 跳转到用户新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toadduser")
	public ModelAndView toadduser()throws Exception{
		ModelAndView view = new ModelAndView("/jsp/user/addsysuser");
		return view;
	}
	/**
	 * 跳转到用户新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveuser",method={RequestMethod.POST})
	@ResponseBody
	public Object saveuser(AUser user)throws Exception{
		System.out.println(user.getId());
		ReturnObj result = us.saveuser(user);
		return result;
	}
	
	/**
	 * 跳转到用户修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toedituser")
	public ModelAndView toedituser(String id)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/user/editsysuser");
		try {
			AUser user = us.get(id);
			view.addObject("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	/**
	 * 跳转到用户新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteuser",method={RequestMethod.GET})
	@ResponseBody
	public Object deleteuser(HttpServletRequest request)throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.DELETE_SUCCESS_MSG,null);
		try {
			String idStr = request.getParameter("ids");
			String[] ids = idStr.split(",");
			AUserRelProExample example = new AUserRelProExample();
			AUserRelProExample.Criteria cri = null;
			for(String id:ids){
				cri = example.createCriteria();
				cri.andUseridEqualTo(id);
				userrelpro.deleteByExample(example); //删除项目人员关系表里的关系
				us.delete(id);
			}
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.DELETE_FAIL_MSG);
		}
		return result;
	}
	/**
	 * 跳转到用户密码修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toupdatepwd")
	public ModelAndView toupdatepwd()throws Exception{
		ModelAndView view = new ModelAndView("/jsp/user/updatepwd");
		return view;
	}
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updatepwd",method={RequestMethod.POST})
	@ResponseBody
	public Object updatepwd(HttpServletRequest request,String oldpwd,String newpwd)throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.UPDATE_SUCCESS_MSG,null);
		try {
			HttpSession session = request.getSession();
			User activeUser = (User) session.getAttribute("user");
			if(!oldpwd.equals(activeUser.getPwd())){
				result.setSuccess(ReturnObj.FAIL);
				result.setMsg("原密码输入错误！");
				return result;
			}
			AUser tab = us.get(activeUser.getId());
			tab.setPwd(newpwd);
			us.updateSelective(tab);
			activeUser.setPwd(newpwd);
			
			session.setAttribute("user", activeUser);
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.UPDATE_FAIL_MSG);
		}
		return result;
	}
}
