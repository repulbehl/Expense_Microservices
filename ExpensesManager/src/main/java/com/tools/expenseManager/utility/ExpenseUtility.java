package com.tools.expenseManager.utility;

import com.tools.expenseManager.entity.Expense;
import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.model.ErrorReporter;
import com.tools.expenseManager.model.ExpenseMetadataInput;

public class ExpenseUtility {

    public Expense expenseInputConverter(ExpenseMetadataInput expenseMetadataInput){
        if(expenseMetadataInput.getExpenseName().isEmpty()){
            ErrorReporter<String, ExpenseMetadataInput> expenseReporter = new ErrorReporter<String,ExpenseMetadataInput>(expenseMetadataInput.getExpenseName(),expenseMetadataInput);
            throw new NullFieldException(expenseReporter);
        }
    }

}
