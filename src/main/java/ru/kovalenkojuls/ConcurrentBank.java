package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentBank.class);
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankAccount createAccount(BigDecimal amount) {
        int accountId = accounts.size();
        BankAccount bankAccount = new BankAccount(amount, accountId);
        accounts.add(bankAccount);

        logger.info("Created account with ID {} and initial balance {}", accountId, amount);

        return bankAccount;
    }

    public void transfer(BankAccount account1, BankAccount account2, BigDecimal amount) {
        BankAccount firstLock;
        BankAccount secondLock;

        if (account1.getAccountId() < account2.getAccountId()) {
            firstLock = account1;
            secondLock = account2;
        } else {
            firstLock = account2;
            secondLock = account1;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (amount.compareTo(account1.getBalance()) <= 0) {
                    logger.info("Try to transfer {} from account with ID = {} to account with ID = {}:",
                            amount, account1.getAccountId(), account2.getAccountId());

                    account1.withdraw(amount);
                    account2.deposit(amount);
                } else {
                    logger.warn("Insufficient funds in account {}", account1.getAccountId());
                }
            }
        }
    }

    public synchronized BigDecimal getTotalBalance() {
        BigDecimal totalBalance = new BigDecimal(0);
        for (BankAccount account: accounts) {
            totalBalance = totalBalance.add(account.getBalance());
        }
        return totalBalance;
    }
}
