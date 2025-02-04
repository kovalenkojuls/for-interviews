package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class BankAccount {

    private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);
    private final int accountId;
    private BigDecimal balance;

    public BankAccount(BigDecimal balance, int accountId) {
        this.balance = balance;
        this.accountId = accountId;
    }

    public synchronized void deposit(BigDecimal amount) {
        if (isValidAmount(amount)) {
            balance = balance.add(amount);
            logger.info("Deposited {} into account with ID = {}. New balance: {}", amount, accountId, balance);
        }
    }

    public synchronized void withdraw(BigDecimal amount) {
        if (isValidAmount(amount)) {
            if (amount.compareTo(balance) <= 0) {
                balance = balance.subtract(amount);
                logger.info("Withdrew {} from account with ID = {}. New balance: {}", amount, accountId, balance);
            } else {
                logger.info("Insufficient funds in account with ID = {}. Current balance: {}", accountId, balance);
            }
        }
    }

    private boolean isValidAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Invalid amount: {}", amount);
            return false;
        }
        return true;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public int getAccountId() {
        return accountId;
    }
}
