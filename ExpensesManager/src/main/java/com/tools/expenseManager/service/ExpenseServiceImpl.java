package com.tools.expenseManager.service;

import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.model.FetchExpense;
import com.tools.expenseManager.model.ErrorReporter;
import com.tools.expenseManager.model.ExpenseMetadataInput;
import com.tools.expenseManager.model.ExpenseMetadataOutput;
import com.tools.expenseManager.repository.ExpenseRepository;
import com.tools.expenseManager.utility.ExpenseUtility;
import org.springframework.beans.factory.annotation.Autowired;

public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    ExpenseUtility expenseUtility;
    @Override
    public ExpenseMetadataOutput addExpense(ExpenseMetadataInput expenseMetadataInput) {
        Expense expense = expenseUtility.expenseInputConverter(expenseMetadataInput);
        expenseRepository.save(expense);
        ExpenseMetadataOutput expenseMetadataOutput = expenseUtility.expenseOutputConverter(expense);
        return expenseMetadataOutput;
    }

    @Override
    public ExpenseMetadataOutput updateExpenseDetails() {
        return null;
    }

    private Expense getExpenseById(int id){
        Expense expense = expenseRepository.getReferenceById(id);
        return expense;
    }
    @Override
    public ExpenseMetadataOutput deleteExpense(FetchExpense fetchExpense) {
        Expense expense = getExpenseById(fetchExpense.getId());
        if (expense.getExpenseName().equalsIgnoreCase(fetchExpense.getExpenseName())){
            expenseRepository.delete(expense);
            ExpenseMetadataOutput expenseMetadataOutput = expenseUtility.expenseOutputConverter(expense);
            return expenseMetadataOutput;

        }
        ErrorReporter<Integer,Expense> errorReporter = new ErrorReporter<Integer,Expense>(fetchExpense.getId(),expense);
        throw new NullFieldException(errorReporter);
    }

    @Override
    public ExpenseMetadataOutput getExpenseDetails(FetchExpense fetchExpense) {
        Expense expense = getExpenseById(fetchExpense.getId());
        if (expense.getExpenseName().equalsIgnoreCase(fetchExpense.getExpenseName())) {
            ExpenseMetadataOutput expenseMetadataOutput = expenseUtility.expenseOutputConverter(expense);
            return expenseMetadataOutput;
        }
        ErrorReporter<Integer,Expense> errorReporter = new ErrorReporter<Integer,Expense>(fetchExpense.getId(),expense);
        throw new NullFieldException(errorReporter);
    }
}
