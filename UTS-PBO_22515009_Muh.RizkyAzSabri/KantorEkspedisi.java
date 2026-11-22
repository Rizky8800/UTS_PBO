import java.util.ArrayList;

//Class utama untuk mengelola kegiatan kantor ekspedisi.

public class KantorEkspedisi {
    private String namaKantor;
    private String alamat;
    private double tarif;
    private int kapasitas;

    private ArrayList<Paket> daftarPaket;
    private ArrayList<Pengirim> daftarPengirim;
    private ArrayList<Pengiriman> daftarPengiriman;
    private RiwayatStack riwayat;

    public KantorEkspedisi() {
        this("Ekspedisi Nusantara", "Kendari", 10000, 100);
    }

    public KantorEkspedisi(String nama, String alamat,
                           double tarif, int kapasitas) {
        this.namaKantor = nama;
        this.alamat = alamat;
        this.tarif = tarif > 0 ? tarif : 10000;
        this.kapasitas = kapasitas > 0 ? kapasitas : 100;

        daftarPaket = new ArrayList<>();
        daftarPengirim = new ArrayList<>();
        daftarPengiriman = new ArrayList<>();
        riwayat = new RiwayatStack(3);
    }

    public boolean tambahPaket(Paket paket) {
        if (paket == null || !paket.validasiBerat()) {
            return false;
        }

        if (daftarPaket.size() >= kapasitas) {
            return false;
        }

        if (cariPaket(paket.getKode()) != null) {
            return false;
        }

        daftarPaket.add(paket);
        return true;
    }

    public boolean tambahPengirim(Pengirim pengirim) {
        if (pengirim == null || !pengirim.validasiData()) {
            return false;
        }

        if (daftarPengirim.size() >= kapasitas) {
            return false;
        }

        if (cariPengirim(pengirim.getIdPengirim()) != null) {
            return false;
        }

        daftarPengirim.add(pengirim);
        return true;
    }

    public Paket cariPaket(String kode) {
        if (kode == null || kode.trim().isEmpty()) {
            return null;
        }

        for (Paket p : daftarPaket) {
            if (p.getKode().equalsIgnoreCase(kode.trim())) {
                return p;
            }
        }

        return null;
    }

    public Pengirim cariPengirim(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        for (Pengirim p : daftarPengirim) {
            if (p.getIdPengirim().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }

        return null;
    }

    public boolean buatPengiriman(String id, String tanggal,
                                  String kode, String idPengirim) {
        if (id == null || id.trim().isEmpty()
                || tanggal == null || tanggal.trim().isEmpty()) {
            System.out.println("ID dan tanggal tidak boleh kosong.");
            return false;
        }

        if (cariPengiriman(id) != null) {
            System.out.println("ID pengiriman sudah digunakan.");
            return false;
        }

        if (riwayat.isFull()) {
            System.out.println("Stack penuh. Pengiriman tidak dapat ditambahkan.");
            return false;
        }

        Paket paket = cariPaket(kode);
        Pengirim pengirim = cariPengirim(idPengirim);

        if (paket == null || pengirim == null) {
            System.out.println("Paket atau pengirim tidak ditemukan.");
            return false;
        }

        // Paket yang belum selesai tidak boleh dikirim ulang.
        if (!paket.getStatus().equals("Dikemas")) {
            System.out.println("Paket tidak tersedia untuk pengiriman baru.");
            return false;
        }

        paket.hitungOngkir(tarif);

        Pengiriman baru =
                new Pengiriman(id, tanggal, paket, pengirim);

        if (!baru.validasiData()) {
            paket.resetPengiriman();
            return false;
        }

        if (!riwayat.push(baru)) {
            paket.resetPengiriman();
            return false;
        }

        daftarPengiriman.add(baru);
        baru.prosesPengiriman();

        return true;
    }

    public Pengiriman cariPengiriman(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        for (Pengiriman p : daftarPengiriman) {
            if (p.getIdPengiriman().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }

        return null;
    }

    public boolean ubahStatus(String kode, String statusBaru) {
        Paket paket = cariPaket(kode);

        if (paket == null) {
            System.out.println("Paket tidak ditemukan.");
            return false;
        }

        for (Pengiriman p : daftarPengiriman) {
            if (p.getPaket() == paket
                    && !p.getStatus().equals("Dibatalkan")) {
                return p.ubahStatus(statusBaru);
            }
        }

        return false;
    }

    // Membatalkan transaksi pengiriman terakhir
    public boolean batalkanPengirimanTerakhir() {
        if (riwayat.isEmpty()) {
            System.out.println("Tidak ada pengiriman untuk dibatalkan.");
            return false;
        }

        Pengiriman terakhir = riwayat.pop();

        if (terakhir == null) {
            return false;
        }

        terakhir.batalkanPengiriman();
        daftarPengiriman.remove(terakhir);

        System.out.println("Pengiriman "
                + terakhir.getIdPengiriman()
                + " berhasil dibatalkan.");

        return true;
    }

    public void tampilkanPaket() {
        if (daftarPaket.isEmpty()) {
            System.out.println("Belum ada data paket.");
            return;
        }

        for (Paket p : daftarPaket) {
            System.out.println("----------------------");
            p.tampilkanInfo();
        }
    }

    public void tampilkanPengirim() {
        if (daftarPengirim.isEmpty()) {
            System.out.println("Belum ada data pengirim.");
            return;
        }

        for (Pengirim p : daftarPengirim) {
            System.out.println("----------------------");
            p.tampilkanInfo();
        }
    }

    public void tampilkanPengiriman() {
        if (daftarPengiriman.isEmpty()) {
            System.out.println("Belum ada data pengiriman.");
            return;
        }

        for (Pengiriman p : daftarPengiriman) {
            System.out.println("----------------------");
            p.tampilkanInfo();
        }
    }

    public void tampilkanLaporan() {
        double totalOngkir = 0;

        for (Pengiriman p : daftarPengiriman) {
            totalOngkir += p.hitungBiaya();
        }

        System.out.println("\n=== LAPORAN EKSPEDISI ===");
        System.out.println("Nama Kantor       : " + namaKantor);
        System.out.println("Alamat            : " + alamat);
        System.out.println("Jumlah Paket      : " + daftarPaket.size());
        System.out.println("Jumlah Pengirim   : " + daftarPengirim.size());
        System.out.println("Jumlah Pengiriman : " + daftarPengiriman.size());
        System.out.println("Total Ongkir      : Rp" + totalOngkir);
        System.out.println("Isi Stack         : " + riwayat.size());
    }

    public RiwayatStack getRiwayat() {
        return riwayat;
    }
}
