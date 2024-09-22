import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchFlight implements ActionListener
{
	JFrame f;
	Choice ch1,ch2;
	Label b1,d1,dt,re,fname,ftime,r1,r2,s1,s2;
	Button sf;
	TextField tf;
	JPanel login = new JPanel();
	
	public SearchFlight()
	{
		f = new JFrame();
		r1=new Label();
		s1=new Label();
		r1.setText("");
		s1.setText("");
		r1.setForeground(Color.WHITE);
		s1.setForeground(Color.WHITE);
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


		b1 = new Label("Boarding :");
		b1.setForeground(Color.WHITE);
		d1 = new Label("Destination :");
		d1.setForeground(Color.WHITE);
		dt = new Label("Date :");
		dt.setForeground(Color.WHITE);
		re = new Label("Results :");
		re.setForeground(Color.WHITE);
		fname = new Label("Flight Name");
		fname.setForeground(Color.WHITE);
		ftime = new Label("Timings");
		ftime.setForeground(Color.WHITE);
		
		b1.setBounds(80,105,70,30);
		d1.setBounds(80,145,70,30);
		dt.setBounds(80,185,70,30);
		
		re.setBounds(80,280,70,30);
		fname.setBounds(80,320,70,30);
		ftime.setBounds(280,320,70,30);
		
		tf = new TextField();
		
		ch1 = new Choice();
		ch1.add("Select");
		ch1.add("Hyderabad");
		ch1.add("Delhi");
		ch1.add("Mumbai");
		ch1.add("Kolkata");
		
		ch1.setBounds(160,110,140,40);
		
		ch2 = new Choice();
		ch2.add("Select");
		ch2.add("Hyderabad");
		ch2.add("Delhi");
		ch2.add("Mumbai");
		ch2.add("Kolkata");
		
		ch2.setBounds(160,150,140,40);
		
		sf = new Button("Search Flight");
		
		sf.setBounds(170,240,80,30);
		sf.addActionListener(this);
		
		tf.setBounds(160,190,140,20);
		

		r1.setBounds(80,360,70,30);
		s1.setBounds(280,360,70,30);
						
		login.add(r1);
		login.add(s1);
		login.add(b1);
		login.add(ch1);
		login.add(d1);
		login.add(ch2);
		login.add(dt);
		login.add(tf);
		login.add(sf);
		login.add(re);
		login.add(fname);
		login.add(ftime);
		
		f.setLayout(null);
		f.setSize(1200,700);
		f.setVisible(true);


    }
	
	public void actionPerformed(ActionEvent ae)
	{
		int i=1,j=1;
              // String bd=ch1.getSelectedItem().toString();
              // String dt=ch2.getSelectedItem().toString();
              // String d1= tf.getText().toString();
               
		try {
		if(ch1.getSelectedItem()!= ch2.getSelectedItem())
		{
			
                //Creating Connection Object
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/airline","root","");
				Statement stat =connection.createStatement();
				//String selectQuery = "select flightname,time from registerdflights where boarding=(?) and destination=(?) and date=(?)",("Hyderabad","Kolkata","2020-07-25");
			    PreparedStatement stmt=connection.prepareStatement("select flightname,time from registerdflights where boarding=(?) and destination=(?) and date=(?)");
				stmt.setString(1,ch1.getSelectedItem());
				stmt.setString(2,ch2.getSelectedItem());
				stmt.setString(3,tf.getText());


				ResultSet  rs = stmt.executeQuery();
				
				while(rs.next())
				{
					if(i==1 && j==1)
					{
						r1.setText(rs.getString(1));
						s1.setText(rs.getString(2));
					}
					
					/*if(i==2 && j==2)
					{
                                               
                        r2=new Label();
                        s2=new Label();
						r2.setText(rs.getString(3));
						s2.setText(rs.getString(4));
						
						r2.setBounds(80,390,70,30);
						s2.setBounds(280,390,70,30);
						
						f.add(r2);
						f.add(s2);
					}*/
				}
			}
		}
			
			catch (Exception e1) {e1.printStackTrace();}
		
	}

public static void main(String arg[])
{
    SearchFlight obj=new SearchFlight();
}
	
}
		