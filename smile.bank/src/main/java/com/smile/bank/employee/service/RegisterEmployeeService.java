package com.smile.bank.employee.service;

import com.smile.bank.exception.SmileException;

import com.smile.bank.model.Employee;
import com.smile.bank.model.Employee_Creds;

public interface RegisterEmployeeService {
    public int createEmployee(Employee employee) throws SmileException;

    public int createEmployeeCreds(Employee_Creds employeecreds) throws
            SmileException;
    public int purge(int purgeme,int id) throws SmileException;
}
