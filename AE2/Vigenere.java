
public class Vigenere {
	private char[][] cipherAlphabetArray;
	private char[] alphabetArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private int usedRow = 0;
	private int totalRows;
	
	//Constructor. Creates cipher alphabet and sets its total rows
	public Vigenere(String keyword) {
 		//Create cipher alphabet
		createCipherAlphabet(keyword);
		totalRows = keyword.length()-1;
		
		//Code for printing the MonoAlphabetic cipher's alphabet array to console is at the bottom of this class
	}
	
	
	/* 	This method creates the Vigenere cipher which is a 2d array from a keyword
	 * First step is to convert the key word to a char[] and set the length of the cipherAlphabet rows
	 * Then it loops for each character in keyword:
	 * First, it finds the position (alphabetIndex) of the keyword character in the alphabet.
	 * Then the method runs 2 loops that creates a row of the cipher alphabet:
	 * The 1nd loop fills the row with the alphabet until the letter Z.
	 * The 2nd loop fills and completed the row with the alphabet from A until he keyword letter.
	 * The loop completes and runs again, creating all the rows of the cipher alphabet.	 */
	public void createCipherAlphabet(String keyword) {	
		char[] keywordLetterArray = keyword.toCharArray();
		cipherAlphabetArray = new char[keywordLetterArray.length][26];
				
		for (int keywordIndex = 0; keywordIndex<keywordLetterArray.length; keywordIndex++) {
			int alphabetIndex = ((int) keywordLetterArray[keywordIndex]) - 65;
			
			int cipherIndex = 0;
			
			for (int index = alphabetIndex; index < alphabetArray.length; index++) {
				cipherAlphabetArray[keywordIndex][cipherIndex] = alphabetArray[index];
				cipherIndex++;
			}
			for (int index = 0; index < alphabetIndex; index++) {
				cipherAlphabetArray[keywordIndex][cipherIndex] = alphabetArray[index];
				cipherIndex++;
			}
		}
	}

	
	/* This method takes an encrypted char in int format and decodes it based on the cipher alphabet.
	 * First, the int is converted into a character (encryptedLetter).
 	 * By looping, we locate the encryptedLetter's index in the cipher alphabet which is then used to obtain the letter in the normal alphabet
 	 * The row which should be used for the Vigenere alphabet kept in track by the incrementrow() method  */
	public char decodeCharacter(int encryptedInt) {
		char encryptedLetter = (char) encryptedInt;
 		int index = 0;
 		
 		for (int i = 0; i < 26; i++) {
 	        if (cipherAlphabetArray[usedRow][i] == encryptedLetter) {
 	            index = i;
 	        }
 	    }
		char decryptedCharacter = alphabetArray[index];
		incrementRow();
		return decryptedCharacter;
	}

	
	/* This method encodes a character that is received as an integer (decryptedInt) 
 	 * by finding the index of the character in the normal alphabet and using it to convert and return the encrypted character.
	 * The row which should be used for the Vigenere alphabet kept in track by the incrementrow() method  */
	public char encodeCharacter(int decryptedInt) {
		int encryptedInt = decryptedInt-65;
		char encryptedCharacter = cipherAlphabetArray[usedRow][encryptedInt];
		incrementRow();
		return encryptedCharacter;
	}
	
	
	/*Method that keeps track of which row in the cipher alphabet is to be used in encoding/decoding
	 * It increased the value that sets which row is to be used.
	 * If the value exceeds the total count of rows, the value is reset to 0
	 */
	private void incrementRow(){
		usedRow++;
		if (usedRow>totalRows){
			usedRow=0;
		}
	}
	
}

/* CODE FOR PRINTING CIPHER ALPHABET
	String alphabetArray = "";
		System.out.println("Vigenere Cipher Alphabet:");
		for (char[] row : cipherAlphabetArray) {
		   for (char letter : row) {
			   alphabetArray += letter;
		   }
		   System.out.println(alphabetArray);
		   alphabetArray = "";
		}
 */
