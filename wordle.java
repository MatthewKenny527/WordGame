import java.io.*;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  
public class wordle extends Canvas
{
    public static String[] words;
    public static String[] words2;
    public static String randomWord;
    //public static String w;
    JTextField t1;
    JButton t2;
    public static JButton restartButton;
    int letterX=1;
    int LetterY=1;
    int boxX=1;
    int boxY=1;
    public static String guessArray[] = new String[6];
    public static String letter;
    public static int arrayPos =0;
    int tracker =1;
    public static boolean TorF = true;
    public static void main(String [] args)
    {
        randomWord = fiveLetterWord();
        System.out.println(randomWord);
        JFrame frame = new JFrame("Wordle");
        Canvas canvas = new wordle();
        canvas.setSize(700, 800);
        JTextField textField;
        JButton button;
        Font font1 = new Font("SansSerif", Font.BOLD, 30);  
        textField=new JTextField(""); 
        button=new JButton("Submit");
        textField.setFont(font1); 
        textField.setBounds(100,700, 500,100); 
        button.setBounds(600,700,100,100);
        boolean t3=false;
        frame.add(textField);
        frame.add(button);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        //s1[next]  = t1.getText();

        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                if(TorF == true)
                {
                    boolean real = check(textField.getText());
                    if(real == true)
                    {
                        guessArray[arrayPos]  = textField.getText();
                        canvas.repaint();
                        textField.setText("");
                    }
                    else
                    {
                        textField.setText("");
                    }
                }
                else
                {
                    textField.setEnabled(false);
                    
                }
            }  
            });
       
    }
    
    

    public void paint(Graphics g) {
        setBackground(Color.BLACK);
        g.drawLine(100,  100, 600, 100);
        g.drawLine(100, 700, 600, 700);
        g.drawLine(100, 100, 100, 700);
        g.drawLine(600, 100, 600, 700);

        g.drawLine(200, 100, 200, 700);
        g.drawLine(300, 100, 300, 700);
        g.drawLine(400, 100, 400, 700);
        g.drawLine(500, 100, 500, 700);

        g.drawLine(100, 200, 600, 200);
        g.drawLine(100, 300, 600, 300);
        g.drawLine(100, 400, 600, 400);
        g.drawLine(100, 500, 600, 500);
        g.drawLine(100, 600, 600, 600);

        //g.setColor(Color.white);
        //g.drawString(s1, 400, 400);

        
        int y=1;
        int w=1;

        for(int k=0;k<tracker;k++)
        {
            int letterX=1;
            int boxX=1;
            for(int i=0;i<randomWord.length();i++)
            {
                
                letter = guessArray[k].substring(i, i+1);
                for(int j=0;j<randomWord.length();j++)
                {
                    if(randomWord.charAt(j)==guessArray[k].charAt(i) && i==j && i== guessArray[k].length())
                    {
                        letter = guessArray[k].substring(guessArray[k].length());
                        g.setColor(Color.GREEN);
                        g.fillRect(100*boxX,100*w,100,100);
                        break;
                        

                    }
                    else if(randomWord.charAt(j)==guessArray[k].charAt(i) && i==j)
                    {
                        letter = guessArray[k].substring(i, i+1);
                        g.setColor(Color.GREEN);
                        g.fillRect(100*boxX,100*w,100,100);
                        break;
                        

                    }
                    else if(randomWord.charAt(j)==guessArray[k].charAt(i)&& i== guessArray[k].length())
                    {
                        letter = guessArray[k].substring(guessArray[k].length());
                        g.setColor(Color.orange);
                        g.fillRect(100*boxX,100*w,100,100);
                        

                    }
                    else if(randomWord.charAt(j)==guessArray[k].charAt(i))
                    {
                        letter = guessArray[k].substring(i, i+1);
                        g.setColor(Color.orange);
                        g.fillRect(100*boxX,100*w,100,100);
                        
                        

                    }
                    
                }
                
                g.setColor(Color.WHITE);
                Font myFont = new Font ("Consolas", 1, 50);
                g.setFont(myFont);
                g.drawString(letter.toUpperCase(), 135+letterX, 165+y);
                letterX= letterX+100;
                boxX++;
            }
            w++;
            y= y+100;

        }    
       
        for(int l=0;l<tracker;l++)
        {
            TorF = true;
            if(randomWord.equals(guessArray[l]))
            {
                g.setColor(Color.WHITE);
                Font myFont = new Font ("Consolas", 1, 50);
                g.setFont(myFont);
                g.drawString("You win", 250, 50);
                TorF = false;
                break;
            }
            
        }
        if(tracker==6 && TorF == true)
            {
                g.setColor(Color.WHITE);
                Font myFont = new Font ("Consolas", 1, 40);
                g.setFont(myFont);
                g.drawString("You Lose, the word was " + randomWord + ".", 30, 50); 
                TorF = false;
            }
        
        

        arrayPos++;
        tracker++;
        

    }

    public static String fiveLetterWord()
    {
        Dictionary dict = new Dictionary();
        Random rand = new Random();
        words = new String[40000];
        int k=0;
        for(int i=0;i<dict.getSize();i++)
        {
            String w = dict.getWord(i);
            if(w.length() == 5)
            {
                words[k] = w;
                k++;
            }
        }

        int r = rand.nextInt(k);
        return words[r];
    }

    public static boolean check(String x)
    {
        Dictionary2 dict = new Dictionary2();

        for(int i =0;i<dict.getSize();i++)
        {
            if(dict.getWord(i).equals(x))
            {
                return true;
            }
        }
        return false;
    }
          
    
    
}
 

class Dictionary{
     
    private String input[]; 

    public Dictionary(){
        input = load(".\\fiveLetterWords.txt");  
    }
    
    public int getSize(){
        return input.length;
    }
    
    public String getWord(int n){
        return input[n];
    }
    
    private String[] load(String file) {
        File aFile = new File(file);     
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader( new FileReader(aFile) );
            String line = null; 
            int i = 0;
            while (( line = input.readLine()) != null){
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        }catch (FileNotFoundException ex){
            System.out.println("Can't find the file - are you sure the file is in this location: "+file);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        }finally{
            try {
                if (input!= null) {
                    input.close();
                }
            }catch (IOException ex){
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        /*for(String s: array){
            s.trim();
        }*/
        for (int i=0; i<array.length; i++)
        {
            array[i] = array[i].trim();
        }

        return array;
    }
}

class Dictionary2{
     
    private String input[]; 

    public Dictionary2(){
        input = load(".\\allowable.txt");  
    }
    
    public int getSize(){
        return input.length;
    }
    
    public String getWord(int n){
        return input[n];
    }
    
    private String[] load(String file) {
        File aFile = new File(file);     
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader( new FileReader(aFile) );
            String line = null; 
            int i = 0;
            while (( line = input.readLine()) != null){
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        }catch (FileNotFoundException ex){
            System.out.println("Can't find the file - are you sure the file is in this location: "+file);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        }finally{
            try {
                if (input!= null) {
                    input.close();
                }
            }catch (IOException ex){
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        /*for(String s: array){
            s.trim();
        }*/
        for (int i=0; i<array.length; i++)
        {
            array[i] = array[i].trim();
        }

        return array;
    }
}

