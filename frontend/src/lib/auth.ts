import { create } from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware';

interface AuthState {
  user: any | null;
  token: string | null;
  expiresIn: number | null;
  setAuth: (user: object, token: string, expiresIn: number) => void;
  clearAuth: () => void;
}

export const useAuth = create<AuthState>()(
  persist(
    (set, get) => ({
      user: null,
      token: null,
      expiresIn: null,
      setAuth: (user, token, expiresIn) => set({ user, token, expiresIn }), // Temporário até implementar decode do JWT
      clearAuth: () => set({ user: null, token: null }),
      isAuthenticated: () => !!get().token,
    }),
    {
      name: 'auth-storage',
      storage: createJSONStorage(() => localStorage),
    }
  )
);