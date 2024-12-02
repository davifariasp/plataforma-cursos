"use client";

import { Button } from "@/components/ui/button";

interface HeaderProps {
  userName: string;
  onLogout: () => void;
}

export function Header({ userName, onLogout }: HeaderProps) {
  return (
    <div className="flex justify-between items-center mb-8">
      <h1 className="text-2xl font-bold">Cursos Disponíveis</h1>
      <div className="flex items-center gap-4">
        <span>Olá, {userName}</span>
        <Button onClick={onLogout} variant="outline">
          Sair
        </Button>
      </div>
    </div>
  );
}
