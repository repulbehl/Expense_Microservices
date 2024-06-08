package com.tools.expenseManager.service;

import com.tools.expenseManager.model.FetchExpense;
import com.tools.expenseManager.model.ExpenseMetadataInput;
import com.tools.expenseManager.model.ExpenseMetadataOutput;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {

    ExpenseMetadataOutput addExpense(ExpenseMetadataInput expenseMetadataInput);
    ExpenseMetadataOutput updateExpenseDetails();
    ExpenseMetadataOutput deleteExpense(FetchExpense fetchExpense);
    ExpenseMetadataOutput getExpenseDetails(FetchExpense fetchExpense);

}
