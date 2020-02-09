package com.hexad.app;


import com.hexad.app.implementation.OrderProcessor;
import com.hexad.app.request.ProductRequest;
import com.hexad.app.response.ProductResponse;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class CustomerOrder {

	public static void main(String[] args) {
		try{
			//step-I: Take user inputs and parse them as a Map.
			List<String> rawUserInputs = readUserInputFromCommandLine();
			Map<String,Integer> parsedInputs = parseUserInputs(rawUserInputs);

			//step-II: Build OrderRequests from parsed inputs. Validate user inputs here, throw exception if user provides incorrect product code.
			List<ProductRequest> orderRequests = buildOrderRequests(parsedInputs);

			//step-III: Invoke order process engine.
			OrderProcessor orderProcessEngine = new OrderProcessor();
			List<ProductResponse> fullFilledOrders = orderProcessEngine.processOrders(orderRequests);

			//Step-IV: Display orders.
			for(ProductResponse orderResponse : fullFilledOrders){
				System.out.println(orderResponse);
			}
		}
		catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}

	public static List<String> readUserInputFromCommandLine(){
		List<String> input = new ArrayList<String>();
		System.out.println("please enter quantity and product code:");
		try{
			Scanner stdInput = new Scanner(System.in);
			String line;
			while (stdInput.hasNextLine()) {
				line = stdInput.nextLine();
				if (line.isEmpty()) {
					break;
				}
				input.add(line);
			}
			System.out.println();
			System.out.println("Processing your order...");
		}catch(Exception ex){
			System.err.println("Exception in reading the input from command line");
			System.err.println(ex.getMessage());
		}
		return input;
	}

	public static Map<String,Integer> parseUserInputs(List<String> inputs){
		try{
			Map<String,String> inputMap = new HashMap<>();
			return inputs.stream().map(str -> str.split(" ")).collect(toMap(str -> str[1],str -> Integer.parseInt(str[0])));
		}
		catch(Exception ex){
			System.err.println("exception while parsing the inputs, please enter input data in <<quantity>> <<validproductcode>>");
			throw ex;
		}
	}

	private static List<ProductRequest> buildOrderRequests(Map<String,Integer> parsedInputs) {
		List<ProductRequest> userRequestObjects = new ArrayList<ProductRequest>();
		for (Map.Entry<String,Integer> entry : parsedInputs.entrySet()) {
			userRequestObjects.add(ProductRequest.parse(entry.getKey().toUpperCase(),entry.getValue()));
		}
		return userRequestObjects;
	}

}



