package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int MAX_BELANJAAN = 100; // maximum belanja 100 barang sekali transaksi
    private static Barang daftarStok[];
    
    public static void main(String[] args){
        inisialisasi();
        while(true){
            System.out.println("SELAMAT DATANG DI TOKO PETRUS\nMain Menu\n");
            System.out.println("1. Daftar Barang");
            System.out.println("2. Transaksi");
            System.out.print("0. Keluar\n\n>> ");
            int inputUser = new Scanner(System.in).nextInt();
            if(cekInputInt(new int[]{1, 2, 0}, inputUser)){
                switch(inputUser){
                    default: break;
                    case 1:
                        printDaftarBarang();
                        break;
                    case 2:
                        menuTransaksi();
                        break;
                    case 0:
                        return;
                }
            }
        }
    }
    
    private static boolean cekInputInt(int[] inputBoleh, int input){
        for(int i: inputBoleh)
            if(i == input)
                return true;
        
        return false;
    }
    private static void inisialisasi(){
        daftarStok = new Barang[]{
            new Barang("Nama Barang", "Kode Barang", 0),
            new Barang("Gudang Garam Filter", "GGF", 17000),
            new Barang("Tisu Wajah", "TSW", 11000),
            new Barang("Tisu Basah", "TSB", 17000),
            new Barang("Teh Pucuk Harum 135ml", "TPC", 4000),
            new Barang("Potabi", "PTB", 16500),
            new Barang("Sari Roti", "SR", 6000)
        };
    }
    
    private static void printDaftarBarang(){
        int maxNama = 0;
        int maxKode = 0;
        int iter = 0;
        
        //cek maximum nama dan kode
        for(Barang i: daftarStok){
            if(i.getNamaBarang().length() >= maxNama)
                maxNama = i.getNamaBarang().length();
            
            if(i.getKodeBarang().length() >= maxKode)
                maxKode = i.getKodeBarang().length();
        }
        
        //offset
        maxNama += 3;
        maxKode += 3;
        
        System.out.println("Daftar Barang :\n");
        for(Barang i: daftarStok){
            if(iter == 0){
                System.out.print(i.getNamaBarang());
                for(int j=0; j<maxNama - i.getNamaBarang().length(); j++){
                    System.out.print(" ");
                }
                System.out.print("|   " + i.getKodeBarang());
                for(int j=0; j<maxKode - i.getKodeBarang().length(); j++){
                    System.out.print(" ");
                }
                System.out.println("|   Harga Barang");
                for(int j=0; j<maxNama + maxKode + 20; j++){
                    System.out.print("-");
                }
                System.out.println("");
                iter++;
                continue;
            }
            
            System.out.print(i.getNamaBarang());
            for(int j=0; j<maxNama - i.getNamaBarang().length(); j++){
                System.out.print(" ");
            }
            System.out.print("|   " + i.getKodeBarang());
            for(int j=0; j<maxKode - i.getKodeBarang().length(); j++){
                System.out.print(" ");
            }
            System.out.println("|   Rp " + i.hargaBarang + ",-");
        }
        System.out.println("\n");
    }
    
    private static void menuTransaksi(){
        ArrayList<Belanjaan> daftarBelanjaan = new ArrayList<>();
        int totalHarga = 0;
        
        while(true){
            Belanjaan _temp = menuBelanja();
            if(_temp.getKodeBarang().equals("0001")){
                break;
            } else if (_temp.getKodeBarang().equals("0000")) {
                return;
            } else {
                daftarBelanjaan.add(_temp);
            }
        }
        System.out.println("\n");
        System.out.println("Daftar Belanjaan anda : ");
        for(Belanjaan i: daftarBelanjaan){
            Barang x = getBarang(i.getKodeBarang());
            System.out.print(x.getNamaBarang());
            System.out.print(" @ Rp " + x.hargaBarang + ",- ");
            System.out.print("x " + i.getJumlahBarang() + "   =   Rp");
            System.out.println(x.hargaBarang * i.getJumlahBarang() + ",-");
            totalHarga += x.hargaBarang * i.getJumlahBarang();
        }
        System.out.println("Total Harga : Rp " + totalHarga + ",-");
        
        while(true){
            System.out.print("Masukkan Uang Pembeli [0 = batal] : Rp ");
            int uangPembeli = new Scanner(System.in).nextInt();
            
            if(uangPembeli == 0){
                return;
            } else if (uangPembeli > totalHarga) {
                System.out.println("Uang Kembalian : Rp " + (uangPembeli - totalHarga) + ",-");
                break;
            } else if (uangPembeli == totalHarga){
                break;
            } else {
                System.out.println("Uang Anda tidak Cukup !");
                System.out.println("Kekurangan : Rp " + (totalHarga - uangPembeli) + ",-");
            }
        }
        System.out.println("Terima Kasih Telah Berbelanja di TOKO PETRUS !");
        System.out.println("\n");
    }
    
    private static Barang getBarang(String kodeBarang){
        for(Barang i: daftarStok)
            if(kodeBarang.equals(i.getKodeBarang()))
                return i;
        return null;
    }
    
    private static boolean cekKetersediaanBarang(String kodeBarang){
        for(Barang i: daftarStok)
            if(kodeBarang.equals(i.getKodeBarang()))
                return true;
        
        return false;
    }
    
    private static Belanjaan menuBelanja(){
        String kodeBarang;
        int jumlahBarang;
        
        while(true){
            System.out.print("['0000' = Kembali, '0001'] = Pembayaran\nMasukkan Kode Barang : ");
            String inputUser = new Scanner(System.in).nextLine();
            
            if(inputUser.equals("0000")){
                return new Belanjaan("0000", 0);                               // Kembali ke menu
            } else if (inputUser.equals("0001")) {
                return new Belanjaan("0001", 0);
            } else {
                if(cekKetersediaanBarang(inputUser)){
                    kodeBarang = inputUser;
                    break;
                } else {
                    System.out.println("Barang tidak ter-register");
                }
            }
        }
        while(true){
            System.out.print("['0' = kembali]\nJumlah Barang yang di beli : ");
            int inputJumlahBarang = new Scanner(System.in).nextInt();
            
            if(inputJumlahBarang == 0){
                return menuBelanja();                                           // Ulangi
            } else {
                jumlahBarang = inputJumlahBarang;
                break;
            }
        }
        System.out.println("\n");
        return new Belanjaan(kodeBarang, jumlahBarang);
    }
}