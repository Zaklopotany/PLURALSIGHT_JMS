import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by mzukowski on 31/05/2018.
 * God bless young Java Dev : *
 */
public class Main {
    public static void main(String[] args) {
        if(args.length == 0 ) {
            System.out.println("Must supply a message");
            return;
        } else {
            System.out.println(args[0]);
        }


        try {
            InitialContext initialContext = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

}
