package com.tools.expenseManager.utility;

import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.model.ErrorReporter;
import com.tools.expenseManager.model.ExpenseMetadataInput;
import com.tools.expenseManager.model.ExpenseMetadataOutput;
import org.springframework.stereotype.Component;

@Component
public class ExpenseUtility {
    private Integer nullValueCheck = null;
    public Expense expenseInputConverter(ExpenseMetadataInput expenseMetadataInput){
        if(expenseMetadataInput.getExpenseName().isEmpty()){
            ErrorReporter<String, ExpenseMetadataInput> expenseReporter = new ErrorReporter<String,ExpenseMetadataInput>(expenseMetadataInput.getExpenseName(),expenseMetadataInput);
            throw new NullFieldException(expenseReporter);
        }
        else if(expenseMetadataInput.getDescription().isEmpty()){
            ErrorReporter<String, ExpenseMetadataInput> expenseReporter = new ErrorReporter<String,ExpenseMetadataInput>(expenseMetadataInput.getDescription(),expenseMetadataInput);
            throw new NullFieldException(expenseReporter);
        }
        else if (expenseMetadataInput.getAmount() == nullValueCheck ){
            ErrorReporter<Integer, ExpenseMetadataInput> expenseReporter = new ErrorReporter<Integer,ExpenseMetadataInput>(expenseMetadataInput.getAmount(),expenseMetadataInput);
            throw new NullFieldException(expenseReporter);
        }

        Expense expense = new Expense(expenseMetadataInput.getExpenseName(),expenseMetadataInput.getDescription(),expenseMetadataInput.getAmount(),expenseMetadataInput.isExpenseSplitCheck());
        expense.setPaymentMode(expenseMetadataInput.getPaymentMode());
        expense.setExpensePaymentStatus(expenseMetadataInput.getExpensePaymentStatus());
        expense.setContributor(expenseMetadataInput.getContributor());
        return  expense;
    }

    public ExpenseMetadataOutput expenseOutputConverter(Expense expense){
        ExpenseMetadataOutput expenseMetadataOutput = new ExpenseMetadataOutput();
        expenseMetadataOutput.setId(expense.getExpenseId());
        expenseMetadataOutput.setExpenseName(expense.getExpenseName());
        expenseMetadataOutput.setDescription(expense.getDescription());
        expenseMetadataOutput.setAmount(expense.getAmount());
        expenseMetadataOutput.setExpenseSplitCheck(expense.isExpenseSplitCheck());
        expenseMetadataOutput.setPaymentMode(expense.getPaymentMode());
        expenseMetadataOutput.setExpensePaymentStatus(expense.getExpensePaymentStatus());
        expenseMetadataOutput.setContributor(expense.getContributor());
        return  expenseMetadataOutput;
    }
}
