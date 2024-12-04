"use client";

import { useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { api } from "@/services/api";
import { useAuth } from "@/hooks/useAuth";

const nameRegex = /^[A-Z][a-z]+ [A-Z][a-z]+( [A-Z][a-z]+)*$/;
const cpfRegex = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const senhaRegex =
  /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

const registerSchema = z.object({
  nome: z
    .string()
    .min(1, "Nome é obrigatório")
    .regex(
      nameRegex,
      "Nome deve ter pelo menos dois nomes, iniciando com maiúsculas"
    ),
  nascimento: z.string().optional(),
  cpf: z.string().min(1, "CPF é obrigatório").regex(cpfRegex, "CPF inválido"),
  email: z
    .string()
    .optional()
    .refine((val) => !val || emailRegex.test(val), "E-mail inválido"),
  senha: z
    .string()
    .min(1, "Senha é obrigatória")
    .regex(
      senhaRegex,
      "Senha deve conter 8+ caracteres, maiúsculas, minúsculas, números e caracteres especiais"
    ),
});

export default function RegisterPage() {
  const router = useRouter();
  const { handleLogin } = useAuth();
  const [isLoading, setIsLoading] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: zodResolver(registerSchema),
  });

  const onSubmit = async (data: any) => {
    try {
      setIsLoading(true);
      const formattedData = {
        ...data,
        cpf: data.cpf.replace(/\D/g, ""),
        senha: data.senha,
      };
      const response = await api.auth.register(formattedData);

      await handleLogin(formattedData.cpf, formattedData.senha);

      router.push("/cursos");
    } catch (error: any) {
      // toast({
      //   title: 'Erro ao cadastrar',
      //   description: error.response?.data?.message || 'Ocorreu um erro ao cadastrar',
      //   variant: 'destructive',
      // });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 py-12">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h1 className="text-2xl font-bold text-center mb-6">Cadastro</h1>
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <Input
              {...register("nome")}
              placeholder="Nome Completo"
              className="w-full"
            />
            {errors.nome && (
              <p className="text-sm text-red-500 mt-1">
                {errors.nome.message as string}
              </p>
            )}
          </div>
          <div>
            <Input
              {...register("nascimento")}
              type="date"
              max={new Date().toISOString().split("T")[0]}
              className="w-full"
            />
            {errors.nascimento && (
              <p className="text-sm text-red-500 mt-1">
                {errors.nascimento.message as string}
              </p>
            )}
          </div>
          <div>
            <Input
              {...register("cpf")}
              placeholder="CPF (000.000.000-00)"
              className="w-full"
            />
            {errors.cpf && (
              <p className="text-sm text-red-500 mt-1">
                {errors.cpf.message as string}
              </p>
            )}
          </div>
          <div>
            <Input
              {...register("email")}
              type="email"
              placeholder="E-mail (opcional)"
              className="w-full"
            />
            {errors.email && (
              <p className="text-sm text-red-500 mt-1">
                {errors.email.message as string}
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
            {isLoading ? "Cadastrando..." : "Cadastrar"}
          </Button>
        </form>
        <p className="text-center mt-4">
          Já tem uma conta?{" "}
          <Link href="/login" className="text-blue-600 hover:underline">
            Faça login
          </Link>
        </p>
      </div>
    </div>
  );
}
