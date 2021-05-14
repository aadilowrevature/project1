package com.smile.bank.model;

public class Worklogs {
    private String email;
    private String name;
            private int employee_id;
    private int acc_num;
    private String account_type;
    private String status;

    public Worklogs() {
    }

    public Worklogs(String email, String name, int employee_id, int acc_num, String account_type, String status) {
        this.email = email;
        this.name = name;
        this.employee_id = employee_id;
        this.acc_num = acc_num;
        this.account_type = account_type;
        this.status = status;
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

    public int getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(int acc_num) {
        this.acc_num = acc_num;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Worklogs{");
        sb.append("email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", employee_id=").append(employee_id);
        sb.append(", acc_num=").append(acc_num);
        sb.append(", account_type='").append(account_type).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
