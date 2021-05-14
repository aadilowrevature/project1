package com.smile.bank.functions.dao.impl;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.ApproveAccountDAO;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.Account;
import com.smile.bank.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApproveAccountDAOImpl implements ApproveAccountDAO {
    SmileLog smile = new SmileLog();

    @Override
    public int approveAccount(int ID, String account_type,String employee_email, Employee employee) throws SmileException {
        int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;

            if (account_type.equals("checking")) {
                qry = "update bank_schema.checking set account_status = ? where acc_num =?";
            }
            if (account_type.equals("savings")) {
                qry = "update bank_schema.savings set account_status = ? where acc_num =?";
            }

            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setString(1, "Approved");
            preparedStatement.setInt(2, ID);

            c = preparedStatement.executeUpdate();
            //transaction
            String qry2 = "select * from bank_schema.employees where email=?";

            PreparedStatement p2 = null;
            p2 = connection.prepareStatement(qry2);

            p2.setString(1, employee_email);
            ResultSet rs = p2.executeQuery();
            if (rs.next()) {
                employee.setEmployee_id(rs.getInt("employee_id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
            }

            String qry3 = "insert into bank_schema.worklogs (email,name,employee_id,acc_num,account_type,status)" +
                    "values(?,?,?,?,?,?)";
            PreparedStatement p3 = null;
            p3 = connection.prepareStatement(qry3);
            p3.setString(1,employee.getEmail());
            p3.setString(2,employee.getName());
            p3.setInt(3,employee.getEmployee_id());
            p3.setInt(4,ID);
            p3.setString(5,account_type);
            p3.setString(6,"Approved");
            p3.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return c;
    }

    @Override
    public int denyAccount(int ID, String account_type,String employee_email) throws SmileException {
        int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;
            if (account_type.equals("checking")) {
                qry = "delete from bank_schema.checking where acc_num =?";
            }
            if (account_type.equals("savings")) {
                qry = "delete from bank_schema.savings where acc_num =?";
            }

            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setInt(1, ID);

            c = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return c;
    }

    @Override
    public List<Account> searchAccount(String account_type) throws SmileException {
        int acc_num = 0;
        int customer_id = 0;
        double balance = 0;


        List<Account> accountList = new ArrayList<>();

        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;
            if (account_type.equals("checking")) {
                qry = "select acc_num,balance,customer_id from bank_schema.checking where account_status =?";
            }
            if (account_type.equals("savings")) {
                qry = "select acc_num,balance,customer_id  from bank_schema.savings where account_status =?";
            }
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setString(1, "Pending");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Account account= new Account();
                acc_num = rs.getInt("acc_num");
                balance = rs.getDouble("balance");
                customer_id = rs.getInt("customer_id");

                account.setCustomer_id(customer_id);
                account.setBalance(balance);
                account.setAcc_num(acc_num);

                accountList.add(account);
            }
            if (accountList.size() == 0) {
                throw new SmileException("No one is Pending. Good Job!");
            }
        }catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }


        return accountList;
    }
}
