package com.dsaa.bankqueue;

/**
 * Dilempar ketika nomor tiket telah mencapai batas maksimum tipe int
 * sehingga tiket baru tidak dapat diterbitkan tanpa menyebabkan integer overflow.
 */
public class QueueOverflowException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueOverflowException(String message) {
        super(message);
    }
}
