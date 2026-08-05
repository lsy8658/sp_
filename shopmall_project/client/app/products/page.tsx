"use client";

import { deleteProduct, getProducts } from "@/src/lib/api/product";
import { Product } from "@/src/types/product";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function ProductsPage() {
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

  const handleDelete = async (id: number) => {
    try {
      await deleteProduct(id);
      setProducts((prev) => prev.filter((p) => p.id !== id));
    } catch {
      setError("상품 삭제에 실패했습니다.");
    }
  };

  return (
    <div className="mx-auto max-w-4xl px-4 py-8 sm:px-8">
      <h1 className="mb-6 text-2xl font-semibold">상품 목록</h1>

      {error && <p className="text-sm text-red-500">{error}</p>}

      <div className="flex flex-wrap gap-4">
        {products.map((p) => (
          <div
            key={p.id}
            className="flex h-40 w-48 flex-col justify-between rounded-xl border p-4 shadow-sm"
          >
            <div>
              <Link href={`/products/${p.id}`}>
                <h2 className="truncate font-medium hover:underline">
                  {p.name}
                </h2>
              </Link>
              <p className="text-sm text-neutral-500">
                {p.price.toLocaleString()}원
              </p>
            </div>
            <div className="flex gap-2 text-sm">
              <Link
                href={`/products/${p.id}/edit`}
                className="text-blue-600 hover:underline"
              >
                수정
              </Link>
              <button
                onClick={() => handleDelete(p.id)}
                className="text-red-500 hover:underline"
              >
                삭제
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
