import { Component, ViewChild, ElementRef } from '@angular/core';
import { GasPriceService } from './gas-price-service/gas-price.service';

import { chart } from 'highcharts';
import * as Highcharts from 'highcharts';

import { tap, map, mergeAll, switchMap, toArray } from 'rxjs/operators';
import { from } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent {
  @ViewChild("gasPriceChart") chartTarget: ElementRef;

  chart: Highcharts.ChartObject;

  constructor(private gasPriceService: GasPriceService) { }

  public refreshGasPrice() {
    this.gasPriceService.getGasPrices()
      .pipe(
        switchMap(gasPrices => from(gasPrices.content)),
        map<any, any>(gasPrice => [gasPrice.timestamp, gasPrice.price]),
        toArray(),
        tap(data => this.updateGasPriceChart(data)))
        
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
