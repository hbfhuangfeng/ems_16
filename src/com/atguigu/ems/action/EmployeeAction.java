package com.atguigu.ems.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.ems.entities.Employee;
import com.atguigu.ems.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Controller
public class EmployeeAction extends ActionSupport implements RequestAware,
	SessionAware, ModelDriven<Employee>, Preparable{

	@Autowired
	private EmployeeService employeeService;
	
	public void prepareLogin(){
		model = new Employee();
	}
	
	public String login(){
		String loginName = model.getLoginName();
		String password = model.getPassword();
		Employee employee = employeeService.login(loginName, password);
		this.sessionMap.put("employee", employee);
		
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {}

	private Employee model;
	
	@Override
	public Employee getModel() {
		return model;
	}
	
	private Map<String, Object> sessionMap = null;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
	private Map<String, Object> requestMap = null;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}
	
}
