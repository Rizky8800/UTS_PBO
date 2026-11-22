//Class untuk mengatur transaksi pengiriman

public class Pengiriman {
    private String idPengiriman;
    private String tanggal;
    private String status;
    private double biaya;
    private Paket paket;
    private Pengirim pengirim;

    public Pengiriman() {
        this.idPengiriman = "-";
        this.tanggal = "-";
        this.status = "Belum diproses";
        this.biaya = 0;
        this.paket = null;
        this.pengirim = null;
    }

    public Pengiriman(String id, String tanggal,
                      Paket paket, Pengirim pengirim) {
        this();
        setIdPengiriman(id);
        setTanggal(tanggal);
        this.paket = paket;
        this.pengirim = pengirim;

        if (paket != null) {
            this.biaya = paket.getOngkir();
            this.status = paket.getStatus();
        }
    }

    public boolean validasiData() {
        return !idPengiriman.equals("-")
                && !tanggal.equals("-")
                && paket != null
                && pengirim != null;
    }

    public void prosesPengiriman() {
        if (validasiData()) {
            System.out.println("Pengiriman berhasil diproses.");
        } else {
            System.out.println("Data pengiriman belum lengkap.");
        }
    }

    public boolean ubahStatus(String statusBaru) {
        if (paket != null && paket.ubahStatus(statusBaru)) {
            status = paket.getStatus();
            return true;
        }

        return false;
    }

    public double hitungBiaya() {
        if (paket != null) {
            biaya = paket.getOngkir();
        }
        return biaya;
    }

    public void batalkanPengiriman() {
        if (paket != null) {
            paket.resetPengiriman();
        }

        status = "Dibatalkan";
        biaya = 0;
    }

    public void tampilkanInfo() {
        System.out.println("ID Pengiriman : " + idPengiriman);
        System.out.println("Tanggal       : " + tanggal);

        if (pengirim != null) {
            System.out.println("Pengirim      : " + pengirim.getNama());
        }

        if (paket != null) {
            System.out.println("Kode Paket    : " + paket.getKode());
        }

        System.out.println("Status        : " + status);
        System.out.println("Biaya         : Rp" + biaya);
    }

    public String getIdPengiriman() {
        return idPengiriman;
    }

    public String getStatus() {
        return status;
    }

    public Paket getPaket() {
        return paket;
    }

    public void setIdPengiriman(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.idPengiriman = id.trim();
        }
    }

    public void setTanggal(String tanggal) {
        if (tanggal != null && !tanggal.trim().isEmpty()) {
            this.tanggal = tanggal.trim();
        }
    }
}
