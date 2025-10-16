import { create } from 'zustand';

interface UserState {
    token: string | null;
    name: string | null;
    avatarUrl: string | null;
    uuid: string | null;
    setUser: (token: string, username: string, avatarUrl: string, uuid: string) => void;
    logout: () => void;
}

export const useUserStore = create<UserState>((set) => ({
    token: null,
    name: null,
    avatarUrl: null,
    uuid: null,
    setUser: (token, name, avatarUrl,uuid) => set({ token, name, avatarUrl, uuid }),
    logout: () => set({ token: null, name: null, avatarUrl: null, uuid: null }),
}));