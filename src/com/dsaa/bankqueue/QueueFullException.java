package com.dsaa.bankqueue;

/**
 * Dilempar ketika elemen ditambahkan ke antrean yang kapasitasnya sudah penuh.
 */
public class QueueFullException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueFullException(String message) {
        super(message);
    }
}
