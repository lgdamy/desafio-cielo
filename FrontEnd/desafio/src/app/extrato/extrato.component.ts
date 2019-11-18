import { Component, Inject, ViewChild } from '@angular/core';
import {PageEvent, MatPaginator} from '@angular/material/paginator';
import { ExtratoService } from '../extrato.service';
import {MatDialog,MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

export interface SumarioData {
  quantidadeLancamentos:number;
  quantidadeRemessas:number;
  valorLancamentos:number;
}
export interface CargaData {
  quantidadeGerados: number;
}
export interface MensagemData {
  titulo: string;
  subtitulo: string;
}

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css']
})
export class ExtratoComponent {

  displayedColumns:string[] = ['dataLancamento','descricao','numero','situacao','dataConfirmacao','dadosBancarios','valorFinal']
  registros:Array<any>
  pageEvent: PageEvent;
  pageIndex:number;
  length:number;
  pageSize = 10;
  pageSizeOptions: number[] = [10, 25, 50, 100];
  sumario:string = '';
  sum:SumarioData;
  quantidadeGerados:number = 10;

  private ini:Date;
  private fim:Date;
  private tamanho:number;
  private pagina:number;
  
  @ViewChild(MatPaginator,{static: false}) paginator: MatPaginator;

  constructor(private _service:ExtratoService, public dialog:MatDialog) { }

  paginacaoAlterada(event:PageEvent) {
    this.tamanho = event.pageSize;
    this.pagina = event.pageIndex;
    this.buscarRegistros();
  }

  dataAlterada(event:MatDatepickerInputEvent<Date>) {
    this.ini = event.value['begin'];
    this.fim = event.value['end'];
    this.pagina = 0;
    this.paginator.pageIndex=0;
    this.buscarRegistros();
  }

  buscarRegistros() {
    if (this.ini != undefined || this.fim != undefined) {
      this._service.consultaExtrato(this.ini,this.fim,this.tamanho,this.pagina).subscribe (
        data=>{
          this.registros=data['detalhes'];
          this.length = data['totalElements'];
          this.sum = data['sumario'];
          this.sumario = 'Remessas: ' + this.sum.quantidadeRemessas + ", Lançamentos: "  +this.sum.quantidadeLancamentos + ", Valor: $ " + this.sum.valorLancamentos;
          },
        (error)=>{
          this.dialog.open(MensagemComponentModal, {
            width: '300px',
            data: {titulo:'Erro',mensagem:error.error.mensagem!=undefined?error.error.mensagem:error.status==0?'Servidor inacessivel':'Erro inesperado'}
          });
          this.registros=null;
          this.length = 0;
          this.sumario = '';
          }
        );
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CargaComponentModal, {
      width: '300px',
      data: { quantidadeGerados: this.quantidadeGerados }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.quantidadeGerados = result;
      if (this.quantidadeGerados != undefined) {
        this._service.geraRegistros(this.quantidadeGerados).subscribe(
          data=> {
            this.dialog.open(MensagemComponentModal, {
              width: '300px',
              data: {titulo:'Sucesso',mensagem:data['mensagem']}
          });
          this.buscarRegistros();
          },
          (error)=> {
            this.dialog.open(MensagemComponentModal, {
                width: '300px',
                data: {titulo:'Erro',mensagem:error.error.mensagem!=undefined?error.error.mensagem:error.status==0?'Servidor inacessivel':'Erro inesperado'}
            });
          }
        );
      }
      this.quantidadeGerados = 10;
    });
  }
}

@Component({
  selector: 'app-extrato-dialog',
  templateUrl: './extrato.component.modal.carga.html',
})
export class CargaComponentModal {

  constructor(
    public dialogRef: MatDialogRef<CargaComponentModal>,
    @Inject(MAT_DIALOG_DATA) public data: CargaData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}

@Component({
  selector: 'app-extrato-dialog',
  templateUrl: './extrato.component.modal.mensagem.html',
})
export class MensagemComponentModal {

  constructor(
    public dialogRef: MatDialogRef<MensagemComponentModal>,
    @Inject(MAT_DIALOG_DATA) public data: MensagemData) {}

  onOkClick(): void {
    this.dialogRef.close();
  }
}
