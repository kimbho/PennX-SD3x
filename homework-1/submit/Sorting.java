
public class Sorting {
	
	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 * 
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr
	 */
    
	static void merge(CompareInt[] arr, CompareInt[] aux, int low, int mid, int high){
		for(int i=low; i<=high; i++)
			aux[i] = arr[i];
		int i=low, j=mid+1, k=low;
		while(i<=mid && j<=high){
			if(aux[i].compareTo(aux[j])<0)
				arr[k++] = aux[i++];
			else arr[k++] = aux[j++];
		}
		while(i<=mid) arr[k++] = aux[i++];
		while(j<=high) arr[k++] = aux[j++];
	}
   

	static void sort(CompareInt[] A, CompareInt[] aux, int lo, int hi){
		if(hi <= lo) return;
		int mid = (lo + hi) / 2;
		sort(A, aux, lo, mid);
		sort(A, aux, mid+1,hi);
		merge(A, aux, lo, mid, hi);
	}
		
		
	public static void mergeSort(CompareInt[] arr) {
		CompareInt[] aux = new CompareInt[arr.length];
		sort(arr, aux, 0, (arr.length)-1);
	}
	
	/**
	 * Implement the quickSelect
	 * 
	 * Again, you will probably want to use helper functions here
	 * (ex. partition(), a helper quickselect function)
	 */
	
	public static CompareInt quickSelect(int k, CompareInt[] arr) {
		//TODO
		return kthSmallest(arr, 0, arr.length-1, k);
	}
	
	static CompareInt kthSmallest(CompareInt[] arr, int l, int r, int k){
		if(k>0 && k<=r-l+1){
			int index = partition(arr, l, r);
			if(index - l == k - 1)
				return arr[index];
			if(index - l > k-1)
				return kthSmallest(arr, l, index-1, k);
			return kthSmallest(arr, index+1, r, k-index+l-1);
		}
		return arr[r];
	}
	
	static int partition(CompareInt[] arr, int l, int r){
		CompareInt x = arr[r]; int i=l;
		for(int j=l; j<=r; j++){
			if(arr[j].compareTo(x)<0){
				CompareInt tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
			}
		}
		CompareInt tmp = arr[r];
		arr[r] = arr[i];
		arr[i] = tmp;
		return i;
	}

}
