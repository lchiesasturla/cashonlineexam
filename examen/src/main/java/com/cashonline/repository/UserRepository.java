package com.cashonline.repository;

import com.cashonline.models.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long>{
    
}
