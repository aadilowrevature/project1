package com.smile.bank.functions.service;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.Account;
import com.smile.bank.model.ApproveAccount;

import java.util.List;

public interface ApproveAccountService {

	public int approveAccount(int ID, String account_type) throws SmileException;

	public int denyAccount(int ID, String account_type) throws SmileException;

	public List<Account> searchAccount(String account_type) throws SmileException;

}
