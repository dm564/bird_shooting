
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.Timer;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DEEPAK
 */
public class base extends javax.swing.JFrame {

    /**
     * Creates new form base
     */
    int actual_score=0;
    int high_score=0;
    String level_string="";
    int hund,tens,ones;
    static int level=1;
    static AudioStream first;
    public static String s="DEATH NOTE";
    public static boolean game_sound=true;
    //JLabel bullets[]=new JLabel[10];
    //int bullet_x=70;
    int x=-130,y=150;
    //int x=1200,y=150;
    Timer t1,t2,back_ground,play_time;
    public static int count_bullets=10;
    public static int time=10;
    boolean pause=false,t1_pause=false,t2_pause=true;
    int time_left=0;
    ImageIcon bullet=new ImageIcon("bullet.png");
    public base() {
    
        /*for(int i=0;i<10;++i)
        {
            bullets[i]=new JLabel();
            bullets[i].setIcon(bullet);
            bullets[i].setBounds(1200-bullet_x,0,100,50);
            add(bullets[i]);
            bullets[i].setVisible(true);
            bullet_x+=40;
        }*/
        initComponents();
        display(count_bullets);
       if(level==1)
       {
           level_string="beginer";
       }
       else if(level==2)
       {
           level_string="easy";
       }
       else if(level==3)
       {
           level_string="medium";
       }
       else if(level==4)
       {
           level_string="hard";
       }
       else if(level==5)
       {
           level_string="expert";
       }
        if(!new File("score.txt").isFile())
        {
            File f=new File("score.txt");
            try
            {
                BufferedWriter fout=new BufferedWriter(new FileWriter(f));
                fout.write("beginer : 000");
                fout.newLine();
                fout.write("easy : 000");
                fout.newLine();
                fout.write("medium : 000");
                fout.newLine();
                fout.write("hard : 000");
                fout.newLine();
                fout.write("expert : 000");
                fout.newLine();
                fout.close();
                
            }
            catch(Exception e)
            {}
        }
        File file=new File("score.txt");
        try
        {
            String temp="";
            BufferedReader fin = new BufferedReader(new FileReader(file));
            while((temp=fin.readLine())!=null)
            {
                if(temp.indexOf(level_string)==0)
                {
                    int pos=temp.indexOf(":");
                    String number=temp.substring(pos+2);
                    int num=Integer.parseInt(number);
                    high_score=num;
                    break;
                }
            }
            fin.close();
        }catch(Exception e)
        {}
        if(game_sound==false)
        {
            s="none";
        } 
        setSize(1215,720);
        
        try{
             FileInputStream fin=new FileInputStream(s+".au");
             first=new AudioStream(fin);
             AudioPlayer.player.start(first);
            }
            catch(Exception e)
            {}
        back_ground=new Timer(420*1000,new back_ground_music());
        back_ground.start();
        t1=new Timer(50/level,new fly());
        t1.start();
        t2=new Timer(25/level,new fall());
        play_time=new Timer(1000,new done());
        play_time.start();
     }
    
