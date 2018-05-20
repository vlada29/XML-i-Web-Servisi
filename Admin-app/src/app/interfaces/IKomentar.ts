import { IUser } from './IUser';
export interface IKomentar {
    hjid: number;
    smestajnaJedinica: number;
    user: IUser;
    sadrzaj: string;
    odobren: boolean;
} 