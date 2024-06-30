package com.tools.expenseManager.utility;

import com.tools.expenseManager.dao.ContributorMetadataOutput;
import com.tools.expenseManager.entity.Contributor;
import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.dao.ErrorReporter;
import com.tools.expenseManager.dao.ExpenseMetadataInput;
import com.tools.expenseManager.dao.ExpenseMetadataOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseUtility {

    @Autowired
    ContributorUtility contributorUtility;
    private Integer nullValueCheck = null;
    public Expense expenseInputConverter(ExpenseMetadataInput expenseMetadataInput) throws NullFieldException {
        if(expenseMetadataInput.getExpenseName().isEmpty()){
            ErrorReporter<String, ExpenseMetadataInput> expenseReporter = new ErrorReporter<String,ExpenseMetadataInput>(expenseMetadataInput.getExpenseName(),expenseMetadataInput);
            throw new NullFieldException(expenseReporter);
        }
        else if(expenseMetadataInput.getDescription().isEmpty()){
            ErrorReporter<String, ExpenseMetadataInput> expenseReporter = new ErrorReporter<String,ExpenseMetadataInput>(expenseMetadataInput.getDescription(),expenseMetadataInput);
            throw new NullFieldException(expenseReporter);
        }
        else if (expenseMetadataInput.getAmount() == nullValueCheck ){
            ErrorReporter<Float, ExpenseMetadataInput> expenseReporter = new ErrorReporter<Float,ExpenseMetadataInput>(expenseMetadataInput.getAmount(),expenseMetadataInput);
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
        List<ContributorMetadataOutput> contributorMetadataOutputList = contributorUtility.listOfContributorMetadataOutputMapper(expense.getContributor());
        expenseMetadataOutput.setContributor(contributorMetadataOutputList);
        return  expenseMetadataOutput;
    }

    public Expense expenseMetadataOutputToExpenseConverter(ExpenseMetadataOutput expenseMetadataOutput, List<ContributorMetadataOutput> updatedContributorList) throws NullFieldException {
        if(expenseMetadataOutput.getExpenseName().isEmpty()){
            ErrorReporter<String, ExpenseMetadataOutput> expenseReporter = new ErrorReporter<String,ExpenseMetadataOutput>(expenseMetadataOutput.getExpenseName(),expenseMetadataOutput);
            throw new NullFieldException(expenseReporter);
        }
        else if(expenseMetadataOutput.getDescription().isEmpty()){
            ErrorReporter<String, ExpenseMetadataOutput> expenseReporter = new ErrorReporter<String,ExpenseMetadataOutput>(expenseMetadataOutput.getDescription(),expenseMetadataOutput);
            throw new NullFieldException(expenseReporter);
        }
        else if (expenseMetadataOutput.getAmount() == nullValueCheck ){
            ErrorReporter<Float, ExpenseMetadataOutput> expenseReporter = new ErrorReporter<Float,ExpenseMetadataOutput>(expenseMetadataOutput.getAmount(),expenseMetadataOutput);
            throw new NullFieldException(expenseReporter);
        }
        Expense expense = new Expense(expenseMetadataOutput.getExpenseName(), expenseMetadataOutput.getDescription(), expenseMetadataOutput.getAmount(),expenseMetadataOutput.isExpenseSplitCheck());
        expense.setPaymentMode(expenseMetadataOutput.getPaymentMode());
        expense.setExpensePaymentStatus(expenseMetadataOutput.getExpensePaymentStatus());
        for (ContributorMetadataOutput contributor : updatedContributorList){
            expenseMetadataOutput.getContributor().add(contributor);
        }
        List<Contributor> contributorList = contributorUtility.listOfContributorMapper(expenseMetadataOutput.getContributor());
        expense.setContributor(contributorList);
        return expense;
    }
}
