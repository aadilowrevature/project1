package com.smile.bank.model;

public class Employee {

    private String email;
    private String name;
    private int employee_id;

    public Employee() {
    }

    public Employee(String email, String name, int employee_id) {
        this.email = email;
        this.name = name;
        this.employee_id = employee_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", employee_id=").append(employee_id);
        sb.append('}');
        return sb.toString();
    }
}
