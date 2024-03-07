import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CheckInGUI extends JFrame {
	
    private CardLayout cardLayout = new CardLayout(); // CardLayout to manage the different panels (cards) in the UI.
    private JPanel cardPanel = new JPanel(cardLayout); // Main panel that holds the cards managed by cardLayout.
    private JButton switchButton = new JButton(); // Button to switch between the cards based on the action performed.
    private JButton startCheckInButton = new JButton("Start Check-In"); // Button to initiate the check-in process from the welcome card.
    private JTextField textField1 = new JTextField(15), textField2 = new JTextField(15);  // user input: passenger last name and booking code.
    private JLabel fname = new JLabel("Full Name:"), fcode = new JLabel("Flight Code:"), fbcode = new JLabel("Booking Code:");// for baggage information input: weight, height, length, and width.
    private JTextField[] filedBags = new JTextField[4];
    private JLabel[] labels = new JLabel[4];
    private String[] names = {"Weight", "Height", "Length", "Width"};
    private String[] units = {"KG", "CM", "CM", "CM"};
    private String[] name2 = {"Full Name", "Flight Code", "Booking Code", "Fee"}; 
    private FlightCheckInSystem fcs = new FlightCheckInSystem(); //Instance of the system managing flight check-ins.
    public static String[] checkinInfoS = new String[3];
    public static Double[] checkinInfoD = new Double[3];
    ArrayList<CheckInPassenger> checkinpassengerList = new ArrayList<>(); // store all the checked passenger
    
 
    public CheckInGUI() throws IOException {
        initializeUI();
    }

    private void initializeUI() throws IOException {
        setFrameProperties();
        loadData();
        createCardPanel();
        setupSwitchButton();
        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    
    private void setFrameProperties() {
    	// set frame properties
        setTitle("Flight Check-In System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    private void loadData() {
    	//read the csv files
        try {
            fcs.readPassengers("Passenger Bookings.csv");
            fcs.readFlights("Flight Detail.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        } catch (IOException e) {
            e.printStackTrace(); 
        } catch (MyException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (AggregateException e) {
            StringBuilder errorMessage = new StringBuilder("Multiple errors occurred:");
            for (Exception innerException : e.getExceptions()) {
                errorMessage.append("\n").append(innerException.getMessage());
            }
            JOptionPane.showMessageDialog(this, errorMessage.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void createCardPanel() {
    	//add cards to cardpanel
    	cardPanel.add(createWelcomeCard(), "WelcomeCard");
        cardPanel.add(createCard1(), "Card1");
        cardPanel.add(createCard2(), "Card2");
        cardPanel.add(createCard3(), "Card3");
        cardPanel.add(createCard4(), "Card4");
    }

    private JPanel createWelcomeCard() {
    	//create the card for welcome page 
    	JPanel welcomeCard = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                ImageIcon background = new ImageIcon("15.gif");
                // set background picture
                g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        welcomeCard.setBackground(Color.WHITE); // Set background color to white
      
        startCheckInButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startCheckInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Card1"); // Navigate to the first check-in step
                add(switchButton, BorderLayout.SOUTH);
                switchButton.setText("Start Check-In");
            }
        });
        
        JPanel buttonPanel = new JPanel(); // Use a panel to better manage the layout of the button
        buttonPanel.setBackground(Color.WHITE); // Match the background with the card
        buttonPanel.add(startCheckInButton );
        welcomeCard.add(buttonPanel,BorderLayout.SOUTH);

        return welcomeCard;
    }
    
 
    private JPanel createCard1() {
        // Use GridLayout to organize components vertically in 3 rows and 1 column.
        JPanel card1 = new JPanel(new GridLayout(3, 1)); 
        card1.setName("Card1"); 
        
        // Create a panel for the welcome title using FlowLayout for center alignment.
        JPanel welcomeTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel tname = new JLabel("Check In Here!"); 
        tname.setFont(new Font("Serif", Font.BOLD, 25)); 
        welcomeTitle.add(tname); 
        
        // Create a panel for last name input with FlowLayout for center alignment.
        JPanel inputLastName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputLastName.add(new JLabel("Input Last Name:     ")); 
        inputLastName.setFont(inputLastName.getFont().deriveFont(18f)); 
        inputLastName.add(textField1); 
        
        // Create a panel for booking code input, similar layout as the last name panel.
        JPanel inputBookingCode = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputBookingCode.add(new JLabel("Input Booking code:")); 
        inputBookingCode.setFont(inputBookingCode.getFont().deriveFont(18f)); 
        inputBookingCode.add(textField2); 
        
        // Add the above panels to card1 according to the GridLayout manager.
        card1.add(welcomeTitle);
        card1.add(inputLastName);
        card1.add(inputBookingCode);
        
        return card1; // Return the constructed card panel.
    }



     private JPanel createCard2() {
        JPanel card2 = new JPanel();
        card2.setName("Card2");
        card2.setLayout(new BorderLayout()); // set BorderLayout

        // create baggage information
        JLabel baggageInfoTitle = new JLabel("Enter your baggage information.", SwingConstants.CENTER);
        baggageInfoTitle.setFont(new Font("Serif", Font.BOLD, 24));
        card2.add(baggageInfoTitle, BorderLayout.NORTH);
        //The passenger and baggage information panels are arranged vertically using BoxLayout
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        // add information of passengers
        JPanel passengerDetailsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passengerDetailsPanel.add(fname);
        passengerDetailsPanel.add(fcode);
        passengerDetailsPanel.add(fbcode);
        detailsPanel.add(passengerDetailsPanel);
        //  input baggage details
        JPanel baggageInputPanel = new JPanel(new GridLayout(2, 2, 5, 5));  
        for (int i = 0; i < 4; ++i) {
            JPanel bagPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel bagLabel = new JLabel(names[i] + ": " );
            bagLabel.setFont(new Font("Serif",Font.CENTER_BASELINE, 15));
            filedBags[i] = new JTextField(10);
            bagPanel.add(bagLabel);
            bagPanel.add(filedBags[i]);
            JLabel Label = new JLabel(units[i] );
            Label.setFont(new Font("Serif",Font.CENTER_BASELINE, 15));
            bagPanel.add(Label);
            baggageInputPanel.add(bagPanel);
        }
        detailsPanel.add(baggageInputPanel);

        // add details to card2
        card2.add(detailsPanel, BorderLayout.CENTER);

        return card2;
    }

    private JPanel createCard3() {
        JPanel card3 = new JPanel();
        card3.setLayout(new BorderLayout()); // Use BorderLayout for overall layout
        card3.setName("Card3");
        // Title
        JLabel title = new JLabel("Please review your details", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        card3.add(title, BorderLayout.NORTH);
        // Details panel with vertical layout
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        for (int i = 0; i < 4; ++i) {
            // Each detail in its own panel for horizontal alignment
            JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            labels[i] = new JLabel(name2[i] + ":");
            detailPanel.add(labels[i]);
            detailsPanel.add(detailPanel);
        }

        // Add the details panel to the center
        card3.add(detailsPanel, BorderLayout.CENTER);

        return card3;
    }

  
    private JPanel createCard4() {
        JPanel card4 = new JPanel(new BorderLayout()); 
        card4.setName("Card4");
       
        JLabel paymentLabel = new JLabel("Please pay your excess baggage fee", SwingConstants.CENTER);
        paymentLabel.setFont(new Font("Please pay your excess baggage fee", Font.BOLD, 20)); 
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
                checkinInfoS[0] = p.flightCode;
                checkinInfoS[1] = p.name;
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
                    w = Double.valueOf(filedBags[0].getText()); //width
                    h = Double.valueOf(filedBags[1].getText());//height
                    l = Double.valueOf(filedBags[2].getText());//length
                    wi = Double.valueOf(filedBags[3].getText());//weight
                    double fee = f.calculateFee(wi,h,l,w); // calculate the fee need to pay 
                    labels[0].setText(name2[0] + ": " + p.name);
                    labels[1].setText(name2[1] + ": " + p.flightCode);
                    labels[2].setText(name2[2] + ": " + p.bookingRefCode);
                    labels[3].setText(name2[3] + ": " + Double.toString(fee)+"£");
                    if(filedBags[0].getText().isEmpty() && filedBags[1].getText().isEmpty() && filedBags[2].getText().isEmpty() && filedBags[3].getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter valid numbers for baggage dimensions and weight!");
                        return;
                    }
                    
                    checkinInfoD[0] = wi*h*l;
                    checkinInfoD[1] = w;
                    checkinInfoD[2] = fee;
                    System.out.println(checkinInfoD[0]);
                    if(fee > 0) {                  
                       switchButton.setText("Pay");
                       cardLayout.show(cardPanel, "Card4");
                        
                    } else {
                    	switchButton.setText("Exit");
                        cardLayout.show(cardPanel,"Card3");
                    }
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers for baggage dimensions and weight!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                try{
                    // Add information about passengers who have successfully checked to checkinpassengerList
                    CheckInPassenger cp = new CheckInPassenger(checkinInfoS[0], checkinInfoS[1], checkinInfoS[2], checkinInfoD[0], checkinInfoD[1], checkinInfoD[2]);
                    // Add cp to checked list
                    checkinpassengerList.add(cp);
                }catch(MyException e){
                    System.out.println(e.getMessage());
                }

                // generate report
                Report report = new Report(checkinpassengerList);
                report.generateReport("report.txt");
                break;
            case "Card3":
                resetForm();
                cardLayout.show(cardPanel, "WelcomeCard");
                remove(switchButton); // remove switchButton
                this.validate(); //Refresh the interface to remove switchButton

                break;
            case "Card4":
                // Assuming payment is always successful for this example
            	JOptionPane.showMessageDialog(this, "Your fee is: £" + checkinInfoD[2], "Fee Details", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(this, "Payment Successful! Thank you for checking in.", "Payment", JOptionPane.INFORMATION_MESSAGE);
                resetForm();
                cardLayout.show(cardPanel, "WelcomeCard");
                remove(switchButton); // remove switchButton
                this.validate(); //Refresh the interface to remove switchButton
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
