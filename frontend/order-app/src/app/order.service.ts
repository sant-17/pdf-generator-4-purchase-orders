import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Product {
  nombre: string;
  precioUnitario: number;
  cantidad: number;
}

interface Order {
  cedula: string;
  telefono: string;
  productos: Product[];
}

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:8080/generate-pdf';

  constructor(private http: HttpClient) {}

  generatePdf(order: Order): Observable<Blob> {
    return this.http.post(this.apiUrl, order, { responseType: 'blob' });
  }
}