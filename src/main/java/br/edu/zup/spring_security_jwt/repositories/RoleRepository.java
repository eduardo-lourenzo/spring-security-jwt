package br.edu.zup.spring_security_jwt.repositories;

import br.edu.zup.spring_security_jwt.models.RoleEntity;
import br.edu.zup.spring_security_jwt.models.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByType(RoleEnum type);
}
