package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/*
    В виртуальном банке "ConcurrentBank" решено внедрить многопоточность для обработки операций по счетам клиентов.
    Система должна поддерживать возможность одновременного пополнения (deposit), снятия (withdraw),
    а также переводов (transfer) между счетами.
 */
public class ConcurrentBankExample {
    private static final Logger logger = LoggerFactory.getLogger(ConcurrentBankExample.class);

    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        BankAccount account1 = bank.createAccount(new BigDecimal(1000));
        BankAccount account2 = bank.createAccount(new BigDecimal(500));

        logger.info("Total balance: {}", bank.getTotalBalance());

        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, new BigDecimal(200)));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, new BigDecimal(100)));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("Total balance: {}", bank.getTotalBalance());
    }
}
