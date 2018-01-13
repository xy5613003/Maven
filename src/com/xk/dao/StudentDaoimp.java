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
	 * 1.his.getsessionʵ�����ǵ����˸����еķ������session��ʹ��spring����hibernate��SessionFactory��ʱ��
	 * ������������session�����ó�һsession.�������п��������⣬���ǳ�session����������ʱ��spring�޷��Զ��Ĺر�session��
	 * ���Ƽ�ʹ�� 2. this.getHibernateTemplate().getSessionFactory().getCurrentSession()
	 * ��spring�����sessionFactory��
	 * ����һ�����̵߳�session.spring����ݸ��̵߳�ִ��������Զ��ж��ǹر�session�����ӳٹرա����������Ա����ֶ��Ĺ���ʵ��
	 * ͬʱһ���߳���࿪���͹ر�һ��session�ֿ�����߳�������ܡ� �����Ƽ�ʹ�����ַ��� 3.
	 * this.getHibernateTemplate().getSessionFactory().OpenSession��
	 * ���ַ�����spring�����sessionFactory��
	 * ����һ��session����session�����̰߳󶨵ġ���ִ����һ��ʵ���ʱ���Զ��ر�session.���ַ��������ֶ�����ʵ��
	 * ����ͬһ���̶߳�εĿ����͹ر�session,�˷�ϵͳ��Դ��Ӱ��ִ��Ч�ʣ���������»��ǲ�Ҫ���ˡ�
	 */
	private HibernateTemplate temlate;

	@Override
	public int getCount() {
		String hql = "select count(*) from Student ";
		Integer count = ((Number) temlate.find(hql).listIterator().next()).intValue();
		return count;
	}

	/*
	 * ��Hibernate 3.0.x/3.1.x���������µ�3.2��֮��3.2��ĺܶ�sql������count(),
	 * sum()��Ψһ����ֵ�Ѿ���Integer��ΪLong��������������룬��õ�һ��ClassCastException��
	 * ����仯��Ҫ��Ϊ�˼���JPA��������hibernate.org�������ĵ����ҵ�˵����
	 * 
	 * ���������
	 * 
	 * 1.hibernate��������������ʹ��hibernate�Ĳ�ѯ����count(),sum()�ȵ�ֵʱ��ע�⣺һ����ֻ����Ψһֵ�Ĳ���Ϊ���ָ�ʽ�ǲſ���
	 * �����ѵ���query��uniqueResult();���� �˷�������Object����ֻ��Ҫ����תΪNumber���ͣ�Ȼ�����.intValue()���ɡ�
	 * 
	 * ����:Integer
	 * count=((Number)(session.createQuery(hql)).uniqueResult()).intValue();
	 * 
	 * 2.���Խ�longתΪ�ַ�����Ȼ���ַ�����תΪinteger��
	 * 
	 * Long i = (Long) session.createQuery(hql).uniqueResult();
	 * 
	 * Integer ii= new Integer(String.valueOf(i));
	 * 
	 * OK,�����ַ������ɽ����������~
	 */
	@Override
	public List<Student> queryAll(int row, int page) {
		// hibernateTemplate��ҳ��ѯ��
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
