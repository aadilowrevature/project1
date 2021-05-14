package com.smile.bank.functions.service.impl;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.ApproveAccountDAO;
import com.smile.bank.functions.dao.impl.ApproveAccountDAOImpl;
import com.smile.bank.functions.service.ApproveAccountService;
import com.smile.bank.model.Account;
import com.smile.bank.model.Employee;

import java.util.List;

public class ApproveAccountServiceImpl implements ApproveAccountService {
	private ApproveAccountDAO appaccdao = new ApproveAccountDAOImpl();

	@Override
	public int approveAccount(int ID, String account_type,String employee_email, Employee employee) throws SmileException {
		// TODO Auto-generated method stub
		return appaccdao.approveAccount(ID, account_type,employee_email,employee);
	}

	@Override
	public int denyAccount(int ID, String account_type,String employee_email) throws SmileException {
		// TODO Auto-generated method stub
		return appaccdao.denyAccount(ID, account_type,employee_email);
	}

	@Override
	public List<Account> searchAccount(String account_type) throws SmileException {
		// TODO Auto-generated method stub
		return appaccdao.searchAccount(account_type);
	}
}
