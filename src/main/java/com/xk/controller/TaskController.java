package com.xk.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xk.domain.ATask;
import com.xk.service.TaskServiceImp;
import com.xk.service.UserServiceImp;
import com.xk.vo.Page;
import com.xk.vo.ReturnObj;
import com.xk.vo.Task;
import com.xk.vo.User;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Resource
	private TaskServiceImp tserv;
	@Resource
	private UserServiceImp userv;
	/**
	 * 跳转到任务管理页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/managetask")
	public ModelAndView managetask(HttpServletRequest request)throws Exception{
		ModelAndView mv = new ModelAndView("/jsp/task/managetask");
		try {
			HttpSession session = request.getSession();
			User activeUser = (User) session.getAttribute("user");
			List<User> userList = userv.getUserListByProjectId(activeUser.getProjectid());
			mv.addObject("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 查询任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/querytask")
	@ResponseBody
	public Object querytask(Task vo,Integer page,Integer rows)throws Exception{
		page = page==null?1:page;
		rows = rows==null?15:rows;
		PageBounds pb = new PageBounds(page,rows);
		Page p = tserv.findTaskPage(vo,pb);
		return p;
	}
	
	/**
	 * 跳转到任务新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toaddtask")
	public ModelAndView toaddtask(HttpServletRequest request)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/addtask");
		HttpSession session = request.getSession();
		try {
			User activeUser = (User) session.getAttribute("user");
			List<User> userList = userv.getUserListByProjectId(activeUser.getProjectid());
			view.addObject("userList", userList);
			view.addObject("projectname", activeUser.getProjectname());
			view.addObject("projectid", activeUser.getProjectid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	/**
	 * 跳转到任务新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/savetask",method= {RequestMethod.POST})
	@ResponseBody
	public Object savetask(Task task)throws Exception{
		ReturnObj result = tserv.savetask(task);
		return result;
	}
	
	/**
	 * 加载出任务，并根据action跳转到不同页面
	 * @param request
	 * @param action
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadtask")
	public ModelAndView loadtask(HttpServletRequest request,String action,String id)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/edittask");
		if("view".equals(action)){ //跳转到查看页面
			view.setViewName("/jsp/task/viewtask");
		}
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			User activeUser = (User) session.getAttribute("user");

			List<User> userList = userv.getUserListByProjectId(activeUser.getProjectid());
			view.addObject("userList", userList);
			
			ATask task = tserv.get(id); //逆向工程生成的
			Task taskVo = new Task();
			BeanUtils.copyProperties(task, taskVo);//赋值给自己加了所需属性的对象.用了spring自带的工具
			taskVo.setProjectname(activeUser.getProjectname());
			taskVo.setStarttime(sdf.format(task.getJhstarttime()));//用String接收时间格式
			taskVo.setEndtime(sdf.format(task.getJhendtime()));
			if("copy".equals(action)){  //跳转到复制页面，其实就是编辑页面，但是没有id
				taskVo.setId("");
			}
			view.addObject("task", taskVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	/**
	 * 跳转到任务新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deletetask",method={RequestMethod.GET})
	@ResponseBody
	public Object deletetask(HttpServletRequest request)throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.DELETE_SUCCESS_MSG,null);
		try {
			String idStr = request.getParameter("ids");
			String[] ids = idStr.split(",");
			for(String id:ids){
				tserv.delete(id);
			}
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.DELETE_FAIL_MSG);
		}
		return result;
	}
	/**
	 * 跳转到任务新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myTasks")
	public ModelAndView myTasks(HttpServletRequest request)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/mytasks");
		return view;
	}
	/**
	 * 查询我的任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/querymytask")
	@ResponseBody
	public Object querymytask(HttpServletRequest request,Task vo,Integer page,Integer rows)throws Exception{
		page = page==null?1:page;
		rows = rows==null?15:rows;
	
		HttpSession session = request.getSession();
		User activeUser = (User) session.getAttribute("user");
		vo.setUserid(activeUser.getId());
		PageBounds pb = new PageBounds(page,rows);
		Page p = tserv.findTaskPage(vo,pb);
		return p;
	}
	/**
	 * 启动任务
	 * @param request
	 * @param tab
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/startTask")
	@ResponseBody
	public Object startTask(HttpServletRequest request,ATask tab)throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.DO_SUCCESS_MSG,null);
		try {
			tab.setSjstarttime(new Date());
			tab.setState(1);
			tserv.updateSelective(tab);//开始任务,设置开始时间,更新Task表
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.DO_FAIL_MSG);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 提交任务
	 * @param request
	 * @param tab
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/submitTask")
	@ResponseBody
	public Object submitTask(HttpServletRequest request,ATask tab)throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.DO_SUCCESS_MSG,null);
		try {
			tab.setState(2);//提交后设置为测试状态..2
			System.out.println(tab.toString());
			tserv.updateSelective(tab);
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.DO_FAIL_MSG);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 跳转到我的bug页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myBug")
	public ModelAndView myBug(HttpServletRequest request)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/mybug");
		return view;
	}
	/**
	 * bug查看
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showBug")
	public ModelAndView showBug(HttpServletRequest request,String id)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/showbug");
		ATask tab = tserv.get(id);
		view.addObject("bug", tab.getBug());
		return view;
	}
	/**
	 * 任务描述查看
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showdes")
	public ModelAndView showdes(HttpServletRequest request,String id)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/showdes");
		ATask tab = tserv.get(id);
		view.addObject("description", tab.getDescription());
		return view;
	}
	/**
	 * 查询我的bug
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/querymybug")
	@ResponseBody
	public Object querymybug(HttpServletRequest request,Task vo,Integer page,Integer rows)throws Exception{
		page = page==null?1:page;
		rows = rows==null?15:rows;
		HttpSession session = request.getSession();
		User activeUser = (User) session.getAttribute("user");
		vo.setUserid(activeUser.getId());
		vo.setState(3);
		PageBounds pb = new PageBounds(page,rows);
		Page p = tserv.findTaskPage(vo,pb);
		return p;
	}
	/**
	 * 查询测试任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/querytestingtask")
	@ResponseBody
	public Object querytestingtask(HttpServletRequest request,Task vo,Integer page,Integer rows)throws Exception{
		page = page==null?1:page;
		rows = rows==null?15:rows;
		HttpSession session = request.getSession();
		User activeUser = (User) session.getAttribute("user");
		vo.setState(2);
		vo.setProjectid(activeUser.getProjectid());
		PageBounds pb = new PageBounds(page,rows);
		Page p = tserv.findTaskPage(vo,pb);
		return p;
	}
	/**
	 * 测试任务管理
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/testingtask")
	public ModelAndView testingtask(HttpServletRequest request,String id)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/testingtask");
		return view;
	}
	/**
	 * 提出bug
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toproposebug")
	public ModelAndView toproposebug(HttpServletRequest request,String id)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/proposebug");
		view.addObject("id", id);
		ATask t = tserv.get(id);
		view.addObject("bug", t.getBug());
		return view;
	}
	/**
	 * 完成任务
	 * @param request
	 * @param tab
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/finishtask")
	@ResponseBody
	public Object finishtask(HttpServletRequest request,ATask tab)throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.DO_SUCCESS_MSG,null);
		try {
			tab.setState(4);
			tab.setSjendtime(new Date());
			tserv.updateSelective(tab);
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.DO_FAIL_MSG);
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/proposebug")
	@ResponseBody
	public Object proposebug(HttpServletRequest request,ATask tab)throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,ReturnObj.DO_SUCCESS_MSG,null);
		try {
			HttpSession session = request.getSession();
			User activeUser = (User) session.getAttribute("user");
			tab.setState(3);
			tab.setTesterid(activeUser.getId());
			tserv.updateSelective(tab);
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.DO_FAIL_MSG);
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/toEchardata")
	public ModelAndView toEchardata(HttpServletRequest request)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/Echarts");
		return view;
	}
	@RequestMapping("/Echartdata")
	@ResponseBody
	public Map<String,Object> Echartdata(HttpServletRequest request,Integer month ){
		 month=(month==null?0:month);
			HttpSession session = request.getSession();
			User activeUser = (User) session.getAttribute("user");
			String role=activeUser.getRole();
			if(!"2".equals(role) && !"3".equals(role)) {
				return null;
			}
		Map<String,Object> map=new HashMap<String, Object>();
		List<Map<String,Object>> maplist=new ArrayList<Map<String,Object>>();
		List<String> items=new ArrayList<>();
		List<Integer> datalist=new ArrayList<>();
		
		List<ATask> tasklist=tserv.getEchartdata(month);
		for(ATask task:tasklist) {
			Integer st= task.getState();
			 Map<String,Object> map1 =new HashMap<String, Object>();
			switch (st) {
			case 0: 
				items.add("新建项目");
				map1.put("name", "新建项目");
				map1.put("value",task.getCount());
				break;
			case 1: 
				items.add("进行中");
				map1.put("name", "进行中");
				map1.put("value",task.getCount());
				break;
			case 2: 
				items.add("测试中");
				map1.put("name", "测试中");
				map1.put("value",task.getCount());
				break;
			case 3: 
				items.add("修改中");
				map1.put("name", "修改中");
				map1.put("value",task.getCount());
				break;
			case 4: 
				items.add("已完成");
				map1.put("name", "已完成");
				map1.put("value",task.getCount());
				break;
			}
			maplist.add(map1);
			datalist.add(task.getCount());
			
		}
		map.put("items", items);//横轴名称数组
		map.put("series", maplist);//饼图所需的数组,数组里是name value的键值对map
		 //外部需要的数据类型有2个:第一个是图例 数组类型,里面包含名称.也就是items数组或者集合,第二个是series数据源 是一个
		 //包含着name:**,value:**的map的数组.所以需要将一个name:**,value:**的map 放进数组.最后将这两种数据再用一个map传出去.
		return map;
		
	}
	@RequestMapping("/tofileupload")
	public ModelAndView tofileupload(HttpServletRequest request,@Param("taskid")String taskid)throws Exception{
		ModelAndView view = new ModelAndView("/jsp/task/fileupload");
		HttpSession session = request.getSession();
		User activeUser = (User) session.getAttribute("user");
		view.addObject("name",activeUser.getName());
		view.addObject("taskid", taskid);
		System.out.println(taskid+"taskid");
		return view;
	}
	@RequestMapping("/fileupload")
	@ResponseBody
	public ReturnObj fileupload(HttpServletRequest request,@Param("file") MultipartFile file,String taskid )throws Exception{
		ReturnObj result = new ReturnObj(ReturnObj.SUCCESS,"上传成功",taskid);
		try {
			
			User user=(User) request.getSession().getAttribute("user");
			 // 获得原始文件名  
	        String fileName = file.getOriginalFilename();  
	        System.out.println("原始文件名:" + fileName);  
	        // 新文件名  
	        String newFileName =taskid+"_"+fileName;  
	        // 获得项目的路径  
	        ServletContext sc = request.getSession().getServletContext();  
	        // 上传位置  
	        String path = sc.getRealPath("/file") + "/"; // 设定文件保存的目录  
	        File f = new File(path+user.getName(),newFileName);  
	        if (!f.exists())
	            f.mkdirs();  
	        if (!file.isEmpty()) {  
	            	file.transferTo(f); 
	        }  
	  
	        System.out.println("上传文件到:" + path+user.getName() + newFileName);  
		} catch (Exception e) {
			result.setSuccess(ReturnObj.FAIL);
			result.setMsg(ReturnObj.DO_FAIL_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
}
