"use client";
import { getProducts } from "@/src/lib/api/product";
import { Product } from "@/src/types/product";
import React, { useEffect, useState } from "react";

export default function ProductPage() {
  const [products, setProducts] = useState<Product[]>([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const data = await getProducts();
        setProducts(data);
      } catch {
        setError("상품 목록을 불러오지 못했습니다.");
      }
    };
    fetchProducts();
  }, []);

  return (
    <div className="mx-auto max-w-4xl px-4 py-8 sm:px-8">
      <h1 className="mb-6 text-2xl font-semibold">상품 목록</h1>
      {error && <p className="text-sm text-red-500">{error}</p>}

      <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 md:grid-cols-3">
        {products.map((product) => (
          <div key={product.id} className="rounded-xl border p-4 shadow-sm">
            <h2 className="font-medium">{product.name}</h2>
            <p className="text-sm text-neutral-500">
              {product.price.toLocaleString()}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}
