import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class EmailClientComposeMessage implements ActionListener { //in the swing package//
				//The class which processes the ActionEvent //
    private JFrame frame = null;
    private JPanel panel1 = null;
    private JPanel panel2 = null;
    private JPanel panel3 = null;
    private JLabel receiverLabel = null;
    private JTextField receiver = null;
    private JLabel subjectLabel = null;
    private JTextField subject = null;
    private JTextArea message = null;
    private JButton send = null;
    private JButton cancel = null;
    
    //A private member, by contrast, can only be accessed from inside the same class, so if something goes wrong with that, there is usually only one source file to look at.//
    
    public EmailClientComposeMessage() {
    //creates constructor that builds the object which is being instantiated//
    
    
        //set up frame//
        frame = new JFrame("ICS3U Client");
        frame.setLocation(180,160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        //set up container - contentPane and adds panel 1,2,3//
        Container contentPane = frame.getContentPane();
        BoxLayout contentPaneLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(contentPaneLayout);
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);
        
        //set up first panel//
        FlowLayout panel1Layout = new FlowLayout(FlowLayout.CENTER);
        panel1.setLayout(panel1Layout);
        
        receiverLabel = new JLabel("To");
        panel1.add(receiverLabel);
        receiver = new JTextField(Globals.RECEIVER_LEN);
        panel1.add(receiver);
        
        subjectLabel = new JLabel("Subject");
        panel1.add(subjectLabel);
        subject = new JTextField(51);
        panel1.add(subject);
        
        // set up second panel //
        
        // class begin with upper case//
        FlowLayout panel2Layout = new FlowLayout(FlowLayout.CENTER);
        panel2.setLayout(panel2Layout);
		        //rows.colums//    
        message = new JTextArea (10,69);
        message.setLineWrap(true); //skip lines when message hit the end//
        message.setWrapStyleWord(true);//keep words together when skipping lines//
        panel2.add(message);
        
        //set up third panel //
        FlowLayout panel3Layout = new FlowLayout(FlowLayout.RIGHT);
        panel3.setLayout(panel3Layout);
        
        send = new JButton ("Send");
        cancel = new JButton ("Cancel");
        panel3.add(send);
        panel3.add(cancel);
        
        send.addActionListener(this);
        cancel.addActionListener(this);
        
        //closing statement//
        frame.pack();
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event){
        Object buttonPressed = event.getSource();
        if (buttonPressed == send){
	String receiverId = receiver.getText(); //extract from JTextField//
	if (receiverId.length() == 0){
	    JOptionPane.showMessageDialog(null, "Invalid receiver", "ICS3U Bloor CI", JOptionPane.ERROR_MESSAGE);
	}
	else  if (receiverId.length() != Globals.RECEIVER_LEN){
	    JOptionPane.showMessageDialog(null, "Invalid receiver ID", "ICS3U Bloor CI", JOptionPane.ERROR_MESSAGE);
	}
	else if (!Utils.isANumber(receiverId)){
	    JOptionPane.showMessageDialog(null, "Receiver ID can contain only digits", "ICS3U Bloor CI", JOptionPane.ERROR_MESSAGE); 
	}
	else {
	    String senderId = NetIO.myUserName();
	    String entireMessage = Globals.SEND_MESSAGE +
			   senderId +
			   receiverId +
			   "00000000" +
			   Globals.FIRST_RECORD_OF_MESSAGE +
			   subject.getText() +
			   Globals.END_OF_SUBJECT_MARKER + 
			   message.getText();
			   
			   
			   
	    int errorCode = NetIO.sendRequest(entireMessage,Globals.SERVER_IP_ADDRESS);
			   
			   
	}
        }
        else if(buttonPressed == cancel){
	frame.dispose();
        }
    }
    
    public static void main(String[]args){
        EmailClientComposeMessage window = new EmailClientComposeMessage();
    }
}
    
