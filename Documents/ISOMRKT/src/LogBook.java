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
public class LogBook extends javax.swing.JFrame {
    logBookDao ld;
    /**
     * Creates new form LogBook
     */
    public LogBook() {
        ld= new logBookDao();
        ld.getConnection();
        initComponents();
        setLocationRelativeTo(null);
        viewAll();
        tampil_combo();
    }
    
    public void tampil_combo()
    {
        try {
            
        Connection con = ld.getConnection();
        Statement stt = con.createStatement();
        String sql = "select no_dokumen_log from dataform order by no_dokumen_log asc";      // disini saya menampilkan NIM, anda dapat menampilkan
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
        Connection con = ld.getConnection();
        Statement stt = con.createStatement();
        String sql = "select tanggal,revisi,nama_staf_market,nama_manager from dataform where no_dokumen_log='"+cmb_noDOK.getSelectedItem()+"'";  
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
            
            Statement statement =ld.connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)FROM tb_logbook");// memanggil Resulset dari library dg var resulset dg nilai query utk mengetahui jml baris yg ada di table
             
            resultSet.next(); //melihat baris berikutnya 
            int rows = resultSet.getInt(1); //akan berisi jml baris dari table db
            Object[]header = {"Kode Marketing","No. Penawaran","Tanggal","Nilai Penawaran",
                "Kualifikasi","Nama Perusahaan","Nama Proyek","Status","No.Dokumen LogBook","Tanggal Efektif","Revisi","Nama Staf","Nama Manager"};
            Object[][]data =new Object [rows][13]; // 7 adalah jml kolom . jml rows dikdeklarasikan 1
            resultSet = statement.executeQuery("SELECT * FROM tb_logbook ORDER BY kode_marketing");
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
            data[i][12]=resultSet.getString(13);
           i++;
        }
            DefaultTableModel tablemodel = new DefaultTableModel(data,header);// membuat kelas DefaultTableModel dari library dan objek kelas tablemodel
            //Tabel model berfungsi menampikan data dari table database ke jtabel(tabel java) form
            mytable.setModel(tablemodel); // memberikan nilai tableviw(jtabel) melalui objek kelas tablemodel yg sudah dibuat
            //utk bisa menampilkanya buatlah method viewALL() di construktor
        } catch (SQLException ex) {
            Logger.getLogger(logBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } //sampai sini pembuatan tablemodel yg ada dlm method viewALL selesai .data dari table db  sudah bisa dimasukan dalam jtable form
    
    // mthod ini digunakan utk menampilkan data dari jtable ke masing2 textfield(nim,nama,dll)
    protected void getSelectedData(int row){ //parameter (int row) utk memilih data dari baris mana yg akan  ditampilkan ke textfield
        txtkode_marketing.setText(mytable.getValueAt(row,0).toString());// mmbri nilai textNim dri tableview dg cara mengambil nilai dari (indeks 0 = kolom ke 1) di table mahasisiwadb lalu mngbah Objek(array) kestring 
       txtNo_penawaran.setText(mytable.getValueAt(row,1).toString());
        txtTangggal.setText(mytable.getValueAt(row,2).toString());
        txtNilai_penawaran.setText(mytable.getValueAt(row,3).toString());
        txtKualifikasi.setText(mytable.getValueAt(row,4).toString());
        txtNama_perusahaan.setText(mytable.getValueAt(row,5).toString());
        txtNama_proyek.setText(mytable.getValueAt(row,6).toString());
        cmbStatus.setSelectedItem(mytable.getValueAt(row,7));
        cmb_noDOK.setSelectedItem(mytable.getValueAt(row,8));
        txt_tanggal_efektif.setText(mytable.getValueAt(row,9).toString());// mmbri nilai textNim dri tableview dg cara mengambil nilai dari (indeks 0 = kolom ke 1) di table mahasisiwadb lalu mngbah Objek(array) kestring 
       txtrevisi.setText(mytable.getValueAt(row,10).toString());
        txtStaff.setText(mytable.getValueAt(row,11).toString());
        txtManager.setText(mytable.getValueAt(row,12).toString());
        
        //txtDocumentStatusPO.setSelectedItem(mytable.getValueAt(row,3));
        
    }//utk manggil method ini klk kanan  jtable>mouse clicked ketikkan kode getSelectedData(tableview.getSelectedRow());
    
    //membuat method insert untuk memasukan data dari masing2 komponen text field yg ada di  kolom ke objek mahasiswadao lalu ke table mahasiswa lalu ditampilkan ke jtable
    //method ini digunakan utk memfungsikan tombol jbuton yg diberi nama Save
    protected void insert(){ 
        ld.setKode_marketing(txtkode_marketing.getText()); //memberikan nilai nama ke table data_siswa dari textfield Nim ke objek mahasiswadao
        ld.setNo_penawaran(txtNo_penawaran.getText());
        ld.setTanggal(txtTangggal.getText());
        ld.setNilai_penawaran(txtNilai_penawaran.getText());
        ld.setKualifikasi(txtKualifikasi.getText());
        ld.setNama_perusahaan(txtNama_perusahaan.getText());
        ld.setNama_proyek(txtNama_proyek.getText());
        ld.setStatus(cmbStatus.getSelectedItem().toString());
        
        ld.setNo_dok_logbook(cmb_noDOK.getSelectedItem().toString());
        ld.setTanggal_efektif(txt_tanggal_efektif.getText()); 
        ld.setRevisi(txtrevisi.getText()); 
        ld.setNama_staf(txtStaff.getText()); 
        ld.setNama_manager(txtManager.getText()); 
        
        
        ld.insertData(); //memanggil methode insertdataData yg sdh dibuat dimahasiswadao utk memasukan/save nilai dari masing2 textfield kedlm table mahasiwa db
        viewAll(); //memanggil metode viewALL yg berisi objek kelas tablemodel yg dpt menampilkan table mahasiswa db ke jtabel form
    }// method insert sudah jadi utk menggunakanya klik 2x pada tombol Save kemudian tulis kode insert();
    //utk mengujinya . silahkan di RUn masukan data di masing2 kolom  textfiel(nim, nama,dll) kemudian klik tombol Save maka data akan tersimpan
    
    protected void update(){ //method ini fungsinya utk merubah data langkah2 kurang lebih sama dg methode insert();
        ld.setKode_marketing(txtkode_marketing.getText()); //memberikan nilai nama ke table data_siswa dari textfield Nim ke objek mahasiswadao
        ld.setNo_penawaran(txtNo_penawaran.getText());
        ld.setTanggal(txtTangggal.getText());
        ld.setNilai_penawaran(txtNilai_penawaran.getText());
        ld.setKualifikasi(txtKualifikasi.getText());
        ld.setNama_perusahaan(txtNama_perusahaan.getText());
        ld.setNama_proyek(txtNama_proyek.getText());
        ld.setStatus(cmbStatus.getSelectedItem().toString());
        
        ld.setNo_dok_logbook(cmb_noDOK.getSelectedItem().toString());
        ld.setTanggal_efektif(txt_tanggal_efektif.getText()); 
        ld.setRevisi(txtrevisi.getText()); 
        ld.setNama_staf(txtStaff.getText()); 
        ld.setNama_manager(txtManager.getText()); 
        
        
        ld.updateData(); //memanggil methode updateData() yg sdh dibuat di data_siswaDao utk merubah/Update nilai dari masing2 textfield kedlm table mahasiwa db
        viewAll();//memanggil metode viewALL yg berisi objek kelas tablemodel yg dpt menampilkan table mahasiswa db ke jtabel form
        //membuat method getSelectedData utk menampilkan nilai dari jtable ke masing2 textfield(nim,nama,dll) saat di click data dari jtabel 
    }// method update sudah jadi utk menggunakanya klik 2x pada tombol Update kemudian tulis kode update();
    //utk mengujinya . silahkan di RUn masukan data di masing2 kolom  textfiel(nim, nama,dll) kemudian klik tombol Save maka data akan tersimpan
    protected void delete(){
        ld.setKode_marketing(txtkode_marketing.getText());      
        ld.deleteData(); //memanggil method ini utk menghapus data di table mahasiswa 
        viewAll(); //menampilkan data setelah dihapus ke jtable form
        reset();
    } //utk mgunakan method delete() klik 2x pd tombol Delete pada desain form lalu ketikkan kode Delete();
    // utk memunculkan pemberitahuan konfirmasi ketkkan kode if(JOptionPane.showConfirmDialog(null,"Apakah Anda yakin akan menghapus data ini?","Hapus data",1)==0) sebelum kode delete();
     
    public void reset(){
    txtkode_marketing.setText("");
    txtNo_penawaran.setText("");
    txtTangggal.setText("");
    txtNilai_penawaran.setText("");
    txtKualifikasi.setText("");
    txtNama_perusahaan.setText("");
    txtNama_proyek.setText("");
    cmbStatus.setSelectedIndex(0);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        txtkode_marketing = new javax.swing.JTextField();
        txtNo_penawaran = new javax.swing.JTextField();
        txtNilai_penawaran = new javax.swing.JTextField();
        txtKualifikasi = new javax.swing.JTextField();
        txtNama_perusahaan = new javax.swing.JTextField();
        txtNama_proyek = new javax.swing.JTextField();
        txtTangggal = new javax.swing.JTextField();
        cmb_noDOK = new javax.swing.JComboBox<>();
        txt_tanggal_efektif = new javax.swing.JTextField();
        txtrevisi = new javax.swing.JTextField();
        txtStaff = new javax.swing.JTextField();
        txtManager = new javax.swing.JTextField();
        date = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        mytable = new javax.swing.JTable();
        btn_simpan = new javax.swing.JButton();
        btn_Hapus = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        kembali3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel1.setText("Data penawaran PT.Tehnik Bayu Murni");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Kode Marketing");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("No. Penawaran");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Tanggal");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nilai Penawaran");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Kualifikasi");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Nama Perusahaan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Status");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Nama Proyek");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dalam Pengerjaan", "Finishing" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("No. Doc");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Tanggal Efektif");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Revisi");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Staff Marketing");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Manager Marketing");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Cari");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb_noDOK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "===PILIH===" }));
        cmb_noDOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_noDOKActionPerformed(evt);
            }
        });

        txtManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtManagerActionPerformed(evt);
            }
        });

        date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePropertyChange(evt);
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.jpg"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("APLIKASI MONITORING PROSEDUR MUTU BAGIAN PEMASARAN ");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("PT TEHNIK BAYU MURNI");

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
                .addContainerGap(397, Short.MAX_VALUE))
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

        kembali3.setBackground(new java.awt.Color(0, 0, 255));
        kembali3.setText("Kembali");
        kembali3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama_proyek)
                            .addComponent(txtNama_perusahaan)
                            .addComponent(txtkode_marketing)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTangggal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNo_penawaran)
                            .addComponent(txtNilai_penawaran)
                            .addComponent(txtKualifikasi)
                            .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmb_noDOK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tanggal_efektif, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtrevisi, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtManager, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 287, Short.MAX_VALUE)))
                                .addGap(25, 25, 25))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_simpan)
                                .addGap(1, 1, 1)
                                .addComponent(btn_Hapus)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ubah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_reset)
                                .addGap(18, 18, 18)
                                .addComponent(kembali3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtkode_marketing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNo_penawaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel11)
                                .addComponent(txtTangggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNilai_penawaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtKualifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_noDOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tanggal_efektif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtrevisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNama_proyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpan)
                    .addComponent(btn_Hapus)
                    .addComponent(btn_reset)
                    .addComponent(btn_ubah)
                    .addComponent(kembali3))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_noDOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_noDOKActionPerformed
        // TODO add your handling code here:
        tampil();
    }//GEN-LAST:event_cmb_noDOKActionPerformed

    private void txtManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtManagerActionPerformed

    private void datePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePropertyChange
        // TODO add your handling code here:
        try{
            String tampilan ="dd-MMM-yyyy" ;
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format( date.getDate()));
            txtTangggal.setText(tanggal);
        }catch(Exception e){}
    }//GEN-LAST:event_datePropertyChange

    private void mytableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mytableMouseClicked
        // TODO add your handling code here:
        getSelectedData(mytable.getSelectedRow());
    }//GEN-LAST:event_mytableMouseClicked

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

    private void kembali3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali3ActionPerformed
        Menu_utama xx;
        xx =new Menu_utama();
        xx.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembali3ActionPerformed

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
            java.util.logging.Logger.getLogger(LogBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Hapus;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmb_noDOK;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox5;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali3;
    private javax.swing.JTable mytable;
    private javax.swing.JTextField txtKualifikasi;
    private javax.swing.JTextField txtManager;
    private javax.swing.JTextField txtNama_perusahaan;
    private javax.swing.JTextField txtNama_proyek;
    private javax.swing.JTextField txtNilai_penawaran;
    private javax.swing.JTextField txtNo_penawaran;
    private javax.swing.JTextField txtStaff;
    private javax.swing.JTextField txtTangggal;
    private javax.swing.JTextField txt_tanggal_efektif;
    private javax.swing.JTextField txtkode_marketing;
    private javax.swing.JTextField txtrevisi;
    // End of variables declaration//GEN-END:variables
}
