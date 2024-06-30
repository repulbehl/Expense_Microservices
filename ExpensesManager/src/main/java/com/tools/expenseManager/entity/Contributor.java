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
    @Column(name = "EXPENSE_CREATOR")
    @NonNull
    private boolean expenseCreator;  // it is the value for person who created the expense
    @Column(name = "EXPENSE_CONTRIBUTOR")
    @NonNull
    private boolean expenseContributor; // check the person contributes in the expense
    @Column(name = "CONTRIBUTOR_SHARE_AMOUNT")
    private float contributorShareAmount; // Amount contributed in the expense
    @Column(name = "SPLIT_AMOUNT_PORTION")
    private float splitAmountPortion; // Will fill by the logic of code
    @Column(name ="ContributorStatus")
    private String contributorStatus;  // Will have the value that it is receiver or payer of expense
    @ManyToOne(targetEntity = Expense.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "expense")
    private  Expense expense;
}

