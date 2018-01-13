package com.xk.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.xk.vo.Student;

public class StudentDaoimp implements IStudentDao {
	/*
	 * 1.his.getsession实际上是调用了父类中的方法获得session。使用spring管理hibernate的SessionFactory的时候
	 * ，这个方法会从session池中拿出一session.这样做有可能有问题，就是超session池连接数的时候，spring无法自动的关闭session。
	 * 不推荐使用 2. this.getHibernateTemplate().getSessionFactory().getCurrentSession()
	 * 从spring管理的sessionFactory中
	 * 创建一个绑定线程的session.spring会根据该线程的执行情况来自动判断是关闭session还是延迟关闭。这样做可以避免手动的管理实务，
	 * 同时一个线程最多开启和关闭一次session又可以提高程序的性能。 极力推荐使用这种方法 3.
	 * this.getHibernateTemplate().getSessionFactory().OpenSession。
	 * 这种方法从spring管理的sessionFactory中
	 * 创建一个session，此session不是线程绑定的。当执行完一个实务的时候自动关闭session.这种方法不用手动管理实务，
	 * 但是同一个线程多次的开启和关闭session,浪费系统资源和影响执行效率，正常情况下还是不要用了。
	 */
	private HibernateTemplate temlate;

	@Override
	public int getCount() {
		String hql = "select count(*) from Student ";
		Integer count = ((Number) temlate.find(hql).listIterator().next()).intValue();
		return count;
	}

	/*
	 * 从Hibernate 3.0.x/3.1.x升级到最新的3.2版之后，3.2版的很多sql函数如count(),
	 * sum()的唯一返回值已经从Integer变为Long，如果不升级代码，会得到一个ClassCastException。
	 * 这个变化主要是为了兼容JPA，可以在hibernate.org的最新文档中找到说明。
	 * 
	 * 解决方案：
	 * 
	 * 1.hibernate尤其解决方案。当使用hibernate的查询函数count(),sum()等的值时（注意：一定是只返回唯一值的并且为数字格式是才可以
	 * ）可已调用query的uniqueResult();方法 此方法返回Object对象，只需要把它转为Number类型，然后调用.intValue()即可。
	 * 
	 * 例如:Integer
	 * count=((Number)(session.createQuery(hql)).uniqueResult()).intValue();
	 * 
	 * 2.可以将long转为字符串，然后将字符串再转为integer；
	 * 
	 * Long i = (Long) session.createQuery(hql).uniqueResult();
	 * 
	 * Integer ii= new Integer(String.valueOf(i));
	 * 
	 * OK,这两种方案即可解决此类问题~
	 */
	@Override
	public List<Student> queryAll(int row, int page) {
		// hibernateTemplate分页查询哈
		String hql = "from Student s order by s.s_id";

		List list = temlate.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((page - 1) * row);
				query.setMaxResults(row);
				List<Student> list = query.list();
				return list;
			}
		});

		return list;
	}

	public HibernateTemplate getTemlate() {
		return temlate;
	}

	public void setTemlate(HibernateTemplate temlate) {
		this.temlate = temlate;
	}

}
