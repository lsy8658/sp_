"use client";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { isLoggedIn, logout } from "../lib/api/auth";
import Link from "next/link";

export default function Header() {
  const [loggedIn, setLoggedIn] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const loggedCheck = () => {
      const isLogged = isLoggedIn();
      setLoggedIn(isLogged);
    };
    loggedCheck();
  }, []);

  const handleLogout = () => {
    logout();
    setLoggedIn(false);
    router.push("/login");
  };
  return (
    <header className="flex items-center justify-between border-b px-4 py-3 sm:px-8">
      <Link href={"/"} className="text-lg font-semibold">
        Shopmall
      </Link>

      <nav className="flex items-center gap-3 text-sm">
        {loggedIn ? (
          <button
            onClick={handleLogout}
            className="text-neutral-600 hover:text-black"
          >
            로그아웃
          </button>
        ) : (
          <>
            <Link
              href={"/login"}
              className="text-neutral-600 hover: text-black"
            >
              로그인
            </Link>
            <Link
              href={"/signup"}
              className="text-neutral-600 hover: text-black"
            >
              회원가입
            </Link>
          </>
        )}
      </nav>
    </header>
  );
}
