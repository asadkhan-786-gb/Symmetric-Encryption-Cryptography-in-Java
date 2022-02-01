//Importing libs
import javax.crypto.*;
import java.security.*;
import javax.xml.bind.DatatypeConverter;
//Class to create key (symmetric)
public class symmetric {
    public static final String AES ="AES"; //Algorithm for encryption

    // Function to create a secret key(Method)
    public static SecretKey createAESKey() throws Exception{
        //Create new inst. for SecureRandom class
        SecureRandom securerandom = new SecureRandom();
        //Passing string to key gen
        KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
        //Initialize Key gen with 256 bits
        keygenerator.init(256,securerandom);
        SecretKey key = keygenerator.generateKey();
        return key;
    }
    //Driver code
    public static void main(String[] args) throws Exception {
        SecretKey SymmetricKey = createAESKey();
        System.out.println("--Output--");
        System.out.println("Symmetric Key: "+DatatypeConverter.printHexBinary(SymmetricKey.getEncoded()));


// 4665669A98CD8FDC35A59E420C6CFE3C56D5FD380D29CA5C027A72275D75C596 (Remember key)
// D5B30BA9DF03BDB132F13732B380FDD38FAF8165BBE47E05F79C777CFA985DEB (Another different key)
    }

}
