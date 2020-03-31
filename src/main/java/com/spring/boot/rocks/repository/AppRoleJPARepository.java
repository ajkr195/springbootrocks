package com.spring.boot.rocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.model.AppRole;

@Repository
public interface AppRoleJPARepository extends JpaRepository<AppRole, Long> {

	AppRole findById(Integer id);

	AppRole findByname(String rolename);
}
