package com.consultmidia.testeconsultmidia;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CalcularDistancia {
    public static void main(String[] args) {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDVfMJAY4hRe3q1rWCjj5NdAw9gVPvhjtI")
                .build();

        double precoPorKm = 0.0;
        double fatorMultiplicacao = 1.0;

        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Digite o endereço de origem");
            String endereco1 = sc.nextLine();

            System.out.println("Digite o endereço de destino");
            String endereco2 = sc.nextLine();

                    DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context)
                    .origins(endereco1)
                    .destinations(endereco2);


            DistanceMatrix matrix = request.await();

            if (matrix.rows.length > 0 && matrix.rows[0].elements.length > 0) {
                DistanceMatrixElement element = matrix.rows[0].elements[0];
                Distance distance = element.distance;

                System.out.println("A distância entre " + endereco1 + " e " + endereco2 + " é de " + distance.inMeters/1000 + " Km.");

            } else {
                System.out.println("Não foi possível calcular a distância.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
