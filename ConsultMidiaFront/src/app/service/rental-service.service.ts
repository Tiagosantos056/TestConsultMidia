import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RentalService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  calcularAluguel(origem: string, destino: string, tipoCarro: string): Observable<any> {
    const url = `${this.baseUrl}/calcular-aluguel?origem=${origem}&destino=${destino}&tipoCarro=${tipoCarro}`;
    return this.http.get<any>(url);
  }

}
