package com.tools.expenseManager.exceptions;

import com.tools.expenseManager.model.ErrorReporter;

public class NullFieldException extends RuntimeException{

    public NullFieldException(ErrorReporter<?, ?> errorReporter){
        super(errorReporter.getErrorField().toString() +  " is null for input metadata " +errorReporter.getErrorMetadata().toString() );
    }
}
