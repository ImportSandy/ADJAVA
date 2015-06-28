package captcha;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class ImageGenerator 
{
    private String captchaText;
    private String path;

    public ImageGenerator()
    {
      path= "c:\\advJava\\ajax\\Captcha\\web\\images";
      File temp = new File(path);
      if(!temp.exists())
          temp.mkdirs();
    }
    
    public void setCaptchaText(String s)
    {
        captchaText = s;
    }
    
    public String getCaptchaText()
    {
        return captchaText;
    }
    
    public String generateCaptcha()
    {
        try
        {
            String s;
            s = CaptchaTextGenerator.getNewCaptchaText();
            setCaptchaText(s);
            File f = getCaptchaImage();
            return "images/" + f.getName();
        }
        catch(Exception ex)
        {
            return ex.toString();
        }
    }
    
    public boolean deleteCaptcha()//deletes current captcha
    {
        String s = getCaptchaText() + ".png";
        if(deleteCaptcha(s))
        {
            setCaptchaText("");
            return true;
        }
        return false;
    }
    
    public boolean deleteCaptcha(String fname)
    {
        File temp = new File(fname);
        String absPath = path + System.getProperty("file.separator") + temp.getName();
        System.out.println("**** " + absPath);
        temp = new File(absPath);
        return temp.delete();
    }
    private File getCaptchaImage() throws Exception
    {
        //create a BufferedImage
        BufferedImage bImg = new BufferedImage(420, 120, BufferedImage.TYPE_INT_RGB);
        //access its graphics pen
        Graphics g = bImg.getGraphics();
        //set image background color to white
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());
        //write captcha
        int i,x,y;
        x = 10;
        y = bImg.getHeight()/2;
        
        for(i =0; i< captchaText.length(); i++)
        {
            //background fill
            if(i %2 == 0)
            {
                g.setColor(Color.GRAY);
                g.fillRect(x-20, y-30, 50, 50);
            }
            g.setColor(getColor(i));
            g.setFont(getFont(i));
            g.drawString(String.valueOf(captchaText.charAt(i)), x, y);
            x += 50;
            if(i %2 == 0)
              y += 10;
            else
              y -= 10;
        }
        //save the buffered image in PNG format
        File f = new File(path + System.getProperty("file.separator") + captchaText + ".png");
        if(ImageIO.write(bImg, "PNG", f))
        {
            Thread.sleep(2000);
            return f;
        }
        else
            throw new Exception("Captcha Generation Failed");
    }
    
    Color getColor(int x)
    {
        switch(x)
        {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.CYAN;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.MAGENTA;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.RED;
            case 7:
                return Color.PINK;
            default :
                return Color.DARK_GRAY;
        }
    }//getColor
    
    Font getFont(int x)
    {
        int size = x+ 30;
        int style = Font.PLAIN;
        
        switch(x % 2)
        {
            case 0:
                style = style + Font.BOLD;
            case 1:
                style = style  + Font.ITALIC;
        }//switch
        switch(x)
        {
            case 0:
            case 1:
                return new Font("Times New Roman", style, size);
            case 2:
            case 3:
                return new Font("Comic Sans MS", style, size);
            case 4:
            case 5:
                return new Font("Arial", style, size);
            default:
                return new Font("Courier New", style, size);
        }
    }//getFont
}   
