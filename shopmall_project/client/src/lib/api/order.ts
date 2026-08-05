import { Order } from "@/src/types/order";
import { api } from "../axios";

export const createOrder = async (data: Omit<Order, "id">): Promise<number> => {
  const res = await api.post<number>("/api/order", data);
  return res.data;
};
