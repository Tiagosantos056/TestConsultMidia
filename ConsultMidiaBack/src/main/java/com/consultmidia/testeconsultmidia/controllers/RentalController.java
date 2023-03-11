package com.consultmidia.testeconsultmidia.controllers;

import com.consultmidia.testeconsultmidia.models.Rental;
import com.consultmidia.testeconsultmidia.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RentalController {
    @Autowired
    RentalService rentalService;

    @PostMapping("/rental")
    public ResponseEntity<Rental> calculateRentalPrice(@RequestBody Rental rental) throws Exception {
        Rental calculatedRental = rentalService.calculateRentalPrice(rental);
        return ResponseEntity.ok(calculatedRental);
    }
}
