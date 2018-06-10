export class AdvancedSearchDTO
{
    private place : string;
    private from : string;
    private to : string;
    private numberPerson : string;
    private parking: boolean;
    private wifi: boolean;
    private dorucak: boolean;
    private polupansion: boolean;
    private pansion: boolean;
    private tv: boolean;
    private kuhinja: boolean;
    private kupatilo: boolean;
    private kategorija: String;
    private tip: String;

    constructor(
        place : string,
        from : string,
        to : string,
        numberPerson : string,
        parking : boolean,
        wifi : boolean,
        dorucak : boolean,
        polupansion : boolean,  
        pansion : boolean,
        tv : boolean,
        kuhinja : boolean,
        kupatilo : boolean,
        kategorija: String,
        tip: String
    ) {
 
        this.place = place;
        this.from = from;
        this.to = to;
        this.numberPerson = numberPerson;
        this.parking = parking;
        this.wifi = wifi;
        this.dorucak = dorucak;
        this.polupansion = polupansion;
        this.pansion = pansion;
        this.tv = tv;
        this.kuhinja = kuhinja;
        this.kupatilo = kupatilo;
        this.kategorija = kategorija;
        this.tip = tip;

    }

 }