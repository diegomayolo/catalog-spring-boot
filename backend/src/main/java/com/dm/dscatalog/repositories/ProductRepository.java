package com.dm.dscatalog.repositories;

import com.dm.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dm
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
