package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
}
