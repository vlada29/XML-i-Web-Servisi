import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class RatingService {

  constructor(private http: HttpClient) { }

  public postComment(smestajnaJedinica,user,sadrzaj){
      var endpoint = 'https://us-central1-rating-system-ca802.cloudfunctions.net/addComment';

      const comment = {
        hjid: 0,  
        smestajnaJedinica: smestajnaJedinica, 
        user: user, 
        sadrzaj: sadrzaj,
        odobren: false
      };

      this.http.post(endpoint,comment).subscribe(data => {});
  }
  
  public postRating(smestajnaJedinica,user,ocena){
      var endpoint2 = 'https://us-central1-rating-system-ca802.cloudfunctions.net/addRating';

      const rating = {
        hjid: 0,
        smestajnaJedinica: smestajnaJedinica,
        user: user,
        ocena: ocena
      };

      this.http.post(endpoint2,rating).subscribe(data => {});
  }


  
  
}
