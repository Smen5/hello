import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import ProfileSummery from "@/components/ui/profile/Profile";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Smen5 TroubleShooting Archive",
  description: "Smen5'TroubleShooting Archive",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <header>
          <div>
            Smen5's Troubble shooting archive
          </div>
          <ProfileSummery/>
        </header>
        <div className="content">
          {children}
        </div>
      </body>
    </html>
  );
}