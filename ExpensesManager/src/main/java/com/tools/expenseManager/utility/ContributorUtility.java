package com.tools.expenseManager.utility;

import com.tools.expenseManager.dao.ContributorMetadataInput;
import com.tools.expenseManager.dao.ContributorMetadataOutput;
import com.tools.expenseManager.dao.ErrorReporter;
import com.tools.expenseManager.dao.ExpenseMetadataOutput;
import com.tools.expenseManager.entity.Contributor;
import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.exceptions.NullFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContributorUtility {

    @Autowired
    ExpenseUtility expenseUtility;
    public Expense addContributorMapper(ContributorMetadataInput contributorMetadataInput, ExpenseMetadataOutput expenseMetadataOutput) throws NullFieldException {
        if(contributorMetadataInput.getContributorName().isEmpty()){
            throw  new NullFieldException(new ErrorReporter<String,ContributorMetadataInput>(contributorMetadataInput.getContributorName(),contributorMetadataInput));
        } else if (contributorMetadataInput.getAccountNumber().isEmpty()) {
            throw  new NullFieldException(new ErrorReporter<String,ContributorMetadataInput>(contributorMetadataInput.getAccountNumber(),contributorMetadataInput));
        }
        ContributorMetadataOutput contributor = new ContributorMetadataOutput(contributorMetadataInput.getContributorName(), contributorMetadataInput.getAccountNumber(), contributorMetadataInput.isExpenseCreator(), contributorMetadataInput.isExpenseContributor());
        contributor.setContributorShareExpense(contributorMetadataInput.getContributorShareExpense());
        List<ContributorMetadataOutput> contributorList = new ArrayList<>();
        contributorList.add(contributor);
        return expenseUtility.expenseMetadataOutputToExpenseConverter(expenseMetadataOutput,contributorList);
    }

    public Expense updateContributorMapper(ExpenseMetadataOutput expenseMetadataOutput) throws NullFieldException {
        Expense expense = new Expense(expenseMetadataOutput.getExpenseName(),expenseMetadataOutput.getDescription(),expenseMetadataOutput.getAmount(),expenseMetadataOutput.isExpenseSplitCheck());
        expense.setPaymentMode(expenseMetadataOutput.getPaymentMode());
        expense.setExpensePaymentStatus(expense.getExpensePaymentStatus());
        return expense;
    }

    public  List<ContributorMetadataOutput> listOfContributorMetadataOutputMapper (List<Contributor> contributorList){
        List<ContributorMetadataOutput> contributorMetadataOutputList = new ArrayList<>();
        for(Contributor contributor : contributorList){
           ContributorMetadataOutput contributorMetadataOutput = contributorMetadataOutputMapper(contributor);
           contributorMetadataOutputList.add(contributorMetadataOutput);
        }
        return  contributorMetadataOutputList;
    }

    public ContributorMetadataOutput contributorMetadataOutputMapper(Contributor contributor){
        ContributorMetadataOutput contributorMetadataOutput = new ContributorMetadataOutput();
        contributorMetadataOutput.setContributorId(contributor.getContributorId());
        contributorMetadataOutput.setContributorName(contributor.getContributorName());
        contributorMetadataOutput.setAccountNumber(contributor.getAccountNumber());
        contributorMetadataOutput.setExpenseCreator(contributor.isExpenseCreator());
        contributorMetadataOutput.setExpenseContributor(contributor.isExpenseContributor());
        contributorMetadataOutput.setContributorShareExpense(contributor.getContributorShareAmount());
        contributorMetadataOutput.setSplitAmountPortion(contributor.getSplitAmountPortion());
        contributorMetadataOutput.setExpense( expenseUtility.expenseOutputConverter(contributor.getExpense()));
        return  contributorMetadataOutput;
    }

    public List<Contributor> listOfContributorMapper(List<ContributorMetadataOutput> contributorMetadataOutputList){
        List<Contributor> contributorList = new ArrayList<>();
        for(ContributorMetadataOutput contributorMetadataOutput : contributorMetadataOutputList){
            Contributor contributor = contributorMapper(contributorMetadataOutput);
            contributorList.add(contributor);
        }
        return contributorList;
    }

    public Contributor contributorMapper(ContributorMetadataOutput contributorMetadataOutput){
        Contributor contributor = new Contributor(contributorMetadataOutput.getContributorName(),contributorMetadataOutput.getAccountNumber(),contributorMetadataOutput.isExpenseCreator(),contributorMetadataOutput.isExpenseContributor());
        contributor.setContributorShareAmount(contributorMetadataOutput.getContributorShareExpense());
        return contributor;
    }


}
