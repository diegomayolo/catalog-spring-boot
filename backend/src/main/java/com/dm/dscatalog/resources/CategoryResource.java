package com.dm.dscatalog.resources;

import com.dm.dscatalog.dto.CategoryDTO;
import com.dm.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
     * @return ResponseEntity<Page<CategoryDTO>>
     */
    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll( @RequestParam( value = "page",         defaultValue = "0"    ) Integer page,
                                                      @RequestParam( value = "linesPerPage", defaultValue = "12"   ) Integer linesPerPage,
                                                      @RequestParam( value = "direction",    defaultValue = "ASC"  ) String direction,
                                                      @RequestParam( value = "orderBy",      defaultValue = "name" ) String orderBy )
    {
        PageRequest pageRequest = PageRequest.of( page, linesPerPage, Sort.Direction.valueOf( direction ), orderBy );

        Page<CategoryDTO> categories = service.findAllPaged( pageRequest );

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

    /**
     * update
     *
     * @param id Long
     * @param dto CategoryDTO
     * @return ResponseEntity<CategoryDTO>
     */
    @PutMapping( value =  "/{id}" )
    public ResponseEntity<CategoryDTO> update( @PathVariable Long id, @RequestBody CategoryDTO dto )
    {
        dto = service.update(id, dto );

        return ResponseEntity.ok().body(dto);
    }

    /**
     * delete
     *
     * @param id Long
     * @return ResponseEntity<Void>
     */
    @DeleteMapping( value = "/{id}" )
    public ResponseEntity<Void> delete( @PathVariable Long id )
    {
        service.delete(id);

        return ResponseEntity.status( HttpStatus.OK ).build();
    }
}
