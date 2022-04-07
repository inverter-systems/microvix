package com.topinternacional.linx.model.nl.view.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.topinternacional.linx.model.nl.view.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}