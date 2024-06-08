package com.tools.expenseManager.service;

import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.model.ExpenseMetadataInput;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {

    Expense addExpense(ExpenseMetadataInput expenseMetadataInput);
    Expense updateExpenseDetails();
    Expense deleteExpense();
    Expense getExpenseDetails();

}
