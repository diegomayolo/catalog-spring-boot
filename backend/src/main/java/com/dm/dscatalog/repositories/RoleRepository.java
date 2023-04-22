package com.dm.dscatalog.repositories;

import com.dm.dscatalog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dm
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
