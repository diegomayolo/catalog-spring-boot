package com.dm.dscatalog.resources;

import com.dm.dscatalog.dto.UserDTO;
import com.dm.dscatalog.dto.UserInsertDTO;
import com.dm.dscatalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping( value =  "/users" )
public class UserResource
{
    @Autowired
    private UserService service;

    /**
     * findAll
     *
     * @return ResponseEntity<Page<UserDTO>>
     */
    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll( Pageable pageable )
    {
        Page<UserDTO> users = service.findAllPaged( pageable );

        return ResponseEntity.ok().body( users );
    }

    /**
     * findById
     *
     * @return ResponseEntity<UserDTO>
     */
    @GetMapping( value =  "/{id}" )
    public ResponseEntity<UserDTO> findById( @PathVariable Long id )
    {
        UserDTO dto = service.findById( id );

        return ResponseEntity.ok().body( dto );
    }

    /**
     * insert
     *
     * @param dto UserDTO
     * @return ResponseEntity<UserDTO>
     */
    @PostMapping
    public ResponseEntity<UserDTO> insert( @RequestBody UserInsertDTO dto )
    {
        UserDTO newDTO = service.insert( dto );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" )
                                                                  .buildAndExpand( newDTO.getId() )
                                                                  .toUri();

        return ResponseEntity.created( uri ).body( newDTO );
    }

    /**
     * update
     *
     * @param id Long
     * @param dto UserDTO
     * @return ResponseEntity<UserDTO>
     */
    @PutMapping( value =  "/{id}" )
    public ResponseEntity<UserDTO> update( @PathVariable Long id, @RequestBody UserDTO dto )
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
