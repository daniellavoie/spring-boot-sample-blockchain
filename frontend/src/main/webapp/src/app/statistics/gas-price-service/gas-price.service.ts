import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

@Injectable()
export class GasPriceService {
  private GAS_PRICE_URL = "/api/gas-price";

  constructor(private httpClient: HttpClient) {

  }

  getGasPrices(): Observable<any> {
    return this.httpClient.get<number>(this.GAS_PRICE_URL);
  }
}