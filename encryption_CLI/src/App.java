//Import libs
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
import java.security.*;
import java.util.*;

public class App {
    //AES algo
    private static final String AES = "AES";
    //Using block cipher (CBS)
    private static final String AES_CIP="AES/CBC/PKCS5PADDING";


    //Method to create secrete key
    public static SecretKey createAESKey() throws Exception {
        //Randomizing key gen
        SecureRandom securerandom = new SecureRandom();
        KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
        //Initialize Key gen with 256 bits
        keygenerator.init(256,securerandom);
        SecretKey key = keygenerator.generateKey();
        return key;
     }
     //Method to init vector with any value
    public static byte[] Create_Initial_vector(){
        //Used with encrypt
        byte[] Initial_Vector = new byte[16];
        SecureRandom securerandom = new SecureRandom();
        securerandom.nextBytes(Initial_Vector);
        return Initial_Vector;
    }
    //Method to take plain text and with key convert to Cypher text
    public static byte[] do_AESEncryption(
        String plainText,
        SecretKey secretkey,byte[] Initial_Vector) throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CIP); //Block of Cipher text
        //Parameter aspects of cipher block
        IvParameterSpec ivParameterSpec = new IvParameterSpec(Initial_Vector);
        //Init Cipher block
        cipher.init(Cipher.ENCRYPT_MODE,secretkey,ivParameterSpec);
        return cipher.doFinal(plainText.getBytes());
    }
    //Method to perform Cipher text -> Plain text
    public static String do_AESDecryption(
            byte[] cipherText,
            SecretKey secretkey,
            byte[] Initial_Vector) throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CIP);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(Initial_Vector);
        //Decrypt mode
        cipher.init(Cipher.DECRYPT_MODE,secretkey,ivParameterSpec);
        byte[] result = cipher.doFinal(cipherText);

        return new String(result);
    }
    //Driver code
    public static void main(String[] args) throws Exception {
        SecretKey sk = createAESKey();
        System.out.println("-*-Encrypt Mode-*-");
        System.out.println("The symmetric key:" + DatatypeConverter.printHexBinary(sk.getEncoded()));
        byte[] Initial_Vector = Create_Initial_vector();
        System.out.println("Enter Msg:");
        Scanner msg = new Scanner(System.in);
        String plainText = msg.nextLine();

        // Encrypting the message
        // using the symmetric key
        byte[] cipherText = do_AESEncryption(plainText, sk, Initial_Vector);

        System.out.println("The ciphertext or " + "Encrypted Message is: " + DatatypeConverter.printHexBinary(cipherText));

        // Decrypting the encrypted
        // message
        System.out.println("\n");
        System.out.println("-*-Decrypt Mode-*-");
        String decryptedText = do_AESDecryption(cipherText, sk, Initial_Vector);

        System.out.println("Your original message is: " + decryptedText);
    }
}

