"use client";

import { useEffect, useState, FormEvent } from "react";
import { useParams } from "next/navigation";
import axios from "axios";
import { Product } from "@/src/types/product";
import { Review } from "@/src/types/review";
import { getProducts } from "@/src/lib/api/product";
import { createReview, getReviewsByProduct } from "@/src/lib/api/review";

export default function ProductDetailPage() {
  const [product, setProduct] = useState<Product | null>(null);
  const [reviews, setReviews] = useState<Review[]>([]);
  const [tab, setTab] = useState<"info" | "review">("info");
  const [content, setContent] = useState("");
  const [rating, setRating] = useState("");
  const [error, setError] = useState("");
  const params = useParams();
  const id = Number(params.id);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const products = await getProducts();
        const target = products.find((p) => p.id === id);
        if (!target) {
          setError("상품을 찾을 수 없습니다.");
          return;
        }
        setProduct(target);
        const reviewData = await getReviewsByProduct(id);
        setReviews(reviewData);
      } catch (err) {
        if (axios.isAxiosError(err) && err.response?.data) {
          setError(String(err.response.data));
        } else {
          setError("정보를 불러오지 못했습니다.");
        }
      }
    };
    fetchData();
  }, [id]);

  const handleReviewSubmit = async (e: FormEvent) => {
    e.preventDefault();
    try {
      await createReview({
        memberId: 1,
        productId: id,
        content,
        rating: Number(rating),
      });
      const reviewData = await getReviewsByProduct(id);
      setReviews(reviewData);
      setContent("");
      setRating("");
    } catch (err) {
      if (axios.isAxiosError(err) && err.response?.data) {
        setError(String(err.response.data));
      } else {
        setError("리뷰 작성에 실패했습니다.");
      }
    }
  };

  if (error) return <p className="p-8 text-sm text-red-500">{error}</p>;
  if (!product)
    return <p className="p-8 text-sm text-neutral-400">불러오는 중...</p>;

  return (
    <div className="mx-auto max-w-2xl px-4 py-8 sm:px-8">
      <h1 className="text-2xl font-semibold">{product.name}</h1>
      <p className="mt-2 text-lg text-neutral-700">
        {product.price.toLocaleString()}원
      </p>

      <div className="mt-6 flex gap-4 border-b text-sm">
        <button
          onClick={() => setTab("info")}
          className={`pb-2 ${tab === "info" ? "border-b-2 border-black font-medium" : "text-neutral-400"}`}
        >
          상품정보
        </button>
        <button
          onClick={() => setTab("review")}
          className={`pb-2 ${tab === "review" ? "border-b-2 border-black font-medium" : "text-neutral-400"}`}
        >
          리뷰({reviews.length})
        </button>
      </div>

      {tab === "info" && (
        <p className="mt-4 text-sm text-neutral-600">{product.description}</p>
      )}

      {tab === "review" && (
        <div className="mt-4 space-y-4">
          <form
            onSubmit={handleReviewSubmit}
            className="space-y-2 rounded-lg border p-4"
          >
            <input
              value={content}
              onChange={(e) => setContent(e.target.value)}
              placeholder="리뷰 내용"
              maxLength={500}
              className="w-full rounded border px-3 py-2 text-sm"
            />
            <input
              value={rating}
              onChange={(e) => setRating(e.target.value)}
              placeholder="평점 (1~5)"
              type="number"
              className="w-full rounded border px-3 py-2 text-sm"
            />
            <button
              type="submit"
              className="rounded bg-neutral-900 px-4 py-2 text-sm text-white"
            >
              등록
            </button>
          </form>

          {reviews.map((r) => (
            <div key={r.id} className="rounded-lg border p-3 text-sm">
              <p className="font-medium">평점 {r.rating}</p>
              <p className="text-neutral-600">{r.content}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
