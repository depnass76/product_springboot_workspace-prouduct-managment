package com.redasp.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.redasp.pma.entities.UserAccount;

public interface IUserAccountRepository extends PagingAndSortingRepository<UserAccount,Long>  {

}
