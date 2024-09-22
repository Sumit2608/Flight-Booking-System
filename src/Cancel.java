import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Cancel extends WindowAdapter implements ActionListener
{
JFrame f;
Dialog d,d1;
Label l8,l,l2,l3,l4;
Choice c;
TextField t1,t2;
Button b1,b2,b3;
Cancel()
{  
   f=new JFrame("CANCELLATION OF TICKET");
   
   JPanel login = new JPanel();
   login.setSize(600,350);
   login.setLayout(null);
   login.setBackground(new Color(0,0,0,60));
   login.setBounds(200,100,600,500);


   ImageIcon background_image = new ImageIcon(ClassLoader.getSystemResource("background4.jpg"));
   Image i2 = background_image.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT);
   ImageIcon i3 = new ImageIcon(i2);
   JLabel background = new JLabel("",i3,JLabel.CENTER);
   background.setBounds(0,0,1600,900);
   background.add(login);
   f.add(background);

   l=new Label("TICKET CANCELLATION ");
   l.setForeground(Color.WHITE);
   l.setBounds(100,50,200,40);
   l2=new Label("UNIQUE ID :");
   l2.setForeground(Color.WHITE);
   l3=new Label("BOARDING :");
   l3.setForeground(Color.WHITE);
   l2.setBounds(100,100,90,30);
   l3.setBounds(100,150,80,30);

   t1= new TextField("");
   t1.setBounds(200,100,180,20);
   c=new Choice();
   c.setBounds(200,150,150,75);  
   c.add("HYDERABAD");  
   c.add("CHENNAI");  
   c.add("MUMBAI");  
   c.add("DELHI");  
   c.add("KOLKATA");
   login.add(c);
   login.add(t1);
   login.add(l2);
   login.add(l3);
   login.add(l);
   l4=new Label("DOJ :");
   l4.setForeground(Color.WHITE);
   l4.setBounds(100,200,70,30);  
   
   t2= new TextField("");
   t2.setBounds(200,200,70,20);
    
   login.add(l4);
   login.add(t2);
   b1=new Button("CANCEL");
   b1.setBounds(100,250,55,30);
   b1.addActionListener(this);
   f.addWindowListener(this);
      
   login.add(b1);
   login.setVisible(true);
   f.setSize(1200,700);  
   f.setLayout(null);  
   f.setVisible(true);
}
public void actionPerformed(ActionEvent e) 
{   
  Object s=e.getSource();
  if(s==b1)
   {
      d=new Dialog(f,"CONFIRMATION BOX",true);
      Label  l9=new Label("ARE YOU SURE TO CANCEL??");
      l9.setBounds(105,90,180,10);
      b2=new Button("YES");
      b3=new Button("NO");
	  d.setLayout(null);
      b2.setBounds(150,115,35,25);
      b3.setBounds(215,115,35,25);
      d.add(l9);
      d.add(b2);
      d.add(b3);
      b2.addActionListener(this);
      b3.addActionListener(this);
      d.addWindowListener(this);
	  d.pack();
	  d.setLocationRelativeTo(f);
	  d.setLocation(new Point(150,80));
	  
      d.setSize(400,300);
      d.setVisible(true);
     }
    if(s==b2)
    {
       try{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/airline";
        String uname="root";
        String pass="";
        Connection con=DriverManager.getConnection(url,uname,pass);
        String query="delete from bookings where uniquecode="+"?";
        PreparedStatement stmt=con.prepareStatement(query);  
        stmt.setString(1,t1.getText());
        stmt.executeUpdate();
        stmt.close();
        con.close(); 
        d1=new Dialog(f,"TICKET CANCELLATION",true);
        l8=new Label("CONGRATS YOUR TICKET HAS BEEN CANCELLED");
        l8.setBounds(180,150,450,40);
	    d1.setLayout(null);
        d1.add(l8);
        d1.addWindowListener(this);
      
	    d1.pack();
	    d1.setLocationRelativeTo(f);
        d1.setLocation(new Point(150,80));
        d1.setSize(600,300);
        d1.setVisible(true);
       }
       catch(Exception ae){ae.printStackTrace();}
    }
    if(s==b3) {
        d.setVisible(false);
    }
    else{
        f.dispose();
    }
}
public void windowClosing(WindowEvent e)
{
    if(e.getSource()==d) {
        d.dispose();
    }
    else if(e.getSource()==d1)
    {
        d1.setVisible(false);
    }
    else if(e.getSource()==f)
    {
        f.dispose();
    }
}
public static void main(String[] args) {
Cancel c=new Cancel();
}
}

