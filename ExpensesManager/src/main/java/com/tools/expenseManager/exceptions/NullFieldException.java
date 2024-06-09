package com.tools.expenseManager.exceptions;

import com.tools.expenseManager.dao.ErrorReporter;

public class NullFieldException extends Exception{

    public NullFieldException(ErrorReporter<?, ?> errorReporter){
        super(errorReporter.getErrorField().toString() +  " is null for input metadata " +errorReporter.getErrorMetadata().toString() );
    }
}
