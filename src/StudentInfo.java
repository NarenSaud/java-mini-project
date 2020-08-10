/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class StudentInfo extends JFrame implements ActionListener{
    JLabel lroll_num,lsname,llevel,lage,lbaner;
    JTextField trollnum,tsname, tlevel,tage;
    JButton insert,delete,update,search;
    JPanel p1,p2,p3,p4,p5;
    
    StudentInfo()
    {
        setSize(600,400);
	setTitle("Student Information Managemnt");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p4=new JPanel();
        p5=new JPanel();
        
        setLayout(new BorderLayout());
	add(p1,BorderLayout.CENTER);
	add(p2,BorderLayout.SOUTH);
        add(p3,BorderLayout.NORTH);
	p1.setLayout(new GridLayout(1,2));
	p1.add(p4);
	p1.add(p5);
        
        p3.setLayout(new FlowLayout(FlowLayout.CENTER, 75,20));
        lbaner=new JLabel("Student Information Mangement");
        lbaner.setFont(new Font("Elephant", Font.PLAIN, 24));
        lbaner.setOpaque(true);
        lbaner.setBackground(Color.YELLOW);
        lbaner.setForeground(Color.GREEN);
        p3.add(lbaner);
        
        p4.setLayout(new FlowLayout(FlowLayout.LEFT, 150,25));
	p5.setLayout(new FlowLayout(FlowLayout.LEFT, 50,20));
        p4.setBackground(Color.MAGENTA);
	p5.setBackground(Color.PINK);
	lroll_num=new JLabel("Roll_Number ");
	lsname=new JLabel("Student Name");
        llevel=new JLabel("Level       ");
	lage=new JLabel("Age         ");
	p4.add(lroll_num);
	p4.add(lsname);
        p4.add(llevel);
	p4.add(lage);
        
        trollnum=new JTextField(10);
	tsname=new JTextField(10);
        tlevel=new JTextField(10);
	tage=new JTextField(10);
	p5.add(trollnum);
	p5.add(tsname);
        p5.add(tlevel);
	p5.add(tage);
        
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
	insert=new JButton("Add");
	delete=new JButton("Delete");
	update=new JButton("Modify");
	search=new JButton("Find");
	p2.add(insert);
	p2.add(delete);
	p2.add(update);
	p2.add(search);
        
        insert.addActionListener(this);
	delete.addActionListener(this);
	update.addActionListener(this);
	search.addActionListener(this);
		
	setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        Connection con=null;
        Statement st=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/StudentDB", "root", "naren0425");
            st=con.createStatement();
            if(ae.getSource()==insert)
            {
                int rollnum,age;
                String name,level;
		rollnum=Integer.parseInt(trollnum.getText());			
		age=Integer.parseInt(tage.getText());
		name=tsname.getText();
                level=tlevel.getText();
                String query="insert into Student_Info values("+rollnum+",'"+name+"', '"+level+"',"+age+")";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this,"Data is Registered!!!!");			
            }
            if(ae.getSource()==delete)
            {
                int rollnum;
                rollnum=Integer.parseInt(trollnum.getText());	
                String query="delete from Student_Info where Roll_Num="+rollnum;
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this,"Data is Deleted!!!!");                   
            }
            if(ae.getSource()==update)
            {
                int rollnum;
                rollnum=Integer.parseInt(trollnum.getText());	
                String age=tage.getText();
                String name=tsname.getText();
                String level=tlevel.getText();
                if(name.equalsIgnoreCase(""));
                else
                    {
                        String query="update Student_Info set Sname='"+name+"'where Roll_Num="+rollnum;
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(this,"Name is Upadted!!!!"); 
                    }
                if(level.equalsIgnoreCase(""));
                else
                    {
                        String query="update Student_Info set Level='"+name+"'where Roll_Num="+rollnum;
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(this,"Level is Upadted!!!!"); 
                    }           
                if(age.equalsIgnoreCase(""));
                else
                    {
                        int a=Integer.parseInt(age);
                        String query="update Student_info set age="+a+" where Roll_Num="+rollnum;
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(this,"Age is Upadted!!!!"); 
                    }                     
                }
            if(ae.getSource()==search)
            {
                int rollnum;
                rollnum=Integer.parseInt(trollnum.getText());	
                String query="Select * from Student_Info where Roll_num="+rollnum;
                ResultSet rs=st.executeQuery(query);
                if(rs.next())
                {
                    String name = rs.getString("sname");
                    String level = rs.getString("level");
                    int age=rs.getInt("age");
                    tsname.setText(name);
                    tlevel.setText(level);
                    tage.setText(String.valueOf(age)); 
                }
                else
                {
                    trollnum.setText(" ");
                    tsname.setText(" ");
                    tlevel.setText(" ");
                    tage.setText(" "); 
                    JOptionPane.showMessageDialog(this,"Data Not Found!!!!");
                }
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public static void main(String a[])
	{
		new StudentInfo();
	}
}
