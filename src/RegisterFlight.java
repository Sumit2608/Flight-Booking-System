import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
//import java.sql.Statement;
import java.sql.*;
//import java.mqsql.jdbc;


public class RegisterFlight extends WindowAdapter implements ActionListener
{
     
   JFrame f;
   TextField name,phone,unique,date;
   Button b1;
   Choice c,d,time;
   Dialog dg,dg1,dg2;
           

   RegisterFlight()
   {
	 f=new JFrame();
     JPanel login = new JPanel();
     login.setSize(600,350);
     login.setLayout(null);
     login.setBackground(new Color(0,0,0,60));
     login.setBounds(200,100,600,800);

     ImageIcon background_image = new ImageIcon(ClassLoader.getSystemResource("background4.jpg"));
     Image i2 = background_image.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT);
     ImageIcon i3 = new ImageIcon(i2);
     JLabel background = new JLabel("",i3,JLabel.CENTER);
     background.setBounds(0,0,1600,900);
     background.add(login);
     f.add(background);

     Label n=new Label("Flight Name:",Label.CENTER);
     //Label p=new Label("Phone No.:",Label.CENTER);
     Label b=new Label("Select Boarding:",Label.CENTER);
     Label d1=new Label("Select Destination:",Label.CENTER);
     Label t=new Label("Select Time",Label.CENTER);
     Label dt=new Label("Enter your Date",Label.CENTER);
     //Label u=new Label("Unique Code:",Label.CENTER);
     name=new TextField(20);
     //phone=new TextField(20);
     //unique=new TextField(20);
     date=new TextField(20);

     c=new Choice();
     c.add("Select city");
     c.add("Hyderabad");
     c.add("Delhi");
     c.add("Kolkata");
     c.add("Mumbai");
 
     d=new Choice();
     d.add("Select city");
     d.add("Hyderabad");
     d.add("Delhi");
     d.add("Kolkata");
     d.add("Mumbai");

     time=new Choice();
     time.add("Select time");
     time.add("1:00 AM");
     time.add("7:00 AM");
     time.add("8:00 PM");
     time.add("9:00 PM");

     b1=new Button("Register Flight");

     f.setLayout(null);
     login.add(n);
     login.add(name);
     //   f.add(p);
       // f.add(phone);
 
       // f.add(u);
       // f.add(unique);
	   
       login.add(b);
       login.add(c);
       login.add(d1);
       login.add(d);
       login.add(t);
       login.add(time);
       login.add(dt);
       login.add(date);
       login.add(b1);

       b1.addActionListener(this);
       f.setVisible(true);
       f.setSize(1200,700);
       f.setTitle("Register Flight");
       n.setBounds(30,95,70,20);
       n.setForeground(Color.WHITE);
       name.setBounds(180,95,200,20);
       //	p.setBounds(20,95,70,20);
       //phone.setBounds(180,95,200,20);
       //u.setBounds(20,345,80,20);
       // unique.setBounds(180,345,200,20);

       b.setBounds(20,145,90,30);
       b.setForeground(Color.WHITE);
       c.setBounds(180,145,200,20);
       d1.setBounds(20,195,100,30);
       d1.setForeground(Color.WHITE);
       d.setBounds(180,195,200,20);
       t.setBounds(20,295,70,20);
       t.setForeground(Color.WHITE);
       time.setBounds(180,295,200,20);
              
       dt.setBounds(20,245,90,20);
       dt.setForeground(Color.WHITE);
       date.setBounds(180,245,200,20);
                
       b1.setBounds(150,400,100,50);


}


public void actionPerformed(ActionEvent ae)
{
   System.out.println("Hello");
  try{
	  if(name.getText().isEmpty() ||  date.getText().isEmpty())
	  {
		dg = new Dialog(f,"Oops !!!",true);
			Label lerr = new Label("Empty Fields are required!!");
			dg.add(lerr);
			dg.addWindowListener(this);
			dg.pack();
			dg.setLocationRelativeTo(f);
			dg.setLocation(new Point(80,80));
			dg.setSize(300,300);
			dg.setVisible(true);
	  }
	  
	 else if(c.getSelectedItem().equals(d.getSelectedItem()))
	  {
		  dg1 = new Dialog(f,"Oops !!!",true);
			Label lerr1 = new Label("Boarding and destination cannot be same!");
			dg1.add(lerr1);
			dg1.addWindowListener(this);
			dg1.pack();
			dg1.setLocationRelativeTo(f);
			dg1.setLocation(new Point(80,80));
			dg1.setSize(300,300);
			dg1.setVisible(true);
	  }
else{  
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3307/airline";
      String uname="root";
      String pass="";
      Connection con=DriverManager.getConnection(url,uname,pass);
       
      PreparedStatement stmt=con.prepareStatement("insert into registerdflights values(?,?,?,?,?)");
      stmt.setString(1,name.getText());
      stmt.setString(2,c.getSelectedItem());
      stmt.setString(3,d.getSelectedItem());
      stmt.setString(4,date.getText());
      stmt.setString(5,time.getSelectedItem());
      stmt.executeUpdate();
      stmt.close();
      con.close();

      dg2 = new Dialog(f,"Kudos !!!",true);
      Label lerr2 = new Label("Congratulations!! Flight registered successfully");
      dg2.add(lerr2);
      dg2.addWindowListener(this);
      dg2.pack();
      dg2.setLocationRelativeTo(f);
      dg2.setLocation(new Point(80,80));
      dg2.setSize(350,350);
      dg2.setVisible(true);

      System.out.println("Success");

   }
}
catch(Exception e){  e.printStackTrace();}

}

public void windowClosing(WindowEvent e) {  
    if(e.getSource()==dg) 
       dg.dispose();
    else if(e.getSource()==dg1)
      {
        dg1.dispose();
      }
   else
       dg2.dispose();  
}  
public static void main(String arg[])
    {
        RegisterFlight reg1=new RegisterFlight();
    }

}