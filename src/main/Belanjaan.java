package main;

public class Belanjaan {
    private final String kodeBarang;
    private final int jumlahBarang;
    
    Belanjaan(String kodeBarang, int jumlahBarang){
        this.kodeBarang = kodeBarang;
        this.jumlahBarang = jumlahBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public int getJumlahBarang() {
        return jumlahBarang;
    }
}
