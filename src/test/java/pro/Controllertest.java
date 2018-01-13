package pro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.xk.dao.UserDao;
import com.xk.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:springMVC.xml","classpath:applicationContext.xml"})
public class Controllertest {
	@Autowired
	private WebApplicationContext wec;
	@Resource
	private UserDao ud;
	private MockMvc moc;
	
	@Before
	public void setup() {
		moc=MockMvcBuilders.webAppContextSetup(wec).build();
	}

	@Test
	public void insert() throws Exception {
		User user=new User();
		user.setUsername("1");
		user.setPwd("123456");
		user.setProjectid("905dd5297f274a68b366ef635b6bff5b");
		User user1=ud.login(user);
		System.out.println(user1.getName());
//		 moc.perform(get("/first.action"))
//	        .andDo(print())
//	        .andExpect(redirectedUrl("/jsp/login.jsp"));
		 }
  

}
