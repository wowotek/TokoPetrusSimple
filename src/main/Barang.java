package main;

public class Barang {
    private final String namaBarang;
    private final String kodeBarang;
    public int hargaBarang;
    
    Barang(String nama, String kode, int harga){
        this.namaBarang  = nama;
        this.kodeBarang  = kode;
        this.hargaBarang = harga;
    }
    
    public String getNamaBarang(){
        return namaBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }
}
