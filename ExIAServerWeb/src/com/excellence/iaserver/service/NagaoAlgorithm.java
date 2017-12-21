package com.excellence.iaserver.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class NagaoAlgorithm {
	private final static String stopWords = 
			"的很了么呢是嘛个都也比还这于不与才上用就好在和对挺去后没说";
	
	private int N;
	
	private Map<String, TFNeighbor> wordTFNeighbor;
	private List<String> leftPTable, rightPTable;
	private int[] leftLTable, rightLTable;
	
	private long wordNumber;
	
	private NagaoAlgorithm(){
		this.N = 5;
		this.leftPTable = new ArrayList<String>();
		this.rightPTable = new ArrayList<String>();
		this.wordTFNeighbor = new HashMap<String, TFNeighbor>(); 
	}
	
	 private void setN(int n){
		 this.N = n;
	 }
	
	// co-prefix length of s1 and s2
	private int coPrefixLength(String s1, String s2) {
		int coPrefixLength = 0;
		for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
			if (s1.charAt(i) == s2.charAt(i))
				coPrefixLength++;
			break;
		}
		return coPrefixLength;
	}
	
	private void addToPTable(String line) {
		// split line according to consecutive none Chinese character
		String[] phrases = line.split("[^\u4E00-\u9FA5]+|[" + stopWords + "]");
		for (String phrase : phrases) {
			for (int i = 0; i < phrase.length(); i++){
				this.rightPTable.add(phrase.substring(i));
			}
			String reversePhrase = StringUtils.reverse(phrase);
			for (int i = 0; i < reversePhrase.length(); i++){
				this.leftPTable.add(reversePhrase.substring(i));
			}
			this.wordNumber += phrase.length();
		}
	}
	
	private void countLTable() {
		Collections.sort(rightPTable);
		this.rightLTable = new int[this.rightPTable.size()];
		for (int i = 1; i < this.rightPTable.size(); i++){
			this.rightLTable[i] = coPrefixLength(this.rightPTable.get(i - 1),
					this.rightPTable.get(i));
		}

		Collections.sort(this.leftPTable);
		this.leftLTable = new int[this.leftPTable.size()];
		for (int i = 1; i < this.leftPTable.size(); i++){
			this.leftLTable[i] = coPrefixLength(this.leftPTable.get(i - 1),
					this.leftPTable.get(i));
		}
	}
	
	private void countTFNeighbor(){
		for(int pIndex = 0; pIndex < this.rightPTable.size(); pIndex++){  
			String phrase = this.rightPTable.get(pIndex);
			for (int length = 1 + this.rightLTable[pIndex]; length <= N
					&& length <= phrase.length(); length++) {
				String word = phrase.substring(0, length);
				TFNeighbor tfNeighbor = new TFNeighbor();
				tfNeighbor.incrementTF();
				if(phrase.length() > length)
					tfNeighbor.addToRightNeighbor( phrase.charAt(length) );
				for(int lIndex = pIndex+1; lIndex < this.rightLTable.length; lIndex++){  
					if(this.rightLTable[lIndex] >= length){ 
						tfNeighbor.incrementTF();
						String coPhrase = this.rightPTable.get(lIndex);
						if(coPhrase.length() > length)  
				              tfNeighbor.addToRightNeighbor(coPhrase.charAt(length));
					}else{
						break;
					}
				}
				this.wordTFNeighbor.put(word, tfNeighbor);
			}
		}
		
		for(int pIndex = 0; pIndex < this.leftPTable.size(); pIndex++){  
			String phrase = this.leftPTable.get(pIndex);
			for(int length = 1 + leftLTable[pIndex]; length <= N 
					&& length <= phrase.length(); length++){ 
				String word = StringUtils.reverse(phrase.substring(0, length)); 
		        TFNeighbor tfNeighbor = this.wordTFNeighbor.get(word); 
		        if(phrase.length() > length)  
		            tfNeighbor.addToLeftNeighbor(phrase.charAt(length));
				for(int lIndex = pIndex + 1; lIndex < leftLTable.length; lIndex++){  
					if(leftLTable[lIndex] >= length){  
					String coPhrase = leftPTable.get(lIndex);  
					if(coPhrase.length() > length)  
						tfNeighbor.addToLeftNeighbor(coPhrase.charAt(length));  
					}else{
						break;  
					}
				}
			}
		}
	}
	
	private double countMI(String word){  
		if(word.length() <= 1)  return 0;
		float coProbability = this.wordTFNeighbor.get(word).getTF()/wordNumber;
		List<Float> mi = new ArrayList<Float>(word.length());
		for(int pos = 1; pos < word.length(); pos++){
			String leftPart = word.substring(0, pos);
			String rightPart = word.substring(pos);
			float leftProbability = this.wordTFNeighbor.get(leftPart).getTF()/wordNumber;
			float rightProbability = this.wordTFNeighbor.get(rightPart).getTF()/wordNumber;
			float probability = (leftProbability*rightProbability);
			if(probability==0)probability = 1;
			mi.add(coProbability/probability);
		}  
		return Collections.min(mi);
	}
	
	private String printTFNeighborInfoMI(String stopList, String[] threshold){
		try{
			Set<String> stopWords = new HashSet<String>();
			if(StringUtils.isNotEmpty(stopList)){
				BufferedReader br = new BufferedReader(new FileReader(stopList));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.length() > 1) stopWords.add(line);
				}
				br.close();
			}
			
			StringBuffer out = new StringBuffer();
			for (Map.Entry<String, TFNeighbor> entry : this.wordTFNeighbor.entrySet()) {
				if (entry.getKey().length() <= 1 || stopWords.contains(entry.getKey()))
					continue;
				TFNeighbor tfNeighbor = entry.getValue();

				int tf, leftNeighborNumber, rightNeighborNumber;
				double mi;
				tf = tfNeighbor.getTF();
				leftNeighborNumber = tfNeighbor.getLeftNeighborNumber();
				rightNeighborNumber = tfNeighbor.getRightNeighborNumber();
				mi = countMI(entry.getKey());
				if (tf > Integer.parseInt(threshold[0])
						&& leftNeighborNumber > Integer.parseInt(threshold[1])
						&& rightNeighborNumber > Integer.parseInt(threshold[2])
						&& mi > Integer.parseInt(threshold[3])) {
					StringBuilder sb = new StringBuilder();
					sb.append(entry.getKey()).append("\n");
					/*sb.append(",").append(tf);
					sb.append(",").append(leftNeighborNumber);
					sb.append(",").append(rightNeighborNumber);
					sb.append(",").append(tfNeighbor.getLeftNeighborEntropy());
					sb.append(",").append(tfNeighbor.getRightNeighborEntropy());
					sb.append(",").append(mi).append("\n");*/
					out.append(sb.toString());
				}
			}
			return out.toString();
		}catch(IOException ex){
			throw new RuntimeException(ex);
		}
	}
	
	public static String applyNagao(String content){
		NagaoAlgorithm nagaoAlgorithm = new NagaoAlgorithm();
		
		String[] phrases = content.split("\t|\r|\n");
		for(String phrase : phrases){
			if(StringUtils.isEmpty(phrase.trim()))continue;
			nagaoAlgorithm.addToPTable(phrase.trim());
		}
		
		nagaoAlgorithm.countLTable();
		nagaoAlgorithm.countTFNeighbor();
		
		String result = nagaoAlgorithm.printTFNeighborInfoMI("",
				"0,0,0,-1".split(","));
		return result;
	}
	
	class TFNeighbor {
		private int tf;
		private Map<Character, Integer> leftNeighbor;
		private Map<Character, Integer> rightNeighbor;

		public TFNeighbor() {
			leftNeighbor = new HashMap<Character, Integer>();
			rightNeighbor = new HashMap<Character, Integer>();
		}

		// add word to leftNeighbor
		public void addToLeftNeighbor(char word) {
			// leftNeighbor.put(word, 1 + leftNeighbor.getOrDefault(word, 0));
			Integer number = leftNeighbor.get(word);
			leftNeighbor.put(word, number == null ? 1 : 1 + number);
		}

		// add word to rightNeighbor
		public void addToRightNeighbor(char word) {
			// rightNeighbor.put(word, 1 + rightNeighbor.getOrDefault(word, 0));
			Integer number = rightNeighbor.get(word);
			rightNeighbor.put(word, number == null ? 1 : 1 + number);
		}

		// increment tf
		public void incrementTF() {
			tf++;
		}

		public int getLeftNeighborNumber() {
			return leftNeighbor.size();
		}

		public int getRightNeighborNumber() {
			return rightNeighbor.size();
		}

		public double getLeftNeighborEntropy() {
			double entropy = 0;
			int sum = 0;
			for (int number : leftNeighbor.values()) {
				entropy += number * Math.log(number);
				sum += number;
			}
			if (sum == 0)
				return 0;
			return Math.log(sum) - entropy / sum;
		}

		public double getRightNeighborEntropy() {
			double entropy = 0;
			int sum = 0;
			for (int number : rightNeighbor.values()) {
				entropy += number * Math.log(number);
				sum += number;
			}
			if (sum == 0)
				return 0;
			return Math.log(sum) - entropy / sum;
		}

		public int getTF() {
			return tf;
		}
	}
}
