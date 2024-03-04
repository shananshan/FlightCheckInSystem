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
        JPanel card1 = new JPanel(new GridLayout(3, 1)); // 浣跨敤GridLayout浣跨粍浠跺瀭鐩村眳涓�
        card1.setName("Card1");
//        JTextField textField1 = new JTextField(15); // 璁剧疆鏂囨湰妗嗙殑瀹藉害
//        JTextField textField2 = new JTextField(15);
        // 鍒涘缓鍖呭惈鏍囬鐨勯潰鏉匡紝浣跨敤FlowLayout灞呬腑瀵归綈
        JPanel welcomeTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel tname = new JLabel("Check In Here!");
        tname.setFont(new Font("Serif", Font.BOLD, 20)); // 璁剧疆鏂囧瓧澶у皬
        welcomeTitle.add(tname);
        // 鍒涘缓杈撳叆濮撴皬鐨勯潰鏉匡紝浣跨敤FlowLayout灞呬腑瀵归綈
        JPanel inputLastName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputLastName.add(new JLabel("Input Last Name:     ")); // 娣诲姞鏍囩鏉ユ爣璇嗚緭鍏ユ
        inputLastName.setFont(inputLastName.getFont().deriveFont(18f));// change the size of the lable
        inputLastName.add(textField1); // 娣诲姞鏂囨湰妗�
        // 鍒涘缓杈撳叆棰勫畾鐮佺殑闈㈡澘锛屼娇鐢‵lowLayout灞呬腑瀵归綈
        JPanel inputBookingCode = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputBookingCode.add(new JLabel("Input Booking code:"));
        inputBookingCode.setFont(inputBookingCode.getFont().deriveFont(18f));// change the size of the lable
        inputBookingCode.add(textField2); // 娣诲姞鏂囨湰妗�
        // 灏嗛潰鏉挎坊鍔犲埌card1
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
//    	  get the output of the confirmation
        JPanel card3 = new JPanel(new BorderLayout());
        card3.setName("Card3");
        JLabel title = new JLabel("Please review your details", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        card3.add(title, BorderLayout.NORTH);

        JPanel chek = new JPanel(new FlowLayout(FlowLayout.CENTER));
        chek.setLayout(new GridLayout(2, 2));
        JPanel [] res = new JPanel[4];
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
        JPanel card4 = new JPanel(new BorderLayout()); // 浣跨敤BorderLayout甯冨眬
        card4.setName("Card4");
       
        JLabel paymentLabel = new JLabel("Please pay your excess baggage fee", SwingConstants.CENTER);
        paymentLabel.setFont(new Font("Please pay your excess baggage fee", Font.BOLD, 20)); // 璁剧疆鏂囧瓧澶у皬
        card4.add(paymentLabel, BorderLayout.CENTER);

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
        	try {
        	    if (!isValidBookingCode(textField2.getText())) {
        	        throw new MyException("Invalid booking code format. Please enter a code in the format 'XX-123456'.");
        	    }
        	} catch (MyException e) {
        	    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	    return;
        	}


            if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
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
                   
                    
                    labels[0].setText(name2[0] + ": " + p.name);
                    labels[1].setText(name2[1] + ": " + p.flightCode);
                    labels[2].setText(name2[2] + ": " + p.bookingRefCode);
                    labels[3].setText(name2[3] + ": " + Double.toString(fee)+"拢");
                    if(filedBags[0].getText().isEmpty() && filedBags[1].getText().isEmpty() && filedBags[2].getText().isEmpty() && filedBags[3].getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter valid numbers for baggage dimensions and weight!");
                        return;
                    }
                    System.out.println(fee);
                    checkinInfoD[0] = wi*h*l;
                    checkinInfoD[1] = w;
                    checkinInfoD[2] = fee;
                    System.out.println(checkinInfoD[0]);
                    if(fee > 0) {
//                    	  card4.add(new JLabel("Please pay your excess baggage fee:" + fee+"拢"));
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
//            	this.add(new JLabel("Please pay your excess baggage fee:" +checkinInfoD[2] +"拢"));
//            	createCard4().add(new JLabel("Please pay your excess baggage fee:" +checkinInfoD[2] +"拢"));
            	JOptionPane.showMessageDialog(this, "Your fee is: 拢" + checkinInfoD[2], "Fee Details", JOptionPane.INFORMATION_MESSAGE);
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

    private boolean isValidBookingCode(String bookingCode) {
        // Implement your booking code validation logic here
        // For example, check if the booking code follows the format 'XX-123456'
        // You can use regular expressions for this validation
        // Return true if valid, false otherwise
        return bookingCode.matches("[A-Z]{2}-\\d{6}");
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

}
