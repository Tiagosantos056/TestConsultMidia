package com.consultmidia.testeconsultmidia.services;


import com.consultmidia.testeconsultmidia.CalcularDistancia;
import com.consultmidia.testeconsultmidia.enums.CarTypes;
import com.consultmidia.testeconsultmidia.models.City;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService {

    @Value("${google.maps.apiKey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();


    private final GeoApiContext context;

    public GoogleMapsService(@Value("${google.maps.apikey}") String apiKey) {
         context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDVfMJAY4hRe3q1rWCjj5NdAw9gVPvhjtI")
                .build();
    }

    public double calculateDistance(String origin, String destination) throws Exception {
        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context);
        DistanceMatrix result = request.origins(origin)
                .destinations(destination)
                .mode(TravelMode.DRIVING)
                .language("pt-BR")
                .await();

        DistanceMatrix matrix = request.await();
        if (result.rows.length > 0 && result.rows[0].elements.length > 0) {
            DistanceMatrixElement element = matrix.rows[0].elements[0];
            Distance distance = element.distance;

            System.out.println("A distância entre " + origin + " e " + destination + " é de " + (distance.inMeters)/1000 + " Km.");
        } else {
            throw new Exception("Não foi possível calcular a distância entre as cidades");
        }
        return result.rows[0].elements[0].distance.inMeters / 1000.0;
    }

}
