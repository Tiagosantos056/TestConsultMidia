import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RentalService } from '../../service/rental-service.service';

@Component(
  {
  selector: 'app-rental-form',
  templateUrl: './rental-form.component.html',
  styleUrls: ['./rental-form.component.css']
})
export class RentalFormComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    // const url = 'http://localhost:8080/api';
    //
    // this.http.get(url).subscribe((data: any) => {
    //   console.log(data);
    // });
  }


}

export class AluguelCarroComponent {
  origem: string;
  destino: string;
  tipoCarro: string;
  distancia: number;
  valorAluguel: number;

  constructor(private rentalService: RentalService) { }

  calcularAluguel() {
    this.rentalService.calcularAluguel(this.origem, this.destino, this.tipoCarro)
      .subscribe((data: any) => {
        this.distancia = data.distancia;
        this.valorAluguel = data.valorAluguel;
      });
  }
}
