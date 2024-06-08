package com.tools.expenseManager.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.NonNull;

@Data
@Entity
@Table(name = "CONTRIBUTORS")
public class Contributor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contributorId;

    @JoinColumn(name = "CONTRIBUTOR_NAME")
    @NonNull
    private String contributorName;  // Name of the Contributor
    @Column(name = "ACCOUNT_NUMBER")
    @NonNull
    private String accountNumber;  // Account number of the Contributor
    @Column(name = "EXPENSE_PAYER")
    @NonNull
    private boolean expenseCreator;  // it is the value for person who created the expense
    @Column(name = "EXPENSE_CONTRIBUTOR")
    @NonNull
    private boolean expenseContributor; // check the person contributes in the expense
    @Column(name = "CONTRIBUTOR_TYPE")
    private int contributorShareExpense; // Amount contributed in the expense
    @Column(name = "SPLIT_AMOUNT_PORTION")
    private int splitAmountPortion;
    @ManyToOne(targetEntity = Expense.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "expense")
    private  Expense expense;
}

