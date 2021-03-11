package formdatamahasiswa;

import java.io.IOException;
import java.util.Scanner;

public class FormDataMahasiswa {
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner userInput = new Scanner(System.in);
    String pilihanUser;
    boolean looping = true;
    while(looping){
        
        System.out.println("\nDATABASE MAHASISWA");
        System.out.println("1. Lihat seluruh Data Mahasiswa");
        System.out.println("2. Cari data Mahasiswa");
        System.out.println("3. Tambah data Mahasiswa");
        System.out.println("4. Ubah data Mahasiswa");
        System.out.println("5. Hapus data Mahasiswa");
        System.out.print("Pilihan anda = ");
        pilihanUser = userInput.next();

            switch (pilihanUser){
                case "1":
                    System.out.println("\n===================");
                    System.out.println("List Data Mahasiswa");
                    System.out.println("=====================");
                    Operasi_data.listData();
                    
                break;
                case "2":
                    System.out.println("\n====================");
                    System.out.println("Search data Mahasiswa");
                    System.out.println("======================");
                    Operasi_data.cariData();
                    
                break;
                case "3":
                    System.out.println("\n====================");
                    System.out.println("Tambah Data Mahasiswa");
                    System.out.println("======================");
                    Operasi_data.tambahData();
                break;
                case "4":
                    System.out.println("\n====================");
                    System.out.println("Ubah Data Mahasiswa");
                    System.out.println("======================");
                    Operasi_data.ubahData();
                break;
                case "5":
                    System.out.println("\n====================");
                    System.out.println("Hapus data mahasiswa");
                    System.out.println("======================");
                    Operasi_data.hapusData();
                    
                break;
                default:
                    System.out.println("\ninput anda tidak ditemukan \nSilahkan pilih(1-5)");
            }
            looping = Operasi_data.getYesorNo("Apakah anda ingin melanjutkannya lagi ?");
        }
    
    }
    
}
