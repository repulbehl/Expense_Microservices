package com.tools.expenseManager.service;

import com.tools.expenseManager.entity.Expense;
import org.springframework.stereotype.Service;

@Service
public interface AmountSplitterService {
    public Expense splitAmount(Expense expense);


}
