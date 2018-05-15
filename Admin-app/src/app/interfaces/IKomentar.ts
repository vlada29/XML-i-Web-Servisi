import { ISmestajnaJedinica } from './ISmestajnaJedinica';
import { IUser } from './IUser';
export interface IKomentar {
    idKomentara: number;
    ocena: number;
    smestajnaJedinica: ISmestajnaJedinica;
    user: IUser;
    sadrzaj: string;
    odobren: boolean;
}