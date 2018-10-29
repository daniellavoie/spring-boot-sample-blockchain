import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class TransactionService {
  private static TRANSACTION_URL = "/api/transaction";

  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<any> {
    return this.httpClient.get<any>(TransactionService.TRANSACTION_URL);
  }
}