package com.tools.expenseManager.repository;

import com.tools.expenseManager.entity.Contributor;
import com.tools.expenseManager.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContributorRepository extends JpaRepository< Contributor, Integer> {

}