    public class done implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
                if(time_left==(time+2)*1000)
                {
                    AudioPlayer.player.stop(first);
                    info i=new info(actual_score,"Time Over",level_string);
                    i.setVisible(true);
                    play_time.stop();
                    dispose();
                }
                else
                {
                    if(time_left/1000 <= time)
                    display_time((time*1000-time_left)/1000);
                    time_left+=1000;
                }
        }
    }
    public void display_time(int b)
    {
       int x=b;
       hund=x/100;
       x=x%100;
       tens=x/10;
       x=x%10;
       ones=x;
       String h=Integer.toString(hund);
       String t=Integer.toString(tens);
       String o=Integer.toString(ones);
       ImageIcon i1=new ImageIcon(h+"n.png");
       jLabel10.setIcon(i1);
       ImageIcon i2=new ImageIcon(t+"n.png");
       jLabel12.setIcon(i2);
       ImageIcon i3=new ImageIcon(o+"n.png");
       jLabel13.setIcon(i3);
    }
    public class back_ground_music implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
             FileInputStream fin=new FileInputStream(s+".au");
             AudioStream a=new AudioStream(fin);
             a=first;
             AudioPlayer.player.stop(a);
             AudioPlayer.player.start(a);
             first=a;
            }
            catch(Exception x)
            {}   
        }  
    }
 
    public class fly implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            jLabel2.setBounds(x,y,130,104);
            x=x+10;
            //x=x-10;
           //y=y+10;
            if(x>=1200)
            {
                y=((int)(Math.random()*(700-104))+150);
                if(y>(700-104))
                    y=y-104-150;
                x=-130;
            }       
        }
    }
    public class fall implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e){
        
            jLabel2.setBounds(x,y,100,100);
            y=y+10;
            if(y>=700)
            {
                t2.stop();
                t2_pause=true;
                jLabel2.setIcon(new ImageIcon("bird.gif"));
                x=0;
                y=((int)(Math.random()*(700-104))+150);
                if(y>(700-104))
                    y=y-104-150;
                t1.start();
                t1_pause=false;
            }
            
        }
        
    }
    public void play_sound()
    {
        try{
            if(count_bullets<1)
            {
                if(game_sound)
                {
                FileInputStream fin=new FileInputStream("empty.au");
                AudioStream a=new AudioStream(fin);
                AudioPlayer.player.start(a);
                }
                
                if(count_bullets<=0)
                {
                    --count_bullets;
                }
                if(count_bullets==-2)
                {
                    play_time.stop();
                    AudioPlayer.player.stop(first);
                    t1.stop();
                    info i=new info(actual_score,"Out of Bullets",level_string);
                    i.setVisible(true);
                    dispose();
                }
            }
            else
            {
                if(game_sound)
                {
                FileInputStream fin=new FileInputStream("sound.au");
                AudioStream a=new AudioStream(fin);
                AudioPlayer.player.start(a);
                --count_bullets;
                display(count_bullets);
                }
                //bullets[count_bullets-1].setVisible(false);
                //--count_bullets;
            }
        }catch(Exception e)
        {}
        //bullets[count_bullets].setVisible(false);
        //--count_bullets;
    }

    public void display(int b)
    {
       int x=b;
       hund=x/100;
       x=x%100;
       tens=x/10;
       x=x%10;
       ones=x;
       String h=Integer.toString(hund);
       String t=Integer.toString(tens);
       String o=Integer.toString(ones);
       ImageIcon i1=new ImageIcon(h+"b.png");
       jLabel7.setIcon(i1);
       ImageIcon i2=new ImageIcon(t+"b.png");
       jLabel8.setIcon(i2);
       ImageIcon i3=new ImageIcon(o+"b.png");
       jLabel9.setIcon(i3);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
        });
        jPanel1.setLayout(null);

        jButton1.setText("Menu");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(0, 0, 70, 30);

        jButton2.setText("Pause");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(170, 0, 90, 30);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/0n.png"))); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(840, 10, 50, 50);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/0n.png"))); // NOI18N
        jPanel1.add(jLabel12);
        jLabel12.setBounds(790, 10, 50, 50);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/watch.png"))); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(680, 0, 60, 60);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/0n.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(740, 10, 50, 50);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/0b.png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(520, 10, 50, 50);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1b.png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(470, 10, 50, 50);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/0b.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(420, 10, 50, 50);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bullet.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(370, 10, 40, 50);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/0.png"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(1130, 0, 60, 90);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/0.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(1010, 0, 60, 90);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/0.png"))); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(1070, 0, 60, 90);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bird.gif"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
        });
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 40, 110, 90);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsz_angrynew.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(1200, 700));
        jLabel1.setMinimumSize(new java.awt.Dimension(1200, 700));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1200, 700);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1200, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

       if(pause==false)
       {
       play_sound();
       if(count_bullets>=0)
        {
       ImageIcon img=new ImageIcon("fall.png");
       jLabel2.setIcon(img);
       t1.stop();
       t1_pause=true;
       t2_pause=false;
       t2.start();
       ++actual_score;
       int score=actual_score;
       hund=score/100;
       score=score%100;
       tens=score/10;
       score=score%10;
       ones=score;
       String h=Integer.toString(hund);
       String t=Integer.toString(tens);
       String o=Integer.toString(ones);
       ImageIcon i1=new ImageIcon(h+".png");
       jLabel5.setIcon(i1);
       ImageIcon i2=new ImageIcon(t+".png");
       jLabel4.setIcon(i2);
       ImageIcon i3=new ImageIcon(o+".png");
       jLabel6.setIcon(i3);
       if(actual_score>high_score)
       {
           File temp=new File("temp.txt");
           File exist=new File("score.txt");
           try
           {
                BufferedWriter fout=new BufferedWriter(new FileWriter(temp));
                BufferedReader fin=new BufferedReader(new FileReader(exist));
                String line="";
                while((line=fin.readLine())!=null)
                {
                    System.out.println(line);
                    if(line.indexOf(level_string)==0)
                    {
                        String x=Integer.toString(actual_score);
                        fout.write(level_string+" : "+x);
                        fout.newLine();
                        
                    }
                    else
                    {
                        fout.write(line);
                        fout.newLine();
                    }
                }
                fout.close();
                fin.close();
           }
           catch(Exception e)
           {}
           exist.delete();
           temp.renameTo(exist);
       }
    }
       }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        if(pause==false)
            play_sound();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered

        Toolkit tool1=Toolkit.getDefaultToolkit();
        ImageIcon i=new ImageIcon("target.png");
        Cursor c=tool1.createCustomCursor(i.getImage(),new Point(0,0), "name");
        setCursor(c);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
      
        Toolkit tool1=Toolkit.getDefaultToolkit();
        ImageIcon i=new ImageIcon("target.png");
        Cursor c=tool1.createCustomCursor(i.getImage(),new Point(0,0), "name");
        setCursor(c);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered

         
        
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        AudioPlayer.player.stop(first);
        menu m=new menu();
        m.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained
            
    }//GEN-LAST:event_jPanel1FocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        if(pause==false)
        {
            pause=true;
            if(t1_pause==true)
            {
                t2.stop();
            }
            else
            {
                t1.stop();
            }
            play_time.stop();
            jButton2.setText("Resume");
            AudioPlayer.player.stop(first);
       }
        else
        {
            pause=false;
            if(t1_pause==true)
            {
                t2.start();
            }
            else
            {
                t1.start();
            }
            play_time.start();
            jButton2.setText("Pause");
            AudioPlayer.player.start(first);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               base b = new base();
               b.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
