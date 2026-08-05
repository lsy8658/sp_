"use client";
import { createOrder } from "@/src/lib/api/order";
import axios from "axios";
import { useRouter } from "next/navigation";
import React, { useState } from "react";
import { FormEvent } from "react";

export default function NewOrderPage() {
  const [productId, setProductId] = useState("");
  const [quantity, setQuantity] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError("");

    try {
      await createOrder({
        memberId: 1,
        productId: Number(productId),
        quantity: Number(quantity),
      });

      router.push("/products");
    } catch (err) {
      if (axios.isAxiosError(err) && err.response?.data) {
        setError(err.response.data);
      } else {
        setError("주문에 실패했습니다.");
      }
    }
  };
  return (
    <div className="mx-auto max-w-sm px-4 py-8 sm:px-8">
      <h1 className="mb-6 text-2xl font-semibold">주문하기</h1>
      <form onSubmit={handleSubmit} className="space-y-3">
        <input
          type="number"
          placeholder="상품 ID"
          value={productId}
          onChange={(e) => setProductId(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <input
          type="number"
          placeholder="수량"
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <button
          type="submit"
          className="w-full rounded-lg bg-neutral-900 py-2.5 text-sm font-medium text-white"
        >
          주문
        </button>
        {error && <p className="text-sm text-red-500">{error}</p>}
      </form>
    </div>
  );
}
