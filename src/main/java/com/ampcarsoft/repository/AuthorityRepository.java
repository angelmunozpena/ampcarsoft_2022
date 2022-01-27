package com.ampcarsoft.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ampcarsoft.entity.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long>  {}