export class SearchDTO
{
    private place : string;
    private from : string;
    private to : string;
    private numberPerson : string;

    constructor(
        place : string,
        from : string,
        to : string,
        numberPerson : string   
    ) {
 
        this.place = place;
        this.from = from;
        this.to = to;
        this.numberPerson = numberPerson;

    }

 }