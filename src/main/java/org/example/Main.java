package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {


    public static String getdata(String c)throws Exception{
        StringBuffer br=new StringBuffer();
        br.append("<html>"+"<body style='text-align:center;color:red;'>");
        br.append(c.toUpperCase()+"<br>");
        String url="https://www.worldometers.info/coronavirus/country/"+c+"/";
        Document doc= Jsoup.connect(url).get();
        //#maincounter-wrap

        Elements elements=doc.select("#maincounter-wrap");
        elements.forEach((e)-> {
            String text = e.select("h1").text();
            String count = e.select(".maincounter-number>span").text();
            br.append(text).append(count).append("<br>");

        });
        br.append("</body"+"</html");
        return br.toString();
    }


    public static void main(String[] args)throws Exception {
      //  System.out.println("Hello world!");
      //  Scanner sc=new Scanner(System.in);
      //  System.out.println("Enter Country: ");
      //  String co=sc.nextLine();
      //  System.out.println(getdata(co));

        //Creating GUI
        JFrame root=new JFrame("Details of Country");
        root.setSize(500,500);
        Font f=new Font("Poppins",Font.BOLD,30);
        //textfield
        JTextField field=new JTextField();
        //label
        JLabel datal=new JLabel();
        field.setFont(f);
        datal.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        datal.setHorizontalAlignment(SwingConstants.CENTER);
        JButton button=new JButton("Get");

        button.addActionListener(event->{
           //click
            try {
                String maindata = field.getText();
                String result = getdata(maindata);
                datal.setText(result);

            }catch (Exception e){
                e.printStackTrace();
            }
        });



        button.setFont(f);
        root.setLayout(new BorderLayout());
        root.add(field,BorderLayout.NORTH);
        root.add(datal,BorderLayout.CENTER);
        root.add(button,BorderLayout.SOUTH);
        root.setVisible(true);


    }
}
