package com.tools.expenseManager.dao;

import lombok.Data;

@Data
public class FetchContributor {
    private  int expenseId;
    private String expenseName;
    private  int contributorId;
    private String contributorName;
}
