package com.stargazerproject.transaction;

public interface Transaction extends TransactionAssemble, 
                                     TransactionResult, 
                                     TransactionExecute, 
                                     Entity<String>{
}
