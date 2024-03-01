import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CheckInGUI extends JFrame{
    public static String[] checkinInfoS = new String[3];
    public static Double[] checkinInfoD = new Double[3];
    ArrayList checkinpassengerList = new ArrayList<>();
    public CheckInGUI() throws IOException {
        FlightCheckInSystem fcs = new FlightCheckInSystem();
        try {
            fcs.readPassengers("Passenger Bookings.csv");
            fcs.readFlights("Flight Detail.csv");
        }catch(FileNotFoundException e){
          e.printStackTrace();
        }catch(IOException e) {
          e.printStackTrace();
        }

        JFrame frame = new JFrame("Flight Check-In System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);


        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // page1  ask for the input part
        JPanel card1 = new JPanel(new GridLayout(3, 1)); // 使用GridLayout使组件垂直居中
        card1.setName("Card1");
        JTextField textField1 = new JTextField(15); // 设置文本框的宽度
        JTextField textField2 = new JTextField(15);
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


        // this is for input the volume and weight of the baggage
        JPanel card2 = new JPanel();
        card2.setName("Card2");
        card2.add(new JLabel("Enter your baggage information."));

        JPanel BaggageTit = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
        String[] units = {"KG", "CM", "CM", "CM"}; // 单位数组
        for(int i=0; i<4; ++i){
            baggs[i] = new JPanel();
            baggs[i].add(new JLabel(names[i] + ":"));
            filedBags[i] = new JTextField(10);
            baggs[i].add(filedBags[i]);
            baggs[i].add(new JLabel(units[i]));
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

        // Card4: transaction panel
        JPanel card4 = new JPanel();
        card4.setName("Card4");
        JPanel pay = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pay.setLayout(new GridLayout(2, 2));




        cardPanel.add(card1, "Card 1");
        cardPanel.add(card2, "Card 2");
        cardPanel.add(card3, "Card 3");
        cardPanel.add(card4, "Card 4");


        JButton switchButton = new JButton("Submit");

        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在按钮点击时切换到另一个卡片
                String currentCard = getCurrentCardName(cardPanel);
                if(Objects.equals(currentCard, "Card1")) {
                    System.out.println(textField1.getText());
                    System.out.println(textField2.getText());
                    if(textField1.getText().isEmpty() && textField2.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter your last name and booking number!");
                        return;
                    } else if(!fcs.checkIn(textField1.getText(), textField2.getText())){
                        tname.setText("the input is invalid! Please input again!");
                        System.out.println("failed");
                        JOptionPane.showMessageDialog(frame, "Information Mismatch");
                        return;
                    } else if (CheckInPassenger.duplicatePassenger(textField2.getText(), checkinpassengerList)) { //Repeat check-in
                        tname.setText("The passenger is duplicate! Please input again!");
                        System.out.println("failed");
                        JOptionPane.showMessageDialog(frame, "Passenger duplicate");
                        return;
                    } else {
                        System.out.println("success");
                        // change to card2 which is the baggage part
                        Passenger p = fcs.getPassenger(textField1.getText(), textField2.getText());
                        fname.setText("Full Name:" + p.name);
                        fcode.setText("Flight Code:" + p.flightCode);
                        fbcode.setText("Booking Code:" + p.bookingRefCode);
                        switchButton.setText("Confirm");
                        checkinInfoS[0] = p.name;
                        checkinInfoS[1] = p.flightCode;
                        checkinInfoS[2] = textField2.getText();
                    }
                    cardLayout.next(cardPanel);
                }
                if(Objects.equals(currentCard, "Card2")) {
                    Passenger p = fcs.getPassenger(textField1.getText(), textField2.getText());
                    Flight f = fcs.getFlight(p.getFlightCode());
                    try {
                        double w, h, l, wi;
                        w = Double.valueOf(filedBags[0].getText());
                        h = Double.valueOf(filedBags[1].getText());
                        l = Double.valueOf(filedBags[2].getText());
                        wi = Double.valueOf(filedBags[3].getText());
                        double fee = f.calculateFee(w,h,l,wi);
                        labels[0].setText(name2[0] + ": " + p.name);
                        labels[1].setText(name2[1] + ": " + p.flightCode);
                        labels[2].setText(name2[2] + ": " + p.bookingRefCode);
                        labels[3].setText(name2[3] + ": " + Double.toString(fee)+"£");
                        if(filedBags[0].getText().isEmpty() && filedBags[1].getText().isEmpty() && filedBags[2].getText().isEmpty() && filedBags[3].getText().isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for baggage dimensions and weight!");
                            return;
                        }
                        if(fee == 0) {
                            switchButton.setText("Exit");
                            cardLayout.next(cardPanel);
                        } else {
                            card4.add(new JLabel("Please pay your excess baggage fee:" + fee+"£"));
                            switchButton.setText("Pay");
                            cardLayout.show(cardPanel, "Card 4");
                        }
                        checkinInfoD[0] = w*h*l;
                        checkinInfoD[1] = wi;
                        checkinInfoD[2] = fee;
                    }catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(frame, "Please enter valid numbers for baggage dimensions and weight!");
                        return;
    //	                }catch(NullPointerException npe) {
    //	                	JOptionPane.showMessageDialog(frame, "Please enter valid numbers for baggage dimensions and weight!");
    //	                	return;
    //	                }

                    }
                    CheckInPassenger cp = new CheckInPassenger(checkinInfoS[0], checkinInfoS[1], checkinInfoS[2], checkinInfoD[0], checkinInfoD[1], checkinInfoD[2]);
                    checkinpassengerList.add(cp);
                    Report report = new Report(checkinpassengerList);
                    report.generateReport("report.txt");
                }
                if(Objects.equals(currentCard, "Card3")) {
                    clear();
                    if(e.getActionCommand().equals("Exit")) {
                        cardLayout.show(cardPanel, "Card 1");

                    }
                }

                if(Objects.equals(currentCard, "Card4")) {
                    clear();
                    if(e.getActionCommand().equals("Pay")) {
                        cardLayout.show(cardPanel, "Card 1");
                        switchButton.setText("Submit");
                    }

                }

                // deal with the right flow
    //                cardLayout.next(cardPanel);
                currentCard = getCurrentCardName(cardPanel);
                frame.setTitle(currentCard);

            }
            private void clear() {
                // Reset card 1
                textField1.setText("");
                textField2.setText("");
                switchButton.setText("Submit");
                // Reset card 2
                for(int i = 0; i < 4; ++i){
                    filedBags[i].setText("");
                }
                // Reset card 4
                card4.removeAll();
                card4.validate();
                card4.repaint();
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
//public static ArrayList<CheckInPassenger> addCheckPassenger(){
//   ArrayList<CheckInPassenger> checkinpassengerList = CheckInPassenger.checkinPassengerList(checkinInfoS[0], checkinInfoS[1], checkinInfoD[0], checkinInfoD[1], checkinInfoD[2]);
//   for (CheckInPassenger checkinpassenger : checkinpassengerList) {
//       System.out.print(checkinpassenger.getName()+", ");
//       System.out.print(checkinpassenger.getFlightCode()+", ");
//       System.out.print(checkinpassenger.getWeight()+", ");
//       System.out.print(checkinpassenger.getSize()+", ");
//       System.out.print(checkinpassenger.getFee());
//       System.out.println();
//   }
//   return checkinpassengerList;
//}
}
