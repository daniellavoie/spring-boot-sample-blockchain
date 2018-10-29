import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { GasPriceService } from './statistics/gas-price-service/gas-price.service';
import { HttpClientModule } from '@angular/common/http';
import { StatisticsComponent } from './statistics/statistics.component';
import { AppRoutingModule } from './app-routing.module';
import { TransactionsComponent } from './transactions/transactions.component';
import { FooterComponent } from './footer/footer.component';
import { TransactionService } from './transactions/transaction.service';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavigationComponent,
    StatisticsComponent,
    TransactionsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [GasPriceService, TransactionService],
  bootstrap: [
    AppComponent]
})
export class AppModule { }
