package org.efrei.demo.repository;

import org.efrei.demo.models.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends JpaRepository<Salle, String> {

}
