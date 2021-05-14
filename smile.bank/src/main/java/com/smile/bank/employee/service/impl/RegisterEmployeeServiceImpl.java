package com.smile.bank.employee.service.impl;

import com.smile.bank.customer.service.RegisterCustomerService;
import com.smile.bank.customer.service.impl.CustomerValidation;
import com.smile.bank.employee.dao.RegisterEmployeeDAO;
import com.smile.bank.employee.dao.impl.RegisterEmployeeDAOImpl;
import com.smile.bank.employee.service.RegisterEmployeeService;
import com.smile.bank.exception.SmileException;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.Employee;
import com.smile.bank.model.Employee_Creds;

public class RegisterEmployeeServiceImpl implements RegisterEmployeeService {
    SmileLog smile=new SmileLog();
    private RegisterEmployeeDAO dao=new RegisterEmployeeDAOImpl();

    public int createEmployee(Employee employee) throws SmileException{
        if (!CustomerValidation.isValidName(employee.getName())) {
            throw new SmileException(employee.getName() + " is not a valid name.");
        }
        if (!CustomerValidation.isValidEmail(employee.getEmail())) {
            throw new SmileException(employee.getEmail() + " is not a valid Email.");
        }
        return dao.createEmployee(employee);
    }

    public int createEmployeeCreds(Employee_Creds employeecreds) throws
            SmileException{
        if (!CustomerValidation.isValidEmail(employeecreds.getEmail())) {
            throw new SmileException(employeecreds.getEmail() + " is not a valid Email.");
        }
        if (!CustomerValidation.isValidPassword(employeecreds.getPassword())) {
            throw new SmileException(employeecreds.getPassword() + " is not a valid Password.");
        }
        return dao.createEmployeeCreds(employeecreds);
    }

    @Override
    public int purge(int purgeme, int id) throws SmileException{
        if(purgeme==1) {
            smile.message("If this got called, your data got deleted from Database. Customer ID: " +id);
            return dao.purge(purgeme, id);
        }
        if(purgeme==0) {
            smile.message("Customer ID: " +id);
        }
        return 0;
    }
}
