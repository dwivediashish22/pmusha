package org.nic.pmusha.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.nic.pmusha.enums.SMSConstants;

public class SMSUtil {



	public static String sendMessage(String mobileNumber, String message) {

		//	logger.info("SMS functionality is under development and is only enabled for few users.");

		try {

			/*
			 * The following is a list of mobile numbers that are allowed for testing the SMS functionality
			 */
			List<String> allowedMobileNumbersForTesting = new ArrayList<String>();

			//Add mobile numbers for testing
			allowedMobileNumbersForTesting.add("9871921258"); //Vipin
			allowedMobileNumbersForTesting.add("9953825972"); //Bhanu
			allowedMobileNumbersForTesting.add("8800144203"); //Jitendra

			// if(allowedMobileNumbersForTesting.contains(mobileNumber)) {//If mobile number is allowed

			String encodedSMSMessage = URLEncoder.encode(message.trim(), "ISO-8859-1");

			//TODO: Handle double character spaces e.g. curly bracket characters
			int messageLength = encodedSMSMessage.length();
			String smsGateWayHTTPSAPIString = null;

			String result = null;



			System.out.println("SMS length is: " + messageLength);
			if(messageLength <= SMSConstants.MAXIMUM_CHARACTERS_FOR_SINGLE_SMS) {

				smsGateWayHTTPSAPIString = SMSConstants.SMS_GATEWAY_URL_72_SERVER_DNS_BASED
						+ "?username="+ SMSConstants.SMS_GATEWAY_USER_NAME
						+ "&pin="+ SMSConstants.SMS_GATEWAY_PASSWORD
						+ "&message="+encodedSMSMessage
						+ "&mnumber="+mobileNumber
						+ "&signature="+ SMSConstants.SMS_GATEWAY_SENDER;

				System.out.println("SMS gateway string is: " + smsGateWayHTTPSAPIString);
			}
			else {

				result = "maxCharLimitExceeded";

				return result;
			}


			//String result = makeHTTPSConnection(urlString, 60000);
			result = makeHTTPSConnection(smsGateWayHTTPSAPIString, 60000);


			if(result == null || result.isEmpty()) {

			}

			return result;
			/*}
			else {

				logger.info("SMS functionality has been disabled, as this is currently under development.");
			}*/
		}
		catch (Exception e) {

		}

		return null;
	}

	public static String sendMessageWithTemplatedId(String mobileNumber, String message,String templateId) {

		//	logger.info("SMS functionality is under development and is only enabled for few users.");

		try {

			/*
			 * The following is a list of mobile numbers that are allowed for testing the SMS functionality
			 */
			List<String> allowedMobileNumbersForTesting = new ArrayList<String>();

			//Add mobile numbers for testing
			allowedMobileNumbersForTesting.add("9871921258"); //Vipin
			allowedMobileNumbersForTesting.add("9953825972"); //Bhanu
			allowedMobileNumbersForTesting.add("8800144203"); //Jitendra

			// if(allowedMobileNumbersForTesting.contains(mobileNumber)) {//If mobile number is allowed

			String encodedSMSMessage = URLEncoder.encode(message.trim(), "ISO-8859-1");

			//TODO: Handle double character spaces e.g. curly bracket characters
			int messageLength = encodedSMSMessage.length();
			String smsGateWayHTTPSAPIString = null;

			String result = null;



			System.out.println("SMS length is: " + messageLength);
			if(messageLength <= SMSConstants.MAXIMUM_CHARACTERS_FOR_SINGLE_SMS) {

				smsGateWayHTTPSAPIString = SMSConstants.SMS_GATEWAY_URL_72_SERVER_DNS_BASED
						+ "?username="+ SMSConstants.SMS_GATEWAY_USER_NAME
						+ "&pin="+ SMSConstants.SMS_GATEWAY_PASSWORD
						+ "&message="+encodedSMSMessage
						+ "&mnumber="+mobileNumber
						+ "&signature="+ SMSConstants.SMS_GATEWAY_SENDER
						+ "&dlt_entity_id="+ SMSConstants.DLT_ENTITY_ID
						+ "&dlt_template_id="+templateId;

				System.out.println("SMS gateway string is: " + smsGateWayHTTPSAPIString);
			}
			else {

				result = "maxCharLimitExceeded";

				return result;
			}


			//String result = makeHTTPSConnection(urlString, 60000);
			result = makeHTTPSConnection(smsGateWayHTTPSAPIString, 60000);


			if(result == null || result.isEmpty()) {

			}

			return result;
			/*}
			else {

				logger.info("SMS functionality has been disabled, as this is currently under development.");
			}*/
		}
		catch (Exception e) {

		}

		return null;
	}

