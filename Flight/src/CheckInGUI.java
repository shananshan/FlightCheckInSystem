//import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CheckInGUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JButton switchButton = new JButton("Submit");
    private JTextField textField1 = new JTextField(15), textField2 = new JTextField(15);
    private JLabel fname = new JLabel("Full Name:"), fcode = new JLabel("Flight Code:"), fbcode = new JLabel("Booking Code:");
    private JTextField[] filedBags = new JTextField[4];
    private JLabel[] labels = new JLabel[4];
    private String[] names = {"Weight", "Height", "Length", "Width"};
    private String[] units = {"KG", "CM", "CM", "CM"};
    private String[] name2 = {"Full Name", "Flight Code", "Booking Code", "Fee"};
    private FlightCheckInSystem fcs = new FlightCheckInSystem();
    public static String[] checkinInfoS = new String[3];
    public static Double[] checkinInfoD = new Double[3];
    ArrayList checkinpassengerList = new ArrayList<>();

    public CheckInGUI() throws IOException {
        initializeUI();
    }

    private void initializeUI() throws IOException {
        setFrameProperties();
        loadData();
        createCardPanel();
        setupSwitchButton();
        add(cardPanel, BorderLayout.CENTER);
        add(switchButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void setFrameProperties() {
        setTitle("Flight Check-In System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    private void loadData() {
        try {
            fcs.readPassengers("Passenger Bookings.csv");
            fcs.readFlights("Flight Detail.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCardPanel() {
        cardPanel.add(createCard1(), "Card1");
        cardPanel.add(createCard2(), "Card2");
        cardPanel.add(createCard3(), "Card3");
        cardPanel.add(createCard4(), "Card4");
    }

    private JPanel createCard1() {
        
        // Add components to card1...
    	  // page1  ask for the input part
        JPanel card1 = new JPanel(new GridLayout(3, 1)); // 使用GridLayout使组件垂直居中
        card1.setName("Card1");
//        JTextField textField1 = new JTextField(15); // 设置文本框的宽度
//        JTextField textField2 = new JTextField(15);
        // 创建包含标题的面板，使用FlowLayout居中对齐
        JPanel welcomeTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel tname = new JLabel("Check In Here!");
        tname.setFont(new Font("Serif", Font.BOLD, 20)); // 设置文字大小
        welcomeTitle.add(tname);
        // 创建输入姓氏的面板，使用FlowLayout居中对齐
        JPanel inputLastName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputLastName.add(new JLabel("Input Last Name:     ")); // 添加标签来标识输入框
        inputLastName.setFont(inputLastName.getFont().deriveFont(18f));// change the size of the lable
        inputLastName.add(textField1); // 添加文本框
        // 创建输入预定码的面板，使用FlowLayout居中对齐
        JPanel inputBookingCode = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputBookingCode.add(new JLabel("Input Booking code:"));
        inputBookingCode.setFont(inputBookingCode.getFont().deriveFont(18f));// change the size of the lable
        inputBookingCode.add(textField2); // 添加文本框
        // 将面板添加到card1
        card1.add(welcomeTitle);
        card1.add(inputLastName);
        card1.add(inputBookingCode);
        return card1;
       
    }

    private JPanel createCard2() {
    	 // this is for input the volume and weight of the baggage
        JPanel card2 = new JPanel();
        card2.setName("Card2");
        card2.add(new JLabel("Enter your baggage information."));

//        JPanel BaggageTit = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel PassDetail = new JPanel(new FlowLayout(FlowLayout.CENTER));
        PassDetail.setLayout(new GridLayout(2, 2));
        PassDetail.add(fname);
        PassDetail.add(fcode);
        PassDetail.add(fbcode);
        JPanel inputBagg = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputBagg.setLayout(new GridLayout(2, 2));
        JPanel [] baggs = new JPanel[4];
//        JTextField[] filedBags = new JTextField[4];
        for(int i=0; i<4; ++i){
            baggs[i] = new JPanel();
            baggs[i].add(new JLabel(names[i] + ":"));
            filedBags[i] = new JTextField(10);
            baggs[i].add(filedBags[i]);
            baggs[i].add(new JLabel(units[i]));
            inputBagg.add(baggs[i]);
        }
//        card2.add(BaggageTit);
        card2.add(PassDetail);
        card2.add(inputBagg);
        return card2;
    }

    private JPanel createCard3() {
    	 // get the output of the confirmation
        JPanel card3 = new JPanel();
        card3.setName("Card3");
        card3.add(new JLabel("Please review your details"));
        JPanel chek = new JPanel(new FlowLayout(FlowLayout.CENTER));
        chek.setLayout(new GridLayout(2, 2));
        JPanel [] res = new JPanel[4];
//        JLabel [] labels = new JLabel[4];
//        String[] name2 = {"Full Name", "Flight Code", "Booking Code", "Fee"};
        for(int i=0; i<4; ++i){
            res[i] = new JPanel();
            labels[i] = new JLabel(name2[i] + ":");
            res[i].add(labels[i]);
            chek.add(res[i]);
        }
        card3.add(chek);
        return card3;
    }


    private JPanel createCard4() {
        JPanel card4 = new JPanel(new BorderLayout()); // 使用BorderLayout布局
        card4.setName("Card4");

        JLabel paymentLabel = new JLabel("Please pay your excess baggage fee: ", SwingConstants.CENTER);
        card4.add(paymentLabel, BorderLayout.NORTH);
//
//        JPanel feePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        feePanel.add(new JLabel("Fee: £" + checkinInfoD[2]));
//        if (checkinInfoD[2] != null) {
//            feePanel.add(new JLabel("Fee: £" + checkinInfoD[2]));
//        } else {
//            feePanel.add(new JLabel("Fee: Not Calculated"));
//        }
//        card4.add(feePanel, BorderLayout.CENTER);
////        JButton payButton = new JButton("Pay Fee");
////        payButton.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                // 支付逻辑处理
////                JOptionPane.showMessageDialog(card4, "Payment Successful!");
////                cardLayout.show(cardPanel, "Card1"); // 支付成功后跳转回Card1
////            }
////        });
////        card4.add(payButton, BorderLayout.SOUTH);
//        card4.add(paymentLabel);

        return card4;
    }


    private void setupSwitchButton() {
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	handleSwitchButtonClick();
                
            }
        });
    }

    private void handleSwitchButtonClick() {
        String currentCard = getCurrentCardName(cardPanel);
        switch (currentCard) {
            case "Card1":
            	 System.out.println(textField1.getText());
                 System.out.println(textField2.getText());
                if(textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your last name and booking number!");
                } else if (!fcs.checkIn(textField1.getText(), textField2.getText())) {
                    JOptionPane.showMessageDialog(this, "Information mismatch. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (CheckInPassenger.duplicatePassenger(textField2.getText(), checkinpassengerList)) {
                    JOptionPane.showMessageDialog(this, "Passenger duplicate", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	System.out.println("success");
                    Passenger p = fcs.getPassenger(textField1.getText(), textField2.getText());
                    fname.setText("Full Name: " + p.name);
                    fcode.setText("Flight Code: " + p.flightCode);
                    fbcode.setText("Booking Code: " + p.bookingRefCode);
                    checkinInfoS[0] = p.name;
                    checkinInfoS[1] = p.flightCode;
                    checkinInfoS[2] = textField2.getText();
                    cardLayout.show(cardPanel, "Card2");
                    switchButton.setText("Submit Baggage Info");
                }
                break;
            case "Card2":
            	Passenger p = fcs.getPassenger(textField1.getText(), textField2.getText());
                Flight f = fcs.getFlight(p.getFlightCode());
                try {
                	double w, h, l, wi;
                    w = Double.valueOf(filedBags[0].getText());
                    h = Double.valueOf(filedBags[1].getText());
                    l = Double.valueOf(filedBags[2].getText());
                    wi = Double.valueOf(filedBags[3].getText());
                    double fee = f.calculateFee(wi,h,l,w);
//                    double fee = 50;
                    System.out.println(fee);
                    
                    labels[0].setText(name2[0] + ": " + p.name);
                    labels[1].setText(name2[1] + ": " + p.flightCode);
                    labels[2].setText(name2[2] + ": " + p.bookingRefCode);
                    labels[3].setText(name2[3] + ": " + Double.toString(fee)+"£");
                    if(filedBags[0].getText().isEmpty() && filedBags[1].getText().isEmpty() && filedBags[2].getText().isEmpty() && filedBags[3].getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter valid numbers for baggage dimensions and weight!");
                        return;
                    }
                    System.out.println(fee);
                    checkinInfoD[0] = wi*h*l;
                    checkinInfoD[1] = w;
                    checkinInfoD[2] = fee;
//                    System.out.println(checkinInfoD[2]);
                    if(fee > 0) {
//                    	  card4.add(new JLabel("Please pay your excess baggage fee:" + fee+"£"));
//                       
                        
                       switchButton.setText("Pay");
                       cardLayout.show(cardPanel, "Card4");
                        
                    } else {
                        
                    	switchButton.setText("Exit");
                        cardLayout.show(cardPanel,"Card3");
                    }
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers for baggage dimensions and weight!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                CheckInPassenger cp = new CheckInPassenger(checkinInfoS[0], checkinInfoS[1], checkinInfoS[2], checkinInfoD[0], checkinInfoD[1], checkinInfoD[2]);
                checkinpassengerList.add(cp);
                Report report = new Report(checkinpassengerList);
                report.generateReport("report.txt");
                break;
            case "Card3":
                // Assuming Exit button is meant to conclude the process and reset for a new check-in
                resetForm();
                cardLayout.show(cardPanel, "Card1");
                switchButton.setText("Start Check-In");
                break;
            case "Card4":
                // Assuming payment is always successful for this example
//            	this.add(new JLabel("Please pay your excess baggage fee:" +checkinInfoD[2] +"£"));
//            	createCard4().add(new JLabel("Please pay your excess baggage fee:" +checkinInfoD[2] +"£"));
            	JOptionPane.showMessageDialog(this, "Your fee is: £" + checkinInfoD[2], "Fee Details", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(this, "Payment Successful! Thank you for checking in.", "Payment", JOptionPane.INFORMATION_MESSAGE);
                resetForm();
                cardLayout.show(cardPanel, "Card1");
                switchButton.setText("Start Check-In");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown card.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }


    private void resetForm() {
        // Reset all fields and data here
        textField1.setText("");
        textField2.setText("");
        for (JTextField textField : filedBags) {
            textField.setText("");
        }
        // Reset other UI components and data structures as needed
    }



    private String getCurrentCardName(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp.isVisible() && comp instanceof JPanel) {
                return ((JPanel) comp).getName();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new CheckInGUI().setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
//}
}
