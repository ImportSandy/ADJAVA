package captcha;
import java.util.Random;

public class CaptchaTextGenerator 
{
    final static int maxCaptchaLength = 8;
    
    public static String getNewCaptchaText()
    {
        Random r = new Random();
        String captcha= "";
        int i, x;
        for(i =0; i< maxCaptchaLength; i++)
        {
            x = r.nextInt(26);//a random int value in range 0-25
            if(x %2 == 0)
                captcha = captcha + (char) (65 + x);
            else
                captcha = captcha + (char) (97 + x);
        }//for
        return captcha;
    }
    
}
