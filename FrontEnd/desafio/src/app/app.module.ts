import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatTableModule,MatPaginatorModule, MatDatepickerModule, MatFormFieldModule, MatInputModule, MatNativeDateModule, MatButtonModule, MatDialogModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ExtratoComponent, CargaComponentModal, MensagemComponentModal } from './extrato/extrato.component';
import { ExtratoService } from './extrato.service';
import { SatDatepickerModule, SatNativeDateModule } from 'saturn-datepicker';

@NgModule({
  declarations: [
    AppComponent,
    ExtratoComponent,CargaComponentModal, MensagemComponentModal
  ],
  imports: [
    BrowserModule,BrowserAnimationsModule,
    AppRoutingModule,
    MatTableModule,MatFormFieldModule,MatPaginatorModule,MatInputModule,MatDatepickerModule,MatNativeDateModule,MatButtonModule,MatDialogModule,
    SatDatepickerModule,SatNativeDateModule,
    HttpClientModule,
    FormsModule, ReactiveFormsModule
  ],
  entryComponents: [CargaComponentModal, MensagemComponentModal],
  providers: [ExtratoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
