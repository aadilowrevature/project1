package com.smile.bank.functions.service;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.Account;
import com.smile.bank.model.ApproveAccount;
import com.smile.bank.model.Employee;

import java.util.List;

public interface ApproveAccountService {

	public int approveAccount(int ID, String account_type,String employee_email, Employee employee) throws SmileException;

	public int denyAccount(int ID, String account_type,String employee_email) throws SmileException;

	public List<Account> searchAccount(String account_type) throws SmileException;

}