	public static String sendMessageTest(String mobileNumber, String message,String templateId) {

		//	logger.info("SMS functionality is under development and is only enabled for few users.");

		try {

			/*
			 * The following is a list of mobile numbers that are allowed for testing the SMS functionality
			 */
			List<String> allowedMobileNumbersForTesting = new ArrayList<String>();

			//Add mobile numbers for testing
			allowedMobileNumbersForTesting.add("9871921258"); //Vipin
			allowedMobileNumbersForTesting.add("9953825972"); //Bhanu
			allowedMobileNumbersForTesting.add("8800144203"); //Jitendra

			// if(allowedMobileNumbersForTesting.contains(mobileNumber)) {//If mobile number is allowed

			String encodedSMSMessage = URLEncoder.encode(message.trim(), "ISO-8859-1");

			//TODO: Handle double character spaces e.g. curly bracket characters
			int messageLength = encodedSMSMessage.length();
			String smsGateWayHTTPSAPIString = null;

			String result = null;



			System.out.println("SMS length is: " + messageLength);
			if(messageLength <= SMSConstants.MAXIMUM_CHARACTERS_FOR_SINGLE_SMS) {

				smsGateWayHTTPSAPIString = SMSConstants.SMS_GATEWAY_URL_72_SERVER_DNS_BASED
						+ "?username="+ SMSConstants.SMS_GATEWAY_USER_NAME
						+ "&pin="+ SMSConstants.SMS_GATEWAY_PASSWORD
						+ "&message="+encodedSMSMessage
						+ "&mnumber="+mobileNumber
						+ "&signature="+ SMSConstants.SMS_GATEWAY_SENDER
						+ "&dlt_entity_id="+ SMSConstants.DLT_ENTITY_ID
						+ "&dlt_template_id="+templateId;

				System.out.println("SMS gateway string is: " + smsGateWayHTTPSAPIString);
			}
			else {

				result = "maxCharLimitExceeded";

				return result;
			}


			//String result = makeHTTPSConnection(urlString, 60000);
			result = makeHTTPSConnection(smsGateWayHTTPSAPIString, 60000);


			if(result == null || result.isEmpty()) {

			}

			return result;
			/*}
			else {

				logger.info("SMS functionality has been disabled, as this is currently under development.");
			}*/
		}
		catch (Exception e) {

		}

		return null;
	}


	private static String makeHTTPSConnection(String urlString, int connectTimeOut) {

		StringBuffer output = new StringBuffer();
		HttpsURLConnection connection = null;
		BufferedReader reader = null;

		if(urlString.length() > 500) {

			return "maxCharLimitExceeded";
		}

		try {

			/*
			 * Verify the hostname of the certifiate manually since some domain names are missing from the SMS Gateway Certificate
			 */
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {


					List<String> allowedSMSGatewayHostNames = new ArrayList<String>();

					allowedSMSGatewayHostNames.add("smsgw.nic.in");
					allowedSMSGatewayHostNames.add("smsgw71.nic.in");
					allowedSMSGatewayHostNames.add("smsgw72.nic.in");
					allowedSMSGatewayHostNames.add("smsgw.sms.gov.in");


					if(allowedSMSGatewayHostNames.contains(hostname)) {

						return true;
					}

					return false;
				}
			});


			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, // key manager
					trust_mgr, // trust manager
					new SecureRandom()); // random number generator
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx
					.getSocketFactory());

			URL url = new URL(urlString);
			connection = (HttpsURLConnection) url.openConnection();

			connection.setConnectTimeout(connectTimeOut);
			connection.setReadTimeout(connectTimeOut);

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String str;

			while ((str = reader.readLine()) != null) {
				output.append(str);
			}
		}
		catch (Exception e) {

		}
		finally {

			try {

				reader.close();
				connection.disconnect();
			}
			catch (Exception e) {

				reader = null;
				connection = null;
			}
		}

		return output.toString();
	}

	private static TrustManager[] get_trust_mgr() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String t) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String t) {
			}
		} };
		return certs;
	}

	/*
	 * HTTP should be used only for testing. HTTPS should be used in production.
	 */
	/*@Deprecated
	private static String makeHTTPConnection(String urlString, int connectTimeOut) {

		StringBuffer output = new StringBuffer();
		HttpURLConnection connection = null;
		BufferedReader reader = null;

		try {

			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(connectTimeOut);
			connection.setReadTimeout(connectTimeOut);

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String str;

			while ((str = reader.readLine()) != null) {
				output.append(str);
			}
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {

			try {

				reader.close();
				connection.disconnect();
			}
			catch (Exception e) {

				reader = null;
				connection = null;
			}
		}

		return output.toString();
	}*/

	/*
	 * Main method for testing only
	 */
	public static void main(String[] args) throws Exception {

		//testCharacterLength();
	}

	/*private static void testCharacterLength() throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("*** SMS Util ***");
		System.out.print("Enter Message: ");

		String message = br.readLine();

		System.out.print("Enter Mobile Number: ");

		String mobileNumber = br.readLine();

		System.out.println("\nSending message...\n");

		String result = SMSUtil.sendMessage(mobileNumber, message);

		System.out.println("Result >>>" + result + "<<<");

		System.out.println("Done.");
	}*/
}
