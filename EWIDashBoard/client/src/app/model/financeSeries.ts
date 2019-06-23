
import { MarkerClass } from './marker-class';


export interface IFinancSeries {
    type:string;
    name: string;
    data: number[];
    marker:MarkerClass;

}
export class FinanceSeries implements IFinancSeries{
    type:string;
    name: string;
    data: number[];
    marker:MarkerClass;


}
