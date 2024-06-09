package com.tools.expenseManager.dao;

import lombok.Data;

@Data
public class ContributorMetadataOutput {

    private int contributorId;
    private String contributorName;  // Name of the Contributor
    private String accountNumber;  // Account number of the Contributor
    private boolean expenseCreator;  // it is the value for person who created the expense
    private boolean expenseContributor; // check the person contributes in the expense
    private int contributorShareExpense;
    private int splitAmountPortion;
    private ExpenseMetadataOutput expense;
}
