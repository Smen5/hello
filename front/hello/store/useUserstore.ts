import { create } from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware'
interface UserState {
    token: string | null;
    name: string | null;
    avatarUrl: string | null;
    uuid: string | null;
    role: string | null;
    setUser: (token: string, username: string, avatarUrl: string, uuid: string, role: string) => void;
    logout: () => void;
}

export const useUserStore = create<UserState>()(
  persist(
    (set) => ({
      token: null,
      name: null,
      avatarUrl: null,
      uuid: null,
      role: null,
      setUser: (token, name, avatarUrl, uuid, role) =>
        set({ token, name, avatarUrl, uuid, role }),
      logout: () =>
        set({ token: null, name: null, avatarUrl: null, uuid: null, role: null }),
    }),
    {
      name: 'user-storage', // localStorage key
      storage: createJSONStorage(() => localStorage), // or sessionStorage
    }
  )
)