"use client";
import { getProducts, updateProduct } from "@/src/lib/api/product";
import axios from "axios";
import { useParams, useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";
import { FormEvent } from "react";

export default function EditProductPage() {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [stock, setStock] = useState("");
  const [description, setDescription] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();
  const params = useParams();
  const id = Number(params.id);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const products = await getProducts();
        const target = products.find((product) => product.id === id);
        if (!target) {
          setError("상품을 찾을 수 없습니다.");
          return;
        }
        setName(target.name);
        setPrice(String(target.price));
        setStock(String(target.stock));
        setDescription(target.description);
      } catch {
        setError("상품 정보를 불러오지 못했습니다.");
      }
    };
    fetchProduct();
  }, [id]);

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError("");

    try {
      await updateProduct(id, {
        name,
        price: Number(price),
        stock: Number(stock),
        description,
      });
      router.push("/products");
    } catch (err) {
      if (axios.isAxiosError(err) && err.response?.data) {
        setError(String(err.response.data));
      } else {
        setError("상품 수정에 실패했습니다.");
      }
    }
  };
  return (
    <div className="mx-auto max-w-sm px-4 py-8 sm:px-8">
      <h1 className="mb-6 text-2xl font-semibold">상품 수정</h1>
      <form onSubmit={handleSubmit} className="space-y-3">
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <input
          type="number"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <input
          type="number"
          value={stock}
          onChange={(e) => setStock(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <input
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm"
        />
        <button
          type="submit"
          className="w-full rounded-lg bg-neutral-900 py-2.5 text-sm font-medium text-white"
        >
          수정
        </button>
        {error && <p className="text-sm text-red-500">{error}</p>}
      </form>
    </div>
  );
}
