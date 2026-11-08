package com.dsaa.bankqueue;

/**
 * Representasi nasabah yang mengantre. Objek bersifat immutable
 * (semua field private final) sebagai penerapan encapsulation.
 */
public final class Customer {

    private final int ticketNumber;
    private final String name;
    private final CustomerType type;

    public Customer(int ticketNumber, String name, CustomerType type) {
        if (ticketNumber <= 0) {
            throw new IllegalArgumentException("Nomor tiket harus bernilai positif.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama nasabah tidak boleh null atau kosong.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Jenis nasabah tidak boleh null.");
        }
        this.ticketNumber = ticketNumber;
        this.name = name.trim();
        this.type = type;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getName() {
        return name;
    }

    public CustomerType getType() {
        return type;
    }

    /** Kode tiket, contoh: V-001 atau R-012. */
    public String getTicketCode() {
        return String.format("%s-%03d", type.getPrefix(), ticketNumber);
    }

    @Override
    public String toString() {
        return getTicketCode() + " (" + name + ")";
    }
}
