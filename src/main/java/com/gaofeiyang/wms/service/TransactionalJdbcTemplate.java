package com.gaofeiyang.wms.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TransactionalJdbcTemplate extends JdbcTemplate {
    private PlatformTransactionManager platformTransactionManager;
    private final TransactionDefinition transactionDefinition;
    private TransactionStatus transaction;

    public TransactionalJdbcTemplate(DataSource dataSource, TransactionDefinition transactionDefinition) {
        super(dataSource);
        if (transactionDefinition == null) {
            this.transactionDefinition = new DefaultTransactionDefinition();
        } else {
            this.transactionDefinition = transactionDefinition;
        }
        transaction = null;
    }

    public TransactionalJdbcTemplate(DataSource dataSource) {
        this(dataSource, null);
    }

    public void beginTranstaion() throws SQLException {
        if (transaction != null) {
            throw new SQLException("transaction has already begun");
        } else {
            transaction = platformTransactionManager.getTransaction(transactionDefinition);
        }
    }

    public void commitTransaction() throws SQLException {
        if (transaction == null) {
            throw new SQLException("no transcation");
        }

        try {
            platformTransactionManager.commit(transaction);
        } finally {
            transaction = null;
        }
    }

    public void rollbackTransaction() throws SQLException {
        if (transaction == null) {
            throw new SQLException("no transcation");
        }

        try {
            platformTransactionManager.rollback(transaction);
        } finally {
            transaction = null;
        }
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        platformTransactionManager = new DataSourceTransactionManager(getDataSource());

    }
}
