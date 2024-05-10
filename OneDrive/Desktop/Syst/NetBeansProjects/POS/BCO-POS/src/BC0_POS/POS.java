/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BC0_POS;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import BC0_POS.Invoice;
import BC0_POS.Home_source;
import BC0_POS.Main_menu;
import BC0_POS.Login;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 *
 * @author Scave
 */
public class POS extends javax.swing.JFrame {

    Main_menu m = new Main_menu();
    Home_source OOP = new Home_source();
    Invoice i = new Invoice();
    public POS() {
        
        initComponents();
        setupListeners();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        m.data_load();
        m.data();
        sDate();
        sTime();
        m.setDate();
        m.setTime();
        m.cdata();
        
        org.jdesktop.swingx.autocomplete.AutoCompleteDecorator.decorate(jComboBox2);
        org.jdesktop.swingx.autocomplete.AutoCompleteDecorator.decorate(m.jComboBox3);
        org.jdesktop.swingx.autocomplete.AutoCompleteDecorator.decorate(m.jComboBox4);
        org.jdesktop.swingx.autocomplete.AutoCompleteDecorator.decorate(m.jComboBox5);
        m.sdata();
        m.stockdata();
        m.dataLoad();
        m.costumerdata();
        m.costumerd();
        m.ccdata();
    
       
    }
      private void setupListeners() {
        itemField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                displayProductName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                displayProductName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // wala yan
            }
        });
    }

 private void displayProductName() {
    String itemCode = itemField1.getText().trim();
    if (!itemCode.isEmpty()) {
        try (Connection connection = Home_source.mycon();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT product_name FROM tblproduct WHERE bar_code = ?")) {

            preparedStatement.setString(1, itemCode);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String productName = rs.getString("product_name");
                itemNameArea.setText(productName);
            } else {
                itemNameArea.setText("Product not found");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    } else {
        itemNameArea.setText(""); // Clear the text area if no item code is entered
    }
}
 public void cdata() {
        try {
            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery("select product_name from tblproduct order by `product_name` asc ");

            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("product_name"));
                DefaultComboBoxModel c = new DefaultComboBoxModel(v);
               
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ccdata() {
        try {
            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery("select product_name from tblproduct order by `product_name` asc ");

            Vector r = new Vector();

            while (rs.next()) {
                r.add(rs.getString("product_name"));
                DefaultComboBoxModel c = new DefaultComboBoxModel(r);
                m.jComboBox5.setModel(c);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void costumerdata() {
        try {
            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery("select fname from tblcostumer order by `fname` asc ");

            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("fname"));
                DefaultComboBoxModel c = new DefaultComboBoxModel(v);
                jComboBox2.setModel(c);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void costumerd() {
        try {
            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery("select fname from tblcostumer order by `fname` asc ");

            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("fname"));
                DefaultComboBoxModel c = new DefaultComboBoxModel(v);
                m.jComboBox4.setModel(c);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        posdate.setText(s.format(d));

    }

    public void sTime() {

        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                postime.setText(s.format(d));
            }
        }
        ).start();
    }

   

    public void data_load() {

        try {
            DefaultTableModel table = (DefaultTableModel) m.jTable1.getModel();
            table.setRowCount(0);

            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery(" select * from tblcostumer ");

            while (rs.next()) {
                Vector r = new Vector();

                r.add(rs.getString(1));
                r.add(rs.getString(2));
                r.add(rs.getString(3));
                r.add(rs.getString(4));
                r.add(rs.getString(5));
                r.add(rs.getString(6));
                r.add(rs.getString(7));
                r.add(rs.getString(8));
                r.add(rs.getString(9));
                table.addRow(r);
            }
        } catch (Exception x) {
            System.out.println(x.getMessage());

        }
    }

    public void data() {

        try {
            DefaultTableModel table1 = (DefaultTableModel) m.jTable2.getModel();
            table1.setRowCount(0);

            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery(" select * from tblproduct ");

            while (rs.next()) {
                Vector r = new Vector();

                r.add(rs.getString(1));
                r.add(rs.getString(2));
                r.add(rs.getString(3));
                r.add(rs.getString(4));
                r.add(rs.getString(5));
                r.add(rs.getString(6));
                r.add(rs.getString(7));
                r.add(rs.getString(8));
                r.add(rs.getString(9));
                table1.addRow(r);
            }
        } catch (Exception x) {
            System.out.println(x.getMessage());

        }
    }

    public void sdata() {

        try {
            DefaultTableModel table1 = (DefaultTableModel) m.jTable4.getModel();
            table1.setRowCount(0);

            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery("select `selling_id`,`product_id`,`product_name`,sum(`quantity`),sum(`total_price`) FROM `tblselling`  GROUP BY `product_id` order by sum(`quantity`) desc");

            while (rs.next()) {

                Vector r = new Vector();

                r.add(rs.getString(1));
                r.add(rs.getString(2));
                r.add(rs.getString(3));
                r.add(rs.getString(4));
                r.add(rs.getString(5));
                table1.addRow(r);

            }
        } catch (Exception x) {
            System.out.println(x.getMessage());

        }
    }

    public void stockdata() {

        try {
            DefaultTableModel table1 = (DefaultTableModel) m.jTable5.getModel();
            table1.setRowCount(0);

            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery("select prod_id,product_name,quantity from tblproduct");

            while (rs.next()) {
                Vector r = new Vector();

                r.add(rs.getString(1));
                r.add(rs.getString(2));
                r.add(rs.getString(3));
                table1.addRow(r);
            }
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
    }

    public void total_purchase() {

        int numofrow = jTable3.getRowCount();

        double total = 0;
        int i;

        for (i = 0; i < numofrow; i++) {

            double value = Double.valueOf(jTable3.getValueAt(i, 4).toString());
            total += value;
        }

        total_purchase.setText(Double.toString(total));

        int numofrows = jTable3.getRowCount();
        double totals = 0;
        int ii;
        for (ii = 0; ii < numofrows; ii++) {

            double values = Double.valueOf(jTable3.getValueAt(ii, 2).toString());
            totals += values;

        }
        total_qty.setText(Double.toString(totals));

        int row = m.jTable4.getRowCount();
        double totalsss = 0;
        int iiii;
        for (iiii = 0; iiii < row; iiii++) {

            double values = Double.valueOf(m.jTable4.getValueAt(iiii, 3).toString());
            totalsss += values;

        }
        m.tot_quantity.setText(Double.toString(totalsss));

        int rowS = m.jTable6.getRowCount();
        double totalssss = 0;
        int iiiii;
        for (iiiii = 0; iiiii < rowS; iiiii++) {

            double values = Double.valueOf(m.jTable6.getValueAt(iiiii, 4).toString());
            totalssss += values;

        }
        m.Total_Income.setText(Double.toString(totalssss));

    }

    public void mark_up() {

        Double mar = Double.valueOf(mark_p.getText());
        Double qty = Double.valueOf(jSpinner1.getValue().toString());
        Double mark_Up;

        mark_Up = mar * qty;

        mark_p.setText(String.valueOf(mark_Up));
    }

    public void dataLoad() {

        try {

            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery("select * from tblnum where numid = 1");

            if (rs.next()) {
                jLabel75.setText(rs.getString("value"));
            }
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
        int i = Integer.valueOf(jLabel75.getText());
        i++;
        jLabel75.setText(String.valueOf(i));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        POS = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        stocks = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        panel_cart = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        total_purchase = new javax.swing.JLabel();
        itemField1 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        itemNameArea = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        avail_stocks = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        mark_p = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        paid = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        change = new javax.swing.JLabel();
        pAy = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        rEmove = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        rEfresh = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        t_price = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        total_qty = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        product_id = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        unit_price = new javax.swing.JLabel();
        postime = new javax.swing.JLabel();
        posdate = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        sellingID = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        log = new javax.swing.JLabel();
        jPanel65 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        POS.setBackground(new java.awt.Color(255, 255, 255));
        POS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                POSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                POSMouseExited(evt);
            }
        });
        POS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 17, 65));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Available Stocks :");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Select product :");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel32.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Quantity :");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        stocks.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        stocks.setForeground(new java.awt.Color(255, 255, 255));
        stocks.setText("0");
        jPanel4.add(stocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jPanel9.setBackground(new java.awt.Color(230, 240, 230));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTable3.setBackground(new java.awt.Color(0, 45, 156));
        jTable3.setForeground(new java.awt.Color(255, 255, 255));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Id", "Product name", "Quantity", "Unit price", "Total price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable3MouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 600, 240));

        panel_cart.setBackground(new java.awt.Color(237, 245, 255));

        jLabel40.setBackground(new java.awt.Color(237, 245, 255));
        jLabel40.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_shopping_cart_20px.png"))); // NOI18N
        jLabel40.setText("Add to Cart");
        jLabel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel40MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel40MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel_cartLayout = new javax.swing.GroupLayout(panel_cart);
        panel_cart.setLayout(panel_cartLayout);
        panel_cartLayout.setHorizontalGroup(
            panel_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_cartLayout.setVerticalGroup(
            panel_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(panel_cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(398, 60, 150, 40));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        jSpinner1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jSpinner1KeyReleased(evt);
            }
        });
        jPanel4.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 145, -1));

        jPanel7.setBackground(new java.awt.Color(0, 17, 65));

        jLabel48.setFont(new java.awt.Font("Segoe UI Emoji", 0, 20)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Sub Total");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 0)));

        total_purchase.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        total_purchase.setForeground(new java.awt.Color(204, 0, 0));
        total_purchase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_purchase.setText("0.0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(total_purchase, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(total_purchase, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel48)
                .addGap(33, 33, 33)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel48)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 590, 70));

        itemField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemField1ActionPerformed(evt);
            }
        });
        jPanel4.add(itemField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 170, 20));

        itemNameArea.setColumns(1);
        itemNameArea.setRows(1);
        itemNameArea.setTabSize(4);
        itemNameArea.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                itemNameAreaPropertyChange(evt);
            }
        });
        itemNameArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemNameAreaKeyPressed(evt);
            }
        });
        jScrollPane10.setViewportView(itemNameArea);

        jPanel4.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 170, -1));

        POS.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 590, 420));

        jPanel5.setBackground(new java.awt.Color(0, 45, 156));

        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("1");

        jLabel76.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_tick_box_20px_3.png"))); // NOI18N
        jLabel76.setText("Transaction No.");

        jLabel73.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_bell_20px.png"))); // NOI18N
        jLabel73.setText("0");
        jLabel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel73MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel76)
                .addGap(28, 28, 28)
                .addComponent(jLabel75)
                .addGap(148, 148, 148)
                .addComponent(jLabel73)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(jLabel76)
                    .addComponent(jLabel73))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        POS.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 590, 20));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        avail_stocks.setForeground(new java.awt.Color(255, 255, 0));
        avail_stocks.setText("0.0");

        jLabel61.setForeground(new java.awt.Color(145, 194, 141));
        jLabel61.setText("0.0");

        jLabel62.setForeground(new java.awt.Color(145, 194, 141));
        jLabel62.setText("0.0");

        mark_p.setBackground(new java.awt.Color(145, 194, 141));
        mark_p.setForeground(new java.awt.Color(145, 194, 141));
        mark_p.setText("0.0");

        jLabel77.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_apple_logo_20px.png"))); // NOI18N
        jLabel77.setText("Paid amount");

        paid.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        paid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paid.setText("0.0");
        paid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        paid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                paidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paidKeyReleased(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_logo_30px.png"))); // NOI18N
        jLabel78.setText("Change");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        change.setBackground(new java.awt.Color(255, 255, 255));
        change.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        change.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        change.setText("0.0");
        change.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(change, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(change, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel77)
                .addGap(18, 18, 18)
                .addComponent(paid, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel78)
                .addGap(28, 28, 28)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(avail_stocks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel62)
                        .addGap(51, 51, 51)
                        .addComponent(mark_p)
                        .addGap(111, 111, 111))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(avail_stocks)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mark_p))
                        .addGap(16, 16, 16)
                        .addComponent(jLabel61))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(paid, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel77)
                        .addComponent(jLabel78))
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        POS.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 850, -1));

        pAy.setBackground(new java.awt.Color(130, 207, 255));
        pAy.setForeground(new java.awt.Color(255, 255, 255));

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_tick_box_30px_1.png"))); // NOI18N
        jLabel42.setText("Done");
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel42MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel42MouseExited(evt);
            }
        });

        javax.swing.GroupLayout pAyLayout = new javax.swing.GroupLayout(pAy);
        pAy.setLayout(pAyLayout);
        pAyLayout.setHorizontalGroup(
            pAyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pAyLayout.setVerticalGroup(
            pAyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        POS.add(pAy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 140, -1));

        rEmove.setBackground(new java.awt.Color(130, 207, 255));

        jLabel52.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_file_delete_30px.png"))); // NOI18N
        jLabel52.setText("Remove");
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel52MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel52MouseExited(evt);
            }
        });

        javax.swing.GroupLayout rEmoveLayout = new javax.swing.GroupLayout(rEmove);
        rEmove.setLayout(rEmoveLayout);
        rEmoveLayout.setHorizontalGroup(
            rEmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rEmoveLayout.setVerticalGroup(
            rEmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        POS.add(rEmove, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 140, -1));

        rEfresh.setBackground(new java.awt.Color(130, 207, 255));

        jLabel53.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_refresh_30px.png"))); // NOI18N
        jLabel53.setText("Refresh");
        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel53MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel53MouseExited(evt);
            }
        });

        javax.swing.GroupLayout rEfreshLayout = new javax.swing.GroupLayout(rEfresh);
        rEfresh.setLayout(rEfreshLayout);
        rEfreshLayout.setHorizontalGroup(
            rEfreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rEfreshLayout.setVerticalGroup(
            rEfreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        POS.add(rEfresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 550, 140, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel34.setText("Total unit price :");
        jPanel8.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        t_price.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        t_price.setText("0.0");
        jPanel8.add(t_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jLabel35.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel35.setText("Total quantity :");
        jPanel8.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        total_qty.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        total_qty.setText("0.0");
        jPanel8.add(total_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setText("Product Id :");
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        product_id.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        product_id.setText("0.0");
        jPanel8.add(product_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel33.setText("Unit price :");
        jPanel8.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        unit_price.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        unit_price.setText("0.0");
        jPanel8.add(unit_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, -1));

        postime.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        postime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_clock_20px_3.png"))); // NOI18N
        postime.setText("TIME");
        jPanel8.add(postime, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        posdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_calendar_8_20px.png"))); // NOI18N
        posdate.setText("DATE");
        jPanel8.add(posdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        jPanel20.setBackground(new java.awt.Color(153, 153, 153));
        jPanel20.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 153)));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 240, 10));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Bradley Hand ITC", 1, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Date & Time");
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel8.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, -1));

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 51, 153)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 260, 10));

        jLabel50.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel50.setText("Selling ID :");
        jPanel8.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        sellingID.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        sellingID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sellingID.setText("0");
        jPanel8.add(sellingID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 30, -1));

        jLabel79.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_parenting_20px.png"))); // NOI18N
        jLabel79.setText("Costumer :");
        jPanel8.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular Costumer" }));
        jPanel8.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 140, -1));

        POS.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 260, 440));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel80.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_add_shopping_cart_20px.png"))); // NOI18N
        jLabel80.setText("Point Of Sale System");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(jLabel80)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        POS.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 30));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel36.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel36.setText("Logged in :");

        log.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        log.setForeground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(log, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        POS.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 550, 260, -1));

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));

        jLabel106.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("Log Out ");
        jLabel106.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jLabel106.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel106MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel106MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel106MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel106, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        POS.add(jPanel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 580, 260, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 855, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(POS, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(POS, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int t = jTable3.getSelectedRow();
        String p = jTable3.getValueAt(t, 0).toString();
        String pn = jTable3.getValueAt(t, 1).toString();
        String bc = jTable3.getValueAt(t, 2).toString();
        String qty = jTable3.getValueAt(t, 4).toString();

        jLabel62.setText(bc);
        try {
            DefaultTableModel table = (DefaultTableModel) m.jTable5.getModel();
            table.setRowCount(0);
            Statement state = Home_source.mycon().createStatement();
            ResultSet rs = state.executeQuery("SELECT `selling_id`,`product_id`,`unit_price`,`product_name`,`quantity` FROM `tblselling` where `product_id` = '" + p + "' and `product_name` = '" + pn + "' and `quantity` =  '" + bc + "' and `total_price`= '" + qty + "'");
            while (rs.next()) {
                sellingID.setText(rs.getString("selling_id"));
                product_id.setText(rs.getString("product_id"));
                unit_price.setText(rs.getString("unit_price"));
                itemField1.setText(rs.getString("product_name"));
                total_qty.setText(rs.getString("quantity"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            DefaultTableModel table = (DefaultTableModel) m.jTable5.getModel();
            table.setRowCount(0);
            Statement state = Home_source.mycon().createStatement();
            ResultSet sr = state.executeQuery("SELECT `quantity`,`prod_id` FROM `tblproduct` where `prod_id` = '" + p + "'");
            while (sr.next()) {
                jLabel61.setText(sr.getString("quantity"));
                product_id.setText(sr.getString("prod_id"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        double Stock = Double.valueOf(jLabel61.getText());
        double qUantity = Double.valueOf(jLabel62.getText());

        double tOtal = Stock + qUantity;
        jLabel62.setText(String.valueOf(tOtal));
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseEntered

    }//GEN-LAST:event_jTable3MouseEntered

    private void jTable3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseExited

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked

        // TODO add your handling code here:
        DefaultTableModel table = (DefaultTableModel) jTable3.getModel();
        int r = 0;

        if (product_id.getText().equals("0.0")) {
            JOptionPane.showMessageDialog(null, "Please Input Bar Code");
        } else if (jSpinner1.getValue().equals(r)) {
            JOptionPane.showMessageDialog(null, "Please click quantity");
        } else {
            try {
                mark_up();
                Statement state = Home_source.mycon().createStatement();
                state.executeUpdate("insert into tblselling (`product_id`,`product_name`,`quantity`,`total_price`,`unit_price`,`profit`,`date`,`time`) values ('" + product_id.getText() + "','" + itemNameArea.getText() + "','" + jSpinner1.getValue().toString() + "','" + t_price.getText() + "','" + unit_price.getText() + "','" + mark_p.getText() + "','" + posdate.getText() + "','" + postime.getText() + "')");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            try {

                Statement state = Home_source.mycon().createStatement();
                state.executeUpdate("update tblproduct set quantity = '" + stocks.getText() + "' where prod_id = '" + product_id.getText() + "'");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            Vector v = new Vector();

            v.add(product_id.getText());
            v.add(itemNameArea.getText());
            v.add(jSpinner1.getValue().toString());
            v.add(unit_price.getText());
            v.add(t_price.getText());
            table.addRow(v);
            total_purchase();

            OOP.number(avail_stocks, mark_p, unit_price, t_price, product_id, stocks);
            jSpinner1.setValue(r);
            itemField1.setText("");
        }
        data();
        paid.setText("0.0");
        change.setText("0.0");
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseEntered
        // TODO add your handling code here:
        OOP.recolor(panel_cart);
        jLabel40.setForeground(Color.green);
    }//GEN-LAST:event_jLabel40MouseEntered

    private void jLabel40MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseExited
        // TODO add your handling code here:
        OOP.color(panel_cart);
        jLabel40.setForeground(Color.blue);
    }//GEN-LAST:event_jLabel40MouseExited

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:

        int t = 0;
        if (avail_stocks.getText().equals("0.0")) {
            jSpinner1.setValue(t);

        } else if (!sellingID.getText().equals("0")) {
            jSpinner1.setValue(t);
            stocks.setText("0.0");
        } else {

        }
        OOP.availableStocks(jSpinner1, avail_stocks, stocks);
        OOP.totalPurchase(jSpinner1, unit_price, t_price);

        data();
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinner1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinner1KeyReleased

    private void itemField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemField1ActionPerformed

        //commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmment
        String search = itemField1.getText();

        if (!sellingID.getText().equals("0")) {
            stocks.setText("0.0");
        } else {
            try {
                Statement state = Home_source.mycon().createStatement();
                ResultSet rs = state.executeQuery("select prod_id,price,product_name,mark_up,quantity  from tblproduct where bar_code =  '" + search + "'");

                if (rs.next()) {

                    product_id.setText(rs.getString("prod_id"));
                    mark_p.setText(rs.getString("mark_up"));
                    avail_stocks.setText(rs.getString("quantity"));
                    unit_price.setText(rs.getString("price"));

                } else {
                }

                OOP.totalPurchase(jSpinner1, unit_price, t_price);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_itemField1ActionPerformed

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jLabel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel73MouseClicked
        // TODO add your handling code here:
        if (jLabel73.getText().equals("0")) {
        } else {
            OOP.scave(m.Stock_in, m.home, POS, m.Product, m.Costumer, m.Selling, m.Income, m.Removed, m.Invoice);
            
            try {
                DefaultTableModel table = (DefaultTableModel) m.jTable5.getModel();
                table.setRowCount(0);
                Statement state = Home_source.mycon().createStatement();
                ResultSet rs = state.executeQuery("SELECT `prod_id`,`product_name`,`quantity` FROM `tblproduct` WHERE `quantity` = '" + 0.0 + "'");

                while (rs.next()) {

                    Object o[] = {rs.getString("prod_id"), rs.getString("product_name"), rs.getString("quantity")};
                    table.addRow(o);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jLabel73MouseClicked

    private void paidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidKeyPressed
        // TODO add your handling code here:
        if (paid.getText().equals("0.0")) {
            paid.setText("");
        }
    }//GEN-LAST:event_paidKeyPressed

    private void paidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidKeyReleased
        // TODO add your handling code here:
        if (paid.getText().isEmpty()) {
            change.setText("0.0");
            paid.setText("0.0");
        } else if (total_purchase.getText().equals("0.0")) {
            paid.setText("0.0");
        } else {
            Double am = Double.valueOf(total_purchase.getText());
            Double total = Double.valueOf(paid.getText());

            Double paidd;

            paidd = total - am;

            change.setText(String.valueOf(paidd));
        }
    }//GEN-LAST:event_paidKeyReleased

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
        if (total_qty.getText().equals("0.0")) {

        } else if (paid.getText().equals("0.0") && jComboBox2.getSelectedItem().equals("Regular Costumer")) {

        } else {
            Double tot = Double.valueOf(total_purchase.getText());
            Double p = Double.valueOf(paid.getText());

            String status = null;

            if (p.equals(0.0)) {
                status = "Unpaid";
            } else if (tot > p) {
                status = "Partial";
            } else if (tot <= p) {
                status = "Paid";
            }

            try {
                String idd = jLabel75.getText();
                Statement state = Home_source.mycon().createStatement();
                state.executeUpdate("update tblnum set value = '" + idd + "' where numid = 1");

            } catch (Exception x) {
                System.out.println(x.getMessage());
            }

            try {

                Statement state = Home_source.mycon().createStatement();
                state.executeUpdate("insert into tblinvoice (`user`,`Costumer`,`paid_amount`,`Status`,`Balance`,`sub_total`,`total_quantity`,`transaction_no`,`date`,`time`) values ('" + log.getText() + "','" + jComboBox2.getSelectedItem().toString() + "','" + paid.getText() + "','" + status + "','" + change.getText() + "','" + total_purchase.getText() + "','" + total_qty.getText() + "','" + jLabel75.getText() + "','" + posdate.getText() + "','" + postime.getText() + "')");
                jComboBox2.setSelectedItem("Regular Costumer");
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Transaction Completed..");
            paid.setText("0.0");
            total_purchase.setText("0.0");
            change.setText("0.0");
            total_qty.setText("0.0");
            DefaultTableModel table = (DefaultTableModel) jTable3.getModel();
            table.setRowCount(0);
        }
        dataLoad();
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jLabel42MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseEntered
        // TODO add your handling code here:
        OOP.colorfull(pAy);
        jLabel42.setForeground(Color.black);
    }//GEN-LAST:event_jLabel42MouseEntered

    private void jLabel42MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseExited
        // TODO add your handling code here:\
        OOP.colorful(pAy);
        jLabel42.setForeground(Color.white);
    }//GEN-LAST:event_jLabel42MouseExited

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        // TODO add your handling code here:
        if (jLabel62.getText().equals("0.0")) {
        } else {
            try {
                DefaultTableModel table = (DefaultTableModel) jTable3.getModel();
                int rem = jTable3.getSelectedRow();
                Statement state = Home_source.mycon().createStatement();

                state.executeUpdate("delete from tblselling where selling_id = '" + sellingID.getText() + "'");
                state.executeUpdate("update tblproduct set `quantity` = '" + jLabel62.getText() + "' where prod_id = '" + product_id.getText() + "'");
                state.execute("insert into tblremove (`remove_by`,`product_id`,`product_name`,`quantity`,`unit_price`,`date`,`time`) values ('" + log.getText() + "','" + product_id.getText() + "','" + itemField1.getText() + "','" + total_qty.getText() + "','" + unit_price.getText() + "','" + posdate.getText() + "','" + postime.getText() + "')");

                table.removeRow(rem);
                m.total_purchase();
                sellingID.setText("0");
                jLabel62.setText("0.0");
                jLabel61.setText("0.0");
                product_id.setText("0.0");
                unit_price.setText("0.0");
                itemField1.setText("Input Bar Code: ");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            paid.setText("0.0");
            change.setText("0.0");
        }
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jLabel52MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseEntered
        // TODO add your handling code here:
        OOP.colorful(rEmove);
        jLabel52.setForeground(Color.black);
    }//GEN-LAST:event_jLabel52MouseEntered

    private void jLabel52MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseExited
        // TODO add your handling code here:
        OOP.colorfull(rEmove);
        jLabel52.setForeground(Color.white);
    }//GEN-LAST:event_jLabel52MouseExited

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
        // TODO add your handling code here:
        jTable3.clearSelection();
        sellingID.setText("0");
        product_id.setText("0");
        jLabel62.setText("0.0");
        jLabel61.setText("0.0");
        unit_price.setText("0");
        t_price.setText("0.0");
        itemField1.setText("Input Bar Code: ");
        stocks.setText("0.0");
        total_purchase();
        jComboBox2.setSelectedItem("Regular Costumer");
    }//GEN-LAST:event_jLabel53MouseClicked

    private void jLabel53MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseEntered
        // TODO add your handling code here:
        OOP.colorful(rEfresh);
        jLabel53.setForeground(Color.black);
    }//GEN-LAST:event_jLabel53MouseEntered

    private void jLabel53MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseExited
        // TODO add your handling code here:
        OOP.colorfull(rEfresh);
        jLabel53.setForeground(Color.white);
    }//GEN-LAST:event_jLabel53MouseExited

    private void jLabel106MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel106MouseClicked
        // TODO add your handling code here:
        Login Log = new Login();
        if (total_qty.getText().equals("0.0")) {
            Log.show();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "One or more transaction may not completed!!");
        }
    }//GEN-LAST:event_jLabel106MouseClicked

    private void jLabel106MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel106MouseEntered
        // TODO add your handling code here:
        jLabel106.setForeground(Color.red);
    }//GEN-LAST:event_jLabel106MouseEntered

    private void jLabel106MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel106MouseExited
        // TODO add your handling code here:
        jLabel106.setForeground(Color.black);
    }//GEN-LAST:event_jLabel106MouseExited

    private void POSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_POSMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_POSMouseEntered

    private void POSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_POSMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_POSMouseExited

    private void itemNameAreaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_itemNameAreaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameAreaPropertyChange

    private void itemNameAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemNameAreaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameAreaKeyPressed

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
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel POS;
    private javax.swing.JLabel avail_stocks;
    private javax.swing.JLabel change;
    public javax.swing.JTextField itemField1;
    private javax.swing.JTextArea itemNameArea;
    public javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    public javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField2;
    public javax.swing.JLabel log;
    private javax.swing.JLabel mark_p;
    private javax.swing.JPanel pAy;
    private javax.swing.JTextField paid;
    private javax.swing.JPanel panel_cart;
    private javax.swing.JLabel posdate;
    private javax.swing.JLabel postime;
    private javax.swing.JLabel product_id;
    private javax.swing.JPanel rEfresh;
    private javax.swing.JPanel rEmove;
    private javax.swing.JLabel sellingID;
    private javax.swing.JLabel stocks;
    private javax.swing.JLabel t_price;
    private javax.swing.JLabel total_purchase;
    private javax.swing.JLabel total_qty;
    private javax.swing.JLabel unit_price;
    // End of variables declaration//GEN-END:variables
}
