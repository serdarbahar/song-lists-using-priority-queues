import java.util.HashMap;

public class MinPriorityQueue {

	public int currentSize = 0;
	public Song[] array = new Song[10];
	public String type;
	
	HashMap<String, Integer> hashMap = new HashMap<>();
	
	public static int counter = 0;

	MinPriorityQueue(String t) {
		this.type = t;
	}

	public void insert(Song song) {
		

		if (currentSize == array.length - 1) {
			enlargeArray( array.length * 2 - 1);
		}

		int hole = ++currentSize;
		
		if (type.equals("heart")) {
			
			
	
			for ( array[0] = song ; Project3.isAfter("heart",song,array[hole/2]) ; hole /= 2) {
					
				array[hole] = array[hole/2]; 
				hashMap.put(array[hole].name,hole);
			}

			array[hole] = song;
			hashMap.put(array[hole].name,hole);
			
			
			
			
			
		}

		
		else if (type.equals("road")) {
			for ( array[0] = song ; Project3.isAfter("road",song,array[hole/2]) ; hole /= 2) {

				
				array[hole] = array[hole/2]; 
				hashMap.put(array[hole].name,hole);
			}

			array[hole] = song;
			hashMap.put(array[hole].name,hole);


		}
		
		
		else if (type.equals("bliss")) {
			for ( array[0] = song ; Project3.isAfter("bliss",song,array[hole/2])  ; hole /= 2) {
				
				array[hole] = array[hole/2];
				hashMap.put(array[hole].name,hole);
			}
			
			array[hole] = song;
			hashMap.put(array[hole].name,hole);
		}



	}


	public Song deleteMin() {
		
		Song s = array[1];

		array[1] = array[ currentSize-- ];

		percolateDown(1);
	
		hashMap.remove(s.name);
		
		return s;
		
		

	}


	private void percolateDown(int hole) {
		
		
		
		int child;
		Song tmp = array[hole];
		


		if (type.equals("heart")) {
			for ( ; hole * 2 <= currentSize ; hole = child) {

				child = hole*2;
								
				if ( child != currentSize && Project3.isAfter("heart",array[child+1],array[child]) ) {
						child++;
					}
					
				if (Project3.isAfter("heart",array[child],tmp)) {
					array[hole] = array[child];
					hashMap.put(array[hole].name,hole);
				}
				
				else
					break;
			}

			array[hole] = tmp;
			hashMap.put(array[hole].name,hole);
			

		}

		else if (type.equals("road")) {
			for ( ; hole * 2 <= currentSize ; hole = child) {

				
				child = hole*2;
				

				if ( child != currentSize && Project3.isAfter("road",array[child+1],array[child] )) {
						child++;
					}
					
				if (Project3.isAfter("road",array[child],tmp)) {
					array[hole] = array[child];
					hashMap.put(array[hole].name,hole);
				}
				
				else
					break;
			}
				

			array[hole] = tmp;
			hashMap.put(array[hole].name,hole);
		}

		else if (type.equals("bliss")) {
			for ( ; hole * 2 <= currentSize ; hole = child) {

				child = hole*2;
				
				
				if ( child != currentSize && Project3.isAfter("bliss",array[child+1],array[child] )) {
						child++;
					}
					
				if (Project3.isAfter("bliss",array[child],tmp)) {
					array[hole] = array[child];
					hashMap.put(array[hole].name,hole);
				}
				
				else
					break;
			}

			array[hole] = tmp;
			hashMap.put(array[hole].name,hole);
		}

	}


	private void enlargeArray(int i) {
		
		

		Song[] array = new Song[i];
		System.arraycopy(this.array,0,array,0,this.array.length);
		this.array = array;
		


	}


	//check deep copy
	public PriorityQueue clone() {
		PriorityQueue clone = new PriorityQueue();
		clone.currentSize = this.currentSize;
		
		Song[] arrayforClone = new Song[this.array.length];
		System.arraycopy(this.array,0,arrayforClone,0,this.array.length);
		clone.array = arrayforClone;
		
		clone.type = this.type;
		return clone;
		
	}


	public void remove(String name) {
		
		int index = hashMap.get(name);
		
		array[index] = array[ currentSize-- ];
		
		percolateUp(index);
		
		percolateDown(index);
		
		hashMap.remove(name);
		
		

	}
	
	private void percolateUp(int index) {
		
		if (type.equals("heart")) {

			Song song = array[index];

			for (array[0] = song; Project3.isAfter("heart", song, array[index/2]); index/=2) {
				array[index] = array[index/2];
				hashMap.put(array[index].name,index);
			}
			
			array[index] = song;
			hashMap.put(array[index].name,index);
			
		}
		
		if (type.equals("road")) {

			Song song = array[index];

			for (array[0] = song; Project3.isAfter("road", song, array[index/2]); index/=2) {
				array[index] = array[index/2];
				hashMap.put(array[index].name,index);
			}
			
			array[index] = song;
			hashMap.put(array[index].name,index);
			
		}
		
		if (type.equals("bliss")) {

			Song song = array[index];

			for (array[0] = song; Project3.isAfter("bliss", song, array[index/2]); index/=2) {
				array[index] = array[index/2];
				hashMap.put(array[index].name,index);
			}
			
			array[index] = song;
			hashMap.put(array[index].name,index);
			
		}
		
	}

	
	
	
	public boolean contains(String name) {
		
		return hashMap.containsKey(name);
		
	}
	
	
	
	
	
	
	public static boolean isHeap(MinPriorityQueue heap) {
		

		for (int i = 1; i <= heap.currentSize / 2; i++) {
			
			if (!Project3.isAfter("heart",heap.array[i],heap.array[2*i])) {
				return false;
			}
			if (2*i+1<=heap.currentSize)
				if (!Project3.isAfter("heart",heap.array[i],heap.array[2*i+1])) {
					return false;
				}
		}
		return true;
		
		
		
		
	}
	
	
	public void printHeap() {
		for (int i = 1; i<currentSize+1; i++) {
			System.out.println(this.array[i].id + " " + this.array[i].blissScore + " Playlist: " + this.array[i].playlist);
		}
	}
	
	
	
	
	
}


