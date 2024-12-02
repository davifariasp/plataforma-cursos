'use client'
import React, { createContext, useContext, ReactNode } from 'react'
import { useAuth } from '@/hooks/useAuth'

interface AuthProviderProps {
  children: ReactNode
}

const AuthContext = createContext<ReturnType<typeof useAuth> | undefined>(
  undefined
)

export const AuthProvider = ({ children }: AuthProviderProps) => {
  const auth = useAuth()
  return <AuthContext.Provider value={auth}>{children}</AuthContext.Provider>
}

export const useAuthContext = () => {
  const context = useContext(AuthContext)
  if (context === undefined) {
    throw new Error('useAuthContext must be used within an AuthProvider')
  }
  return context
}
