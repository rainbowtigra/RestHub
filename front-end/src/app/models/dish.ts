import {Category} from "./category";
import {Image} from "./image";

export class Dish {
   id: number;
   name: string;
   description: string;
   weight: number;
   calories: number;
   preparingtime: number;
   price: number;
   category: Category
   images: Image[];
   availability: boolean;
}
