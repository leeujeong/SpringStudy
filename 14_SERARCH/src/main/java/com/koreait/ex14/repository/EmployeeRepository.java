package com.koreait.ex14.repository;

import java.util.List;
import java.util.Map;

import com.koreait.ex14.domain.Employee;


public interface EmployeeRepository {

	public int selectTotalRecordCount();
	public List<Employee> selectEmployeeList(Map<String, Object> map);
	public int selectFindRecordCount(Map<String, Object> map);
	public List<Employee> selectFindList(Map<String , Object> map);
}