package com.dm.dscatalog.repositories;

import com.dm.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dm
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail( String email );
}
