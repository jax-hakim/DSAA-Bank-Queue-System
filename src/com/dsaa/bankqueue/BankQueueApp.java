package com.dsaa.bankqueue;

/**
 * Program simulasi sistem antrean bank (VIP : Reguler = 3 : 1).
 * Jalankan kelas ini untuk melihat mekanisme pelayanan, penanganan error,
 * perilaku circular array (wrap-around), dan jaminan anti-starvation.
 */
public class BankQueueApp {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  SIMULASI ANTREAN BANK - ROUND-ROBIN 3 VIP : 1 REGULER");
        System.out.println("=================================================");

        skenario1ValidasiDanKapasitas();
        skenario2RoundRobin();
        skenario3AntiStarvation();
        skenario4WrapAround();
        skenario5OverflowDanKonfigurasi();

        System.out.println();
        System.out.println("Simulasi selesai.");
    }

    // ---------------------------------------------------------------
    // Skenario
    // ---------------------------------------------------------------

    private static void skenario1ValidasiDanKapasitas() {
        judul("Skenario 1: Input tidak valid dan antrean penuh (kapasitas VIP=2, Reguler=2)");
        BankQueueSystem sistem = new BankQueueSystem(2, 2);

        tambahVip(sistem, null);
        tambahVip(sistem, "   ");
        tambahReguler(sistem, "");

        tambahVip(sistem, "Andi");
        tambahVip(sistem, "Budi");
        tambahVip(sistem, "Citra");      // antrean VIP penuh
        tambahReguler(sistem, "Dewi");
        tambahReguler(sistem, "Eko");
        tambahReguler(sistem, "Fajar");  // antrean reguler penuh

        sistem.displayQueue();
    }

    private static void skenario2RoundRobin() {
        judul("Skenario 2: Pelayanan Round-Robin 3 VIP : 1 Reguler (6 VIP, 3 Reguler)");
        BankQueueSystem sistem = new BankQueueSystem(6, 4);

        for (int i = 1; i <= 6; i++) {
            tambahVip(sistem, "VIP-" + i);
        }
        for (int i = 1; i <= 3; i++) {
            tambahReguler(sistem, "Reg-" + i);
        }
        sistem.displayQueue();

        System.out.println("Proses pelayanan (percobaan ke-10 menguji kondisi antrean kosong):");
        for (int nomor = 1; nomor <= 10; nomor++) {
            layani(sistem, nomor);
        }
        sistem.displayQueue();
    }

    private static void skenario3AntiStarvation() {
        judul("Skenario 3: Reguler tidak starvation walau VIP terus berdatangan");
        BankQueueSystem sistem = new BankQueueSystem(10, 5);

        tambahReguler(sistem, "Sari");
        tambahReguler(sistem, "Tono");
        for (int i = 1; i <= 6; i++) {
            tambahVip(sistem, "VIP-Awal-" + i);
        }
        sistem.displayQueue();

        System.out.println("Setiap selesai melayani, satu VIP baru datang sehingga antrean VIP tidak pernah kosong:");
        for (int nomor = 1; nomor <= 10; nomor++) {
            layani(sistem, nomor);
            tambahVip(sistem, "VIP-Susulan-" + nomor);
        }
        sistem.displayQueue();
    }

    private static void skenario4WrapAround() {
        judul("Skenario 4: Wrap-around circular array dan urutan FIFO (kapasitas VIP=3)");
        BankQueueSystem sistem = new BankQueueSystem(3, 3);

        tambahVip(sistem, "A");
        tambahVip(sistem, "B");
        tambahVip(sistem, "C");
        layani(sistem, 1);   // A keluar, indeks 0 kosong
        layani(sistem, 2);   // B keluar, indeks 1 kosong
        tambahVip(sistem, "D");  // masuk ke indeks 0 (wrap-around)
        tambahVip(sistem, "E");  // masuk ke indeks 1 (wrap-around)
        sistem.displayQueue();   // urutan logis harus C -> D -> E
        layani(sistem, 3);
        layani(sistem, 4);
        layani(sistem, 5);
        sistem.displayQueue();
    }

    private static void skenario5OverflowDanKonfigurasi() {
        judul("Skenario 5: Overflow nomor tiket dan konfigurasi tidak valid");

        BankQueueSystem hampirOverflow = new BankQueueSystem(3, 3, Integer.MAX_VALUE - 1);
        tambahVip(hampirOverflow, "Nasabah Terakhir");
        tambahVip(hampirOverflow, "Nasabah Berikutnya"); // memicu overflow nomor tiket

        try {
            new BankQueueSystem(0, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("[GAGAL] Konfigurasi sistem ditolak : " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // Fungsi bantu simulasi (menangani exception agar program tidak berhenti)
    // ---------------------------------------------------------------

    private static void tambahVip(BankQueueSystem sistem, String nama) {
        try {
            Customer c = sistem.enqueueVIP(nama);
            System.out.println("[OK]    VIP masuk antrean     : " + c);
        } catch (IllegalArgumentException | QueueFullException | QueueOverflowException e) {
            System.out.println("[GAGAL] enqueueVIP(" + tampil(nama) + ") : " + e.getMessage());
        }
    }

    private static void tambahReguler(BankQueueSystem sistem, String nama) {
        try {
            Customer c = sistem.enqueueReguler(nama);
            System.out.println("[OK]    Reguler masuk antrean : " + c);
        } catch (IllegalArgumentException | QueueFullException | QueueOverflowException e) {
            System.out.println("[GAGAL] enqueueReguler(" + tampil(nama) + ") : " + e.getMessage());
        }
    }

    private static void layani(BankQueueSystem sistem, int nomor) {
        try {
            Customer c = sistem.dequeue();
            System.out.println("  Pelayanan ke-" + nomor + " : " + c.getType().getLabel() + " -> " + c);
        } catch (QueueEmptyException e) {
            System.out.println("  Pelayanan ke-" + nomor + " : [KOSONG] " + e.getMessage());
        }
    }

    private static String tampil(String nama) {
        return (nama == null) ? "null" : "\"" + nama + "\"";
    }

    private static void judul(String teks) {
        System.out.println();
        System.out.println(">>> " + teks);
    }
}
