import { IUser } from './IUser';
export interface IKomentar {
    idKomentara: number;
    smestajnaJedinica: number;
    user: IUser;
    sadrzaj: string;
    odobren: boolean;
} 