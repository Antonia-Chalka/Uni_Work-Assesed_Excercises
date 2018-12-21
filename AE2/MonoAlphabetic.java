
public class MonoAlphabetic{
 private char[] cipherAlphabetArray = new char[26];
 private char[] alphabetArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
 private char[] truncatedAlphabetArray;

 	//Constructor. Calls method that creates cipher alphabet
 	public MonoAlphabetic(String keyword) {
		createCipherAlphabet(keyword);
		//Code for printing the MonoAlphabetic cipher's alphabet array to console is at the bottom of this class
	}
 	
 	
 	/* This method creates the cipher alphabet. 
 	 * First, it created an array of the keyword letters and iterated over it, 
 	 * removing each letter from an alphabet string to create a truncated alphabet.
 	 * The resulting truncated alphabet is converted into a array and,
 	 *  both it and the keyword array are copied to create the cipher's alphabet in the form of a char[] array. 	 */
 	private void createCipherAlphabet(String keyword) {
 		String truncatedAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //to be truncated and converted to char[] later
 		char[] keywordLetterArray = keyword.toCharArray();
		
		for (int i = 0; i<keywordLetterArray.length; i++) {
			truncatedAlphabet = truncatedAlphabet.replace(String.valueOf(keywordLetterArray[i]), ""); //replace with empty string
		}
		truncatedAlphabetArray = truncatedAlphabet.toCharArray();
		
		System.arraycopy(keywordLetterArray, 0, cipherAlphabetArray, 0, keywordLetterArray.length);
		System.arraycopy(truncatedAlphabetArray, 0, cipherAlphabetArray, keywordLetterArray.length, truncatedAlphabetArray.length);
		
	}
 	
 	
 	/*This method takes an encrypted char in int for9mat and decodes it based on the cipher alphabet.
 	 * First, the int is converted into a character (encryptedLetter).
 	 * By looping, we locate the encryptedLetter's index in the cipher alphabet which is then used to obtain the letter in the normal alphabet
 	 * For e.g., if keyword=B and we want A->B:
 	 * Alphabet: ABCDEFGHIJKLMNOPQRSTUVWXYZ
 	 * Cipher Alphabet: BACDEFGHIJKLMNOPQRSTUVWXYZ
 	 * If the encoded character was A, the method would get ecryptedInt=65, which would be converted to A
 	 * Then the method would iterate over the cipher Alphabet array to find the index of A, which would be 1 (2nd element)
 	 * Then the method would find the character of the normal alphabet in the same index (B) and return it 	 */
 	public char decodeCharacter(int encryptedInt){
 		char encryptedLetter = (char) encryptedInt;
 		int index = 0;
 		for (int i = 0; i <cipherAlphabetArray.length; i++) {
 	        if (cipherAlphabetArray[i] == encryptedLetter) {
 	            index = i;
 	        }
 	    }
		char decryptedCharacter = alphabetArray[index];
		return decryptedCharacter;
	}
 	
 	
 	/*This method encodes a character that is received as an integer (decryptedInt) 
 	 * by finding the index of the character in the normal alphabet and using it to convert and return the encrypted character.
 	 * For e.g., if keyword=B and we want B->A:
 	 * Alphabet: ABCDEFGHIJKLMNOPQRSTUVWXYZ
 	 * Cipher Alphabet: BACDEFGHIJKLMNOPQRSTUVWXYZ
 	 * If the decoded character was B, the method would get decryptedInt=66.
 	 * To find B's index in the normal alphabet (i.e. 1), we can just remove 65 from the integer (as per ASCII table)
 	 * (alternately, we could have iterated in the normal alphabet array but that would not be efficient).
 	 * Then, using the index, we would find the equivalent encrypted character in the cipher Alphabet and return it (index 1 -> A) 	 */
 	public char encodeCharacter(int decryptedInt){
		int encryptedInt = decryptedInt-65;
		char encryptedCharacter = cipherAlphabetArray[encryptedInt];
		return encryptedCharacter;
	}

}
/* CODE FOR PRINTING CIPHER ALPHABET
		String myString = "";
		for (char letter : cipherAlphabetArray) {
			myString += letter;
		}
		System.out.println("Monoalphabetic Cipher Array/Alphabet:" + myString);
*/
