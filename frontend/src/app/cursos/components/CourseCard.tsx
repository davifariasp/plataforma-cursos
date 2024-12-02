"use client";

import { Button } from "@/components/ui/button";
import { Card } from "@/components/ui/card";

interface CourseCardProps {
  curso: {
    idCurso: string;
    nomeCurso: string;
    numeroInscricoes: number;
    numeroVagas: number;
  };
  inscricoes: any;
  onEnroll: (idCurso: string) => void;
}

export function CourseCard({ curso, inscricoes, onEnroll }: CourseCardProps) {
  const isEnrolled = inscricoes.find(
    (inscricao: any) => inscricao === curso.idCurso
  );

  return (
    <Card className="p-6">
      <h2 className="text-xl font-semibold mb-4">{curso.nomeCurso}</h2>
      <div className="space-y-2 mb-4">
        <p>Inscrições: {curso.numeroInscricoes}</p>
        <p>Vagas: {curso.numeroVagas}</p>
      </div>
      {curso.numeroVagas > 0 &&
        curso.numeroVagas > curso.numeroInscricoes &&
        !isEnrolled && (
          <Button onClick={() => onEnroll(curso.idCurso)} className="w-full">
            Inscrever
          </Button>
        )}
      {isEnrolled && <p className="text-green-600 text-center">Inscrito</p>}
      {curso.numeroInscricoes >= curso.numeroVagas && !isEnrolled && (
        <p className="text-red-600 text-center">Sem vagas</p>
      )}
    </Card>
  );
}
