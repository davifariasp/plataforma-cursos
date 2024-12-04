import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";
import { KEY_CACHE_USER } from "./services/authService";

export interface User {
    nome: string;
    cpf: string;
}

const PROTECTED_PATHS = ['/cursos']

export async function middleware(request: NextRequest) {
    
    const userDataCookie = request.cookies.get(KEY_CACHE_USER)?.value;

    const isAuthenticated = Boolean(userDataCookie);
    const requestedPath = request.nextUrl.pathname;
    
    const isProtectedRoute = PROTECTED_PATHS.includes(requestedPath);

    let user: User | null = null;

    if (isAuthenticated && userDataCookie) {
        try {
        user = JSON.parse(userDataCookie)
        } catch (error) {
        console.error('Falha ao analisar os dados do usuário do cookie:', error)
        }
    }

    if (!isAuthenticated && isProtectedRoute) {
        console.log('Usuário não autenticado, redirecionando para /login')
        return NextResponse.redirect(new URL('/login', request.url))
    }

    return NextResponse.next();
}

export const config = {
    matcher: ['/cursos/:path*']
}