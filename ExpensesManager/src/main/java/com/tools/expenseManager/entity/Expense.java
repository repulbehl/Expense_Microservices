package com.tools.expenseManager.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

@Data
@Entity
@Table(name = "EXPENSES")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int expenseId;
    @Column(name = "EXPENSE_NAME")
    @NonNull
    private  String expenseName; // Name of the Expense
    @Column(name = "DESCRIPTION")
    @NonNull
    private  String description; // Expense Description
    @Column(name = "AMOUNT")
    @NonNull
    private float amount; // Expense Total Amount
    @Column(name = "EXPENSE_SPLIT_CHECK")
    @NonNull
    private  boolean expenseSplitCheck; //if its true then count the number of contributors and divide the splitting amount as per they paid and update the amount in contributor splitAmountPortion field
    @Column(name = "PAYMENT_MODE")
    private String paymentMode; // Check the final payment made by UPI,CASH,MANDATE
    @Column(name = "EXPENSE_PAYMENT_STATUS")
    private String expensePaymentStatus; // True if payment for expense is completed or False if not completed
    @OneToMany(targetEntity = Contributor.class ,mappedBy = "expense",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contributor> contributor;

}
