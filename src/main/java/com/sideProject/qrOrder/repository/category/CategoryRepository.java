package com.sideProject.qrOrder.repository.category;

import com.sideProject.qrOrder.entity.Account;
import com.sideProject.qrOrder.entity.Category;
import com.sideProject.qrOrder.entity.Closing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryCustomRepository {
    Optional<Category> findByCaName(String caName);
    Optional<Category> findTopByOrderByCaSeqDesc();
}
