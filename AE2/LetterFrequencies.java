
public class LetterFrequencies {
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private int[] Freq = new int[26];
	private double[] FreqPerc = new double[26];
	private double[] FreqAvgPerc = new double[] {
			8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 
			6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};
	private double[] diff = new double[26];
	private int totalCount = 0; //count of all characters in a file

	//Constructor initialises frequency array of letters
	public LetterFrequencies() {
		for (int i =0; i<26; i++) {
			Freq[i] = 0;
		}
	}
	
	
	/* This method checks if an inputed character is a capital letter. 
	 * If it is, it increased the frequency (i.e. occurrence of the letter by 1. 
	 * Every time the method is run, the total count of characters in a file is increased by 1 */
	public void addLetter(char letter) {
		int index = alphabet.indexOf(letter); //if letter is not in alphabet, returns -1
		if (index != -1) { 
			Freq[index]++;
			totalCount++;
			}
	}
	
	
	/* This method returns strings used to construct the alphabet table in the Letter Frequency File
	 * The inputed index values range from -1 to 25. At index=-1, the method returns the header.
	 * Between values 0-25, the method calculates the frequency percentage, and difference, 
	 * and returns a formatted string of the letter at the index along with its associated values */
	public String generateletterFreq(int index){
		if (index==-1){
			return String.format("%-6s %6s %7s %10s %9s \r\n", 
					"Letter", "Freq", "Freq%", "AvgFreq%", "diff");
		} else{
			FreqPerc[index] = (Freq[index] / (double) totalCount) * 100;  //Calculate Frequency Percentage
			diff[index] = FreqPerc[index] - FreqAvgPerc[index]; //Calculate difference Frequency percentage values
			return String.format("%3s %7d %8.1f %9.1f %11.1f \r\n", 
					alphabet.charAt(index), Freq[index], FreqPerc[index], FreqAvgPerc[index], diff[index]);
		}
	}

	
	/* This method first finds the most frequent letter by finding the highest value in the Frequency array. 
	 * Then, the index of the biggest value is used to locate the letter and return an appropriate string  */
	public String generateLastLine(){
		int highestFreq = 0;
		int highestFreqIndex = 0;
		for (int i = 0; i < 26; i++) {
			if (Freq[i] >= highestFreq){
				highestFreq = Freq[i];
				highestFreqIndex = i;
			}
		}
		return "\nThe most frequent letter is " + alphabet.charAt(highestFreqIndex) + " at " + String.format("%.1f", FreqPerc[highestFreqIndex]) +"%";
	}
	
}
