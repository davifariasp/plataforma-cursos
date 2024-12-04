import {login, setToken, setUser, clearToken} from "@/services/authService";
import { useRouter } from 'next/navigation'

export function useAuth() {
    const router = useRouter()

    const handleLogin = async (username: string, senha: string) => {
        try {
            const response = await login({login: username, senha});
            setToken(response.token);
            setUser(response.user);
        } catch (error: any) {
            throw new Error("Erro ao fazer login");
        }
    }

    const handleLogout = () => {
        clearToken()
        router.push('/')
    }

    return {
        handleLogin,
        handleLogout
    }
}