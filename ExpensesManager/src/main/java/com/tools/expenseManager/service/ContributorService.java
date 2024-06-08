package com.tools.expenseManager.service;

import com.tools.expenseManager.entity.Contributor;
import org.springframework.stereotype.Service;

@Service
public interface ContributorService {

    Contributor addContributor();
    Contributor updateContributorDetails();
    Contributor removeContributor();
    Contributor getContributorDetails();

}
