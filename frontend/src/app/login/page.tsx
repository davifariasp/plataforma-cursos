"use client";

import { useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { api } from "@/lib/api";
import { useAuth } from "@/lib/auth";
import { useAuthContext } from '@/context/AuthContext'

const loginSchema = z.object({
  login: z.string().min(1, "CPF ou E-mail é obrigatório"),
  senha: z.string().min(1, "Senha é obrigatória"),
});

export default function LoginPage() {
  const router = useRouter();
  const { setAuth } = useAuth();
  const { handleLogin } = useAuthContext();
  const [isLoading, setIsLoading] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: zodResolver(loginSchema),
  });

  const onSubmit = async (data: any) => {
    try {
      setIsLoading(true);
      const credentials = {
        login: data.login,
        senha: data.senha,
      };
      //const response = await api.auth.login(credentials);

      const r = await handleLogin(credentials.login, credentials.senha)

      console.log(r)

      //setAuth(response.user, response.accessToken, response.expiresIn);
      router.push("/cursos");
    } catch (error: any) {
      // toast({
      //   title: 'Erro ao fazer login',
      //   description: 'CPF/E-mail ou senha incorretos',
      //   variant: 'destructive',
      // });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h1 className="text-2xl font-bold text-center mb-6">Login</h1>
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <Input
              {...register("login")}
              placeholder="CPF ou E-mail"
              className="w-full"
            />
            {errors.login && (
              <p className="text-sm text-red-500 mt-1">
                {errors.login.message as string}
              </p>
            )}
          </div>
          <div>
            <Input
              {...register("senha")}
              type="password"
              placeholder="Senha"
              className="w-full"
            />
            {errors.senha && (
              <p className="text-sm text-red-500 mt-1">
                {errors.senha.message as string}
              </p>
            )}
          </div>
          <Button type="submit" className="w-full" disabled={isLoading}>
            {isLoading ? "Entrando..." : "Entrar"}
          </Button>
        </form>
        <p className="text-center mt-4">
          Não tem uma conta?{" "}
          <Link href="/cadastro" className="text-blue-600 hover:underline">
            Cadastre-se
          </Link>
        </p>
      </div>
    </div>
  );
}
