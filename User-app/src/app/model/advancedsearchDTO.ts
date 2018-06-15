import { dodatnaDTO } from "./dodatnaDTO";

export class AdvancedSearchDTO
{
    private place : string;
    private from : string;
    private to : string;
    private numberPerson: string;
    private dodatne: dodatnaDTO[];
    private kategorija: string;
    private tip: string;

    constructor(
        place : string,
        from : string,
        to : string,
        numberPerson : string,
        dodatne: dodatnaDTO[],
        kategorija: string,
        tip: string
    ) {
 
        this.place = place;
        this.from = from;
        this.to = to;
        this.numberPerson = numberPerson;
        this.dodatne = dodatne;
        this.kategorija = kategorija;
        this.tip = tip;

    }

 }