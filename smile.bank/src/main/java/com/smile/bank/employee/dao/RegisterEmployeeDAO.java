package com.smile.bank.employee.dao;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.Employee;
import com.smile.bank.model.Employee_Creds;

public interface RegisterEmployeeDAO {

    public int createEmployee(Employee employee) throws SmileException;

    public int createEmployeeCreds(Employee_Creds employeecreds) throws
            SmileException;
    public int purge(int purgeme,int id) throws SmileException;
}
