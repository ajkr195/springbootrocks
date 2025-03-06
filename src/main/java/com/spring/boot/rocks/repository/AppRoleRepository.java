package com.spring.boot.rocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.model.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByName(String name);
}
