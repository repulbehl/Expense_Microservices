package com.tools.expenseManager.controler;

import com.tools.expenseManager.dao.ExpenseMetadataInput;
import com.tools.expenseManager.dao.ExpenseMetadataOutput;
import com.tools.expenseManager.dao.FetchExpense;
import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.exceptions.ObjectNotFoundException;
import com.tools.expenseManager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("expense_manager")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;


    @PostMapping(value = "/add_expense")
    public ResponseEntity<ExpenseMetadataOutput> addNewExpenseDetails(@RequestBody  ExpenseMetadataInput expenseMetadataInput) throws NullFieldException {
        ExpenseMetadataOutput expenseMetadataOutput = expenseService.addExpense(expenseMetadataInput);
        return  new ResponseEntity<>(expenseMetadataOutput, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove_Expense")
    public ResponseEntity<ExpenseMetadataOutput>  removeExpense(@RequestBody FetchExpense fetchExpense) throws ObjectNotFoundException, NullFieldException {
        ExpenseMetadataOutput expenseMetadataOutput = expenseService.deleteExpense(fetchExpense);
        return  new ResponseEntity<>(expenseMetadataOutput, HttpStatus.OK);
    }

    @GetMapping(value = "/fetch_Expenses")
    public ResponseEntity<ExpenseMetadataOutput> fetchExpenseDetails(@RequestBody FetchExpense fetchExpense) throws ObjectNotFoundException, NullFieldException {
        ExpenseMetadataOutput expenseMetadataOutput = expenseService.getExpenseDetails(fetchExpense);
        return  new ResponseEntity<>(expenseMetadataOutput, HttpStatus.OK);
    }
}
