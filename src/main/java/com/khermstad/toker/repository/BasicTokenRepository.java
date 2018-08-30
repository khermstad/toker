package com.khermstad.toker.repository;

import com.khermstad.toker.entity.BasicToken;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BasicTokenRepository extends JpaRepository<BasicToken, Long> {

    BasicToken findByApplicationId(String applicationId);
    BasicToken findByApplicationIdAndValue(String applicationId, String value);

}
