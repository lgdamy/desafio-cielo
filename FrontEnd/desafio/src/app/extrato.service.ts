import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


const httpOptions = {
headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'TamanhoPagina': '10',
    'indice' : '1'
  })
};
@Injectable({
  providedIn: 'root'
})

export class ExtratoService {
  private baseUrl:string="http://localhost:8080/api/extrato";
  constructor(private _http:HttpClient) { }

  consultaExtrato(ini:Date,fim:Date,tamanho:number,pagina:number) {
    const iniStr = ("0" + ini.getDate()).slice(-2) + ("0" + (ini.getMonth() + 1)).slice(-2) + ini.getFullYear();
    const fimStr = ("0" + fim.getDate()).slice(-2) + ("0" + (fim.getMonth() + 1)).slice(-2) + fim.getFullYear();
    httpOptions.headers = httpOptions.headers.set('TamanhoPagina',tamanho!=undefined?tamanho+'':'10');
    httpOptions.headers = httpOptions.headers.set('indice',pagina!=undefined?pagina+'':'0');
    return this._http.get(this.baseUrl + '/consulta/' + iniStr + '/' + fimStr,httpOptions);
  }

  geraRegistros(quantidade:number) {
    return this._http.post(this.baseUrl + '/carga/' + quantidade,null);
  }
}
