package com.avinash.kumar.module11.services;

import com.avinash.kumar.module11.entities.Employee;
import com.avinash.kumar.module11.entities.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
