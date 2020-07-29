package DriverFactory;

import org.testng.annotations.Test;

public class AppTest {
@Test
public void kictStart()
{
	DriverScript ds= new DriverScript();
	try {
	ds.startTest();
	}catch(Throwable t)
	{
		System.out.println(t.getMessage());
	}
}
}
