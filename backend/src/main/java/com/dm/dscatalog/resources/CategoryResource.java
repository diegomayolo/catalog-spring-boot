package com.dm.dscatalog.resources;

import com.dm.dscatalog.dto.CategoryDTO;
import com.dm.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    /**
     * insert
     *
     * @param dto CategoryDTO
     * @return ResponseEntity<CategoryDTO>
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> insert( @RequestBody CategoryDTO dto )
    {
        dto = service.insert( dto );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" )
                                                                  .buildAndExpand( dto.getId() )
                                                                  .toUri();

        return ResponseEntity.created( uri ).body( dto );
    }
}
