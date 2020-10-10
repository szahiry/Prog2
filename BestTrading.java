package cscd300_Porogram2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BestTrading {
	
	public static void main (String[] args) throws FileNotFoundException {
		Scanner kb = new Scanner (System.in);

	   //System.out.println("enter the file name ");
	   //String filename = kb.nextLine();
	   
	   ArrayList<Integer> numbers = fillArray(args[0]);
	   //ArrayList<Integer> numbers = fillArray(filename);
	   int[] A = BestTrading(numbers, 0, numbers.size() );
	   System.out.println("The best to buy " + A[0] + "\nThe best day to sell " + A[1] + "\nThe best day to profit " + A[2]);
	}//end the main.
	
	public static ArrayList<Integer> fillArray(String filename) throws FileNotFoundException {
	    Scanner newFile = new Scanner(new File(filename));//variable that will take in file
	    ArrayList<Integer> numbers = new ArrayList<Integer>();
	   
	    while(newFile.hasNext()){
	        int currentNum = newFile.nextInt();
	        numbers.add(currentNum);
	    }
	   
	        newFile.close();
	        return numbers;

	}//end of fillArray method.
	
	public static int [] BestTrading(ArrayList<Integer> p, int low, int high) throws FileNotFoundException {
		
		if(low > high)
			
				throw new IllegalArgumentException("Something is worng");
		
		if(low == high) {
			int [] array_0 = {low, high ,0};
			return array_0; //buy day = low, sell day = high; profit = 0;
		}
		
        int mid = (int) Math.floor((low+high)/2);
        
        int [] array_left = BestTrading(p, low, mid);// candidate 1
        
        int [] array_right = BestTrading(p, mid + 1, high); // candidate 2
        
        int [] array_across = BestTradingAcross(p, low, high);// candidate 3
        
		
		if(array_left[2] >= array_right[2] && array_left[2] >= array_across[2])
			return array_left;
		else if(array_right[2] >= array_left[2] && array_right[2] >= array_across[2])
			return array_right;
		else
			return array_across;
		}//end of BestTrading
	
	
	public static int[] BestTradingAcross(ArrayList<Integer> p, int low, int high) {

		if(low >= high)
			throw new IllegalArgumentException("low is greater or equal to high");
		
		int mid = (int)Math.floor((low+high)/2);
		int buy = 0, sell = 0;
		int L = p.get(low);
			
        for (int x = 0; x <= mid; x++) { // searching for smallest elements.
        	if(p.get(x) < L) {
                L = p.get(x);
                buy = x;
        	}
        }
        	int H = 0;
        	for(int y = mid +1; y > high; y++) {// searching for largest elements.
        		if(p.get(y) > H) {
        		H = p.get(y);
        		sell = y;
        		}	
        }
  
        int[] array = new int [3];
        	array[0] = buy;
        	array[1] = sell;
        	array[2] = p.get(sell)-p.get(buy);
		return array;
		}//end of BestTradingAcross
		
	
}