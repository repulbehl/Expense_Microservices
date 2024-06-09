package com.tools.expenseManager.service;

import com.tools.expenseManager.dao.FetchExpense;
import com.tools.expenseManager.dao.ExpenseMetadataInput;
import com.tools.expenseManager.dao.ExpenseMetadataOutput;
import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {

    ExpenseMetadataOutput addExpense(ExpenseMetadataInput expenseMetadataInput) throws NullFieldException;
    ExpenseMetadataOutput updateExpenseDetails(Expense expense) throws ObjectNotFoundException;
    ExpenseMetadataOutput deleteExpense(FetchExpense fetchExpense) throws NullFieldException, ObjectNotFoundException;
    ExpenseMetadataOutput getExpenseDetails(FetchExpense fetchExpense) throws NullFieldException, ObjectNotFoundException;

}
