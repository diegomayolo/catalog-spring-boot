package com.dm.dscatalog.resources;

import com.dm.dscatalog.dto.ProductDTO;
import com.dm.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 *
 * @author dm
 */
@RestController
@RequestMapping( value =  "/products" )
public class ProductResource
{
    @Autowired
    private ProductService service;

    /**
     * findAll
     *
     * @return ResponseEntity<Page<ProductDTO>>
     */
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll( Pageable pageable )
    {
        Page<ProductDTO> products = service.findAllPaged( pageable );

        return ResponseEntity.ok().body( products );
    }

    /**
     * findById
     *
     * @return ResponseEntity<ProductDTO>
     */
    @GetMapping( value =  "/{id}" )
    public ResponseEntity<ProductDTO> findById( @PathVariable Long id )
    {
        ProductDTO dto = service.findById( id );

        return ResponseEntity.ok().body( dto );
    }

    /**
     * insert
     *
     * @param dto ProductDTO
     * @return ResponseEntity<ProductDTO>
     */
    @PostMapping
    public ResponseEntity<ProductDTO> insert( @Valid @RequestBody ProductDTO dto )
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
     * @param dto ProductDTO
     * @return ResponseEntity<ProductDTO>
     */
    @PutMapping( value =  "/{id}" )
    public ResponseEntity<ProductDTO> update( @Valid @PathVariable Long id, @RequestBody ProductDTO dto )
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
