package com.smile.bank.main;

import com.smile.bank.customer.service.CustomerLoginService;
import com.smile.bank.customer.service.RegisterCustomerService;
import com.smile.bank.customer.service.impl.CustomerLoginServiceImpl;
import com.smile.bank.customer.service.impl.RegisterCustomerServiceImpl;
import com.smile.bank.employee.service.EmployeeLoginService;
import com.smile.bank.employee.service.RegisterEmployeeService;
import com.smile.bank.employee.service.impl.EmployeeLoginServiceImpl;
import com.smile.bank.employee.service.impl.RegisterEmployeeServiceImpl;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.Functions;
import com.smile.bank.functions.service.*;
import com.smile.bank.functions.service.impl.*;
import com.smile.bank.model.*;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class JavalinMain  {
    public static void main(String[] args){



        Javalin app = Javalin.create(config->config.enableCorsForAllOrigins()).
                start(9000);

        app.get("/account/checking/:id", ctx -> {
            QuickFindService functions = new QuickFindServiceImpl();
            List<Account> accountList = functions.findAccounts("checking",Integer.parseInt(ctx.pathParam("id")));
            ctx.json(accountList);
        });

        app.get("/employee/worklogs", ctx -> {
            QuickFindService service = new QuickFindServiceImpl();
            try {
                List<Worklogs> workList = service.printWorklogs();
                ctx.json(workList);
            } catch (SmileException e){
                ctx.status(406);
                ctx.json(e);
            }

        });

        app.get("/register/employee/:name/:email/:password", ctx -> {
            RegisterEmployeeService service= new RegisterEmployeeServiceImpl();
            QuickFindService quick = new QuickFindServiceImpl();

            Employee newEmployee= new Employee();
            Employee_Creds newEmployeeCreds= new Employee_Creds();

            String name= ctx.pathParam("name");
            String email= ctx.pathParam("email");
            String pwd= ctx.pathParam("password");


            newEmployee.setName(name);
            newEmployee.setEmail(email);
            int employee=service.createEmployee(newEmployee);

            int employee_id= newEmployee.getEmployee_id();

            newEmployeeCreds.setEmail(email);
            newEmployeeCreds.setPassword(pwd);
            newEmployeeCreds.setEmployee_id(employee_id);

            int creds=0;
            try {
                creds=service.createEmployeeCreds(newEmployeeCreds);

            }catch(SmileException e){
                ctx.status(406);
                ctx.json(e);
            }
            if(employee==1 && creds==1) {
                ctx.status(201);
                ctx.json(newEmployee);
            }

        });

        app.get("/employee/transactions/:filter/:filter_spec", ctx -> {
            QuickFindService service=new QuickFindServiceImpl();
            int filter= Integer.parseInt(ctx.pathParam(":filter"));
            String filter_spec=ctx.pathParam(":filter_spec");
            List<Transactions> transactionsList=new ArrayList<>();



            try {
                transactionsList=service.findTransactions(filter_spec,filter);
                ctx.json(transactionsList);
                ctx.status(201);

            }catch(SmileException e){
                ctx.status(406);
                ctx.json(e);
            }


        });

        app.get("/employee/search/:id/:acctype", ctx -> {
            ViewAccountService service=new ViewAccountServiceImpl();
            int id= Integer.parseInt(ctx.pathParam(":id"));
            String acc=ctx.pathParam(":acctype");
            List<ViewAccount> view= new ArrayList<>();



            try {
                view=service.viewAccount(id,acc);
                ctx.json(view);
                ctx.status(201);

            }catch(SmileException e){
                ctx.status(406);
                ctx.json(e);
            }

        });

        app.get("/register/:name/:ssn/:email/:password", ctx -> {
            RegisterCustomerService service= new RegisterCustomerServiceImpl();
            QuickFindService quick = new QuickFindServiceImpl();

            Customer newCustomer= new Customer();
            Customer_Creds newCustomerCreds= new Customer_Creds();

            String name= ctx.pathParam("name");
            String ssn=ctx.pathParam("ssn");
            String email= ctx.pathParam("email");
            String pwd= ctx.pathParam("password");


            newCustomer.setName(name);
            newCustomer.setSsn(ssn);
            int customer=service.createCustomer(newCustomer);

            int customer_id= newCustomer.getCustomerid();

            newCustomerCreds.setEmail(email);
            newCustomerCreds.setPassword(pwd);
            newCustomerCreds.setCustomerid(customer_id);

            int creds=0;
            try {
                creds=service.createCustomerCreds(newCustomerCreds);

            }catch(SmileException e){
                ctx.status(406);
                ctx.json(e);
            }
            if(customer==1 && creds==1) {
                ctx.status(201);
                ctx.json(newCustomer);
            }

        });
        app.get("/customer/login/:email/:pwd", ctx -> {
            CustomerLoginService loginService=new CustomerLoginServiceImpl();
            UserLogin loginHTML = new UserLogin();
            loginHTML.setEmail(ctx.pathParam("email"));
            loginHTML.setPassword(ctx.pathParam("pwd"));

            int login = loginService.customerLogin(loginHTML);
            if(login==0){
                //login fail
                ctx.status(401);
                ctx.json("");
            }
            if(login==1){
                //login pass
                ctx.status(201);
                ctx.json(loginHTML);

            }

        });

        app.get("/employee/login/:email/:pwd", ctx -> {
            EmployeeLoginService loginService= new EmployeeLoginServiceImpl();
            UserLogin loginHTML = new UserLogin();
            loginHTML.setEmail(ctx.pathParam("email"));
            loginHTML.setPassword(ctx.pathParam("pwd"));

            int login = loginService.employeeLogin(loginHTML);
            if(login==0){
                //login fail
                ctx.status(401);
                ctx.json("");
            }
            if(login==1){
                //login pass
                ctx.status(201);
                ctx.json(loginHTML);
            }

        });

        app.get("/employee/work/:acc_type", ctx -> {
            ApproveAccountService work= new ApproveAccountServiceImpl();
            String acc_type = ctx.pathParam(":acc_type");
            List<Account> accountList=new ArrayList<>();

            try {
                accountList= work.searchAccount(acc_type);
            }
            catch(SmileException e){
                ctx.json(e);
                ctx.status(201);
            }

            ctx.json(accountList);
            ctx.status(201);
        });

        app.get("/employee/approve/:email/:acc_type/:id", ctx -> {
            ApproveAccountService work= new ApproveAccountServiceImpl();
            String acc_type = ctx.pathParam(":acc_type");
            int id= Integer.parseInt(ctx.pathParam(":id"));
            String email=ctx.pathParam(":email");
            Employee employee= new Employee();

            int job=0;
            try {
                job=work.approveAccount(id, acc_type,email,employee);
            }
            catch(SmileException e){
                ctx.json(e);
                ctx.status(201);
            }

            if(job==1) {
                ctx.json(employee);
            }
            else
            {
                ctx.json("");
            }
        });

        app.get("/employee/deny/:email/:acc_type/:id", ctx -> {
            ApproveAccountService work= new ApproveAccountServiceImpl();
            String acc_type = ctx.pathParam(":acc_type");
            int id= Integer.parseInt(ctx.pathParam(":id"));
            String email=ctx.pathParam(":email");

        int job=0;
            try {
                job=work.denyAccount(id, acc_type, email);
            }
            catch(SmileException e){
                ctx.json(e);
                ctx.status(201);
            }

            if(job==1) {
                ctx.json(job);
            }
            else
            {
                ctx.json("");
            }
        });

        app.get("/customer/new-account/:email/:acc-type/:balance", ctx -> {
            OpenAccountService open=new OpenAccountServiceImpl();
            OpenAccount newAccount= new OpenAccount();
            QuickFindService quick=new QuickFindServiceImpl();

            String email=ctx.pathParam("email");
            String acc_type= ctx.pathParam("acc-type");
            Double balance=Double.parseDouble(ctx.pathParam("balance"));

            newAccount.setCustomer_id(quick.findID(email));
            newAccount.setBalance(balance);


            if(acc_type.equals("checking")) {

                open.openChecking(newAccount);
                ctx.status(201);
                ctx.json(newAccount);
            }
            else if(acc_type.equals("savings")) {

                open.openSavings(newAccount);
                ctx.status(201);
                ctx.json(newAccount);
            }


        });

        app.get("/:transaction_type/:account_type/:acc_num/:balance/:amount", ctx -> {
            NewTransactionService service =  new  NewTransactionServiceImpl();
            QuickFindService quick=new QuickFindServiceImpl();

            int transaction=0;
            String account_type=ctx.pathParam(":account_type");
            int acc_num=Integer.parseInt(ctx.pathParam(":acc_num"));
            double balance= Double.parseDouble(ctx.pathParam(":balance"));
            double amount = Double.parseDouble(ctx.pathParam(":amount"));
            String transaction_type=ctx.pathParam(":transaction_type");
            int id= quick.findID(acc_num,account_type);
            if(transaction_type.equals("withdraw")) {
                transaction= service.withdrawAcc(id, acc_num, account_type, balance, amount);
            }
            if(transaction_type.equals("deposit")){
                transaction=service.depositAcc(id,acc_num,account_type,amount);
            }

                ctx.status(201);
                ctx.json(transaction);

        });

        app.get("/send-money/:account_type/:acc_num/:balance/:amount/:target_acc_type" +
                "/:target_acc_num", ctx -> {
            NewTransactionService service =  new  NewTransactionServiceImpl();
            QuickFindService quick=new QuickFindServiceImpl();

            int transaction=0;
            String account_type=ctx.pathParam(":account_type");
            int acc_num=Integer.parseInt(ctx.pathParam(":acc_num"));
            double balance= Double.parseDouble(ctx.pathParam(":balance"));
            double amount = Double.parseDouble(ctx.pathParam(":amount"));


            String target_acc_type=ctx.pathParam(":target_acc_type");
            int target_acc_num=Integer.parseInt(ctx.pathParam(":target_acc_num"));


            int id=0;
            int target_id =0;

            try {
            target_id = quick.findID(target_acc_num, target_acc_type);
                id=quick.findID(acc_num,account_type);
                transaction=service.sendMoney(target_id,target_acc_num,target_acc_type,id,acc_num,account_type,balance,amount);
        }catch(SmileException e){
                ctx.status(406);
                ctx.json(e);
        }

           if(transaction==1) {
               ctx.status(201);
               ctx.json(transaction);
           }

        });

        app.get("/transactions/:email", ctx -> {
            QuickFindService quick=new QuickFindServiceImpl();
            String email= ctx.pathParam("email");

            int customer_id= quick.findID(email);

            List<Transactions> transactionsList=quick.findTransactions(customer_id+"",4);
            ctx.status(201);
            ctx.json(transactionsList);
        });

        app.get("/accounts/:acc-type/:email", ctx -> {
        QuickFindService quick=new QuickFindServiceImpl();
        String account_type= ctx.pathParam("acc-type");
        String email= ctx.pathParam("email");

        int customer_id= quick.findID(email);

        List<Account> accountList=quick.findAccounts(account_type,customer_id);
            ctx.status(201);
            ctx.json(accountList);
        });
    }
}
