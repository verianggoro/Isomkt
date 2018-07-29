/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author cahndeso
 */
public class statusDao {
Connection connection = null;


String kd_proyek;
String tgl_spk;
String nm_perusahaan;
String no_spk;
String status;
String nm_proyek;
String nilai_kontrak;
String no_dokumen_statuspo;
String revisi;
String tanggal;
String nama_staf_market;
String nama_manager;

    public String getKd_proyek() {
        return kd_proyek;
    }

    public void setKd_proyek(String kd_proyek) {
        this.kd_proyek = kd_proyek;
    }

    public String getTgl_spk() {
        return tgl_spk;
    }

    public void setTgl_spk(String tgl_spk) {
        this.tgl_spk = tgl_spk;
    }

    public String getNm_perusahaan() {
        return nm_perusahaan;
    }

    public void setNm_perusahaan(String nm_perusahaan) {
        this.nm_perusahaan = nm_perusahaan;
    }

    public String getNo_spk() {
        return no_spk;
    }

    public void setNo_spk(String no_spk) {
        this.no_spk = no_spk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNm_proyek() {
        return nm_proyek;
    }

    public void setNm_proyek(String nm_proyek) {
        this.nm_proyek = nm_proyek;
    }

    public String getNilai_kontrak() {
        return nilai_kontrak;
    }

    public void setNilai_kontrak(String nilai_kontrak) {
        this.nilai_kontrak = nilai_kontrak;
    }

    public String getNo_dokumen_statuspo() {
        return no_dokumen_statuspo;
    }

    public void setNo_dokumen_statuspo(String no_dokumen_statuspo) {
        this.no_dokumen_statuspo = no_dokumen_statuspo;
    }

    public String getRevisi() {
        return revisi;
    }

    public void setRevisi(String revisi) {
        this.revisi = revisi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama_staf_market() {
        return nama_staf_market;
    }

    public void setNama_staf_market(String nama_staf_market) {
        this.nama_staf_market = nama_staf_market;
    }

    public String getNama_manager() {
        return nama_manager;
    }

    public void setNama_manager(String nama_manager) {
        this.nama_manager = nama_manager;
    }


    public Connection getConnection() {
        String url="jdbc:mysql://localhost:3306/db_kkp";
         String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Class tidak ditemukan"+ex.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, "root","");
            //JOptionPane.showMessageDialog(null,"Selamat datang di Aplikasi TK Kamis");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Koneksi Gagal"+ex.getMessage());
        }
        return connection;
    }

    

    
    
    public void insertData(){
    String insertSQL = "INSERT INTO tb_statusPO VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"; //query untuk insert nilai variael (?) akan diisi oleh perintah preparedstatement 
    PreparedStatement statement = null; // membuat objek statement dari kelas preparedstatement dg inisialisasi null

        try {
            statement = connection.prepareStatement(insertSQL); 
            //statement ini mengkoneksikan  preparedstatement yg punya parameter perintah query sql memasukan data dgn tipedata string ke table mahasiswa di database
            //mengisi  data sesuai urutan values
            statement.setString(1,kd_proyek); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            statement.setString(2,tgl_spk);
            statement.setString(3,nm_perusahaan);
            statement.setString(4,no_spk);
            statement.setString(5,status);
            statement.setString(6,nm_proyek); 
            statement.setString(7,nilai_kontrak);
            statement.setString(8,no_dokumen_statuspo); //1 = indeks parameter =nim .memberi nilai nim dg tipe data string dg peintah statement
            statement.setString(9,revisi);
            statement.setString(10,tanggal);
            statement.setString(11,nama_staf_market);
            statement.setString(12,nama_manager);
            
            
            statement.executeUpdate(); // mengekskusi peintah insert 
            JOptionPane.showMessageDialog(null, "Data Sukses disimpan"); //menampilkan pesan bahwa data berhasil di simpan
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal disimpan"+ex.getMessage());//menampilkan pesan jika data gagal di simpan
        }finally{
            try {
                statement.close(); //mengclose statement yg sudah dibuka
            } catch (SQLException ex) {
                Logger.getLogger(dataFormDao.class.getName()).log(Level.SEVERE, null, ex);
            } //smpai dsini selesai mbuat method insertData(); utk menguji buka kelas main dan masukan data dan panggil method ini //smpai dsini selesai mbuat method insertData(); utk menguji buka kelas main dan masukan data dan panggil method ini
}
}

    public void updateData(){
    String updateSQL = "UPDATE tb_statusPO SET tgl_spk=?,nm_perusahaan=?,no_spk=?,status=?,nm_proyek=?,nilai_kontrak=?,"
            + "no_dokumen_statuspo=?,revisi=?,tanggal=?,nama_staf_market=?,nama_manager=?  WHERE kd_proyek=?"; //query utk update dg nilai var ? krn akn disi dg preparedstatement
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);

            statement.setString(1,tgl_spk); // sesuaikan dengan urutan di query update
            statement.setString(2,nm_perusahaan);
            statement.setString(3,no_spk);
            statement.setString(4,status);
            statement.setString(5,nm_proyek);
            statement.setString(6,nilai_kontrak);
            statement.setString(7,no_dokumen_statuspo);
            statement.setString(8,revisi);
            statement.setString(9,tanggal);
            statement.setString(10,nama_staf_market);
            statement.setString(11,nama_manager);
            statement.setString(12,kd_proyek);
            statement.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Data Sukses diupdate");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diupdate"+ex.getMessage());
        }finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(dataFormDao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}//sampai disini methode updateData(); selesai utk uji ke class main kmudian masukkan data yg akan diupdate lalu panggil metod ini

     public void deleteData(){
    String updateSQL = "DELETE FROM tb_statusPO WHERE kd_proyek=?"; //query sql delete 
    PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);
            statement.setString(1, kd_proyek); //hanya satu variabel yaitu nim sesuai dengan query DELETE sql
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Sukses dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal dihapus"+ex.getMessage());
        }finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(dataFormDao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}
    
}
