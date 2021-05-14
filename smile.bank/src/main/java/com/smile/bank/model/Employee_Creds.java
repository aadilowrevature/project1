package com.smile.bank.model;

public class Employee_Creds {

    private String email;
    private String password;
    private int employee_id;

    public Employee_Creds() {
    }

    public Employee_Creds(String email, String password, int employee_id) {
        this.email = email;
        this.password = password;
        this.employee_id = employee_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee_Creds{");
        sb.append("email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", employee_id=").append(employee_id);
        sb.append('}');
        return sb.toString();
    }
}
