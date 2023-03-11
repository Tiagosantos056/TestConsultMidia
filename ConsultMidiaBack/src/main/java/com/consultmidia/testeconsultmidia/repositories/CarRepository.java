package com.consultmidia.testeconsultmidia.repositories;

import com.consultmidia.testeconsultmidia.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByName(String name);
}
