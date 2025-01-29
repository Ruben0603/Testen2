package com.testing.piggybank;

import com.testing.piggybank.account.AccountService;
import com.testing.piggybank.helper.CurrencyConverterService;
import com.testing.piggybank.model.Account;
import com.testing.piggybank.model.Transaction;
import com.testing.piggybank.transaction.TransactionRepository;
import com.testing.piggybank.transaction.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionRepository TransactionRepository;

    @Autowired
    private CurrencyConverterService CurrencyConverterService;

    @Autowired
    private AccountService AccountService;


    @Test
    public void test_filter() {
        TransactionService ts = new TransactionService(null, null, null);

        List<Transaction> result = ts.filterAndLimitTransactions(Collections.emptyList(), 1L,  10); {

            Assertions.assertEquals(0 , result.size());
        }
    }

    @Test
    public void test_filterTransaction() {
        TransactionService ts = new TransactionService(null, null, null);
        Transaction transaction = new Transaction();
        Account account = new Account();
        account.setId(1);
        List<Transaction> transactionList = List.of(transaction);
        List<Transaction> result = ts.filterAndLimitTransactions(Collections.emptyList(), 1L,  10); {

            Assertions.assertEquals(0 , result.size());
        }
    }

    @Test
    public void test_filterTransactionNoLimit() {
        TransactionService ts = new TransactionService(null, null, null);
        Transaction transaction = new Transaction();
        Account account = new Account();
        account.setId(1);
        List<Transaction> transactionList = List.of(transaction);
        List<Transaction> result = ts.filterAndLimitTransactions(Collections.emptyList(), 1L,  0); {

            Assertions.assertEquals(0 , result.size());
        }
    }

//    @Test
//    public void test_Transactions() {
//        TransactionService ts = new TransactionService(null, null, null);
//        ts.getTransactions(1,1);
//
//        Transaction transaction = new Transaction();
//        Account account = new Account();
//        account.setId(1);
//        List<Transaction> transactionList = List.of(transaction);
//        List<Transaction> result = ts.filterAndLimitTransactions(Collections.emptyList(), 1L,  0); {
//
//            Assertions.assertEquals(0 , result.size());
//        }
//    }
}
