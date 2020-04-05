package proj2;

public class StudentEmailException extends Exception
{

   public StudentEmailException ()
   {
      super ();
   }

   public StudentEmailException (String message)
   {
      super (message);
   }

   public StudentEmailException (Throwable cause)
   {
      super (cause);
   }

   public StudentEmailException (String message, Throwable cause)
   {
      super (message, cause);
   }
}
