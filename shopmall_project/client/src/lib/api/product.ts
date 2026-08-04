import { Product } from "@/src/types/product";
import { api } from "../axios";

export const getProducts = async (): Promise<Product[]> => {
  const res = await api.get<Product[]>("/api/product");
  return res.data;
};

export const createProduct = async (
  data: Omit<Product, "id">,
): Promise<number> => {
  const res = await api.post<number>("/api/product", data);
  return res.data;
};
