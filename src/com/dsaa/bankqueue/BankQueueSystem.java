package com.dsaa.bankqueue;

/**
 * Sistem antrean bank dengan dua circular array terpisah (VIP dan reguler)
 * dan penjadwalan Weighted Round-Robin rasio 3 VIP : 1 reguler.
 *
 * Aturan pelayanan pada dequeue():
 * 1. Jika kedua antrean kosong, dequeue() melempar QueueEmptyException.
 * 2. Jika salah satu antrean kosong, antrean lainnya dilayani.
 * 3. Jika keduanya berisi, VIP dilayani sebanyak VIP_QUOTA kali berturut-turut,
 *    kemudian satu nasabah reguler dilayani (REGULER_QUOTA), lalu siklus diulang.
 *
 * Jaminan anti-starvation: ketika antrean reguler tidak kosong, nasabah reguler
 * terdepan menunggu paling banyak VIP_QUOTA (3) pelayanan VIP.
 */
public class BankQueueSystem {

    /** Jumlah VIP berturut-turut yang boleh dilayani sebelum giliran reguler. */
    public static final int VIP_QUOTA = 3;
    /** Jumlah nasabah reguler yang dilayani setiap siklus. */
    public static final int REGULER_QUOTA = 1;

    private final CircularQueue vipQueue;
    private final CircularQueue regulerQueue;

    private int nextTicketNumber;
    /** Banyaknya VIP yang dilayani berturut-turut selama antrean reguler tidak kosong. */
    private int vipStreak;
    private int totalServedVip;
    private int totalServedReguler;

    public BankQueueSystem(int vipCapacity, int regulerCapacity) {
        this(vipCapacity, regulerCapacity, 1);
    }

    /**
     * Konstruktor dengan nomor tiket awal. Nomor tiket awal yang besar
     * dipakai untuk mensimulasikan kondisi overflow nomor tiket.
     */
    public BankQueueSystem(int vipCapacity, int regulerCapacity, int startTicketNumber) {
        if (startTicketNumber < 1) {
            throw new IllegalArgumentException("Nomor tiket awal minimal 1.");
        }
        this.vipQueue = new CircularQueue(vipCapacity);
        this.regulerQueue = new CircularQueue(regulerCapacity);
        this.nextTicketNumber = startTicketNumber;
        this.vipStreak = 0;
        this.totalServedVip = 0;
        this.totalServedReguler = 0;
    }

    /** Menambahkan nasabah VIP ke antrean VIP. */
    public Customer enqueueVIP(String name) {
        return enqueue(name, CustomerType.VIP, vipQueue);
    }

    /** Menambahkan nasabah reguler ke antrean reguler. */
    public Customer enqueueReguler(String name) {
        return enqueue(name, CustomerType.REGULER, regulerQueue);
    }

    private Customer enqueue(String name, CustomerType type, CircularQueue target) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama nasabah tidak boleh null atau kosong.");
        }
        if (target.isFull()) {
            throw new QueueFullException("Antrean " + type.getLabel()
                    + " penuh (kapasitas " + target.capacity() + ").");
        }
        if (nextTicketNumber == Integer.MAX_VALUE) {
            throw new QueueOverflowException("Nomor tiket telah mencapai batas maksimum.");
        }
        Customer customer = new Customer(nextTicketNumber, name, type);
        target.enqueue(customer);
        nextTicketNumber++;
        return customer;
    }

    /**
     * Menentukan jenis antrean yang mendapat giliran berikutnya tanpa mengubah
     * state. Mengembalikan null jika kedua antrean kosong.
     */
    private CustomerType decideNextType() {
        if (vipQueue.isEmpty() && regulerQueue.isEmpty()) {
            return null;
        }
        if (vipQueue.isEmpty()) {
            return CustomerType.REGULER;
        }
        if (regulerQueue.isEmpty()) {
            return CustomerType.VIP;
        }
        return (vipStreak < VIP_QUOTA) ? CustomerType.VIP : CustomerType.REGULER;
    }

    /** Melayani nasabah berikutnya sesuai kebijakan Round-Robin 3:1. */
    public Customer dequeue() {
        CustomerType next = decideNextType();
        if (next == null) {
            throw new QueueEmptyException("Tidak ada nasabah dalam antrean VIP maupun reguler.");
        }
        Customer served;
        if (next == CustomerType.VIP) {
            served = vipQueue.dequeue();
            if (!regulerQueue.isEmpty()) {
                vipStreak++;
            }
            totalServedVip++;
        } else {
            served = regulerQueue.dequeue();
            vipStreak = 0;
            totalServedReguler++;
        }
        return served;
    }

    /** Menampilkan isi kedua antrean dari depan ke belakang beserta giliran berikutnya. */
    public void displayQueue() {
        System.out.println("---------------- KONDISI ANTREAN ----------------");
        printQueue("VIP    ", vipQueue);
        printQueue("Reguler", regulerQueue);
        CustomerType next = decideNextType();
        String nextText = (next == null) ? "-" : next.getLabel();
        System.out.println("Giliran berikutnya : " + nextText);
        System.out.println("Hitungan VIP berturut-turut : " + vipStreak + " dari " + VIP_QUOTA);
        System.out.println("Total terlayani    : VIP=" + totalServedVip
                + ", Reguler=" + totalServedReguler);
        System.out.println("-------------------------------------------------");
    }

    private void printQueue(String label, CircularQueue queue) {
        StringBuilder line = new StringBuilder();
        if (queue.isEmpty()) {
            line.append("(kosong)");
        } else {
            for (int i = 0; i < queue.size(); i++) {
                if (i > 0) {
                    line.append(" -> ");
                }
                line.append(queue.peekAt(i));
            }
        }
        System.out.println(label + " [" + queue.size() + "/" + queue.capacity() + "] : " + line);
    }

    public boolean isEmpty() {
        return vipQueue.isEmpty() && regulerQueue.isEmpty();
    }

    public int getVipCount() {
        return vipQueue.size();
    }

    public int getRegulerCount() {
        return regulerQueue.size();
    }
}
