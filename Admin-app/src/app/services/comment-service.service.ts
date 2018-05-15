import { Injectable } from '@angular/core';
import { IKomentar } from '../interfaces/IKomentar';

@Injectable()
export class CommentServiceService {
  private comments = null;

  constructor() { }

  public getComments(): IKomentar[]{
    this.comments = [{
      idKomentara: 0, 
      ocena: 10, 
      smestajnaJedinica: {naziv: 'Grand Casino Belgrade'}, 
      user: {
        ime: 'Vlada',prezime: 'Djurdjevic', username: 'Dovla29', password: 'Dovla29', active: true
      }, 
      sadrzaj: 'Ovaj smestaj je bio sjajan',
      odobren: false
    },{
      idKomentara: 1, 
      ocena: 7, 
      smestajnaJedinica: {naziv: 'President Hotel Belgrade'}, 
      user: {
        ime: 'Danilo',prezime: 'Bujisa', username: 'qanilo', password: 'qanilo', active: true
      }, 
      sadrzaj: 'Ovaj smestaj je bio sasvim u redu za nase standarde, vucicu pederu!',
      odobren: false
    },{
      idKomentara: 2, 
      ocena: 2, 
      smestajnaJedinica: {naziv: 'Grand Palace Paris'}, 
      user: {
        ime: 'Olivera',prezime: 'Hrnjakovic', username: 'OljaH', password: 'OljaH', active: false
      }, 
      sadrzaj: 'Ovaj smestaj je bio uzasan aaaaaaaaaaaaaaaaaaaaaaa sram vas bilo kako vas nije sramota',
      odobren: false
    }];



    return this.comments;
  }


}
