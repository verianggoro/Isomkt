/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ISOMKT;
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
public class kepuasan extends javax.swing.JFrame {
    KepuasanDao kd;
    
    /**
     * Creates new form kepuasan
     */
    public kepuasan() {
        kd =new KepuasanDao();
        kd.getConnection();
        initComponents();
        setLocationRelativeTo(null);
        viewAll();
        //tampil_combo();
        
        
    }
    public void viewAll(){
        
        try {
            
            Statement statement =kd.connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)FROM tb_kepuasan");// memanggil Resulset dari library dg var resulset dg nilai query utk mengetahui jml baris yg ada di table
             
            resultSet.next(); //melihat baris berikutnya 
            int rows = resultSet.getInt(1); //akan berisi jml baris dari table db
            Object[]header = {"No Kepuasan","Nama Customer","Nama Perusahaan","Nama Proyek",
                "Tanggal","Saran","Nama Staff Marketing","Nama Manager Marketing"};
            Object[][]data =new Object [rows][12]; // 7 adalah jml kolom . jml rows dikdeklarasikan 1
            resultSet = statement.executeQuery("SELECT * FROM tb_kepuasan ORDER BY no_kepuasan");
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
            
            i++;
        }
            DefaultTableModel tablemodel = new DefaultTableModel(data,header);// membuat kelas DefaultTableModel dari library dan objek kelas tablemodel
            //Tabel model berfungsi menampikan data dari table database ke jtabel(tabel java) form
            mytable.setModel(tablemodel); // memberikan nilai tableviw(jtabel) melalui objek kelas tablemodel yg sudah dibuat
            //utk bisa menampilkanya buatlah method viewALL() di construktor
        } catch (SQLException ex) {
            Logger.getLogger(status_PO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } //sampai sini pembuatan tablemodel yg ada dlm method viewALL selesai .data dari table db  sudah bisa dimasukan dalam jtable form
    
    // mthod ini digunakan utk menampilkan data dari jtable ke masing2 textfield(nim,nama,dll)
    protected void getSelectedData(int row){ //parameter (int row) utk memilih data dari baris mana yg akan  ditampilkan ke textfield
        txtKepuasan.setText(mytable.getValueAt(row,0).toString());// mmbri nilai textNim dri tableview dg cara mengambil nilai dari (indeks 0 = kolom ke 1) di table mahasisiwadb lalu mngbah Objek(array) kestring 
       txtNamaCustomer.setText(mytable.getValueAt(row,1).toString());
        txtNamaPerusahaan.setText(mytable.getValueAt(row,2).toString());
        txtNamaProyek1.setText(mytable.getValueAt(row,3).toString());
        txtTanggal.setText(mytable.getValueAt(row,4).toString()) ;
        txtSaran.setText(mytable.getValueAt(row,5).toString());
        txtStaff.setText(mytable.getValueAt(row,6).toString());
        txtManager.setText(mytable.getValueAt(row,7).toString());
        
        
        //txtDocumentStatusPO.setSelectedItem(mytable.getValueAt(row,3));
        
    }//utk manggil method ini klk kanan  jtable>mouse clicked ketikkan kode getSelectedData(tableview.getSelectedRow());
    
    //membuat method insert untuk memasukan data dari masing2 komponen text field yg ada di  kolom ke objek mahasiswadao lalu ke table mahasiswa lalu ditampilkan ke jtable
    //method ini digunakan utk memfungsikan tombol jbuton yg diberi nama Save
    protected void insert(){ 
        kd.setNo_kepuasan(txtKepuasan.getText()); //memberikan nilai nama ke table data_siswa dari textfield Nim ke objek mahasiswadao
        kd.setNama_customer(txtNamaCustomer.getText());
        kd.setNama_perusahaan(txtNamaPerusahaan.getText());
        kd.setNama_proyek(txtNamaProyek1.getText());
        
        kd.setTanggal(txtTanggal.getText());
        kd.setSaran(txtSaran.getText());
        kd.setNama_staff(txtStaff.getText()); 
        kd.setNama_manager(txtManager.getText()); 
        
        
        kd.insertData(); //memanggil methode insertdataData yg sdh dibuat dimahasiswadao utk memasukan/save nilai dari masing2 textfield kedlm table mahasiwa db
        viewAll(); //memanggil metode viewALL yg berisi objek kelas tablemodel yg dpt menampilkan table mahasiswa db ke jtabel form
    }// method insert sudah jadi utk menggunakanya klik 2x pada tombol Save kemudian tulis kode insert();
    //utk mengujinya . silahkan di RUn masukan data di masing2 kolom  textfiel(nim, nama,dll) kemudian klik tombol Save maka data akan tersimpan
    
    protected void update(){ //method ini fungsinya utk merubah data langkah2 kurang lebih sama dg methode insert();
        kd.setNo_kepuasan(txtKepuasan.getText()); //memberikan nilai nama ke table data_siswa dari textfield Nim ke objek mahasiswadao
        kd.setNama_customer(txtNamaCustomer.getText());
        kd.setNama_perusahaan(txtNamaPerusahaan.getText());
        kd.setNama_proyek(txtNamaProyek1.getText());
        
        kd.setTanggal(txtTanggal.getText());
        kd.setSaran(txtSaran.getText());
        kd.setNama_staff(txtStaff.getText()); 
        kd.setNama_manager(txtManager.getText()); 
        
        
        kd.updateData(); //memanggil methode updateData() yg sdh dibuat di data_siswaDao utk merubah/Update nilai dari masing2 textfield kedlm table mahasiwa db
        viewAll();//memanggil metode viewALL yg berisi objek kelas tablemodel yg dpt menampilkan table mahasiswa db ke jtabel form
        //membuat method getSelectedData utk menampilkan nilai dari jtable ke masing2 textfield(nim,nama,dll) saat di click data dari jtabel 
    }// method update sudah jadi utk menggunakanya klik 2x pada tombol Update kemudian tulis kode update();
    //utk mengujinya . silahkan di RUn masukan data di masing2 kolom  textfiel(nim, nama,dll) kemudian klik tombol Save maka data akan tersimpan
    protected void delete(){
        kd.setNo_kepuasan(txtKepuasan.getText());      
        kd.deleteData(); //memanggil method ini utk menghapus data di table mahasiswa 
        viewAll(); //menampilkan data setelah dihapus ke jtable form
        reset();
    } //utk mgunakan method delete() klik 2x pd tombol Delete pada desain form lalu ketikkan kode Delete();
    // utk memunculkan pemberitahuan konfirmasi ketkkan kode if(JOptionPane.showConfirmDialog(null,"Apakah Anda yakin akan menghapus data ini?","Hapus data",1)==0) sebelum kode delete();
     
    public void reset(){
    txtKepuasan.setText("");
    txtNamaCustomer.setText("");
    txtNamaPerusahaan.setText("");
    txtNamaProyek1.setText("");
    txtTanggal.setText("");
    txtSaran.setText(""); 
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
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtKepuasan = new javax.swing.JTextField();
        txtNamaCustomer = new javax.swing.JTextField();
        txtNamaPerusahaan = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox<>();
        btn_Cari = new javax.swing.JButton();
        btn_Simpan = new javax.swing.JButton();
        btn_Hapus = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_Reset = new javax.swing.JButton();
        txtNamaProyek1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSaran = new javax.swing.JTextArea();
        txtManager = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        mytable = new javax.swing.JTable();
        txtStaff = new javax.swing.JTextField();
        kembali = new javax.swing.JButton();
        date = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.jpg"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("APLIKASI MONITORING PROSEDUR MUTU BAGIAN PEMASARAN ");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("PT TEHNIK BAYU MURNI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addContainerGap(345, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel17)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel1.setText("No. Kepuasan");

        jLabel2.setText("Nama customer");

        jLabel3.setText("Nama Perusahaan");

        jLabel4.setText("Nama Proyek");

        jLabel5.setText("Tanggal");

        jLabel7.setText("Saran Owner");

        jLabel6.setText("Staff Marketing");

        jLabel8.setText("Manager Marketing");

        jLabel9.setText("Cari berdasarkan");

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_Cari.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Cari.setText("Cari");

        btn_Simpan.setBackground(new java.awt.Color(255, 255, 255));
        btn_Simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Simpan.setText("Simpan");
        btn_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SimpanActionPerformed(evt);
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

        btn_Reset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Reset.setText("Reset");
        btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ResetActionPerformed(evt);
            }
        });

        txtSaran.setColumns(20);
        txtSaran.setRows(5);
        jScrollPane1.setViewportView(txtSaran);

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
        jScrollPane2.setViewportView(mytable);

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

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel10.setText("Kepuasan Pelanggan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Hapus)
                        .addGap(14, 14, 14)
                        .addComponent(btn_ubah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Reset)))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtNamaProyek1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNamaPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtKepuasan, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNamaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtManager, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Cari)))
                .addGap(88, 88, 88))
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Cari))
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Simpan)
                            .addComponent(btn_Hapus)
                            .addComponent(btn_ubah)
                            .addComponent(btn_Reset))
                        .addGap(18, 18, 18)
                        .addComponent(kembali))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKepuasan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNamaCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNamaPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNamaProyek1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SimpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btn_SimpanActionPerformed

    private void mytableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mytableMouseClicked
        // TODO add your handling code here:
        getSelectedData(mytable.getSelectedRow());
    }//GEN-LAST:event_mytableMouseClicked

    private void datePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePropertyChange
        // TODO add your handling code here:
        try{
            String tampilan ="dd-MMM-yyyy" ;
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format( date.getDate()));
            txtTanggal.setText(tanggal);
        }catch(Exception e){}
    }//GEN-LAST:event_datePropertyChange

    private void btn_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btn_HapusActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btn_ResetActionPerformed

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
            java.util.logging.Logger.getLogger(kepuasan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kepuasan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kepuasan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kepuasan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kepuasan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cari;
    private javax.swing.JButton btn_Hapus;
    private javax.swing.JButton btn_Reset;
    private javax.swing.JButton btn_Simpan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cmbCari;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kembali;
    private javax.swing.JTable mytable;
    private javax.swing.JTextField txtKepuasan;
    private javax.swing.JTextField txtManager;
    private javax.swing.JTextField txtNamaCustomer;
    private javax.swing.JTextField txtNamaPerusahaan;
    private javax.swing.JTextField txtNamaProyek1;
    private javax.swing.JTextArea txtSaran;
    private javax.swing.JTextField txtStaff;
    private javax.swing.JTextField txtTanggal;
    // End of variables declaration//GEN-END:variables
}
