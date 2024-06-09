package com.tools.expenseManager.exceptions;

import com.tools.expenseManager.dao.ErrorReporter;

public class ObjectNotFoundException extends  Exception{
    public ObjectNotFoundException(ErrorReporter<?, ?> errorReporter){
        super(errorReporter.getErrorField().toString() +  " is not available in database " );
    }
}
