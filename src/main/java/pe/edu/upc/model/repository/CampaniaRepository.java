package pe.edu.upc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.entity.Campania;


@Repository
public interface CampaniaRepository extends JpaRepository<Campania,Integer>{

}
