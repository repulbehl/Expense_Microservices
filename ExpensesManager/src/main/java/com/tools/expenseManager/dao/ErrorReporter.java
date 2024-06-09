package com.tools.expenseManager.dao;

import lombok.Data;

@Data
public class ErrorReporter <F,M>{
    private F errorField;
    private M errorMetadata;

    public ErrorReporter(F field, M errorMetadata){
        this.errorField = field;
        this.errorMetadata = errorMetadata;
    }
}
