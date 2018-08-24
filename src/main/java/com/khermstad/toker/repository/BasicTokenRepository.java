package com.khermstad.toker.repository;

import com.khermstad.toker.entity.BasicToken;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface BasicTokenRepository extends JpaRepository<BasicToken, Long> {

}
