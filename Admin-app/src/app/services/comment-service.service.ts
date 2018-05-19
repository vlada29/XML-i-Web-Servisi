import { Injectable } from '@angular/core';
import { IKomentar } from '../interfaces/IKomentar';

@Injectable()
export class CommentServiceService {
  private comments = null;

  constructor() { }

  public getComments(): IKomentar[]{
    this.comments = [{
      idKomentara: 0, 
      smestajnaJedinica: 1, 
      user: {
        ime: 'Vlada',prezime: 'Djurdjevic', username: 'Dovla29', password: 'Dovla29', active: true
      }, 
      sadrzaj: 'Ovaj smestaj je bio sjajan',
      odobren: false
    },{
      idKomentara: 1, 
      smestajnaJedinica: 3, 
      user: {
        ime: 'Danilo',prezime: 'Bujisa', username: 'qanilo', password: 'qanilo', active: true
      }, 
      sadrzaj: 'Ovaj smestaj je bio sasvim u redu za nase standarde, vucicu pederu!',
      odobren: false
    },{
      idKomentara: 2, 
      smestajnaJedinica: 2, 
      user: {
        ime: 'Olivera',prezime: 'Hrnjakovic', username: 'OljaH', password: 'OljaH', active: false
      }, 
      sadrzaj: 'Ovaj smestaj je bio uzasan aaaaaaaaaaaaaaaaaaaaaaa sram vas bilo kako vas nije sramota',
      odobren: false
    }];



    return this.comments;
  }


}
