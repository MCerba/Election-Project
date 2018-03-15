package lib;

public class EmailTest 
{
	public static void main(String[] args) 
	{
		testingGetters();
		testingHashCode();
		testingToString();
		testingEquals();
		testingCompareTo();
		firstTestingValidateEmail();
		secondTestingValidateEmail();
	}
	
	public static void testingGetters()
	{
		Email e1 = new Email("austinantoine@hey.com");
		Email e2 = new Email("hello@abc.com");
		

		System.out.println("Testing get address");
		System.out.println(e1.getAddress() +"\t"+ e2.getAddress() +"\n");
		
		System.out.println("Testing get host");
		System.out.println(e1.getHost() +"\t"+ e2.getHost() +"\n");
		
		System.out.println("Testing get userID");
		System.out.println(e1.getUserID() +"\t"+ e2.getUserID() +"\n");
	}
	
	public static void testingHashCode()
	{
		Email e1 = new Email("austinantoine@hey.com");
		Email e2 = new Email("hello@abc.com");
		Email e4 = new Email("hello@abc.com");
		
		System.out.println("Testing hash code method. Getting hashCode for Email e1 \"austinantoine@hey.com\", Email e2 \"hello@abc.com\" and Email e4 \"hello@abc.com\"" );
		System.out.println(e1.hashCode() +"\n");
		System.out.println(e2.hashCode() +"\n");
		System.out.println(e4.hashCode() +"\n");
	}
	
	public static void testingToString()
	{
		Email e1 = new Email("austinantoine@hey.com");
		System.out.println("Testing to string method");
		System.out.println(e1.toString() +"\n");
	}
	
	public static void testingEquals()
	{
		Email e1 = new Email("austinantoine@hey.com");
		Email e2 = new Email("hello@abc.com");
		Email e4 = new Email("hello@abc.com");
		
		System.out.println("Testing equals method. Testing if: austinantoine@hey.com equals hello@abc.com");
		System.out.println(e1.equals(e2) +"\n");
		
		System.out.println("Testing equals method. Testing if: hello@abc.com equals hello@abc.com");
		System.out.println(e2.equals(e4) +"\n");
	}
	
	public static void testingCompareTo()
	{
		Email e2 = new Email("hello@abc.com");
		Email e3 = new Email("austinantoine@zzz.com");
		Email e4 = new Email("hello@abc.com");
		Email e5 = new Email("whatsup@yoyo.com");
		Email e6 = new Email("hihihi@abcd.com");
		
		System.out.println("Testing the compare method. Comparing hello@abc.com to hello@abc.com");
		System.out.println(e2.compareTo(e4) +"\n");
		
		System.out.println("Testing the compare method. Comparing whatsup@yoyo.com to hello@abc.com ");
		System.out.println(e5.compareTo(e4) +"\n");
		
		System.out.println("Testing the compare method. Comparing hihihi@abcd.com to austinantoine@zzz.com");
		System.out.println(e6.compareTo(e3) +"\n");
		
		System.out.println("Testing the compare method. Comparaing hihihi@abcd.com to hello@abc.com");
		System.out.println(e6.compareTo(e2) +"\n");
	}
	
	public static void firstTestingValidateEmail()
	{
		System.out.println("--------All of these emails should be valid-------- \n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: helloo@howareyou.qc.ca");
		try
		{
			Email valid = new Email("helloo@howareyou.qc.ca");
			System.out.println("OBJECT CREATED --> PASS");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("FAIL");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: thisIsAValidEmail@gmail.com");
		try
		{
			Email valid = new Email("thisIsAValidEmail@gmail.com");
			System.out.println("OBJECT CREATED --> PASS");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("FAIL");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: abc123@this.is.a.weird.but.necessary.email.to.test");
		try
		{
			Email valid = new Email("abc123@this.is.a.weird.but.necessary.email.to.test");
			System.out.println("OBJECT CREATED --> PASS");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("FAIL");
		}
		System.out.println("\n");
	}
	
	public static void secondTestingValidateEmail()
	{
		System.out.println("--------All of these emails should be invalid-------- \n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: .hiii@somewhere.com");
		try
		{
		    Email invalid = new Email(".hiii@somewhere.com");
		    System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: boo..boo@boo.com");
		try
		{
		    Email invalid2 = new Email("boo..boo@boo.com");
		    System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: letsgo@somewhere");
		try
		{
			Email something = new Email("letsgo@somewhere");
			System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: testing@this.");
		try
		{
			Email woohoo = new Email("testing@this.");
			System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with no '@'");
		try
		{
			Email blah = new Email("hoooooorahhhhh");
			System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: @hi@how@are@you@");
		try
		{
			Email gg = new Email("@hi@how@are@you@");
			System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: yeahyeah@.yeah");
		try
		{
			Email yoooooo = new Email("yeahyeah@.yeah");
			System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: 123456789123456789123456789123456@yahoo.com");
		try
		{
			Email toomuch = new Email("123456789123456789123456789123456@yahoo.com");
			System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with address: hi@123456789123456789123456789123456");
		try
		{
			Email haha = new Email("hi@123456789123456789123456789123456");
			System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
		System.out.println("\n");
		
		System.out.println("Testing the validateEmail method. Attempting to create email object with empty address");
		try
		{
			Email hhhhhh = new Email("");
			System.out.println("FAIL");
		}
		
		catch (IllegalArgumentException e)
		{
			System.out.println("EXCEPTION CAUGHT --> PASS");
		}
	}

}
