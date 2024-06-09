package com.tools.expenseManager.service;

import com.tools.expenseManager.dao.*;
import com.tools.expenseManager.entity.Contributor;
import com.tools.expenseManager.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ContributorService {

    ExpenseMetadataOutput addContributor(ContributorMetadataInput contributorMetadataInput) throws Exception;
    ExpenseMetadataOutput updateContributorDetails(FetchContributor fetchContributor , ContributorMetadataInput contributorMetadataInput) throws Exception;
    ExpenseMetadataOutput removeContributor(FetchContributor fetchContributor) throws ObjectNotFoundException;
    ContributorMetadataOutput getContributorDetails(FetchContributor fetchContributor) throws ObjectNotFoundException;

}
