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

export const updateProduct = async (
  id: number,
  data: Omit<Product, "id">,
): Promise<void> => {
  await api.put(`/api/product/${id}`, data);
};

export const deleteProduct = async (id: number): Promise<void> => {
  await api.delete(`/api/product/${id}`);
};
