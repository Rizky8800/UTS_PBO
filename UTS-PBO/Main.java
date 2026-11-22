import java.util.Scanner;

//Class Main untuk menjalankan program ekspedisi

public class Main {
    static Scanner input = new Scanner(System.in);

    static KantorEkspedisi kantor =
            new KantorEkspedisi("Ekspedisi Nusantara",
                    "Kendari", 10000, 100);

    public static void main(String[] args) {
        int pilih;

        do {
            System.out.println("\n===== SISTEM EKSPEDISI =====");
            System.out.println("1. Tambah Pengirim");
            System.out.println("2. Tambah Paket");
            System.out.println("3. Buat Pengiriman");
            System.out.println("4. Tampilkan Paket");
            System.out.println("5. Tampilkan Pengirim");
            System.out.println("6. Tampilkan Pengiriman");
            System.out.println("7. Cari Paket");
            System.out.println("8. Ubah Status Paket");
            System.out.println("9. Lihat Riwayat Stack");
            System.out.println("10. Batalkan Pengiriman Terakhir");
            System.out.println("11. Laporan");
            System.out.println("0. Keluar");

            pilih = bacaAngka("Pilih menu: ");

            switch (pilih) {
                case 1:
                    tambahPengirim();
                    break;

                case 2:
                    tambahPaket();
                    break;

                case 3:
                    buatPengiriman();
                    break;

                case 4:
                    kantor.tampilkanPaket();
                    break;

                case 5:
                    kantor.tampilkanPengirim();
                    break;

                case 6:
                    kantor.tampilkanPengiriman();
                    break;

                case 7:
                    cariPaket();
                    break;

                case 8:
                    ubahStatus();
                    break;

                case 9:
                    kantor.getRiwayat().tampilkan();
                    break;

                case 10:
                    kantor.batalkanPengirimanTerakhir();
                    break;

                case 11:
                    kantor.tampilkanLaporan();
                    break;

                case 0:
                    System.out.println("Terima kasih.");
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }

        } while (pilih != 0);
    }

    // Membaca input angka bulat
    static int bacaAngka(String pesan) {
        while (true) {
            System.out.print(pesan);

            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat.");
            }
        }
    }

    // Membaca input berat paket
    static double bacaBerat() {
        while (true) {
            System.out.print("Berat paket (kg): ");

            try {
                double berat = Double.parseDouble(input.nextLine());

                if (berat > 0 && Double.isFinite(berat)) {
                    return berat;
                }
            } catch (NumberFormatException e) {
                // Input akan diminta kembali.
            }

            System.out.println("Berat harus berupa angka lebih dari 0.");
        }
    }

    // Membaca teks dan menolak input kosong
    static String bacaTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();

            if (!teks.isEmpty()) {
                return teks;
            }

            System.out.println("Data tidak boleh kosong.");
        }
    }

    static void tambahPengirim() {
        System.out.println("\n=== TAMBAH PENGIRIM ===");

        String id = bacaTeks("ID Pengirim: ");
        String nama = bacaTeks("Nama: ");
        String alamat = bacaTeks("Alamat: ");
        String nomor = bacaTeks("Nomor Telepon: ");

        Pengirim pengirim =
                new Pengirim(id, nama, alamat, nomor);

        if (kantor.tambahPengirim(pengirim)) {
            System.out.println("Pengirim berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan pengirim. "
                    + "Periksa ID atau kapasitas data.");
        }
    }

    static void tambahPaket() {
        System.out.println("\n=== TAMBAH PAKET ===");

        String kode = bacaTeks("Kode Paket: ");
        String nama = bacaTeks("Nama Paket: ");
        double berat = bacaBerat();
        String tujuan = bacaTeks("Tujuan: ");

        Paket paket = new Paket(kode, nama, berat, tujuan);

        if (kantor.tambahPaket(paket)) {
            System.out.println("Paket berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan paket. "
                    + "Kode mungkin sudah digunakan.");
        }
    }

    static void buatPengiriman() {
        System.out.println("\n=== BUAT PENGIRIMAN ===");

        String id = bacaTeks("ID Pengiriman: ");
        String tanggal = bacaTeks("Tanggal: ");
        String kode = bacaTeks("Kode Paket: ");
        String idPengirim = bacaTeks("ID Pengirim: ");

        kantor.buatPengiriman(id, tanggal, kode, idPengirim);
    }

    static void cariPaket() {
        System.out.println("\n=== CARI PAKET ===");

        String kode = bacaTeks("Masukkan kode paket: ");
        Paket paket = kantor.cariPaket(kode);

        if (paket != null) {
            paket.tampilkanInfo();
        } else {
            System.out.println("Paket tidak ditemukan.");
        }
    }

    static void ubahStatus() {
        System.out.println("\n=== UBAH STATUS PAKET ===");

        String kode = bacaTeks("Kode Paket: ");

        System.out.println("1. Dikirim");
        System.out.println("2. Dalam Perjalanan");
        System.out.println("3. Selesai");

        int pilih = bacaAngka("Pilih status: ");
        String status;

        if (pilih == 1) {
            status = "Dikirim";
        } else if (pilih == 2) {
            status = "Dalam Perjalanan";
        } else if (pilih == 3) {
            status = "Selesai";
        } else {
            System.out.println("Pilihan status tidak tersedia.");
            return;
        }

        if (kantor.ubahStatus(kode, status)) {
            System.out.println("Status berhasil diubah.");
        } else {
            System.out.println("Status tidak dapat diubah. "
                    + "Periksa urutan status paket.");
        }
    }
}
