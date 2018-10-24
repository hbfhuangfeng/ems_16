package com.atguigu.ems.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atguigu.ems.entities.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Employee getBy(String propertyName, Object propertyVal){
		Criteria criteria = getSession().createCriteria(Employee.class);
		
		//���� Restrictions ������������ȡ Criterion ����
		Criterion criterion = Restrictions.eq(propertyName, propertyVal);
		//��Ӳ�ѯ����
		criteria.add(criterion);
		
		//����Ψһ�Ľ��. 
		return (Employee) criteria.uniqueResult();
	}
}
