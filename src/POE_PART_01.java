/*sibu
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.Scanner;
import java.util.regex.Pattern;
//Reference:Regular expressions adapted from Java Pattern documention
//https://doc.oracle.com/javase/8/docs/api/java/util/Pattern.html
/**
 *
 * @author Student
 */
//Login class containing all required methods
class login {
 String storedUsername;//stores the registered username
 String storedPassword;//stores the registered password
 String cellPhoneNumber;//stores user cell phone number
 String firstName;//stores the user first name
 String lastName;// stores user last name
 
 //method to check username format
 public boolean checkUseName(String username) {
     //Check if username contains an underscore and is no more than 5 characters long
     //Thi is a simple validation to ensure username is short and contains a special character
     return username.contains("_") && username.length() <= 5;
 }
 
 //Method to check password comlexity
 public boolean checkPasswordComplexity(String password) {
     //regex pattern explaination:
     //^ asserts the start of the string
     //(?=.*[0-9])-> at least one digit
     //(?=.*[a-z])-> at least one lowercase letter
     //(?=.*[^a-zA-9])->ensures at least one special character
     //(?=.*[A-Z])-> at least one uppercase letter
     //.{8,}     ->at leaST 8 CHARACTHERS long
     //$ sserts the end of the string
     String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[^A-Z0-9])(?=.*[a-z]).{8,}$";
     return Pattern.matches(regex, password);
  
 }
 //Method to check South African cell phone number
 public boolean checkCellPhoneNumber(String number) {
     //Regex pattern explaination
     //^ ->asserts the start of the string
     // \\+27 -> matches the literal "+27" ( SA country code)
     // \\d{9} -> matches exactly 9 digits (the rest of the phone number)
     // $ ->asserts the rest of the string
     String regex = "^\\+27\\d{9}$";
     return Pattern.matches(regex, number);
 }
 //Method to register user
public String registerUser(String username, String password, String firstName, String lastName, String cellPhoneNumber) {
    // Validate username, password, and cell phone number
    if (!checkUseName(username)) {
       return "Username is not correctly formatted " + ":" +"please esure your username contains an underscore and  is less than 5 characters";          
    }
    if (!checkPasswordComplexity(password)){
        return "Password is not correctly formtted : plase ensure your password contains at least 8 characters,a capital letter, a number , and a special character";   
    }
    if (!checkCellPhoneNumber(cellPhoneNumber)){
        return "cell phone number is not correctly formatted: please ensure you use the format +27XXXXXXXXX.";
    }
    //Store the user details
    this.storedUsername = username;
    this.storedPassword = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.cellPhoneNumber = cellPhoneNumber;
    return "Username successfully captured.\nPassword successfully captured.\nCell phone number successsfully added";
    
}
//method to verify login details
public boolean loginUser(String username, String password) {
    //Check if entered credentials match stored credentials
    return username.equals(storedUsername) && password.equals(storedPassword);
}
// REQUIRED method
public String returnLoginStatus( boolean loginSuccess) {
    if (loginSuccess) {
    return "Welcome " + firstName + ";" + lastName +  "! It is great to see you again!";
    } else {
        return "Username or password incorrect; please try again";
    }
}


}
public class POE_PART_01 {

   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        login loginSystem = new login();
        
  
        //====================REGISTRATION====================
        System.out.println(" ====REGISTER====");
   
        //Prompt user for first name
        System.out.println("Enter First Name");
        String firstName = scanner.nextLine();
        
        //prompt user enter last name
        System.out.println("Enter Last Name");
        String lastName= scanner.nextLine();
        
        //prompt user for username
        System.out.println("Enter Username ");
        String username = scanner.nextLine();
        
        //prompt user for password
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        
        //prompt user for cell phone
        System.out.println("Enter Cell Phone Number (+27XXXXXXXXX");
        String cellPhoneNumber = scanner.nextLine();
        
        
        //Register user and dsplay result
        String registrationMessage = loginSystem.registerUser(username, password, firstName,  lastName, cellPhoneNumber);
        System.out.println(registrationMessage);
        
        
        
        //==================== ONLY CONTINUE IFREGISTRATION SUCCESSFUL ====================
     if (registrationMessage.contains("successfully")) {
        //==================== LOGIN ====================
        
        //prompt user to enter username
        System.out.println("\n=== Login ===");
        System.out.println("Enter Username: ");
        String loginUsername = scanner.nextLine();
        
        //prompt user to enter password
        System.out.println("Enter Password: ");
        String loginPassword = scanner.nextLine();
        
        
        boolean loginSuccess = loginSystem.loginUser(loginUsername, loginPassword);
        System.out.println(loginSystem.returnLoginStatus(loginSuccess));
     } else {
         System.out.println("Registration failed. Please restart the program.");
        }
     scanner.close();
    }
}    
     

