package com.dm.dscatalog.resources;

import com.dm.dscatalog.dto.CategoryDTO;
import com.dm.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @return ResponseEntity<List<CategoryDTO>>
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll()
    {
        List<CategoryDTO> categories = service.findAll();

        return ResponseEntity.ok().body( categories );
    }

    /**
     * findById
     *
     * @return ResponseEntity<CategoryDTO>
     */
    @GetMapping( value =  "/{id}" )
    public ResponseEntity<CategoryDTO> findById( @PathVariable Long id )
    {
        CategoryDTO dto = service.findById( id );

        return ResponseEntity.ok().body( dto );
    }
}
