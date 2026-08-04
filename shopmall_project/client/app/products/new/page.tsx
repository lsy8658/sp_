"use client";

import { createProduct } from "@/src/lib/api/product";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { FormEvent } from "react";

export default function NewProductPage() {
  const [name, setName] = useState<string>("");
  const [price, setPrice] = useState("");
  const [stock, setStock] = useState("");
  const [description, setDescription] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError("");

    try {
      await createProduct({
        name,
        price: Number(price),
        stock: Number(stock),
        description,
      });
      router.push("/products");
    } catch (err) {
      if (axios.isAxiosError(err) && err.response?.data) {
        setError(err.response?.data);
      } else {
        setError("상품 등록에 실패했습니다.");
      }
    }
  };
  return (
    <div className="mx-auto max-w-sm px-4 py-8 sm:px-8">
      <h1 className="mb-6 text-2xl font-semibold">상품 등록</h1>
      <form onSubmit={handleSubmit} className="space-y-3">
        <input
          type="text"
          placeholder="상품명"
          value={name}
          onChange={(e) => setName(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <input
          type="number"
          placeholder="가격"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <input
          type="number"
          placeholder="재고"
          value={stock}
          onChange={(e) => setStock(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <input
          type="text"
          placeholder="설명"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <button
          type="submit"
          className="w-full rounded-lg bg-neutral-900 py-2.5 text-sm font-medium text-white"
        >
          등록
        </button>
        {error && <p className="text-sm text-red-500">{error}</p>}
      </form>
    </div>
  );
}
