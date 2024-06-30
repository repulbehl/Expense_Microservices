package com.tools.expenseManager.service;

import com.tools.expenseManager.dao.ExpenseMetadataOutput;
import com.tools.expenseManager.entity.Contributor;
import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.utility.ContributorUtility;
import com.tools.expenseManager.utility.ExpenseUtility;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AmountSplitterServiceImplementation implements  AmountSplitterService{
    @Autowired
    ContributorUtility contributorUtility;

    @Autowired
    ExpenseUtility expenseUtility;

    @Override
    public Expense  splitAmount(Expense expense) {
        float totalAmount = expense.getAmount();
        if (expense.isExpenseSplitCheck()){
            List<Contributor> contributorList = expense.getContributor();
            float amountPerContributor =  totalAmount / contributorList.size();
            for(Contributor contributor : contributorList){
                float splitAmount = amountPerContributor - contributor.getContributorShareAmount();
                if(splitAmount < contributor.getContributorShareAmount()){
                    contributor.setContributorStatus("receive");
                    contributor.setSplitAmountPortion( Math.abs(splitAmount));
                }else{
                    contributor.setContributorStatus("pay");
                    contributor.setSplitAmountPortion(splitAmount);
                }
            }
        }
        return expense;
    }


    public static void main(String[] args) {
        Expense expense = new Expense("Fridge" ,"Rent on summer ", 10000 ,true );
        List<Contributor> contributorList = new ArrayList<>();
        Contributor  contributor1 = new Contributor("Rishav" ,"AC78485189517895",true,true);
        contributor1.setContributorShareAmount(8500);
        Contributor  contributor2 = new Contributor("Repul" ,"AC78485189517895",true,true);
        contributor2.setContributorShareAmount(1000);
        Contributor  contributor3 = new Contributor("Heena" ,"AC78485189517895",true,true);
        contributor3.setContributorShareAmount(500);
        contributorList.add(contributor1);
        contributorList.add(contributor2);
        contributorList.add(contributor3);
        expense.setContributor(contributorList);
        AmountSplitterServiceImplementation amountSplitterServiceImplementation = new AmountSplitterServiceImplementation();
        Expense expense1= amountSplitterServiceImplementation.splitAmount(expense);
        for (Contributor contributor : expense1.getContributor()){
            System.out.println(contributor.getContributorName()+"  :  "+contributor.getContributorStatus() + "   :  "+contributor.getSplitAmountPortion());
        }

    }


}
