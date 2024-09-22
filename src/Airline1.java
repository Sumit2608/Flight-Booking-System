import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
public class Airline1 extends WindowAdapter implements ActionListener{

 int aerox=100;
JFrame f=new JFrame("Passenger Login");

TextField  t1,t2;
Button b1,b2;
Dialog dg1,dg2,dg3;

Airline1(){

JPanel login = new JPanel();
login.setSize(600,350);
login.setLayout(null);
login.setBackground(new Color(0,0,0,60));
login.setBounds(250,175,600,350);

ImageIcon background_image = new ImageIcon(ClassLoader.getSystemResource("background4.jpg"));
Image i2 = background_image.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT);
ImageIcon i3 = new ImageIcon(i2);
JLabel background = new JLabel("",i3,JLabel.CENTER);
background.setBounds(0,0,1600,900);
background.add(login);
f.add(background);


Label l=new Label("PASSENGER LOGIN");
l.setBounds(200,100,200,40);
l.setForeground(Color.WHITE); 
Label  l2 = new Label("User Name");
Label  l3 = new Label("Password");
l2.setBounds(100,150,100,30);
l2.setForeground(Color.WHITE); 
l3.setBounds(100,190,100,30);
l3.setForeground(Color.WHITE); 
 t1 = new TextField(30);
 t2 = new TextField(30);
 t2.setEchoChar('*');
t1.setBounds(220,150,200,30);
t2.setBounds(220,190,200,30);
 b1 = new Button("Login");
 b2 = new Button("Cancel");
b1.addActionListener(this);
b2.addActionListener(this);
b1.setBounds(200,240,55,30);
b2.setBounds(300,240,55,30);
login.add(l);
login.add(l2);
login.add(t1);
login.add(l3);
login.add(t2);
login.add(b1);
login.add(b2);
f.addWindowListener(this);
f.setLayout(null);


f.setSize(1200,700);

f.setVisible(true);
}
public void actionPerformed(ActionEvent ae){
if(ae.getSource()==b1)
{
if(t1.getText().equals("") ||  t2.getText().equals(""))
{
dg1=new Dialog(f,"please fill the fields ");
Label lerr1 = new Label("Oops,You can't Enter the leave any field empty");
    dg1.add(lerr1);
    dg1.addWindowListener(this);
    dg1.pack();
    dg1.setLocationRelativeTo(f);
    dg1.setLocation(new Point(80,80));
    dg1.setSize(280,280);
    dg1.setVisible(true)            ;
}

try{

Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/airline","root","");
System.out.println("database connected");
Statement st=con.createStatement();
String query=" select password from userlogins where username= '"+t1.getText()+"'";
ResultSet rs=st.executeQuery(query);
String get_password="";
while(rs.next()){
get_password=rs.getString(1);
}
if(get_password.equals(t2.getText()))
{
    dg2=new Dialog(f,"Login Successfull");
    Label lerr = new Label("Congrats,Login Successfull");
    dg2.add(lerr);
    dg2.addWindowListener(this);
    dg2.pack();
    dg2.setLocationRelativeTo(f);
    dg2.setLocation(new Point(80,80));
    dg2.setSize(280,280);
    dg2.setVisible(true);
			
    new FlightOptions();
}
else{
    dg3=new Dialog(f,"username or password is incorrect ");
    Label lerr1 = new Label("username or password is incorrect");
    dg3.add(lerr1);
    dg3.addWindowListener(this);
    dg3.pack();
    dg3.setLocationRelativeTo(f);
    dg3.setLocation(new Point(80,80));
    dg3.setSize(280,280);
    dg3.setVisible(true);
}
}
catch(Exception e)
{
System.out.println(e);}
}

else
{
 
    f.dispose();

}
}
public void windowClosing(WindowEvent e) {  
    if(e.getSource()==dg1) 
       dg1.dispose();
    else if(e.getSource()==dg2)
      {
        dg2.dispose();
      }
   else if(e.getSource()==dg3)
       dg3.dispose(); 
  else if(e.getSource()==f)
   {
     f.dispose();
   } 
}  
public static void main(String arg[])
{
     Airline1 obj=new Airline1();
}




}


