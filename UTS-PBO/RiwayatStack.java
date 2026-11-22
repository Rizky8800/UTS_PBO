//ADT Stack untuk menyimpan data pengiriman

public class RiwayatStack {
    private Pengiriman[] data;
    private int top;
    private int kapasitas;

    public RiwayatStack(int kapasitas) {
        if (kapasitas <= 0) {
            kapasitas = 1;
        }

        this.kapasitas = kapasitas;
        this.data = new Pengiriman[kapasitas];
        this.top = -1;
    }

    // Memasukkan pengiriman ke Stack
    public boolean push(Pengiriman pengiriman) {
        if (isFull() || pengiriman == null) {
            return false;
        }

        top++;
        data[top] = pengiriman;
        return true;
    }

    // Mengambil dan menghapus data paling atas
    public Pengiriman pop() {
        if (isEmpty()) {
            return null;
        }

        Pengiriman hasil = data[top];
        data[top] = null;
        top--;

        return hasil;
    }

    // Melihat data paling atas tanpa menghapus
    public Pengiriman peek() {
        if (isEmpty()) {
            return null;
        }

        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == kapasitas - 1;
    }

    public int size() {
        return top + 1;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("Riwayat masih kosong.");
            return;
        }

        System.out.println("=== RIWAYAT PENGIRIMAN ===");

        for (int i = top; i >= 0; i--) {
            System.out.println(
                    data[i].getIdPengiriman()
                            + " - "
                            + data[i].getStatus()
            );
        }
    }
}
