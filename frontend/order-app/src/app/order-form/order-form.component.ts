import { Component } from '@angular/core';
import { OrderService } from '../order.service';
import { saveAs } from 'file-saver';
import { NgForm } from '@angular/forms';

interface Product {
  nombre: string;
  precioUnitario: number;
}

interface OrderProduct extends Product {
  cantidad: number;
}

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent {
  cedula: string = '';
  telefono: string = '';
  productos: Product[] = [
    { nombre: 'Papas fritas', precioUnitario: 1.0 },
    { nombre: 'Galletas', precioUnitario: 0.5 },
    { nombre: 'Refresco', precioUnitario: 1.2 },
    { nombre: 'Chocolatina', precioUnitario: 0.8 },
    { nombre: 'Chicles', precioUnitario: 0.3 }
  ];
  selectedProducts: OrderProduct[] = [];

  constructor(private orderService: OrderService) {}

  addProduct(product: Product) {
    const existingProduct = this.selectedProducts.find(p => p.nombre === product.nombre);
    if (existingProduct) {
      existingProduct.cantidad += 1;
    } else {
      this.selectedProducts.push({ ...product, cantidad: 1 });
    }
  }

  removeProduct(index: number) {
    this.selectedProducts.splice(index, 1);
  }

  validateNumber(event: Event) {
    const input = event.target as HTMLInputElement;
    input.value = input.value.replace(/[^0-9]/g, '');
  }

  validateQuantity(product: OrderProduct) {
    if (product.cantidad < 1) {
      product.cantidad = 1;
    } else if (product.cantidad > 10000) {
      product.cantidad = 10000;
    } else {
      product.cantidad = Math.floor(product.cantidad);
    }
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      const order = {
        cedula: this.cedula,
        telefono: this.telefono,
        productos: this.selectedProducts
      };
      this.orderService.generatePdf(order).subscribe(response => {
        const blob = new Blob([response], { type: 'application/pdf' });
        saveAs(blob, 'orden_compra.pdf');
      });
    } else {
      alert('Por favor, corrija los errores antes de generar el PDF.');
    }
  }
}