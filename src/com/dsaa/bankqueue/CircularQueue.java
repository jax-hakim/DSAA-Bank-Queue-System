package com.dsaa.bankqueue;

/**
 * Antrean FIFO berkapasitas tetap yang diimplementasikan secara manual
 * dengan circular array (array Java biasa, tanpa pustaka koleksi bawaan Java).
 *
 * Invarian:
 * - 0 <= size <= data.length
 * - elemen terdepan berada pada indeks front
 * - posisi penyisipan berikutnya adalah rear = (front + size) % data.length
 */
public class CircularQueue {

    private final Customer[] data;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Kapasitas antrean harus lebih besar dari 0.");
        }
        this.data = new Customer[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    /** Menambahkan nasabah di belakang antrean. Kompleksitas O(1). */
    public void enqueue(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Nasabah yang dimasukkan tidak boleh null.");
        }
        if (isFull()) {
            throw new QueueFullException("Antrean penuh (kapasitas " + data.length + ").");
        }
        data[rear] = customer;
        rear = (rear + 1) % data.length;
        size++;
    }

    /** Mengambil nasabah terdepan antrean (FIFO). Kompleksitas O(1). */
    public Customer dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException("Antrean kosong.");
        }
        Customer served = data[front];
        data[front] = null; // lepaskan referensi agar objek dapat di-garbage-collect
        front = (front + 1) % data.length;
        size--;
        return served;
    }

    /** Melihat nasabah terdepan tanpa mengeluarkannya. Kompleksitas O(1). */
    public Customer peek() {
        if (isEmpty()) {
            throw new QueueEmptyException("Antrean kosong.");
        }
        return data[front];
    }

    /**
     * Mengakses elemen berdasarkan urutan logis (0 = terdepan).
     * Digunakan untuk menampilkan antrean tanpa mengubah isinya.
     */
    public Customer peekAt(int logicalIndex) {
        if (logicalIndex < 0 || logicalIndex >= size) {
            throw new IndexOutOfBoundsException("Indeks logis di luar jangkauan: " + logicalIndex);
        }
        return data[(front + logicalIndex) % data.length];
    }
}
