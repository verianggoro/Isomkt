
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cahndeso
 */
public class Keluhan extends javax.swing.JFrame {
 keluhanDao kd;
    /**
     * Creates new form Keluhan
     */
    public Keluhan() {
        kd = new keluhanDao();
        kd.getConnection();
        initComponents();
        setLocationRelativeTo(null);
        viewAll();
        tampil_combo();
    }
    
    public void tampil_combo()
    {
        try {
            
        Connection con = kd.getConnection();
        Statement stt = con.createStatement();
        String sql = "select no_dok_keluhan from dataform order by no_dok_keluhan asc";      // disini saya menampilkan NIM, anda dapat menampilkan
        ResultSet res = stt.executeQuery(sql);                                // yang anda ingin kan
        while(res.next()){
            Object[] ob = new Object[1];
            ob[0] = res.getString(1);
            cmb_noDOK.addItem((String) ob[0]);                                      // fungsi ini bertugas menampung isi dari database
        }
        res.close(); stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void tampil()
    {
        try {
        Connection con = kd.getConnection();
        Statement stt = con.createStatement();
        String sql = "select tanggal,revisi,nama_staf_market,nama_manager from dataform where no_dok_keluhan='"+cmb_noDOK.getSelectedItem()+"'";  
        ResultSet res = stt.executeQuery(sql);
        
        while(res.next()){
            Object[] ob = new Object[4];
            ob[0]=  res.getString(1);
            ob[1]= res.getString(2);
           ob[2]= res.getString(3);
           ob[3]= res.getString(4);
            
//            cmb_Revisi.setSelectedItem((String) ob[0]);
//            cmb_tgl_efektif.setSelectedItem((String) ob[1]);
//            cmb_staff.setSelectedItem((String) ob[2]);
//            cmb_manager.setSelectedItem((String) ob[3]);
            txt_tanggal_efektif.setText((String) ob[0]);
            txtrevisi.setText((String) ob[1]);
             txtStaff.setText((String) ob[2]);
            txtManager.setText((String) ob[3]);
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }              
    }
    
    public void viewAll(){
        
        try {
            
            Statement statement =kd.connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)FROM tb_keluhan");// memanggil Resulset dari library dg var resulset dg nilai query utk mengetahui jml baris yg ada di table
             
            resultSet.next(); //melihat baris berikutnya 
            int rows = resultSet.getInt(1); //akan berisi jml baris dari table db
            Object[]header = {"No Register","Nama Perusahaan","Tanggal","Nama PIC",
                "Isi Keluhan","Tindak Lanjut","Nama Proyek","No.Dokumen Keluhan","Tanggal Efektif","Revisi","Nama Staff","Nama Manager"};
            Object[][]data =new Object [rows][12]; // 7 adalah jml kolom . jml rows dikdeklarasikan 1
            resultSet = statement.executeQuery("SELECT * FROM tb_keluhan ORDER BY no_register");
            resultSet.beforeFirst();
            int i =0; //variable indeks
            while(resultSet.next()){ //selama reulset masin memiliki data dan while bernilai true maka memasukkan data dalam array data 
            data[i][0]=resultSet.getString(1);
            data[i][1]=resultSet.getString(2);
            data[i][2]=resultSet.getString(3);
            data[i][3]=resultSet.getString(4);
            data[i][4]=resultSet.getString(5);
            data[i][5]=resultSet.getString(6);
            data[i][6]=resultSet.getString(7);
            data[i][7]=resultSet.getString(8);
            data[i][8]=resultSet.getString(9);
            data[i][9]=resultSet.getString(10);
            data[i][10]=resultSet.getString(11);
            data[i][11]=resultSet.getString(12);
            i++;
        }
            DefaultTableModel tablemodel = new DefaultTableModel(data,header);// membuat kelas DefaultTableModel dari library dan objek kelas tablemodel
            //Tabel model berfungsi menampikan data dari table database ke jtabel(tabel java) form
            mytable.setModel(tablemodel); // memberikan nilai tableviw(jtabel) melalui objek kelas tablemodel yg sudah dibuat
            //utk bisa menampilkanya buatlah method viewALL() di construktor
        } catch (SQLException ex) {
            Logger.getLogger(keluhanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } //sampai sini pembuatan tablemodel yg ada dlm method viewALL selesai .data dari table db  sudah bisa dimasukan dalam jtable form
    
    // mthod ini digunakan utk menampilkan data dari jtable ke masing2 textfield(nim,nama,dll)
    protected void getSelectedData(int row){ //parameter (int row) utk memilih data dari baris mana yg akan  ditampilkan ke textfield
        txtno_register.setText(mytable.getValueAt(row,0).toString());// mmbri nilai textNim dri tableview dg cara mengambil nilai dari (indeks 0 = kolom ke 1) di table mahasisiwadb lalu mngbah Objek(array) kestring 
       txtNama_perusahaan.setText(mytable.getValueAt(row,1).toString());
        txt_tanggal.setText(mytable.getValueAt(row,2).toString());
        txtNama_pic.setText(mytable.getValueAt(row,3).toString());
        //txt//.setSelectedItem(mytable.getValueAt(row,4));
        txtKeluhan.setText(mytable.getValueAt(row,4).toString());
        txt_tindak_Lanjut.setText(mytable.getValueAt(row,5).toString());
        txt_nama_proyek.setText(mytable.getValueAt(row,6).toString());
        cmb_noDOK.setSelectedItem(mytable.getValueAt(row,7));// mmbri nilai textNim dri tableview dg cara mengambil nilai dari (indeks 0 = kolom ke 1) di table mahasisiwadb lalu mngbah Objek(array) kestring 
       txt_tanggal_efektif.setText(mytable.getValueAt(row,8).toString());
        txtrevisi.setText(mytable.getValueAt(row,9).toString());
        
        txtStaff.setText(mytable.getValueAt(row,10).toString());
        txtManager.setText(mytable.getValueAt(row,11).toString());
        
        //txtDocumentStatusPO.setSelectedItem(mytable.getValueAt(row,3));
        
    }//utk manggil method ini klk kanan  jtable>mouse clicked ketikkan kode getSelectedData(tableview.getSelectedRow());
    
    //membuat method insert untuk memasukan data dari masing2 komponen text field yg ada di  kolom ke objek mahasiswadao lalu ke table mahasiswa lalu ditampilkan ke jtable
    //method ini digunakan utk memfungsikan tombol jbuton yg diberi nama Save
    protected void insert(){ 
        kd.setNo_register(txtno_register.getText()); //memberikan nilai nama ke table data_siswa dari textfield Nim ke objek mahasiswadao
        kd.setNama_perusahaan(txtNama_perusahaan.getText());
        kd.setTanggal(txt_tanggal.getText());
        kd.setNama_pic(txtNama_pic.getText());
        //kd.setStatus(cmb_status.getSelectedItem().toString());
        kd.setIsi_keluhan(txtKeluhan.getText());
        kd.setTindak_lanjut(txt_tindak_Lanjut.getText());
        kd.setNama_proyek(txt_nama_proyek.getText());
        kd.setNo_dokumen_keluhan(cmb_noDOK.getSelectedItem().toString());
        kd.setTanggal_efektif(txt_tanggal_efektif.getText()); 
        kd.setRevisi(txtrevisi.getText()); 
        kd.setNama_staff(txtStaff.getText()); 
        kd.setNama_manager(txtManager.getText()); 
        
        
        kd.insertData(); //memanggil methode insertdataData yg sdh dibuat dimahasiswadao utk memasukan/save nilai dari masing2 textfield kedlm table mahasiwa db
        viewAll(); //memanggil metode viewALL yg berisi objek kelas tablemodel yg dpt menampilkan table mahasiswa db ke jtabel form
    }// method insert sudah jadi utk menggunakanya klik 2x pada tombol Save kemudian tulis kode insert();
    //utk mengujinya . silahkan di RUn masukan data di masing2 kolom  textfiel(nim, nama,dll) kemudian klik tombol Save maka data akan tersimpan
    
    protected void update(){ //method ini fungsinya utk merubah data langkah2 kurang lebih sama dg methode insert();
        kd.setNo_register(txtno_register.getText()); //memberikan nilai nama ke table data_siswa dari textfield Nim ke objek mahasiswadao
        kd.setNama_perusahaan(txtNama_perusahaan.getText());
        kd.setTanggal(txt_tanggal.getText());
        kd.setNama_pic(txtNama_pic.getText());
        kd.setIsi_keluhan(txtKeluhan.getText());
        kd.setTindak_lanjut(txt_tindak_Lanjut.getText());
        kd.setNama_proyek(txt_nama_proyek.getText());
        kd.setNo_dokumen_keluhan(cmb_noDOK.getSelectedItem().toString());
        kd.setTanggal_efektif(txt_tanggal_efektif.getText()); 
        kd.setRevisi(txtrevisi.getText()); 
        kd.setNama_staff(txtStaff.getText()); 
        kd.setNama_manager(txtManager.getText()); 
        
        kd.updateData(); //memanggil methode updateData() yg sdh dibuat di data_siswaDao utk merubah/Update nilai dari masing2 textfield kedlm table mahasiwa db
        viewAll();//memanggil metode viewALL yg berisi objek kelas tablemodel yg dpt menampilkan table mahasiswa db ke jtabel form
        //membuat method getSelectedData utk menampilkan nilai dari jtable ke masing2 textfield(nim,nama,dll) saat di click data dari jtabel 
    }// method update sudah jadi utk menggunakanya klik 2x pada tombol Update kemudian tulis kode update();
    //utk mengujinya . silahkan di RUn masukan data di masing2 kolom  textfiel(nim, nama,dll) kemudian klik tombol Save maka data akan tersimpan
    protected void delete(){
        kd.setNo_register(txtno_register.getText());      
        kd.deleteData(); //memanggil method ini utk menghapus data di table mahasiswa 
        viewAll(); //menampilkan data setelah dihapus ke jtable form
        reset();
    } //utk mgunakan method delete() klik 2x pd tombol Delete pada desain form lalu ketikkan kode Delete();
    // utk memunculkan pemberitahuan konfirmasi ketkkan kode if(JOptionPane.showConfirmDialog(null,"Apakah Anda yakin akan menghapus data ini?","Hapus data",1)==0) sebelum kode delete();
     
    public void reset(){
    
    
    txtno_register.setText("");
    txtNama_perusahaan.setText("");
    txt_tanggal.setText("");
    txtNama_pic.setText("");
    
    txtKeluhan.setText("");
    txt_tindak_Lanjut.setText("");
    txt_nama_proyek.setText("");
    cmb_noDOK.setSelectedIndex(0);
    txt_tanggal_efektif.setText("");
    txtrevisi.setText("");

    txtStaff.setText("");
    txtManager.setText("");
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtno_register = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNama_perusahaan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNama_pic = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmb_noDOK = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_nama_proyek = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        txt_tindak_Lanjut = new javax.swing.JTextField();
        txt_tanggal = new javax.swing.JTextField();
        txt_tanggal_efektif = new javax.swing.JTextField();
        txtrevisi = new javax.swing.JTextField();
        txtStaff = new javax.swing.JTextField();
        txtManager = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_Hapus = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mytable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKeluhan = new javax.swing.JTextArea();
        kembali = new javax.swing.JButton();
        date = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.jpg"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("PT TEHNIK BAYU MURNI");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("APLIKASI MONITORING PROSEDUR MUTU BAGIAN PEMASARAN ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addContainerGap(383, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel17)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel1.setText("Data keluhan pelanggan");

        jLabel2.setText("No. Register");

        jLabel3.setText("Tanggal");

        jLabel4.setText("Nama Perusahaan");

        jLabel5.setText("Nama PIC");

        jLabel6.setText("Isi Keluhan");

        jLabel7.setText("Tindak lanjut");

        jLabel8.setText("No. Doc");

        cmb_noDOK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "===PILIH===" }));
        cmb_noDOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_noDOKActionPerformed(evt);
            }
        });

        jLabel9.setText("Revisi");

        jLabel10.setText("Tanggal Efektif");

        jLabel11.setText("Staff Marketing");

        jLabel12.setText("Manager marketing");

        jLabel13.setText("Nama Proyek");

        jLabel14.setText("Cari berdasarkan");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("Cari");

        txtManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtManagerActionPerformed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_Hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_Hapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Hapus.setText("Hapus");
        btn_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HapusActionPerformed(evt);
            }
        });

        btn_ubah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_reset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        mytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        mytable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mytableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mytable);

        txtKeluhan.setColumns(20);
        txtKeluhan.setRows(5);
        jScrollPane2.setViewportView(txtKeluhan);

        kembali.setBackground(new java.awt.Color(0, 0, 255));
        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(124, 309, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNama_perusahaan, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                    .addComponent(txtno_register)
                                    .addComponent(txtNama_pic)
                                    .addComponent(txt_tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tindak_Lanjut)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_nama_proyek))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmb_noDOK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel14))
                            .addComponent(txt_tanggal_efektif, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtrevisi, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton5)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 76, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_simpan)
                                .addGap(1, 1, 1)
                                .addComponent(btn_Hapus)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ubah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_reset))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtManager, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtNama_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtno_register, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cmb_noDOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tanggal_efektif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtrevisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(txtManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(txtNama_pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_nama_proyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txt_tindak_Lanjut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_simpan)
                        .addComponent(btn_Hapus)
                        .addComponent(btn_reset)
                        .addComponent(btn_ubah)
                        .addComponent(kembali)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void datePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePropertyChange
        // TODO add your handling code here:
        try{
            String tampilan ="dd-MMM-yyyy" ;
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format( date.getDate()));
            txt_tanggal.setText(tanggal);
        }catch(Exception e){}
    }//GEN-LAST:event_datePropertyChange

    private void txtManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtManagerActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HapusActionPerformed
        delete();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HapusActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void mytableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mytableMouseClicked
        // TODO add your handling code here:
        getSelectedData(mytable.getSelectedRow());
    }//GEN-LAST:event_mytableMouseClicked

    private void cmb_noDOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_noDOKActionPerformed
        // TODO add your handling code here:
        tampil();
    }//GEN-LAST:event_cmb_noDOKActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        Menu_utama xx;
        xx =new Menu_utama();
        xx.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Keluhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Keluhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Keluhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Keluhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Keluhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Hapus;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cmb_noDOK;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kembali;
    private javax.swing.JTable mytable;
    private javax.swing.JTextArea txtKeluhan;
    private javax.swing.JTextField txtManager;
    private javax.swing.JTextField txtNama_perusahaan;
    private javax.swing.JTextField txtNama_pic;
    private javax.swing.JTextField txtStaff;
    private javax.swing.JTextField txt_nama_proyek;
    private javax.swing.JTextField txt_tanggal;
    private javax.swing.JTextField txt_tanggal_efektif;
    private javax.swing.JTextField txt_tindak_Lanjut;
    private javax.swing.JTextField txtno_register;
    private javax.swing.JTextField txtrevisi;
    // End of variables declaration//GEN-END:variables
}
