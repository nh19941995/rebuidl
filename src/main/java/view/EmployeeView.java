package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.formdev.flatlaf.FlatLightLaf;
import controller.CustomizeTableAppearance;
import controller.InstantDateTimeInfo;
import dao.BookingDAO;
import dao.PermissionDAO;
import dao.PersonDAO;
import dao.TableListDAO;
import model.Booking;
import model.Permission;
import model.Person;
import model.TableList;
import java.time.ZoneId;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

/**
 *
 * @author Admin
 */

public class EmployeeView extends JFrame {

    /**
     * Creates new form Employee
     */
    public EmployeeView() {
        initComponents();
        addDataToTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jPanel4 = new JPanel();
        jLabel7 = new JLabel();
        btnAddcus = new JButton();
        inputFname = new JTextField();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        inputLname = new JTextField();
        jLabel10 = new JLabel();
        inputAddress = new JTextField();
        jLabel11 = new JLabel();
        pickPermission = new JComboBox<>();
        jLabel12 = new JLabel();
        inputEmail = new JTextField();
        jLabel13 = new JLabel();
        inputBirth = new JTextField();
        jLabel14 = new JLabel();
        inputPhone = new JTextField();
        btnUpdate = new JButton();
        btnDelete = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBackground(new Color(204, 204, 204));
        jTable1.setModel(new DefaultTableModel(

                new Object [][] {

                },
                new String [] {
                        "First name", "Last name", "Email", "Address", "Birth day", "Permission", "Phone number"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, Object.class, String.class, Object.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });




        jScrollPane1.setViewportView(jTable1);


        jPanel4.setBackground(new Color(102, 102, 102));

        jLabel7.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new Color(255, 255, 255));
        jLabel7.setText("Customer Infomation ");

        btnAddcus.setBackground(new Color(255, 255, 255));
        btnAddcus.setText("Add ");
        btnAddcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddcusMouseClicked(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        inputFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFnameActionPerformed(evt);
            }
        });

        jLabel8.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new Color(255, 255, 255));
        jLabel8.setText("First name :");

        jLabel9.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new Color(255, 255, 255));
        jLabel9.setText("Email :");

        inputLname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputLnameActionPerformed(evt);
            }
        });

        jLabel10.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new Color(255, 255, 255));
        jLabel10.setText("Last name :");

        inputAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputAddressActionPerformed(evt);
            }
        });

        jLabel11.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new Color(255, 255, 255));
        jLabel11.setText("Permission :");



//        dữ liệu của select box
//        pickPermission.setModel(new DefaultComboBoxModel<>(new String[] { "Cashier", "Manage", "Staff", "Chef", "Security" }));
        pickPermission.setModel(new DefaultComboBoxModel<>(PermissionDAO.getInstance().getAll()
                .stream()
                .map(s -> s.getPermissionName())
                .toArray(String[]::new)));
