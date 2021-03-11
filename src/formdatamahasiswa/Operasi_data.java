/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formdatamahasiswa;

/**
 *
 * @author SAPTO
 */

import java.io.*;//import untuk library buffered
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;
import java.nio.file.Files;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class Operasi_data {
    
    public static void listData() throws IOException{
        FileReader fileInput;
        BufferedReader inputBuffer;

        try {
            fileInput = new FileReader("mahasiswa.txt");
            inputBuffer = new BufferedReader(fileInput);
        } catch (Exception e){
            System.err.println("Database Tidak ditemukan/kosong");
            System.err.println("Silahkan tambah data terlebih dahulu");
            tambahData();
            return;
        }

        System.out.println("\n| No |\tNIM   |\tNAMA               |\tPRODI       |\tRUANG |\tNILAI |\tGRADE");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        String data = inputBuffer.readLine();
        int nomorData = 0;
        while(data != null) {
            nomorData++;
            // untuk memanggil data perbaris dan dipisahkan dari dengan ,dari mahasiswa.txt
            StringTokenizer ambilData = new StringTokenizer(data, ",");//tanda , adalah tanda untuk pemisah

            System.out.printf("| %2d ", nomorData);
            System.out.printf("|\t%s ", ambilData.nextToken());//nim
            System.out.printf("|\t%-18s ", ambilData.nextToken());//nama
            System.out.printf("|\t%-10s ", ambilData.nextToken());//prodi
            System.out.printf("|\t%-5s ", ambilData.nextToken());//kelas
            String nilai = ambilData.nextToken();//nilai
            int angka = Integer.parseInt(nilai);
            //untuk mendapatkan nilai grade
            if (angka>=80){
                System.out.printf("|\t%-6s",angka);
                System.out.printf("|\t%-6s","A");
            }else if(angka>=70 && angka<80){
                System.out.printf("|\t%-6s",angka);
                System.out.printf("|\t%-6s","B");
            }else if(angka>=50 && angka<70){
                System.out.printf("|\t%-6s",angka);
                System.out.printf("|\t%-6s","C");
            }else if(angka>=30 && angka<50){
                System.out.printf("|\t%-6s",angka);
                System.out.printf("|\t%-6s","D");
            }else{
                System.out.printf("|\t%-6s",angka);
                System.out.printf("|\t%-6s","E");
            }
            System.out.print("\n");

            data = inputBuffer.readLine();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }
    
    public static void tambahData() throws IOException{
        FileWriter fileOutput = new FileWriter("mahasiswa.txt",true);
        BufferedWriter outputBuffer = new BufferedWriter(fileOutput);
        
        Scanner userInput = new Scanner(System.in);
        String nim,nama,prodi,kelas,nilai;
        do{
            System.out.print("Masukkan NIM [tidak lebih dari 5 karakter] : ");
            nim = userInput.nextLine();
            }while(nim.length()<5||nim.length()>6);
        System.out.print("Masukkan Nama  : ");
        nama = userInput.nextLine();
        System.out.print("Masukkan Prodi : ");
        prodi = userInput.nextLine();
        System.out.print("Masukkan Kelas : ");
        kelas = userInput.nextLine();
        System.out.print("Masukkan Nilai : ");
        nilai = userInput.nextLine();
        
        String[] kataKunci = {nim+","+nama};
        //System.out.println(Arrays.toString(kataKunci));
        boolean key = CekString(kataKunci,false);
        
        if(!key){
        System.out.println("\nData yang akan anda masukan adalah");
            System.out.println("----------------------------------------");
            System.out.println("NIM   : " + nim);
            System.out.println("Nama  : " + nama);
            System.out.println("Prodi : " + prodi);
            System.out.println("Kelas : " + kelas);
            System.out.println("Nilai : " + nilai);
            boolean tambah = getYesorNo("Apakah anda ingin menambahkan data tersebut ? ");
            
            if(tambah){
                outputBuffer.write(nim+","+nama+","+prodi+","+kelas+","+nilai);
                outputBuffer.newLine();
                outputBuffer.flush();
            }
        }else {
            System.out.println("Data yang anda masukan sudah ada, silahkan untuk memasukkan data ulang");
            System.out.println("-------------------------------------------------------");
            tambahData();
        }
        outputBuffer.close();
    }
    
    public static void ubahData() throws IOException{
        File dataku = new File("mahasiswa.txt");
        FileReader fileInput = new FileReader(dataku);
        BufferedReader inputBuffer = new BufferedReader(fileInput);

        // kita buat database sementara
        File newDatabase = new File("newMahasiswa.txt");
        FileWriter fileOutput = new FileWriter(newDatabase);
        BufferedWriter outputBuffer = new BufferedWriter(fileOutput);

        System.out.println("===LIST DATA MAHASISWA===");
        listData();
        
        Scanner userInput = new Scanner(System.in);
        System.out.print("Masukkan Nomor data yang akan di ubah = ");
        int numUbah = userInput.nextInt();
        //menampilkan data yang akan di ubah
        String data = inputBuffer.readLine();
        int noData = 0;
        
        while (data != null){
            noData++;

            StringTokenizer st = new StringTokenizer(data,",");

            // tampilkan entrycounts == updateNum
            if (numUbah== noData){
                System.out.println("\nData yang ingin anda update adalah:");
                System.out.println("---------------------------------------");
                System.out.println("NIM     : " + st.nextToken());
                System.out.println("Nama    : " + st.nextToken());
                System.out.println("Prodi   : " + st.nextToken());
                System.out.println("kelas   : " + st.nextToken());
                System.out.println("nilai   : " + st.nextToken());

                // update data

                // mengambil input dari user
                String[] fieldData = {"nim","nama","prodi","kelas","nilai"};
                String[] dataBaru = new String[5];

                st = new StringTokenizer(data,",");
                for(int i=0;i<fieldData.length;i++){
                //melakukan perulangan pada setiap nilai yang ada pada fieldData
                    boolean isUpdate = getYesorNo("Apakah anda ingin merubah " + fieldData[i]);
                    String dataAwal= st.nextToken();
                    if(isUpdate){
                        userInput = new Scanner(System.in);
                        System.out.print("Masukkan " + fieldData[i]+" Baru : ");
                        dataBaru[i] = userInput.nextLine();
                        
                    }else{
                        dataBaru[i] = dataAwal;
                    }
                }
                
                //Menampilkan data yang baru
                st = new StringTokenizer(data,",");
                System.out.println("\nData yang Telah anda update adalah:");
                System.out.println("---------------------------------------");
                System.out.println("NIM     : " + st.nextToken()+" -> "+dataBaru[0]);
                System.out.println("Nama    : " + st.nextToken()+" -> "+dataBaru[1]);
                System.out.println("Prodi   : " + st.nextToken()+" -> "+dataBaru[2]);
                System.out.println("kelas   : " + st.nextToken()+" -> "+dataBaru[3]);
                System.out.println("nilai   : " + st.nextToken()+" -> "+dataBaru[4]);
                
                boolean isUpdate = getYesorNo("Apakah anda ingin mengubah data tersebut ? ");
                if(isUpdate){
                    boolean key = CekString(dataBaru, false);
                    if(key){
                        System.out.println("Data tersebut sudah ada");
                        outputBuffer.write(data);
                    }else{
                        String nim = dataBaru[0];
                        String nama = dataBaru[1];
                        String prodi = dataBaru[2];
                        String kelas = dataBaru[3];
                        String nilai = dataBaru[4];
                        //Menulis data ke dalam database
                        outputBuffer.write(nim + "," + nama + ","+ prodi +"," + kelas + ","+nilai);
                        System.out.println("Data berhasil di Ubah");
                    }
                }else{
                    outputBuffer.write(data);
                }
            }else{
                outputBuffer.write(data);
            }
            outputBuffer.newLine();
            
            data = inputBuffer.readLine();
        }
        outputBuffer.flush();
        outputBuffer.close();
        fileOutput.close();
        inputBuffer.close();
        fileInput.close();
        
        System.gc();
        
        dataku.delete();
        newDatabase.renameTo(new File("mahasiswa.txt"));
    }
    
    public static void hapusData()throws IOException{
        File dataku = new File("mahasiswa.txt");
        FileReader fileInput = new FileReader(dataku);
        BufferedReader inputBuffer = new BufferedReader(fileInput);

        // kita buat database sementara
        File newDatabase = new File("newMahasiswa.txt");
        FileWriter fileOutput = new FileWriter(newDatabase);
        BufferedWriter outputBuffer = new BufferedWriter(fileOutput);

        System.out.println("==== LIST DATA MAHASISWA====");
        listData();
        
        Scanner userInput = new Scanner(System.in);
        System.out.print("Masukkan nomor data mahasiswa yang akan di hapus : ");
        int numHapus = userInput.nextInt();
        
        boolean isFound = false;
        int noData=0;
        
        String data = inputBuffer.readLine();
        
        while(data != null){
            noData++;
            boolean deleteData = false;
            
             StringTokenizer st = new StringTokenizer(data,",");

            // tampilkan entrycounts == updateNum
            if (numHapus== noData){
                System.out.println("\nData yang ingin anda Hapus adalah:");
                System.out.println("---------------------------------------");
                System.out.println("NIM      : " + st.nextToken());
                System.out.println("Nama     : " + st.nextToken());
                System.out.println("Prodi    : " + st.nextToken());
                System.out.println("kelas    : " + st.nextToken());
                System.out.println("nilai    : " + st.nextToken());
            
                deleteData= getYesorNo("Apakah anda ingin menghapus data ini ? ");
                isFound = true;
            }
            if(deleteData){
                System.out.println("Data Berhasil Dihapus");
            }else{
                outputBuffer.write(data);
                outputBuffer.newLine();
            }
            
            data = inputBuffer.readLine();
        }
        
        if(!isFound){
            System.err.println("Data tidak ditemukan");
        }
        outputBuffer.flush();
        outputBuffer.close();
        fileOutput.close();
        inputBuffer.close();
        fileInput.close();
        
        System.gc();
        
        dataku.delete();
        newDatabase.renameTo(new File("mahasiswa.txt"));
        
    }
    
    public static void cariData() throws IOException{//method untuk mencari sebuah data
        try{
            File data = new File("mahasiswa.txt");
        }catch(Exception x){
            System.err.println("Database"+x+" tidak ditemukan/kosong");
            System.err.println("Silahkan tambahkan data terlebih dahulu");
        return;
        }
        
        Scanner userInput = new Scanner(System.in);
        System.out.print("Silahkan masukkan kata kunci : ");
        String cari = userInput.nextLine();
        System.out.println("Mencari kata = "+cari+" di Database Mahasiswa");
        String[]kataKunci = cari.split("\\s+");
        
        CekString(kataKunci,true);
        
    }
    
    public static boolean CekString(String[] kataKunci, boolean isDisplay) throws IOException{
        FileReader inputFile = new FileReader("mahasiswa.txt");
        BufferedReader inputBuffer = new BufferedReader(inputFile);
        /*fungsinya untuk mengambil inputan/file dan menangani baca tulis ke media
        dalam bahasa java*/
        String data = inputBuffer.readLine();
        boolean key = true;
        int nomorData = 0;
        if(isDisplay){
            System.out.println("\n| No |\tNIM   |\tNAMA               |\tPRODI       |\tRUANG |\tNILAI |\tGRADE");
            System.out.println("----------------------------------------------------------------------------------------------------------");
        }
        while(data != null) {
            key=true;
            for(String keyword:kataKunci){
                key= key&&data.toLowerCase().contains(keyword.toLowerCase());
            }
            
            if(key){
                if(isDisplay){
                    nomorData++;
                    StringTokenizer ambilData = new StringTokenizer(data, ",");//tanda , adalah tanda untuk pemisah

                    System.out.printf("| %2d ", nomorData);
                    System.out.printf("|\t%s ", ambilData.nextToken());//nim
                    System.out.printf("|\t%-18s ", ambilData.nextToken());//nama
                    System.out.printf("|\t%-10s ", ambilData.nextToken());//prodi
                    System.out.printf("|\t%-5s ", ambilData.nextToken());//kelas
                    String nilai = ambilData.nextToken();//nilai
                    int angka = Integer.parseInt(nilai);
                    // untuk menampilkan grade
                    if (angka>=80){
                        System.out.printf("|\t%-6s",angka);
                        System.out.printf("|\t%-6s","A");
                    }else if(angka>=70 && angka<80){
                        System.out.printf("|\t%-6s",angka);
                        System.out.printf("|\t%-6s","B");
                    }else if(angka>=50 && angka<70){
                        System.out.printf("|\t%-6s",angka);
                        System.out.printf("|\t%-6s","C");
                    }else if(angka>=30 && angka<50){
                        System.out.printf("|\t%-6s",angka);
                        System.out.printf("|\t%-6s","D");
                    }else{
                        System.out.printf("|\t%-6s",angka);
                        System.out.printf("|\t%-6s","E");
                    }
                    System.out.print("\n");
                }else {
                    break;
                }
            
            }
            data = inputBuffer.readLine();
        }
        if(isDisplay){
            System.out.println("----------------------------------------------------------------------------------------------------------");
        }
        return key;
    }
    
    static boolean getYesorNo(String message){
        Scanner userInput = new Scanner(System.in);
        System.out.print("\n"+message+"(y/n)? ");
        String pilihanUser = userInput.next();
        while(!pilihanUser.equalsIgnoreCase("y")&&!pilihanUser.equalsIgnoreCase("n")){
            System.err.println("Pilihan anda bukan y atau n");
            System.out.println("\n"+message+"(y/n)? ");
            pilihanUser = userInput.next();
        }
        return pilihanUser.equalsIgnoreCase("y");
    }
}
