import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class CheckInGUI {
    public CheckInGUI() throws IOException {
        FlightCheckInSystem fcs = new FlightCheckInSystem();
        fcs.readPassengers("Passenger Bookings.csv");
        fcs.readFlights("Flight Detail.csv");

        JFrame frame = new JFrame("CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // page1  ask for the input part
        JPanel card1 = new JPanel(new FlowLayout(0));
        card1.setName("Card1");
        card1.setLayout(new GridLayout(3, 1));
        JTextField textField1 = new JTextField(15); // 设置文本框的宽度
        JTextField textField2 = new JTextField(15);
        JPanel welcomeTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel tname = new JLabel("Check In Here!");
        welcomeTitle.add(tname);
        JPanel inputLastName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel inputBookingCode = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputLastName.add(new JLabel("Input Last Name:     ")); // 可以添加标签来标识输入框
        inputLastName.add(textField1);
        inputBookingCode.add(new JLabel("Input Booking code:"));
        inputBookingCode.add(textField2);
        card1.add(welcomeTitle);
        card1.add(inputLastName);
        card1.add(inputBookingCode);
        

        // this is for input the volume and weight of the baggage
        JPanel card2 = new JPanel();
        card2.setName("Card2");
        card2.add(new JLabel("Enter your baggage information."));

        JPanel BaggageTit = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel btit = new JLabel("Baggage Details");
        BaggageTit.add(btit);
        JPanel PassDetail = new JPanel(new FlowLayout(FlowLayout.CENTER));
        PassDetail.setLayout(new GridLayout(2, 2));
        JLabel fname = new JLabel("Full Name:");
        JLabel fcode = new JLabel("Flight Code:");
        JLabel fbcode = new JLabel("Booking Code:");
        PassDetail.add(fname);
        PassDetail.add(fcode);
        PassDetail.add(fbcode);
        JPanel inputBagg = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputBagg.setLayout(new GridLayout(2, 2));
        JPanel [] baggs = new JPanel[4];
        JTextField[] filedBags = new JTextField[4];
        String[] names = {"Weight", "Height", "Length", "Width"};
        for(int i=0; i<4; ++i){
            baggs[i] = new JPanel();
            baggs[i].add(new JLabel(names[i] + ":"));
            filedBags[i] = new JTextField(10);
            baggs[i].add(filedBags[i]);
            inputBagg.add(baggs[i]);
        }
        card2.add(BaggageTit);
        card2.add(PassDetail);
        card2.add(inputBagg);


        // get the output of the confirmation
        JPanel card3 = new JPanel();
        card3.setName("Card3");
        card3.add(new JLabel("Please review your details"));

        JPanel chek = new JPanel(new FlowLayout(FlowLayout.CENTER));
        chek.setLayout(new GridLayout(2, 2));
        JPanel [] res = new JPanel[4];
        JLabel [] labels = new JLabel[4];
        String[] name2 = {"Full Name", "Flight Code", "Booking Code", "Fee"};
        for(int i=0; i<4; ++i){
            res[i] = new JPanel();
            labels[i] = new JLabel(name2[i] + ":");
            res[i].add(labels[i]);
            chek.add(res[i]);
        }
        card3.add(chek);

        cardPanel.add(card1, "Card 1");
        cardPanel.add(card2, "Card 2");
        cardPanel.add(card3, "Card 3");

        JButton switchButton = new JButton("Submit");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在按钮点击时切换到另一个卡片
                String currentCard = getCurrentCardName(cardPanel);
                if(Objects.equals(currentCard, "Card1")) {
                    System.out.println(textField1.getText());
                    System.out.println(textField2.getText());
                    if(!fcs.checkIn(textField1.getText(), textField2.getText())){
                        tname.setText("the input is invalid! Please input again!");
                        System.out.println("failed");
                        return;
                    }
                    else {
                        System.out.println("success");
                        // change to card2 which is the baggage part
                        Passenger p = fcs.getPassenger(textField1.getText(), textField2.getText());
                        fname.setText("Full Name:" + p.name);
                        fcode.setText("Flight Code:" + p.flightCode);
                        fbcode.setText("Booking Code:" + p.bookingRefCode);
                        switchButton.setText("Confirm");
                    }
                }
                else if(Objects.equals(currentCard, "Card2")) {
                    Passenger p = fcs.getPassenger(textField1.getText(), textField2.getText());
                    Flight f = fcs.getFlight(p.getFlightCode());
                    double w, h, l, wi;
                    w = Double.valueOf(filedBags[0].getText());
                    h = Double.valueOf(filedBags[1].getText());
                    l = Double.valueOf(filedBags[2].getText());
                    wi = Double.valueOf(filedBags[3].getText());
                    double fee = f.calculateFee(w,h,l,wi);
                    labels[0].setText(name2[0] + ": " + p.name);
                    labels[1].setText(name2[1] + ": " + p.flightCode);
                    labels[2].setText(name2[2] + ": " + p.bookingRefCode);
                    labels[3].setText(name2[3] + ": " + Double.toString(fee));
                    switchButton.setText("Exit");
                }
                else if(Objects.equals(currentCard, "Card3")) {

                }



                // deal with the right flow
                cardLayout.next(cardPanel);
                currentCard = getCurrentCardName(cardPanel);
                frame.setTitle(currentCard);
            }
        });

        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(switchButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private String getCurrentCardName(Container container) {
        for (Component component : container.getComponents()) {
            if (component.isVisible() && component instanceof JPanel) {
                return ((JPanel) component).getName();
            }
        }
        return null;
    }
}
