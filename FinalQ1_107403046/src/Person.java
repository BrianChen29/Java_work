// Fig. 28.30: Person.java
// Person class that represents an entry in an address book.
public class Person
{
   private int MemberID;
   private String name;
   private String type;
   private String phone;
   private String crowd;
   private String email;

   // constructor
   public Person()
   {
   } 

   // constructor
   public Person(int MemberID, String name, String type, 
      String phone, String crowd, String email)
   {
      setMemberID(MemberID);
      setName(name);
      setType(type);
      setPhone(phone);
      setCrowd(crowd);
      setEmail(email);
   } 

   // sets the addressID
   public void setMemberID(int ID)
   {
      MemberID = ID;
   }

   // returns the addressID 
   public int getMemberID()
   {
      return MemberID;
   }
   
   // sets the firstName
   public void setName(String name)
   {
      this.name = name;
   } 

   // returns the first name 
   public String getType()
   {
      return type;
   } 
   
   // sets the lastName
   public void setType(String type)
   {
      this.type = type;
   } 

   // returns the last name 
   public String getPhone()
   {
      return phone;
   }
   
   // sets the email address
   public void setPhone(String phone)
   {
	   this.phone = phone;
   }

   // returns the name
   public String getName()
   {
      return name;
   } 
   
   // sets the phone number
   public void setCrowd(String crowd)
   {
      this.crowd = crowd;
   }

   // returns the phone number
   public String getCrowd()
   {
      return crowd;
   }
   
   // sets the email address
   public void setEmail(String email)
   {
      this.email = email;
   }

   // gets the email address
   public String getEmail()
   {
      return email;
   }
} // end class Person


/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/

 