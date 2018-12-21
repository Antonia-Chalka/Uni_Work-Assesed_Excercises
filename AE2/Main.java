import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	private static Scanner myScanner= new Scanner(System.in);
	private static String filename;
	private static String keyword;
	private static boolean decoding; //set to true if file is to be decoded, or set to false if file is to be encoded
	private static String outputFilename;
	
	public static void main(String[] args) {
		
		//Obtain file name (the loop will repeat if method cannot determine if file is to be decoded or encoded)
		while (setFilename());
			
		//Set keyword (automatically checks for repeated letters)
		setKeyword();
		
		//decode/encode input file and write output file
		try {
			processFile();
		} catch (IOException e) {
			System.out.println("Error when decoding/encoding file.");
			e.printStackTrace();
			System.exit(0);
		}
		
		//Create and write letter frequencies file
		try {
			generateFileFreqFile(); 
		} catch (IOException e) {
			System.out.println("Error when generating letter frequency file.");
			e.printStackTrace();
		}
	}
	

	/* Setter for for filename that returns false if it cannot determine if file is to be encoded or decoded.
	 * After obtaining the file's name from user input (minus .txt), the method checks if the last character is P or C
	 * to set the decoding boolean to an appropriate value (if decoding -> true, if encoding -> false).
	 * If it can determine whether file is to be encoded/decoded, the method returns false (which breaks the loop in the main).
	 * If not, the method returns true (and the loop in the main continues)
	 * In any case, an appropriate console message is displayed. 	 */
	private static boolean setFilename(){
		System.out.println("Please enter your filename:");
		filename = myScanner.nextLine();
		
		if (filename.charAt(filename.length()-1) == 'P'){
			outputFilename = filename.substring(0, filename.length() - 1) + "C";
			System.out.println("File " + filename + " will be encoded. Output file: " + outputFilename);
			decoding = false;
			return false;
		} else if(filename.charAt(filename.length()-1) == 'C'){
			outputFilename = filename.substring(0, filename.length() - 1) + "D";
			System.out.println("File " + filename + " will be decoded. Output file: " + outputFilename);
			decoding = true;
			return false;
		} else {
			System.out.println("Could not determine if file is to be encoded or decoded. Please retry.");
			return true;
		}
		
	}	

	
	/* Setter method for keyword that checks for repeated letters.
	 * After obtaining the user input and setting it the capitalised version as a keyword (temporary),
	 * a nested loop is used to compare each character against all other characters of the keyword and locate duplicate letters.
	 * If there are repeated letters (indicated by duplicateLetters = true), 
	 * ask the user for a duplicate keyword and starts the loop again.
	 * Otherwise, if duplicateLetters is false (i.e. the loop did not detect any repeating characters),
	 * break the loop and set the scanner input as the keyword.	 */
	private static void setKeyword(){
		System.out.println("Please enter your keyword:");
		while (true) {
			boolean duplicateLetters = false;			
			keyword = (myScanner.nextLine()).toUpperCase();
			for (int i=0; i<keyword.length(); i++){
				for (int j=(i+1); j<keyword.length(); j++){
					if (keyword.charAt(i) == keyword.charAt(j)) { //if two letters are found to be equal, set the duplicateLetters to True and break nested loop
						duplicateLetters = true;
						break;
					}
				}
			}		
			if (duplicateLetters==true){
				System.out.println("Your keyword (" + keyword + ") contains a repeated letter. Please enter a different keyword:");
			} else {
				System.out.println("Your keyword is: " + keyword);
				break;
			}	
		}	
	}
	
	
	/* This method loops over each character in the input file, decodes/encodes it and writes it to output file.
	 * First the method obtains an integer representation of a char in a file (inputInt)
	 * If the character is a capital letter (inputInt<91 && inputInt > 64),  it checks whether the file is to be encoded/decoded,
	 * calls the appropriate method to encode/decode the character and writes the result to the output file.
	 * If the character is not a capital letter, it writes the same character unchanged to the output file and continues the loop.
	 * When the loop has reached the end of the file (inputInt == -1), it closes the output file, breaks the loop 
	 * and a notification is printed on the console	 */
	private static void processFile() throws IOException{
		//MonoAlphabetic myCipher = new MonoAlphabetic(keyword);
		Vigenere myCipher = new Vigenere(keyword);
		
		FileReader inputFile = new FileReader(filename + ".txt");
		FileWriter outputFile = new FileWriter(outputFilename + ".txt");

		while (true) {
			int inputInt = inputFile.read(); //get next character from file in int form				
			if (-1 == inputInt) {  //break loop when loop has reached end of file
				System.out.println("Finished encoding/decoding.");
				outputFile.close();
				inputFile.close();
				break;
			} else if (inputInt<91 && inputInt > 64) {  //encode/decode (capital letters only)
				if (decoding==false){
					char outputCharacter = myCipher.encodeCharacter(inputInt); //convert letter and return chars
					outputFile.write(outputCharacter);	
				}else if (decoding==true) {
					char outputCharacter = myCipher.decodeCharacter(inputInt); //convert letter and return char
					outputFile.write(outputCharacter);
				}	
			} else { //do nothing if character is not a capital letter
				outputFile.write(inputInt);
			}	
		}
	}
	
	
	/* This method generated the letter frequency file.
	 * The method begins by obtaining each character of the output file (outputFileCharacter) and looping.
	 * If the character is a capital letter *outputFileCharacter<91 && outputFileCharacter > 64), 
	 * it calls a method that increases the count (Frequency) for that letter.
	 * The loop breaks when the end of the file is reached (outputFileCharacter=-1).
	 * After that, the method writes the first line of the file written (LETTER ANALYSES...) 
	 * Then, for the header and each letter of the alphabet, it writes the output of another method in the LetterFrequencies objects
	 * that returns the table with letters and values (see other method's comments for more info)
	 * Finally, the method writes the last line of the file (the string returned by another LetterFrequencies method) and closes the file */
	private static void generateFileFreqFile() throws IOException {
		FileReader processesedFile = new FileReader(outputFilename+ ".txt");
		FileWriter letterFreqFile = new FileWriter(filename.substring(0, filename.length()-1) + "F.txt");
		LetterFrequencies myLetterFrequencies = new LetterFrequencies();
		
		while(true) {
			int outputFileCharacter = processesedFile.read();
			if (outputFileCharacter<91 && outputFileCharacter > 64) {  //encode/decode (capital letters only)
				myLetterFrequencies.addLetter((char)(outputFileCharacter));
			}else if (-1 == outputFileCharacter) {  //break loop when loop has reached end of file
				processesedFile.close();
				break;
			}
		}
		letterFreqFile.write("LETTER ANALYSES OF " + outputFilename + " FILE.  \n \n" );
		for (int i =-1; i<26; i++) {		
			letterFreqFile.write(myLetterFrequencies.generateletterFreq(i));
		}
		letterFreqFile.write(myLetterFrequencies.generateLastLine());
		letterFreqFile.close();
	}

}