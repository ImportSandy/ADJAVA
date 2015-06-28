package threeT;

public class StoreManager 
{

    public StoreManager() 
    {
        //connect
    }
    
    public String getPassword(String uid)
    {
        //database or file fetch
        if(uid.equals("student"))
            return "123456";
        else if(uid.equals("admin"))
            return "password";
        else
            return null;
    };
}
