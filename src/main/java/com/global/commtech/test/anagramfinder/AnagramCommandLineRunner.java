package com.global.commtech.test.anagramfinder;

import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(final String... args) throws Exception {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");
        
        String data = "";
        TreeSet<String> inputWordList = new TreeSet<String>();
        int inputWordSize = 0, previousInputWordSize = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
              data = scanner.nextLine();
              //System.out.println("data: "+data);
              inputWordList.add(data);
              inputWordSize = data.length();
              if ((previousInputWordSize != inputWordSize) && (previousInputWordSize > 0)) {
            	  printAnagramList(inputWordList);
            	  inputWordList = new TreeSet<String>();
              } else {
                  inputWordList.add(data);
              }
              previousInputWordSize = inputWordSize;
            }
            scanner.close();
          } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
          }
        
    }
    
    void printAnagramList(TreeSet<String> inputWordList) {
        //System.out.println("printAnagramList()");
    	String str1 = "", outputAnagramSetString = "";
    	TreeSet<String> outputAnagramStringSet = new TreeSet<String>();	
    	Iterator<String> itr = inputWordList.iterator();
    	while (itr.hasNext()) {
        	try {
        		str1 = itr.next();
        	}
        		catch (Exception ex) {
                    System.out.println("An error occurred.");
                    ex.printStackTrace();
        	}
        	TreeSet<String> outputWordSet = new TreeSet<String>();	
        	outputWordSet.add(str1);
        	//System.out.println("str1: "+str1);
            char[] a1 = str1.toCharArray();
            Arrays.sort(a1);
        	for (String str2 : inputWordList) {
                char[] a2 = str2.toCharArray();
                Arrays.sort(a2);
                if (Arrays.equals(a1, a2)) {
                	outputWordSet.add(str2);
                }
        	}
        	//System.out.println("outputWordList: "+outputWordList.toString());
        	for (String outputStr : outputWordSet) {
        		outputAnagramSetString += outputStr + ",";
        	}
    		int len = outputAnagramSetString.length();
    		outputAnagramSetString = outputAnagramSetString.substring(0, len - 1);
        	outputAnagramStringSet.add(outputAnagramSetString);
        	outputAnagramSetString = "";
    	}
    	for (String str : outputAnagramStringSet) {
        	System.out.println(str);
    	}
    }
}
