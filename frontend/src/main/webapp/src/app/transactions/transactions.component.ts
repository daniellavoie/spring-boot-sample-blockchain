import { Component, ViewChild, ElementRef } from '@angular/core';

import { TransactionService } from './transaction.service';

@Component({
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent {
  @ViewChild("gasPriceChart") chartTarget: ElementRef;

  private transactionPage: any

  constructor(private transactionService: TransactionService) { }

  public refreshTransactions() {
    this.transactionService.findAll()

      .subscribe(transactionPage => this.transactionPage = transactionPage);
  }

  ngAfterViewInit() {
    this.refreshTransactions();
  }
}