//



        pickPermission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickPermissionActionPerformed(evt);
            }
        });

        jLabel12.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new Color(255, 255, 255));
        jLabel12.setText("Birth day :");

        inputEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEmailActionPerformed(evt);
            }
        });

        jLabel13.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new Color(255, 255, 255));
        jLabel13.setText("Address :");

        inputBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBirthActionPerformed(evt);
            }
        });

        jLabel14.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new Color(255, 255, 255));
        jLabel14.setText("Phone number :");

        inputPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPhoneActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap(37, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(inputPhone)
                                        .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14)
                                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel12, GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel13)
                                                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(inputEmail)
                                                                        .addComponent(inputAddress)
                                                                        .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel10)
                                                                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                                        .addComponent(inputLname)
                                                                                        .addComponent(jLabel9, GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel8, GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(inputFname, GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel7, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                                        .addComponent(inputBirth))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(jLabel11)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(pickPermission, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(49, 49, 49)
                                                        .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
                                .addGap(29, 29, 29))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(btnAddcus, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(3, 3, 3)
                                .addComponent(inputFname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputLname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel9)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(6, 6, 6)
                                .addComponent(inputBirth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(pickPermission, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnAddcus)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnDelete)
                                        .addComponent(btnUpdate))
                                .addContainerGap(198, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1165, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)

        );

        pack();
    }// </editor-fold>

    private void btnAddcusMouseClicked(java.awt.event.MouseEvent evt) {
        if(inputFname.getText().equals("")||inputLname.getText().equals("")||inputEmail.getText().equals("")||inputAddress.getText().equals("")||inputBirth.getText().equals("")||inputPhone.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Please enter all data !");
        }else{
            String selectedPer = (String) pickPermission.getSelectedItem();
            String data[] = {inputFname.getText(), inputLname.getText(), inputEmail.getText(), inputAddress.getText(), inputBirth.getText(), selectedPer,inputPhone.getText()};
//            tạo đối tượng person với dữ liệu người dùng nhập vào
            Person newPerson = new Person();
            newPerson.setName(inputFname.getText());
            newPerson.setLastName(inputLname.getText());

//            chuyển string thành Instant
            LocalDate localDate = LocalDate.parse(inputBirth.getText());
            // Đặt giờ, phút, giây và millisecond thành 0 để có ngày giờ cụ thể (00:00:00)
            Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            newPerson.setDateOfBirth(instant);
            newPerson.setEmail(inputEmail.getText());
            newPerson.setFlag(1);
            newPerson.setAddress(inputAddress.getText());
            Instant now = Instant.now();
            newPerson.setDateCreat(now);
            newPerson.setDateUpdate(now);
            newPerson.setPassword("");
            newPerson.setPhone(inputPhone.getText());
            newPerson.setUsername("");
//            lấy id của permissions

            int idOfJohn = PermissionDAO.getInstance().getAll().stream()
                    .filter(permission -> permission.getPermissionName().equals(selectedPer))
                    .map(Permission::getId)
                    .findFirst()
                    .orElse(-1); // Giá trị mặc định nếu không tìm thấy

            newPerson.setPermission(PermissionDAO.getInstance().getById(idOfJohn));
            PersonDAO.getInstance().insert(newPerson);


//            đưa dư liệu vào bảng
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            // Cập nhật bảng để hiển thị dữ liệu mới
            tableModel.fireTableDataChanged();
//            làm trống trở lại phần input
            inputFname.setText("");
            inputLname.setText("");
            inputAddress.setText("");
            inputEmail.setText("");
            inputBirth.setText("");
            inputPhone.setText("");
        }
    }



    private void addDataToTable() {
        // Khởi tạo mô hình dữ liệu cho bảng
        this.tableModel = (DefaultTableModel) jTable1.getModel();
        java.util.List<Person> personList = PersonDAO.getInstance().getAll();

        // Sử dụng HashSet để lưu trữ các phần tử không trùng lặp
//        Set<Integer> uniqueElements = new HashSet<>();


        Object[][] personArr  = personList.stream().map(
                s -> new Object[]{
                        s.getName(),  // id bàn
                        s.getLastName(),  // loại bàn
                        s.getEmail(),   // số ghế
                        s.getAddress(),
                        InstantDateTimeInfo.getTime(s.getDateOfBirth(),2),  // ngày
                        s.getPermission().getPermissionName(),
                        s.getPhone(),
                }

        ).toArray(Object[][]::new);

//        List<TableList> tableLists = TableListDAO.getInstance().getAll();
//
//        Object[][] dataNoneBooking = tableLists.stream()
//                .map(s -> {
//                    int tableId = s.getId().intValue();
//                    if (!uniqueElements.contains(tableId)) { // Đảo ngược điều kiện từ contains thành không contains
//                        return new Object[]{
//                                s.getId(),    // id bàn
//                                s.getType().getName(),  // loại bàn
//                                s.getSeatingCapacity(),  // số ghế
//                                "",
//                                "",
//                                "",
//                                s.getFlag()
//                        };
//                    } else {
//                        return null;
//                    }
//                })
//                .filter(Objects::nonNull)
//                .toArray(Object[][]::new);
//        Object[][] allBooking = concatenateArrays(dataNoneBooking,dataOnBooking);
////        sắp xếp theo id
//        Arrays.sort(allBooking, Comparator.comparingInt(arr -> (int) arr[0]));

        this.dataTable = personArr;
//        Arrays.stream(data).map(s->new Object[]{
//
//        }).toArray(Object[][]::new);
//


        // Thêm từng hàng dữ liệu vào bảng
        for (Object[] row : dataTable) {
            tableModel.addRow(row);
        }
        // Cập nhật bảng để hiển thị dữ liệu mới
        tableModel.fireTableDataChanged();
    }










    private void jTable1MouseClicked(java.awt.event.MouseEvent evt){
        int rowNumber = jTable1.getSelectedRow();
        TableModel tblModel = jTable1.getModel();

        String fName = tblModel.getValueAt(rowNumber, 0 ).toString();
        String lName = tblModel.getValueAt(rowNumber, 1 ).toString();
        String email = tblModel.getValueAt(rowNumber, 2 ).toString();
        String address = tblModel.getValueAt(rowNumber, 3).toString();
        String birth = tblModel.getValueAt(rowNumber, 4).toString();
        String permission = tblModel.getValueAt(rowNumber, 5).toString();
        String phone = tblModel.getValueAt(rowNumber, 6).toString();

        inputFname.setText(fName);
        inputLname.setText(lName);
        inputAddress.setText(address);
        inputEmail.setText(email);
        inputBirth.setText(birth);
        inputPhone.setText(phone);
    }


    private void customizeTableAppearance() {
        // Tạo một đối tượng DefaultTableCellRenderer để tùy chỉnh cách hiển thị của các ô trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Căn giữa chữ trong tất cả các cột của bảng
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Điều chỉnh kích thước cột tuỳ theo nội dung trong ô
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void inputFnameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void inputLnameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void inputAddressActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void pickPermissionActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void inputEmailActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void inputBirthActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void inputPhoneActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
        if(jTable1.getSelectedRowCount() == 1){
            String fName = inputFname.getText();
            String lName = inputLname.getText();
            String email = inputEmail.getText();
            String address = inputAddress.getText();
            String birth = inputBirth.getText();
            String phone = inputPhone.getText();
            String permission = (String) pickPermission.getSelectedItem();

            tblModel.setValueAt(fName, jTable1.getSelectedRow(), 0);
            tblModel.setValueAt(lName, jTable1.getSelectedRow(), 1);
            tblModel.setValueAt(email, jTable1.getSelectedRow(), 2);
            tblModel.setValueAt(address, jTable1.getSelectedRow(), 3);
            tblModel.setValueAt(birth, jTable1.getSelectedRow(), 4);
            tblModel.setValueAt(permission, jTable1.getSelectedRow(),5);
            tblModel.setValueAt(phone, jTable1.getSelectedRow(), 6);

            JOptionPane.showMessageDialog(this,"Update succesfully !");
        }
    }

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
        if(jTable1.getSelectedRowCount() != 0){
            int option = JOptionPane.showOptionDialog(rootPane, "Do you want delete "+ jTable1.getSelectedRowCount() + " user ?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Yes", "No"}, "Yes");
            // Xử lý lựa chọn của người dùng
            if (option == JOptionPane.YES_OPTION) {
                tblModel.removeRow(jTable1.getSelectedRow());
            }
        }else {
            if(jTable1.getRowCount() == 0){
                JOptionPane.showMessageDialog(rootPane, "Table user is empty !");
            }else{
                JOptionPane.showMessageDialog(rootPane, "Please select single row for delete!");
            }
        }
    }

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
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    //            chuyển giao diện sang giống ios
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    EmployeeView employeeView = new EmployeeView();
                    employeeView.setVisible(true);
                    //                    căn giữa chữ trong bảng
                    new CustomizeTableAppearance(employeeView.getjTable1());

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }

    public JTable getjTable1() {
        return jTable1;
    }

    // Variables declaration - do not modify
    private JButton btnAddcus;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JTextField inputAddress;
    private JTextField inputBirth;
    private JTextField inputEmail;
    private JTextField inputFname;
    private JTextField inputLname;
    private JTextField inputPhone;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel4;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JComboBox<String> pickPermission;
    // End of variables declaration
    private DefaultTableModel tableModel;
    private Object[][] dataTable;
}

