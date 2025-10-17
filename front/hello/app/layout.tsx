import type { Metadata } from "next";
import "./globals.css";
import ProfileSummery from "@/components/ui/profile/Profile";
import Link from "next/link";

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
          <Link href="/" className="logo">
            Smen5's Troubble shooting archive
          </Link>
          <ProfileSummery/>
        </header>
        <div className="content">
          {children}
        </div>
      </body>
    </html>
  );
}