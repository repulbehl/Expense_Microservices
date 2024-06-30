package com.tools.expenseManager.service;

import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.dao.FetchExpense;
import com.tools.expenseManager.dao.ErrorReporter;
import com.tools.expenseManager.dao.ExpenseMetadataInput;
import com.tools.expenseManager.dao.ExpenseMetadataOutput;
import com.tools.expenseManager.exceptions.ObjectNotFoundException;
import com.tools.expenseManager.repository.ExpenseRepository;
import com.tools.expenseManager.utility.ExpenseUtility;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    ExpenseUtility expenseUtility;
    @Autowired
    AmountSplitterService amountSplitterService;

    @Override
    public ExpenseMetadataOutput addExpense(ExpenseMetadataInput expenseMetadataInput) throws NullFieldException {
        Expense expense = expenseUtility.expenseInputConverter(expenseMetadataInput);
        expense = amountSplitterService.splitAmount(expense);
        expenseRepository.save(expense);
        return expenseUtility.expenseOutputConverter(expense);
    }

    @Override
    public ExpenseMetadataOutput updateExpenseDetails(Expense expense) throws ObjectNotFoundException {
        Expense expenseFetch = getExpenseById(expense.getExpenseId());
        expenseFetch.setExpenseName(expense.getExpenseName());
        expenseFetch.setDescription(expense.getDescription());
        expenseFetch.setAmount(expense.getAmount());
        expenseFetch.setExpenseSplitCheck(expense.isExpenseSplitCheck());
        expenseFetch.setPaymentMode(expense.getPaymentMode());
        expenseFetch.setExpensePaymentStatus(expense.getExpensePaymentStatus());
        expenseFetch.setContributor(expense.getContributor());
        Expense expenseUpdated = expenseRepository.save(expense);
        return expenseUtility.expenseOutputConverter(expenseUpdated);
    }

    private Expense getExpenseById(int id) throws ObjectNotFoundException {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()){
            return expense.get();
        }
        throw  new ObjectNotFoundException(new ErrorReporter<>(id,null));
    }
    @Override
    public ExpenseMetadataOutput deleteExpense(FetchExpense fetchExpense) throws ObjectNotFoundException, NullFieldException {
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
    public ExpenseMetadataOutput getExpenseDetails(FetchExpense fetchExpense) throws NullFieldException, ObjectNotFoundException {
        Expense expense = getExpenseById(fetchExpense.getId());
        if (expense.getExpenseName().equalsIgnoreCase(fetchExpense.getExpenseName())) {
            return expenseUtility.expenseOutputConverter(expense);
        }
        ErrorReporter<Integer,Expense> errorReporter = new ErrorReporter<Integer,Expense>(fetchExpense.getId(),expense);
        throw new NullFieldException(errorReporter);
    }
}
