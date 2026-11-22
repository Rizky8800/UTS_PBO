//Class untuk menyimpan dan mengatur data paket

public class Paket {
    private String kode;
    private String nama;
    private double berat;
    private String tujuan;
    private double ongkir;
    private String status;

    // Konstruktor default
    public Paket() {
        this.kode = "-";
        this.nama = "-";
        this.berat = 1;
        this.tujuan = "-";
        this.ongkir = 0;
        this.status = "Dikemas";
    }

    // Konstruktor berparameter
    public Paket(String kode, String nama, double berat, String tujuan) {
        this();
        setKode(kode);
        setNama(nama);
        setBerat(berat);
        setTujuan(tujuan);
    }

    public boolean validasiBerat() {
        return berat > 0;
    }

    public double hitungOngkir(double tarif) {
        if (validasiBerat() && tarif > 0) {
            ongkir = berat * tarif;
        }
        return ongkir;
    }

    // Status hanya boleh maju satu tahap
    public boolean ubahStatus(String statusBaru) {
        if (status.equals("Dikemas") && statusBaru.equals("Dikirim")) {
            status = statusBaru;
            return true;
        } else if (status.equals("Dikirim")
                && statusBaru.equals("Dalam Perjalanan")) {
            status = statusBaru;
            return true;
        } else if (status.equals("Dalam Perjalanan")
                && statusBaru.equals("Selesai")) {
            status = statusBaru;
            return true;
        }

        return false;
    }

    // Mengembalikan paket ke kondisi awal saat pengiriman dibatalkan
    public void resetPengiriman() {
        status = "Dikemas";
        ongkir = 0;
    }

    public void tampilkanInfo() {
        System.out.println("Kode Paket : " + kode);
        System.out.println("Nama Paket : " + nama);
        System.out.println("Berat      : " + berat + " kg");
        System.out.println("Tujuan     : " + tujuan);
        System.out.println("Ongkir     : Rp" + ongkir);
        System.out.println("Status     : " + status);
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getBerat() {
        return berat;
    }

    public String getTujuan() {
        return tujuan;
    }

    public double getOngkir() {
        return ongkir;
    }

    public String getStatus() {
        return status;
    }

    public void setKode(String kode) {
        if (kode != null && !kode.trim().isEmpty()) {
            this.kode = kode.trim();
        }
    }

    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama.trim();
        }
    }

    public void setBerat(double berat) {
        if (berat > 0 && Double.isFinite(berat)) {
            this.berat = berat;
        }
    }

    public void setTujuan(String tujuan) {
        if (tujuan != null && !tujuan.trim().isEmpty()) {
            this.tujuan = tujuan.trim();
        }
    }
}
