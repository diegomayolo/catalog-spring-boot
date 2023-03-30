package com.dm.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dm.dscatalog.entities.Category;

/**
 *
 * @author dm
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
