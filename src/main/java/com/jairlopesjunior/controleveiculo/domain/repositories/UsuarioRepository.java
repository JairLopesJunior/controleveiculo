package com.jairlopesjunior.controleveiculo.domain.repositories;

import com.jairlopesjunior.controleveiculo.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
