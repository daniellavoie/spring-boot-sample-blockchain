import { Component } from '@angular/core';
import { GasPriceService } from './gas-price-service/gas-price.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  gasPrice: number = 0;

  constructor(private gasPriceService: GasPriceService) {
    this.refreshGasPrice();
  }

  public refreshGasPrice() {
    this.gasPriceService.getGasPrice()

      .subscribe(gasPrice => this.gasPrice = gasPrice);
  }
}
