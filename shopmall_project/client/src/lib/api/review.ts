import { Review } from "@/src/types/review";
import { api } from "../axios";

export const createReview = async (
  data: Omit<Review, "id">,
): Promise<number> => {
  const res = await api.post<number>("/api/review", data);
  return res.data;
};

export const getReviewsByProduct = async (
  productId: number,
): Promise<Review[]> => {
  const res = await api.get<Review[]>(`/api/review/product/${productId}`);
  return res.data;
};
