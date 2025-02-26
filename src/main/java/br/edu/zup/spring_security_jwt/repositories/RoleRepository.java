package br.edu.zup.spring_security_jwt.repositories;

import br.edu.zup.spring_security_jwt.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
