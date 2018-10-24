package com.atguigu.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ems.dao.EmployeeDao;
import com.atguigu.ems.entities.Employee;
import com.atguigu.ems.exceptions.AccountUnabledException;
import com.atguigu.ems.exceptions.LoginNameNotFoundException;
import com.atguigu.ems.exceptions.PasswordNotMatchException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	//��ɵ�¼����
	@Transactional
	public Employee login(String loginName, String password){
		//1. ��ѯ loginName ��Ӧ�� Employee �Ƿ����
		Employee employee = employeeDao.getBy("loginName", loginName);
		
		if(employee == null){
			throw new LoginNameNotFoundException();
		}
		
		//2. ������, ���жϸ��û��Ƿ����
		if(employee.getEnabled() != 1){
			throw new AccountUnabledException();
		}
		
		//3. ���˻�����, ��ȶ�����
		if(!password.equals(employee.getPassword())){
			throw new PasswordNotMatchException();
		}
		
		//4. ������ƥ��, ���½���� + 1
		employee.setVisitedTimes(employee.getVisitedTimes() + 1);
		
		//5. ���� Employee ����
		return employee;
	}
}
