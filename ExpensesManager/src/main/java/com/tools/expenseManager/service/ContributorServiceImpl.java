package com.tools.expenseManager.service;

import com.tools.expenseManager.dao.*;
import com.tools.expenseManager.entity.Contributor;
import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.exceptions.ObjectNotFoundException;
import com.tools.expenseManager.repository.ContributorRepository;
import com.tools.expenseManager.repository.ExpenseRepository;
import com.tools.expenseManager.utility.ContributorUtility;
import com.tools.expenseManager.utility.ExpenseUtility;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ContributorServiceImpl implements ContributorService {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ContributorUtility contributorUtility;


    @Autowired
    ExpenseUtility expenseUtility;

    @Autowired
    ContributorRepository contributorRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    private ExpenseMetadataOutput fetchExpenseDetails (FetchExpense fetchExpense) throws Exception {
        ExpenseMetadataOutput expense = expenseService.getExpenseDetails(fetchExpense);
        if (expense == null ){
            ErrorReporter <Integer, String> errorReporter = new ErrorReporter<>(fetchExpense.getId(), fetchExpense.getExpenseName());
            throw  new ObjectNotFoundException(errorReporter);
        }
        return expense;
    }
    @Override
    public ExpenseMetadataOutput addContributor(ContributorMetadataInput contributorMetadataInput) throws Exception {
        ExpenseMetadataOutput expenseMetadataOutput = fetchExpenseDetails(contributorMetadataInput.getExpense());
        Expense expense = contributorUtility.addContributorMapper(contributorMetadataInput,expenseMetadataOutput);
        return expenseService.updateExpenseDetails(expense);
    }

    @Override
    public ExpenseMetadataOutput updateContributorDetails(FetchContributor fetchContributor , ContributorMetadataInput contributorMetadataInput) throws Exception {
        ExpenseMetadataOutput expenseMetadataOutput = fetchExpenseDetails(new FetchExpense(fetchContributor.getExpenseId(),fetchContributor.getExpenseName()));
        Expense expense = contributorUtility.updateContributorMapper(expenseMetadataOutput);
        for(Contributor contributor : expense.getContributor()){
            if(fetchContributor.getContributorId() == contributor.getContributorId() && fetchContributor.getContributorName().equalsIgnoreCase(fetchContributor.getContributorName())){
                contributor.setContributorName(contributorMetadataInput.getContributorName());
                contributor.setAccountNumber(contributorMetadataInput.getAccountNumber());
                contributor.setExpenseCreator(contributorMetadataInput.isExpenseCreator());
                contributor.setExpenseContributor(contributorMetadataInput.isExpenseContributor());
                contributor.setContributorShareExpense(contributorMetadataInput.getContributorShareExpense());
            }
        }
        return expenseService.updateExpenseDetails(expense);
    }

    @Override
    public ExpenseMetadataOutput removeContributor(FetchContributor fetchContributor) throws ObjectNotFoundException {
        Optional<Expense> optionalExpense = expenseRepository.findById(fetchContributor.getExpenseId());
        Optional<Contributor> optionalContributor = contributorRepository.findById(fetchContributor.getContributorId());
        if (optionalExpense.isEmpty() || optionalContributor.isEmpty()){
            throw  new ObjectNotFoundException(new ErrorReporter<>(fetchContributor.getContributorId(),null));
        }
        if(optionalContributor.get().getExpense().equals(optionalExpense.get())){
            optionalExpense.get().getContributor().remove(optionalContributor.get());
            Expense expense = expenseRepository.save(optionalExpense.get());
            return expenseUtility.expenseOutputConverter(expense);
        }
        throw  new ObjectNotFoundException(new ErrorReporter<>(fetchContributor.getContributorId(),null));
    }

    @Override
    public ContributorMetadataOutput getContributorDetails(FetchContributor fetchContributor) throws ObjectNotFoundException {
        Optional<Expense> optionalExpense = expenseRepository.findById(fetchContributor.getExpenseId());
        Optional<Contributor> optionalContributor = contributorRepository.findById(fetchContributor.getContributorId());
        if (optionalExpense.isEmpty() || optionalContributor.isEmpty()) {
            throw new ObjectNotFoundException(new ErrorReporter<>(fetchContributor.getContributorId(), null));
        }
        if (optionalContributor.get().getExpense().equals(optionalExpense.get())) {
            return contributorUtility.contributorMetadataOutputMapper(optionalContributor.get());
        }
        throw new ObjectNotFoundException(new ErrorReporter<>(fetchContributor.getContributorId(), null));
    }
}
