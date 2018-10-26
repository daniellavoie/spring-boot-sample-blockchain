import { Component, ViewChild, ElementRef } from '@angular/core';
import { GasPriceService } from './gas-price-service/gas-price.service';

import { chart } from 'highcharts';
import * as Highcharts from 'highcharts';

import { tap, mapTo, mergeAll, switchMap } from 'rxjs/operators';
import { from } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @ViewChild("gasPriceChart") chartTarget: ElementRef;

  chart: Highcharts.ChartObject;

  constructor(private gasPriceService: GasPriceService) { }

  public refreshGasPrice() {
    const source = this.gasPriceService.getGasPrices();

    source.pipe(
      switchMap(gasPrices => from(gasPrices.content)),
      mapTo(gasPrice => [gasPrice.timestamp, gasPrice.price]),
      mergeAll(),
      tap<any[]>(data => this.updateGasPriceChart(data)))

      .subscribe();
  }

  updateGasPriceChart(data: any[]) {
    const options: Highcharts.Options = {
      xAxis: {
        type: 'datetime'
      },
      yAxis: {
        title: {
          text: 'WEI'
        }
      },
      legend: {
        enabled: false
      },

      series: [{
        type: 'area',
        name: 'Gas price',
        data: data
      }]
    };

    this.chart = chart(this.chartTarget.nativeElement, options);
  }

  ngAfterViewInit() {
    this.refreshGasPrice();
  }
}
