//Class untuk menyimpan data pengirim paket

public class Pengirim {
    private String idPengirim;
    private String nama;
    private String alamat;
    private String noTelepon;

    public Pengirim() {
        this.idPengirim = "-";
        this.nama = "-";
        this.alamat = "-";
        this.noTelepon = "-";
    }

    public Pengirim(String id, String nama, String alamat, String nomor) {
        this();
        setIdPengirim(id);
        setNama(nama);
        setAlamat(alamat);
        setNoTelepon(nomor);
    }

    public boolean validasiData() {
        return !idPengirim.equals("-")
                && !nama.equals("-")
                && !alamat.equals("-")
                && !noTelepon.equals("-");
    }

    public void ubahAlamat(String alamatBaru) {
        setAlamat(alamatBaru);
    }

    public void ubahNomorTelepon(String nomorBaru) {
        setNoTelepon(nomorBaru);
    }

    public void tampilkanInfo() {
        System.out.println("ID Pengirim : " + idPengirim);
        System.out.println("Nama        : " + nama);
        System.out.println("Alamat      : " + alamat);
        System.out.println("No Telepon  : " + noTelepon);
    }

    public String getIdPengirim() {
        return idPengirim;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setIdPengirim(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.idPengirim = id.trim();
        }
    }

    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama.trim();
        }
    }

    public void setAlamat(String alamat) {
        if (alamat != null && !alamat.trim().isEmpty()) {
            this.alamat = alamat.trim();
        }
    }

    public void setNoTelepon(String nomor) {
        if (nomor != null && !nomor.trim().isEmpty()) {
            this.noTelepon = nomor.trim();
        }
    }
}
