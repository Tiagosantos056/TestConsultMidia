package com.consultmidia.testeconsultmidia.repositories;

import com.consultmidia.testeconsultmidia.models.Car;
import com.consultmidia.testeconsultmidia.models.City;
import com.consultmidia.testeconsultmidia.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByOriginCityAndDestinationCityAndCarType(City originCity, City destinationCity, Car carType);
}
