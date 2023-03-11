package com.consultmidia.testeconsultmidia.services;

import com.consultmidia.testeconsultmidia.enums.CarTypes;
import com.consultmidia.testeconsultmidia.models.Car;
import com.consultmidia.testeconsultmidia.models.City;
import com.consultmidia.testeconsultmidia.models.Rental;
import com.consultmidia.testeconsultmidia.repositories.CarRepository;
import com.consultmidia.testeconsultmidia.repositories.CityRepository;
import com.consultmidia.testeconsultmidia.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentalRepository rentalRepository;

    private GoogleMapsService googleMapsService;
    public Rental calculateRentalPrice(Rental rental) throws Exception {
        City originCity = cityRepository.findByName(rental.getOriginCity().getName());
        City destinationCity = cityRepository.findByName(rental.getDestinationCity().getName());
        Car carType = carRepository.findByName(rental.getCarType().getName());

        double distance = googleMapsService.calculateDistance(originCity.getName(), destinationCity.getName());
        rental.setDistance(distance);
        double price = distance * carType.getPricePerKm();
        rental.setPrice(price);
        rental.setOriginCity(originCity);
        rental.setDestinationCity(destinationCity);
        rental.setCarType(carType);

        rentalRepository.save(rental);

        return rental;
    }
}
