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
	
	//完成登录操作
	@Transactional
	public Employee login(String loginName, String password){
		//1. 查询 loginName 对应的 Employee 是否存在
		Employee employee = employeeDao.getBy("loginName", loginName);
		
		if(employee == null){
			throw new LoginNameNotFoundException();
		}
		
		//2. 若存在, 则判断该用户是否可用
		if(employee.getEnabled() != 1){
			throw new AccountUnabledException();
		}
		
		//3. 若账户可用, 则比对密码
		if(!password.equals(employee.getPassword())){
			throw new PasswordNotMatchException();
		}
		
		//4. 若密码匹配, 则登陆次数 + 1
		employee.setVisitedTimes(employee.getVisitedTimes() + 1);
		
		//5. 返回 Employee 对象
		return employee;
	}
}
