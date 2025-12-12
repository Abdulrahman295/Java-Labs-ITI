package com.example; 

import java.util.regex.*;


public class IpAddressTask {
	public static void runTests(){
		System.out.println("\n--- Testing IpAddressTask ---");
        
        String validIp = "163.121.12.30";
        System.out.println("Testing Valid IP: " + validIp);
        testSplitIpAddress(validIp);

        String invalidIp = "INA.MAMA.12.30";
        System.out.println("Testing Invalid IP: " + invalidIp);
        testSplitIpAddress(invalidIp);
		
	}

	public static void testSplitIpAddress(String ipAddress){
		if(!isValidIPAddress(ipAddress)){
			System.out.println(">> Invalid IP");
			return;
		}

		String fields[] = ipAddress.split("\\.");

		for(String field : fields){
			System.out.println(">> " + field);
		}

	}

	private static boolean isValidIPAddress(String ipAddress){
		if (ipAddress == null) {
            return false;
        }

        String zeroTo255
            = "(\\d{1,2}|(0|1)\\"
              + "d{2}|2[0-4]\\d|25[0-5]){4}";

        String regex
            = zeroTo255 + "\\."
              + zeroTo255 + "\\."
              + zeroTo255 + "\\."
              + zeroTo255;

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(ipAddress);

        return m.matches();
    }
}