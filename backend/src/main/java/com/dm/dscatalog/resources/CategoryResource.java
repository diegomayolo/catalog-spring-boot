package com.dm.dscatalog.resources;

import com.dm.dscatalog.entities.Category;
import com.dm.dscatalog.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author dm
 */
@RestController
@RequestMapping( value =  "/categories" )
public class CategoryResource
{
    @Autowired
    private CategoryService service;

    /**
     * findAll
     *
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<Category>> findAll()
    {
        List<Category> categories = service.findAll();

        return ResponseEntity.ok().body( categories );
    }
}