package com.dsaa.bankqueue;

/**
 * Dilempar ketika dequeue dilakukan pada antrean yang kosong.
 */
public class QueueEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueEmptyException(String message) {
        super(message);
    }
}
