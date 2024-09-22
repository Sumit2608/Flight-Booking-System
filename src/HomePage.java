import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends WindowAdapter implements ActionListener
{
	JFrame f;
	Label h1,h2,h3;
	Button admin,passenger;
	
	public HomePage()
	{
		f = new JFrame();
		JPanel login = new JPanel();
   		login.setSize(600,350);
   		login.setLayout(null);
  		login.setBackground(new Color(0,0,0,60));
		  login.setBounds(250,100,800,500);
   
    
    	ImageIcon background_image = new ImageIcon(ClassLoader.getSystemResource("background4.jpg"));
		Image i2 = background_image.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
    	JLabel background = new JLabel( "",i3,JLabel.CENTER);
    	background.setBounds(0,0,1600,900);
    	f.add(background);
    	background.add(login);
		
		h1 = new Label("WELCOME");
		h2 = new Label("TO");
		h3 = new Label("AIRLINE BOOKING SYSTEM");
		
		admin = new Button("Admin");
		passenger = new Button("Passenger");
		
		h1.setBounds(300,60,120,40);
		h1.setFont(new Font("Algerian", Font.BOLD, 20));
		h1.setForeground(Color.WHITE);
		h2.setBounds(340,90,50,40);
		h2.setFont(new Font("Algerian", Font.BOLD, 20));
		h2.setForeground(Color.WHITE);
		h3.setBounds(250,120,340,40);
		h3.setFont(new Font("Algerian", Font.BOLD, 20));
		h3.setForeground(Color.WHITE);
		
		admin.setBounds(320,230,80,30);
		passenger.setBounds(320,270,80,30);
		
		admin.addActionListener(this);
		passenger.addActionListener(this);
		
		login.add(h1);
		login.add(h2);
		login.add(h3);
		
		login.add(admin);
		login.add(passenger);
		
		f.setTitle("Home Page");
		f.addWindowListener(this);
		f.setLayout(null);
		f.setSize(700,500);
		f.setVisible(true);
	}
	
	
	public void windowClosing(WindowEvent e){
				f.dispose();
			}
			
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == admin)
		{
			new Airline2();
		}
		else if(ae.getSource() == passenger)
		{
			new Airline1();
		}
	}
	
	public static void main(String args[])
	{
		HomePage hp = new HomePage();
	}
}
