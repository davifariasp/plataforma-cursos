"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { toast } from "react-hot-toast";
import { api } from "@/lib/api";
import { useAuth } from "@/lib/auth";
import { CourseCard } from "./components/CourseCard";
import { Header } from "./components/Header";
import { Client } from "@stomp/stompjs";

export default function CoursesPage() {
  const router = useRouter();
  const { user, clearAuth } = useAuth();
  const [courses, setCourses] = useState([]);
  const [inscricoes, setInscricoes] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const data = await api.courses.list();

        setCourses(data);
      } catch (error) {
        toast.error("Erro ao buscar cursos");
      } finally {
        setIsLoading(false);
      }
    };
    fetchCourses();
  }, []);

  useEffect(() => {
    // Verificar se o `user` e `cpf` estão carregados
    if (user && user.cpf) {
      fetchInscricoes();
    } else {
      setIsLoading(false); // Garantir que o loading seja removido
    }
  }, [user]);

  useEffect(() => {
    const client = new Client({
      brokerURL: "ws://localhost:8082/ws-endpoint",
      connectHeaders: {
        // Se precisar enviar um token de autenticação, adicione aqui
      },
      onConnect: () => {
        client.subscribe("/topic", (message) => {
          const updatedCourses = JSON.parse(message.body);

          setCourses(updatedCourses);
        });
      },
    });

    client.activate();

    return () => {
      client.deactivate();
    };
  }, []);

  const fetchInscricoes = async () => {
    try {
      const data = await api.courses.listInscricoes(user.cpf);
      setInscricoes(data);
    } catch (error) {
      toast.error("Erro ao buscar inscrições");
    } finally {
      setIsLoading(false);
    }
  };

  const handleLogout = () => {
    clearAuth();
    router.push("/login");
  };

  const handleEnroll = async (cursoId: string) => {
    const credentials = {
      idCurso: cursoId,
      cpf: user.cpf,
    };

    try {
      await api.courses.enroll(credentials);
      await fetchInscricoes();
    } catch (error: any) {
      toast.error("Erro ao se inscrever");
    }
  };

  if (isLoading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <p>Carregando...</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-7xl mx-auto">
        <Header userName={user?.nome} onLogout={handleLogout} />
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {courses.map((course: any) => (
            <CourseCard
              key={course.idCurso}
              curso={course}
              inscricoes={inscricoes}
              onEnroll={handleEnroll}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
