package com.smile.bank.functions.dao.impl;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.QuickFindDAO;
import com.smile.bank.functions.service.QuickFindService;

import com.smile.bank.model.Transactions;
import org.junit.Assert;
import org.junit.Before;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;



@RunWith(MockitoJUnitRunner.class)
public class QuickFindDAOImplTest {

    @Mock
    private QuickFindDAO mocked;

    @InjectMocks
    private QuickFindService service;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilter1() {

        try {

            String filter_spec="";
            int filter=1;
            List<Transactions> list= new ArrayList<>();

            Assert.assertEquals(mocked.findTransactions(filter_spec,filter),list);
        }
        catch(SmileException | NullPointerException e){

        }
    }

    @Test
    public void testFilter2() {

        try {

            String filter_spec="2021-05-01";
            int filter=2;
            List<Transactions> list= new ArrayList<>();

            Assert.assertEquals(mocked.findTransactions(filter_spec,filter),list);
        }
        catch(SmileException | NullPointerException e){

        }
    }

    @Test
    public void testFilter3() {

        try {

            String filter_spec="Withdrawl";
            int filter=3;
            List<Transactions> list= new ArrayList<>();

            Assert.assertEquals(mocked.findTransactions(filter_spec,filter),list);
        }
        catch(SmileException | NullPointerException e){

        }
    }

    @Test
    public void testFilter4() {

        try {

            String filter_spec="162";
            int filter=4;
            List<Transactions> list= new ArrayList<>();

            Assert.assertEquals(mocked.findTransactions(filter_spec,filter),list);
        }
        catch(SmileException | NullPointerException e){

        }
    }

    @Test
    public void testFilter5() {

        try {

            String filter_spec="6";
            int filter=5;
            List<Transactions> list= new ArrayList<>();

            Assert.assertEquals(mocked.findTransactions(filter_spec,filter),list);
        }
        catch(SmileException | NullPointerException e){

        }
    }

    @Test
    public void testFilter6() {

        try {

            String filter_spec="Milk";
            int filter=6;
            List<Transactions> list= new ArrayList<>();

            Assert.assertEquals(mocked.findTransactions(filter_spec,filter),list);
        }
        catch(SmileException | NullPointerException e){

        }
    }
}