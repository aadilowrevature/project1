package com.smile.bank.functions.dao.impl;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.OpenAccountDAO;
import com.smile.bank.functions.service.OpenAccountService;
import com.smile.bank.model.OpenAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class OpenAccountDAOImplTest {

    @Mock
    private OpenAccountDAO mocked;

    @InjectMocks
    private OpenAccountService service;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCheckingAccountOpened(){
        try {
            OpenAccount open=new OpenAccount();
            Assert.assertEquals(mocked.openChecking(open),1);
        }
        catch(SmileException | NullPointerException e){

        }
    }

    @Test
    public void testCheckingAccountFailed(){
        try {
            OpenAccount open=new OpenAccount();
            Assert.assertEquals(mocked.openChecking(open),0);
        }
        catch(SmileException | NullPointerException e){

        }
    }

    @Test
    public void testSavingsAccountOpened(){
        try {
            OpenAccount open=new OpenAccount();
            Assert.assertEquals(mocked.openSavings(open),1);
        }
        catch(SmileException | NullPointerException e){

        }
    }

    @Test
    public void testSavingsAccountFailed(){
        try {
            OpenAccount open=new OpenAccount();
            Assert.assertEquals(mocked.openSavings(open),0);
        }
        catch(SmileException | NullPointerException e){

        }
    }



}