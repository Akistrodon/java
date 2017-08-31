import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
public class PuzzleJava{
	
	ArrayList<Integer> puzA(int[] arr){
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i=0; i<arr.length; i++){
			System.out.println(arr[i]);
			if(arr[i] > 10){
				results.add(arr[i]);
			}
		}
		return results;
	}

	ArrayList<String> puzB(ArrayList<String> arr){
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> shuffle = new ArrayList<String>();
		System.out.println(arr);
		int size = arr.size();
		// so why didn't the loop below function correctly when written as:
		// for(int i=0; i<arr.size(); i++)
		// ??
		//
		for(int i=0; i<size; i++){
			int randomNum = ThreadLocalRandom.current().nextInt(0,  arr.size());
			shuffle.add(arr.get(randomNum));
			arr.remove(randomNum);
		}
		System.out.println(shuffle);
		System.out.println(arr);
		return results;
	}

}
