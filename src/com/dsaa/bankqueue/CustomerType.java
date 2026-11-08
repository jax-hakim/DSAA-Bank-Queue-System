package com.dsaa.bankqueue;

/**
 * Jenis nasabah pada sistem antrean bank.
 */
public enum CustomerType {
    VIP("V", "VIP"),
    REGULER("R", "Reguler");

    private final String prefix;
    private final String label;

    CustomerType(String prefix, String label) {
        this.prefix = prefix;
        this.label = label;
    }

    /** Awalan kode tiket, misalnya "V" atau "R". */
    public String getPrefix() {
        return prefix;
    }

    /** Label yang ditampilkan kepada pengguna. */
    public String getLabel() {
        return label;
    }
}
