package com.tools.expenseManager.dao;

import com.tools.expenseManager.entity.Contributor;
import lombok.Data;

import java.util.List;

@Data
public class ExpenseMetadataInput {
    private  String expenseName;
    private  String description;
    private  int amount;
    private  boolean expenseSplitCheck; //if its true then count the number of contributors and divide the splitting amount as per they paid and update the amount in contributor splitAmountPortion field
    private String paymentMode; // Check the final payment made by UPI,CASH,MANDATE
    private String expensePaymentStatus; // True if payment for expense is completed or False if not completed
    private List<Contributor> contributor;

}
