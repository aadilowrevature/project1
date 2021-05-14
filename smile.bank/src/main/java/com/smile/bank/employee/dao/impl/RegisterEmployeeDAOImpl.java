package com.smile.bank.employee.dao.impl;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.employee.dao.RegisterEmployeeDAO;
import com.smile.bank.exception.SmileException;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.Employee;
import com.smile.bank.model.Employee_Creds;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterEmployeeDAOImpl implements RegisterEmployeeDAO {
    int ID = 0;
    SmileLog smile= new SmileLog();

    @Override
    public int createEmployee(Employee employee) throws SmileException{
        int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
            String sql = "insert into bank_schema.employees(name,email) values (?,?)";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql, preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            c = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if(rs.next())
            {
            ID = rs.getInt(2);

            }

            employee.setEmployee_id(ID);

        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            throw new SmileException("FUBAR");

        }

        return c;
    }

    @Override
    public int createEmployeeCreds(Employee_Creds employeecreds) throws
            SmileException{
        int c = 0;

        PreparedStatement preparedStatement = null;

        try (Connection connection = PostgresConnection.getConnection()) {
            String sql = "insert into bank_schema.employee_creds(email,password, employee_id) values (?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeecreds.getEmail());
            preparedStatement.setString(2, employeecreds.getPassword());
            preparedStatement.setInt(3, employeecreds.getEmployee_id());
            c = preparedStatement.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            throw new SmileException("User Already Exists with Email: " + employeecreds.getEmail());
        }

        return c;

    }

    @Override
    public int purge(int purgeme,int id) throws SmileException {
        int c = 0;
        if (purgeme == 1) {
            try (Connection connection = PostgresConnection.getConnection()) {
                PreparedStatement purge = null;
                purge = connection.prepareStatement("delete from bank_schema.employees where employee_id=?");
                purge.setInt(1, id);
                c = purge.executeUpdate();

            } catch (ClassNotFoundException | SQLException e) {

                smile.message("catch 22 for employee_id: " +ID);
            }
            return c;
        } else {
            return c;
        }
    }
}
