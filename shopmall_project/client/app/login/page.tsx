"use client";

import { useState, FormEvent } from "react";
import { useRouter } from "next/navigation";
import { login } from "@/src/lib/api/auth";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError("");

    try {
      const token = await login({ email, password });

      if (!token) {
        setError("로그인 응답이 올바르지 않습니다.");
        return;
      }

      localStorage.setItem("token", token);
      router.push("/");
    } catch (err) {
      setError("로그인 실패: 이메일 또는 비밀번호를 확인해주세요.");
    }
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-gray-50 px-4 dark:bg-neutral-950">
      <form
        onSubmit={handleSubmit}
        className="w-full max-w-sm space-y-5 rounded-2xl bg-white p-8 shadow-sm dark:bg-neutral-900 sm:p-10"
      >
        <div className="space-y-1">
          <h1 className="text-2xl font-semibold tracking-tight text-neutral-900 dark:text-white">
            로그인
          </h1>
          <p className="text-sm text-neutral-500 dark:text-neutral-400">
            계정에 접속하려면 정보를 입력하세요
          </p>
        </div>

        <div className="space-y-3">
          <input
            type="email"
            placeholder="이메일"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm outline-none transition focus:border-neutral-900 dark:border-neutral-700 dark:bg-neutral-800 dark:text-white"
          />
          <input
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full rounded-lg border border-neutral-200 px-4 py-2.5 text-sm outline-none transition focus:border-neutral-900 dark:border-neutral-700 dark:bg-neutral-800 dark:text-white"
          />
        </div>

        <button
          type="submit"
          className="w-full rounded-lg bg-neutral-900 py-2.5 text-sm font-medium text-white transition hover:bg-neutral-800 dark:bg-white dark:text-neutral-900 cursor-pointer"
        >
          로그인
        </button>

        {error && (
          <p className="text-sm text-red-500" role="alert">
            {error}
          </p>
        )}
      </form>
    </div>
  );
}
