package com.tools.expenseManager.dao;

import lombok.Data;

@Data
public class FetchExpense {
    private int id;
    private String expenseName;

    public FetchExpense(int expenseId, String expenseName) {
        this.id = expenseId;
        this.expenseName =expenseName;
    }
}
