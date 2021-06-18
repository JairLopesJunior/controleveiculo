package com.jairlopesjunior.controleveiculo.domain.repositories;

import com.jairlopesjunior.controleveiculo.domain.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
}
