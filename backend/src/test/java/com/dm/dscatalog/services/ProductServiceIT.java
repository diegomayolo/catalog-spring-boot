package com.dm.dscatalog.services;

import com.dm.dscatalog.dto.ProductDTO;
import com.dm.dscatalog.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Product service it.
 */
@SpringBootTest
@Transactional
public class ProductServiceIT {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalProducts;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }

    /**
     * Delete should delete resource when id exists.
     */
    @Test
    public void deleteShouldDeleteResourceWhenIdExists() {

        service.delete(existingId);

        Assertions.assertEquals(countTotalProducts - 1, repository.count());
    }

    /**
     * Find all paged should return page when page 0 size 10.
     */
    @Test
    public void findAllPagedShouldReturnPageWhenPage0Size10() {

        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<ProductDTO> result = service.findAllPaged(pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(0, result.getNumber());
        Assertions.assertEquals(10, result.getSize());
        Assertions.assertEquals(countTotalProducts, result.getTotalElements());
    }

    /**
     * Find all paged should return empty page when page does not exist.
     */
    @Test
    public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {

        PageRequest pageRequest = PageRequest.of(50, 10);

        Page<ProductDTO> result = service.findAllPaged(pageRequest);

        Assertions.assertTrue(result.isEmpty());
    }

    /**
     * Find all paged should return sorted page when sort by name.
     */
    @Test
    public void findAllPagedShouldReturnSortedPageWhenSortByName() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));

        Page<ProductDTO> result = service.findAllPaged(pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("Macbook Pro", result.getContent().get(0).getName());
        Assertions.assertEquals("PC Gamer", result.getContent().get(1).getName());
        Assertions.assertEquals("PC Gamer Alfa", result.getContent().get(2).getName());
    }
}
