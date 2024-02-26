import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project3 {

	public static void main(String args[]) throws IOException {

		String outFileName = args[2];
		FileWriter fileWriter = new FileWriter(outFileName);

		String songFilePath = args[0];
		File songFile = new File(songFilePath);
		Scanner scanner1 = new Scanner(songFile);

		String s = scanner1.nextLine();
		int numSongs = Integer.parseInt(s);
		Song[] songs = new Song[numSongs+1];

		for (int i=0; i<numSongs; i++) {
			String line = scanner1.nextLine(); String[] array = line.split(" ");
			int id = Integer.parseInt(array[0]); String name = array[1]; int playCount = Integer.parseInt(array[2]); 
			int heartS = Integer.parseInt(array[3]); int roadS = Integer.parseInt(array[4]); int blissS = Integer.parseInt(array[5]);

			Song song = new Song();
			song.name = name; song.playCount=playCount; song.heartScore = heartS; song.roadScore = roadS; song.blissScore = blissS; song.id = id;

			songs[id] = song;			

		}

		scanner1.close();


		String filePath = args[1];
		File testFile = new File(filePath);
		Scanner scanner2 = new Scanner(testFile);


		String s1 = scanner2.nextLine();
		String[] array = s1.split(" ");

		int playlistLim = Integer.parseInt(array[0]); 
		int heartLim = Integer.parseInt(array[1]); int roadLim = Integer.parseInt(array[2]); int blissLim = Integer.parseInt(array[3]);

		int numPlaylists = Integer.parseInt(scanner2.nextLine());

		
		PriorityQueue[] waitingPlaylistQueuesforHeart = new PriorityQueue[numPlaylists + 1];
		for (int i = 1; i<numPlaylists + 1; i++) {
			waitingPlaylistQueuesforHeart[i] = new PriorityQueue("heart");
		}
		PriorityQueue maximumsHeart = new PriorityQueue("heart");
		
		PriorityQueue[] waitingPlaylistQueuesforRoad = new PriorityQueue[numPlaylists + 1];
		for (int i = 1; i<numPlaylists + 1; i++) {
			waitingPlaylistQueuesforRoad[i] = new PriorityQueue("road");
			//ben geyim
		}
		PriorityQueue maximumsRoad = new PriorityQueue("road");

		PriorityQueue[] waitingPlaylistQueuesforBliss = new PriorityQueue[numPlaylists + 1];
		for (int i = 1; i<numPlaylists + 1; i++) {
			waitingPlaylistQueuesforBliss[i] = new PriorityQueue("bliss");
		}
		PriorityQueue maximumsBliss = new PriorityQueue("bliss");

	
		for (int i = 0; i<numPlaylists; i++) {

			String[] array1 = scanner2.nextLine().split(" ");
			int playlistId = Integer.parseInt(array1[0]); int numSongsinPlaylist = Integer.parseInt(array1[1]);


			String[] array2 = scanner2.nextLine().split(" ");
			for (int j = 0; j<numSongsinPlaylist; j++) {
				int songId = Integer.parseInt(array2[j]);

				Song song = songs[songId];
				song.playlist = playlistId;
				
				if (waitingPlaylistQueuesforHeart[playlistId].currentSize != 0) {
					if (isPrior("heart",song,waitingPlaylistQueuesforHeart[playlistId].array[1])) {
						maximumsHeart.remove(waitingPlaylistQueuesforHeart[playlistId].array[1].name);
						maximumsHeart.insert(song);
					}
				}
				else {
					maximumsHeart.insert(song);
				}
				
				waitingPlaylistQueuesforHeart[playlistId].insert(song);
				
				
				if (waitingPlaylistQueuesforRoad[playlistId].currentSize != 0) {
					if (isPrior("road",song,waitingPlaylistQueuesforRoad[playlistId].array[1])) {
						maximumsRoad.remove(waitingPlaylistQueuesforRoad[playlistId].array[1].name);
						maximumsRoad.insert(song);
					}
				}
				else {
					maximumsRoad.insert(song);
				}
				
				waitingPlaylistQueuesforRoad[playlistId].insert(song);
				
				if (waitingPlaylistQueuesforBliss[playlistId].currentSize != 0) {
					if (isPrior("bliss",song,waitingPlaylistQueuesforBliss[playlistId].array[1])) {
						maximumsBliss.remove(waitingPlaylistQueuesforBliss[playlistId].array[1].name);
						maximumsBliss.insert(song);
					}
				}
				else {
					maximumsBliss.insert(song);
				}
				
				waitingPlaylistQueuesforBliss[playlistId].insert(song);			
				
			}

		}

		
		MinPriorityQueue[] playlistQueuesforHeart = new MinPriorityQueue[numPlaylists + 1];
		for (int i = 1; i<numPlaylists + 1; i++) {
			playlistQueuesforHeart[i] = new MinPriorityQueue("heart");
		}
		

		
		MinPriorityQueue minimumsHeart = new MinPriorityQueue("heart");
		
		buildCategoryforEpicBlend(minimumsHeart, maximumsHeart, waitingPlaylistQueuesforHeart,
				playlistLim, heartLim, numPlaylists, playlistQueuesforHeart, "heart");


		MinPriorityQueue[] playlistQueuesforRoad = new MinPriorityQueue[numPlaylists + 1];
		for (int i = 1; i<numPlaylists + 1; i++) {
			playlistQueuesforRoad[i] = new MinPriorityQueue("road");
		}
	
		MinPriorityQueue minimumsRoad = new MinPriorityQueue("road");
		buildCategoryforEpicBlend(minimumsRoad, maximumsRoad, waitingPlaylistQueuesforRoad,
				playlistLim, roadLim, numPlaylists, playlistQueuesforRoad, "road");


		MinPriorityQueue[] playlistQueuesforBliss = new MinPriorityQueue[numPlaylists + 1];
		for (int i = 1; i<numPlaylists + 1; i++) {
			playlistQueuesforBliss[i] = new MinPriorityQueue("bliss");
		}
	
		MinPriorityQueue minimumsBliss = new MinPriorityQueue("bliss");
		buildCategoryforEpicBlend(minimumsBliss, maximumsBliss, waitingPlaylistQueuesforBliss,
				playlistLim, blissLim, numPlaylists, playlistQueuesforBliss, "bliss");

		
		
		
		
		
		
		
		
		
		
		

		int E = Integer.parseInt(scanner2.nextLine());
		for (int i = 0; i<E; i++) {
			
			s1 = scanner2.nextLine();
			array = s1.split(" ");

			if (array[0].equals("ADD")) {

				int songId = Integer.parseInt(array[1]);
				int playlistId = Integer.parseInt(array[2]);

				Song song = songs[songId];
				song.playlist = playlistId;

				Song addedHeart = null; Song removedHeart = null;
				Song addedRoad = null; Song removedRoad = null;
				Song addedBliss = null; Song removedBliss = null;


				/////// HEART ///////
				/////////////////////
			
				int numH=0;
				for (int k = 1; k<playlistQueuesforHeart.length; k++) {
					numH += playlistQueuesforHeart[k].currentSize;
				}

				if (numH == heartLim) {
					
					if (playlistQueuesforHeart[playlistId].currentSize == playlistLim) {


						if (isPrior("heart",song,playlistQueuesforHeart[playlistId].array[1])) {

							addedHeart = song;	

							removedHeart = playlistQueuesforHeart[playlistId].deleteMin();								
							playlistQueuesforHeart[playlistId].insert(song);	

							
							
							if (waitingPlaylistQueuesforHeart[removedHeart.playlist].currentSize != 0) {
								if (isPrior("heart", removedHeart, waitingPlaylistQueuesforHeart[removedHeart.playlist].array[1])) {
									maximumsHeart.remove(waitingPlaylistQueuesforHeart[removedHeart.playlist].array[1].name);
									maximumsHeart.insert(removedHeart);
								}	
							}
							else {
								maximumsHeart.insert(removedHeart);
							}
							waitingPlaylistQueuesforHeart[removedHeart.playlist].insert(removedHeart);
							
			

							minimumsHeart.remove(removedHeart.name);
							minimumsHeart.insert(playlistQueuesforHeart[playlistId].array[1]);	
						}							
					}

					//remove the last song if playlist limit is not exceeded
					else {
						
						
						
						if (isPrior("heart",song, minimumsHeart.array[1])) {

							addedHeart = song;

							removedHeart = minimumsHeart.deleteMin();
							
							if (waitingPlaylistQueuesforHeart[removedHeart.playlist].currentSize != 0) {
								if (isPrior("heart", removedHeart, waitingPlaylistQueuesforHeart[removedHeart.playlist].array[1])) {
									maximumsHeart.remove(waitingPlaylistQueuesforHeart[removedHeart.playlist].array[1].name);
									maximumsHeart.insert(removedHeart);
								}	
							}
							else {
								maximumsHeart.insert(removedHeart);
							}
							
							waitingPlaylistQueuesforHeart[removedHeart.playlist].insert(removedHeart);
							
							if (removedHeart.playlist == song.playlist) {
								
								playlistQueuesforHeart[removedHeart.playlist].deleteMin();
								playlistQueuesforHeart[playlistId].insert(song);
								minimumsHeart.insert(playlistQueuesforHeart[playlistId].array[1]);	
							}
 
							else {
								playlistQueuesforHeart[removedHeart.playlist].deleteMin();

								if (playlistQueuesforHeart[removedHeart.playlist].currentSize != 0)
									minimumsHeart.insert(playlistQueuesforHeart[removedHeart.playlist].array[1]);

								
								Song prevMinOfAddedSongPlaylist = null;
								if (playlistQueuesforHeart[playlistId].currentSize != 0) {
									prevMinOfAddedSongPlaylist = playlistQueuesforHeart[playlistId].array[1];
								}


								playlistQueuesforHeart[playlistId].insert(song);

								//playlist minimum has changed
								if (prevMinOfAddedSongPlaylist != null) {
									if (!prevMinOfAddedSongPlaylist.equals(playlistQueuesforHeart[playlistId].array[1])) {
										minimumsHeart.remove(prevMinOfAddedSongPlaylist.name);
										minimumsHeart.insert(playlistQueuesforHeart[playlistId].array[1]);
									}
								}
								else {
									minimumsHeart.insert(song);			
								}
							}

						}
					}
				}

				//category limit is not exceeded. freely add if playlist limit is not exceeded.
				else {

					//remove 
					if (playlistQueuesforHeart[playlistId].currentSize == playlistLim) {

						if (isPrior("heart",song,playlistQueuesforHeart[playlistId].array[1])) {

							addedHeart = song;

							removedHeart = playlistQueuesforHeart[playlistId].deleteMin();
							minimumsHeart.remove(removedHeart.name);
							
							if (waitingPlaylistQueuesforHeart[removedHeart.playlist].currentSize != 0) {
								if (isPrior("heart", removedHeart, waitingPlaylistQueuesforHeart[removedHeart.playlist].array[1])) {
									maximumsHeart.remove(waitingPlaylistQueuesforHeart[removedHeart.playlist].array[1].name);
									maximumsHeart.insert(removedHeart);
								}	
							}
							else {
								maximumsHeart.insert(removedHeart);
							}
							waitingPlaylistQueuesforHeart[removedHeart.playlist].insert(removedHeart);

							playlistQueuesforHeart[playlistId].insert(song);
							minimumsHeart.insert(playlistQueuesforHeart[playlistId].array[1]);

						}	
					}
					//add the new song without removing
					else {

						if (numH==0) {
							
							addedHeart = song;
							playlistQueuesforHeart[playlistId].insert(song);
							minimumsHeart.insert(song);
						}

						else {
							
							Song playlistMin = null;
							if (playlistQueuesforHeart[playlistId].currentSize > 0) {
								playlistMin = playlistQueuesforHeart[playlistId].array[1];
							}

							playlistQueuesforHeart[playlistId].insert(song);
							addedHeart = song;

							if (playlistMin != null) {
								if (!playlistMin.equals(playlistQueuesforHeart[playlistId].array[1])) {
									minimumsHeart.remove(playlistMin.name);
									minimumsHeart.insert(playlistQueuesforHeart[playlistId].array[1]);	
								}
							}

							else {
								minimumsHeart.insert(song);
							}
						}
					}
				}
				
				
				int numR=0;
				for (int k = 1; k<playlistQueuesforRoad.length; k++) {
					numR += playlistQueuesforRoad[k].currentSize;
				}

				if (numR == roadLim) {
					
					if (playlistQueuesforRoad[playlistId].currentSize == playlistLim) {


						if (isPrior("road",song,playlistQueuesforRoad[playlistId].array[1])) {

							addedRoad = song;	

							removedRoad = playlistQueuesforRoad[playlistId].deleteMin();								
							playlistQueuesforRoad[playlistId].insert(song);	

							
							
							if (waitingPlaylistQueuesforRoad[removedRoad.playlist].currentSize != 0) {
								if (isPrior("road", removedRoad, waitingPlaylistQueuesforRoad[removedRoad.playlist].array[1])) {
									maximumsRoad.remove(waitingPlaylistQueuesforRoad[removedRoad.playlist].array[1].name);
									maximumsRoad.insert(removedRoad);
								}	
							}
							else {
								maximumsRoad.insert(removedRoad);
							}
							waitingPlaylistQueuesforRoad[removedRoad.playlist].insert(removedRoad);
							
			

							minimumsRoad.remove(removedRoad.name);
							minimumsRoad.insert(playlistQueuesforRoad[playlistId].array[1]);	
						}							
					}

					//remove the last song if playlist limit is not exceeded
					else {
						
						if (isPrior("road",song, minimumsRoad.array[1])) {

							addedRoad = song;

							removedRoad = minimumsRoad.deleteMin();
							
							if (waitingPlaylistQueuesforRoad[removedRoad.playlist].currentSize != 0) {
								if (isPrior("road", removedRoad, waitingPlaylistQueuesforRoad[removedRoad.playlist].array[1])) {
									maximumsRoad.remove(waitingPlaylistQueuesforRoad[removedRoad.playlist].array[1].name);
									maximumsRoad.insert(removedRoad);
								}	
							}
							else {
								maximumsRoad.insert(removedRoad);
							}
							
							waitingPlaylistQueuesforRoad[removedRoad.playlist].insert(removedRoad);
							
							if (removedRoad.playlist == song.playlist) {
								
								playlistQueuesforRoad[removedRoad.playlist].deleteMin();
								playlistQueuesforRoad[playlistId].insert(song);
								minimumsRoad.insert(playlistQueuesforRoad[playlistId].array[1]);	
							}
 
							else {
								playlistQueuesforRoad[removedRoad.playlist].deleteMin();

								if (playlistQueuesforRoad[removedRoad.playlist].currentSize != 0)
									minimumsRoad.insert(playlistQueuesforRoad[removedRoad.playlist].array[1]);

								
								Song prevMinOfAddedSongPlaylist = null;
								if (playlistQueuesforRoad[playlistId].currentSize != 0) {
									prevMinOfAddedSongPlaylist = playlistQueuesforRoad[playlistId].array[1];
								}


								playlistQueuesforRoad[playlistId].insert(song);

								//playlist minimum has changed
								if (prevMinOfAddedSongPlaylist != null) {
									if (!prevMinOfAddedSongPlaylist.equals(playlistQueuesforRoad[playlistId].array[1])) {
										minimumsRoad.remove(prevMinOfAddedSongPlaylist.name);
										minimumsRoad.insert(playlistQueuesforRoad[playlistId].array[1]);
									}
								}
								else {
									minimumsRoad.insert(song);			
								}
							}

						}
					}
				}

				//category limit is not exceeded. freely add if playlist limit is not exceeded.
				else {

					//remove 
					if (playlistQueuesforRoad[playlistId].currentSize == playlistLim) {

						if (isPrior("road",song,playlistQueuesforRoad[playlistId].array[1])) {

							addedRoad = song;

							removedRoad = playlistQueuesforRoad[playlistId].deleteMin();
							minimumsRoad.remove(removedRoad.name);
							
							if (waitingPlaylistQueuesforRoad[removedRoad.playlist].currentSize != 0) {
								if (isPrior("road", removedRoad, waitingPlaylistQueuesforRoad[removedRoad.playlist].array[1])) {
									maximumsRoad.remove(waitingPlaylistQueuesforRoad[removedRoad.playlist].array[1].name);
									maximumsRoad.insert(removedRoad);
								}	
							}
							else {
								maximumsRoad.insert(removedRoad);
							}
							waitingPlaylistQueuesforRoad[removedRoad.playlist].insert(removedRoad);

							playlistQueuesforRoad[playlistId].insert(song);
							minimumsRoad.insert(playlistQueuesforRoad[playlistId].array[1]);

						}	
					}
					//add the new song without removing
					else {

						if (numR==0) {
							
							addedRoad = song;
							playlistQueuesforRoad[playlistId].insert(song);
							minimumsRoad.insert(song);
						}

						else {
							
							Song playlistMin = null;
							if (playlistQueuesforRoad[playlistId].currentSize > 0) {
								playlistMin = playlistQueuesforRoad[playlistId].array[1];
							}

							playlistQueuesforRoad[playlistId].insert(song);
							addedRoad = song;

							if (playlistMin != null) {
								if (!playlistMin.equals(playlistQueuesforRoad[playlistId].array[1])) {
									minimumsRoad.remove(playlistMin.name);
									minimumsRoad.insert(playlistQueuesforRoad[playlistId].array[1]);	
								}
							}

							else {
								minimumsRoad.insert(song);
							}
						}
					}
				}

				

				int numB=0;
				for (int k = 1; k<playlistQueuesforBliss.length; k++) {
					numB += playlistQueuesforBliss[k].currentSize;
				}
			

				if (numB == blissLim) {
					
					if (playlistQueuesforBliss[playlistId].currentSize == playlistLim) {


						if (isPrior("bliss",song,playlistQueuesforBliss[playlistId].array[1])) {

							addedBliss = song;	

							removedBliss = playlistQueuesforBliss[playlistId].deleteMin();								
							playlistQueuesforBliss[playlistId].insert(song);	

							
							
							if (waitingPlaylistQueuesforBliss[removedBliss.playlist].currentSize != 0) {
								if (isPrior("bliss", removedBliss, waitingPlaylistQueuesforBliss[removedBliss.playlist].array[1])) {
									maximumsBliss.remove(waitingPlaylistQueuesforBliss[removedBliss.playlist].array[1].name);
									maximumsBliss.insert(removedBliss);
								}	
							}
							else {
								maximumsBliss.insert(removedBliss);
							}
							waitingPlaylistQueuesforBliss[removedBliss.playlist].insert(removedBliss);
							
			

							minimumsBliss.remove(removedBliss.name);
							minimumsBliss.insert(playlistQueuesforBliss[playlistId].array[1]);	
						}							
					}

					//remove the last song if playlist limit is not exceeded
					else {
						
						if (isPrior("bliss",song, minimumsBliss.array[1])) {

							addedBliss = song;

							removedBliss = minimumsBliss.deleteMin();
							
							if (waitingPlaylistQueuesforBliss[removedBliss.playlist].currentSize != 0) {
								if (isPrior("bliss", removedBliss, waitingPlaylistQueuesforBliss[removedBliss.playlist].array[1])) {
									maximumsBliss.remove(waitingPlaylistQueuesforBliss[removedBliss.playlist].array[1].name);
									maximumsBliss.insert(removedBliss);
								}	
							}
							else {
								maximumsBliss.insert(removedBliss);
							}
							
							waitingPlaylistQueuesforBliss[removedBliss.playlist].insert(removedBliss);
							
							if (removedBliss.playlist == song.playlist) {
								
								playlistQueuesforBliss[removedBliss.playlist].deleteMin();
								playlistQueuesforBliss[playlistId].insert(song);
								minimumsBliss.insert(playlistQueuesforBliss[playlistId].array[1]);	
							}
 
							else {
								playlistQueuesforBliss[removedBliss.playlist].deleteMin();

								if (playlistQueuesforBliss[removedBliss.playlist].currentSize != 0)
									minimumsBliss.insert(playlistQueuesforBliss[removedBliss.playlist].array[1]);

								
								Song prevMinOfAddedSongPlaylist = null;
								if (playlistQueuesforBliss[playlistId].currentSize != 0) {
									prevMinOfAddedSongPlaylist = playlistQueuesforBliss[playlistId].array[1];
								}

								playlistQueuesforBliss[playlistId].insert(song);

								//playlist minimum has changed
								if (prevMinOfAddedSongPlaylist != null) {
									if (!prevMinOfAddedSongPlaylist.equals(playlistQueuesforBliss[playlistId].array[1])) {
										minimumsBliss.remove(prevMinOfAddedSongPlaylist.name);
										minimumsBliss.insert(playlistQueuesforBliss[playlistId].array[1]);
									}
								}
								else {
									minimumsBliss.insert(song);			
								}
							}

						}
					}
				}

				//category limit is not exceeded. freely add if playlist limit is not exceeded.
				else {

				

					//remove 
					if (playlistQueuesforBliss[playlistId].currentSize == playlistLim) {
						

						if (isPrior("bliss",song,playlistQueuesforBliss[playlistId].array[1])) {
	
							
							
							addedBliss = song;
							
							removedBliss = playlistQueuesforBliss[playlistId].deleteMin();
							minimumsBliss.remove(removedBliss.name);
							
							if (waitingPlaylistQueuesforBliss[removedBliss.playlist].currentSize != 0) {
								if (isPrior("bliss", removedBliss, waitingPlaylistQueuesforBliss[removedBliss.playlist].array[1])) {
									maximumsBliss.remove(waitingPlaylistQueuesforBliss[removedBliss.playlist].array[1].name);
									maximumsBliss.insert(removedBliss);
								}	
							}
							else {
								maximumsBliss.insert(removedBliss);
							}
							waitingPlaylistQueuesforBliss[removedBliss.playlist].insert(removedBliss);

							playlistQueuesforBliss[playlistId].insert(song);
							minimumsBliss.insert(playlistQueuesforBliss[playlistId].array[1]);

						}	
					}
					//add the new song without removing
					else {

						if (numB==0) {
							
							addedBliss = song;
							playlistQueuesforBliss[playlistId].insert(song);
							minimumsBliss.insert(song);
						}

						else {
							
			
							
							Song playlistMin = null;
							if (playlistQueuesforBliss[playlistId].currentSize > 0) {
								playlistMin = playlistQueuesforBliss[playlistId].array[1];
							}

							playlistQueuesforBliss[playlistId].insert(song);
							addedBliss = song;

							if (playlistMin != null) {
								if (!playlistMin.equals(playlistQueuesforBliss[playlistId].array[1])) {
									minimumsBliss.remove(playlistMin.name);
									minimumsBliss.insert(playlistQueuesforBliss[playlistId].array[1]);	
								}
							}

							else {
								minimumsBliss.insert(song);
							}
						}
					}
				}

				
				
				
				int ah; int ar; int ab;
				if (addedHeart==null) {
					if (waitingPlaylistQueuesforHeart[song.playlist].currentSize != 0) {
						if (isPrior("heart", song, waitingPlaylistQueuesforHeart[song.playlist].array[1])) {
							maximumsHeart.remove(waitingPlaylistQueuesforHeart[song.playlist].array[1].name);
							maximumsHeart.insert(song);
						}	
					}
					else {
						maximumsHeart.insert(song);
					}
					waitingPlaylistQueuesforHeart[song.playlist].insert(song);
					ah=0;
				}
				else 
					ah = addedHeart.id;

				if (addedRoad==null) {
					if (waitingPlaylistQueuesforRoad[song.playlist].currentSize != 0) {
						if (isPrior("road", song, waitingPlaylistQueuesforRoad[song.playlist].array[1])) {
							maximumsRoad.remove(waitingPlaylistQueuesforRoad[song.playlist].array[1].name);
							maximumsRoad.insert(song);
						}	
					}
					else {
						maximumsRoad.insert(song);
					}
					waitingPlaylistQueuesforRoad[song.playlist].insert(song);
					ar=0;
				}
				else 
					ar = addedRoad.id;

				if (addedBliss==null) {
					if (waitingPlaylistQueuesforBliss[song.playlist].currentSize != 0) {
						if (isPrior("bliss", song, waitingPlaylistQueuesforBliss[song.playlist].array[1])) {
							maximumsBliss.remove(waitingPlaylistQueuesforBliss[song.playlist].array[1].name);
							maximumsBliss.insert(song);
						}	
					}
					else {
						maximumsBliss.insert(song);
					}
					waitingPlaylistQueuesforBliss[song.playlist].insert(song);
					ab=0;
				}
				else 
					ab = addedBliss.id;

				fileWriter.write(ah +" "+ar+" "+ab + "\n");




				int rh; int rr; int rb;
				if (removedHeart==null) {
					rh = 0;
				}
				else 
					rh = removedHeart.id;


				if (removedRoad==null) {
					rr = 0;
				}
				else
					rr = removedRoad.id;

				if (removedBliss==null) {
					rb = 0;
				}
				else
					rb = removedBliss.id;
				fileWriter.write(rh + " " + rr + " " + rb + "\n");



			}
			
			


			if (array[0].equals("REM")) {

				int songId = Integer.parseInt(array[1]);
				int playlistId = Integer.parseInt(array[2]);

				Song song = songs[songId];
				song.playlist = playlistId;

				Song addedHeart = null; Song removedHeart = null;
				Song addedRoad = null; Song removedRoad = null;
				Song addedBliss = null; Song removedBliss = null;
				
				
				//in epic blend
				if (playlistQueuesforHeart[playlistId].contains(song.name)) {
					
					removedHeart = song;
					
					if (song.equals(playlistQueuesforHeart[playlistId].array[1])) {
					
						playlistQueuesforHeart[playlistId].deleteMin();
						minimumsHeart.remove(removedHeart.name);
						if (playlistQueuesforHeart[playlistId].currentSize != 0)
							minimumsHeart.insert(playlistQueuesforHeart[playlistId].array[1]);	
					}
					else {	
						playlistQueuesforHeart[playlistId].remove(removedHeart.name);
					}	
					
					addedHeart = maximumsHeart.findNextMax(playlistQueuesforHeart, 1, playlistLim, "heart");
					if (addedHeart != null) {
						maximumsHeart.remove(addedHeart.name);
						waitingPlaylistQueuesforHeart[addedHeart.playlist].deleteMin();
						
						if (waitingPlaylistQueuesforHeart[addedHeart.playlist].currentSize != 0) {
							maximumsHeart.insert(waitingPlaylistQueuesforHeart[addedHeart.playlist].array[1]);
						}
						
						if (playlistQueuesforHeart[addedHeart.playlist].currentSize != 0) {
							if (isAfter("heart",addedHeart,playlistQueuesforHeart[addedHeart.playlist].array[1])) {
								minimumsHeart.remove(playlistQueuesforHeart[addedHeart.playlist].array[1].name);
								minimumsHeart.insert(addedHeart);
							}
						}
						else {
							minimumsHeart.insert(addedHeart);
						}
						playlistQueuesforHeart[addedHeart.playlist].insert(addedHeart);
					}
					
					
				}

				//not in epic blend
				else {
					
					if (song.equals(waitingPlaylistQueuesforHeart[playlistId].array[1])) {
										
						maximumsHeart.remove(waitingPlaylistQueuesforHeart[playlistId].deleteMin().name);
						if (waitingPlaylistQueuesforHeart[playlistId].currentSize != 0)
							maximumsHeart.insert(waitingPlaylistQueuesforHeart[playlistId].array[1]);

					}
					
					else {
						waitingPlaylistQueuesforHeart[playlistId].remove(song.name);
					}
					
					
				}
				
				
				
				
				
				
				if (playlistQueuesforRoad[playlistId].contains(song.name)) {
					
					removedRoad = song;
					
					if (song.equals(playlistQueuesforRoad[playlistId].array[1])) {
					
						playlistQueuesforRoad[playlistId].deleteMin();
						minimumsRoad.remove(removedRoad.name);
						if (playlistQueuesforRoad[playlistId].currentSize != 0) 
							minimumsRoad.insert(playlistQueuesforRoad[playlistId].array[1]);	
					}
					else {	
						playlistQueuesforRoad[playlistId].remove(removedRoad.name);
					}	
					
					addedRoad = maximumsRoad.findNextMax(playlistQueuesforRoad, 1, playlistLim, "road");
					
					if (addedRoad != null) {	
						maximumsRoad.remove(addedRoad.name);
						waitingPlaylistQueuesforRoad[addedRoad.playlist].deleteMin();
						
						if (waitingPlaylistQueuesforRoad[addedRoad.playlist].currentSize != 0) {
					
							maximumsRoad.insert(waitingPlaylistQueuesforRoad[addedRoad.playlist].array[1]);
						}
						
						if (playlistQueuesforRoad[addedRoad.playlist].currentSize != 0) {
							if (isAfter("road",addedRoad,playlistQueuesforRoad[addedRoad.playlist].array[1])) {
								minimumsRoad.remove(playlistQueuesforRoad[addedRoad.playlist].array[1].name);
								minimumsRoad.insert(addedRoad);
							}
						}
						else {
							minimumsRoad.insert(addedRoad);
						}
						playlistQueuesforRoad[addedRoad.playlist].insert(addedRoad);
					}
					
					
				}

				//not in epic blend
				else {
					
					if (song.equals(waitingPlaylistQueuesforRoad[playlistId].array[1])) {
										
						maximumsRoad.remove(waitingPlaylistQueuesforRoad[playlistId].deleteMin().name);
						if (waitingPlaylistQueuesforRoad[playlistId].currentSize != 0)
							maximumsRoad.insert(waitingPlaylistQueuesforRoad[playlistId].array[1]);

					}
					
					else {
						waitingPlaylistQueuesforRoad[playlistId].remove(song.name);
					
					}
				}

				
				if (playlistQueuesforBliss[playlistId].contains(song.name)) {
										
					removedBliss = song;
					
					if (song.equals(playlistQueuesforBliss[playlistId].array[1])) {
					
						playlistQueuesforBliss[playlistId].deleteMin();
						minimumsBliss.remove(removedBliss.name);
						if (playlistQueuesforBliss[playlistId].currentSize != 0) 
							minimumsBliss.insert(playlistQueuesforBliss[playlistId].array[1]);	
					}
					else {	
						playlistQueuesforBliss[playlistId].remove(removedBliss.name);
					}	

					addedBliss = maximumsBliss.findNextMax(playlistQueuesforBliss, 1, playlistLim, "bliss");
		
					
					if (addedBliss != null) {
						maximumsBliss.remove(addedBliss.name);
						waitingPlaylistQueuesforBliss[addedBliss.playlist].deleteMin();
						if (waitingPlaylistQueuesforBliss[addedBliss.playlist].currentSize != 0) {
						maximumsBliss.insert(waitingPlaylistQueuesforBliss[addedBliss.playlist].array[1]);
						}
						
						if (playlistQueuesforBliss[addedBliss.playlist].currentSize != 0) {
							if (isAfter("bliss",addedBliss,playlistQueuesforBliss[addedBliss.playlist].array[1])) {
								minimumsBliss.remove(playlistQueuesforBliss[addedBliss.playlist].array[1].name);
								minimumsBliss.insert(addedBliss);
							}
							
						}
						else {
							minimumsBliss.insert(addedBliss);
						}
						playlistQueuesforBliss[addedBliss.playlist].insert(addedBliss);
					}
					

					
					
				}

				//not in epic blend
				else {
					
					if (song.equals(waitingPlaylistQueuesforBliss[playlistId].array[1])) {
										
						maximumsBliss.remove(waitingPlaylistQueuesforBliss[playlistId].deleteMin().name);
						if (waitingPlaylistQueuesforBliss[playlistId].currentSize != 0) {
							maximumsBliss.insert(waitingPlaylistQueuesforBliss[playlistId].array[1]);
						}

					}
					
					else {
						
						waitingPlaylistQueuesforBliss[playlistId].remove(song.name);
					}
					
				}

				
				
				
				int ah; int ar; int ab;
				if (addedHeart==null) {
					ah=0;
				}
				else 
					ah = addedHeart.id;

				if (addedRoad==null) {
					ar=0;
				}
				else 
					ar = addedRoad.id;

				if (addedBliss==null) {
					ab=0;
				}
				else 
					ab = addedBliss.id;

				fileWriter.write(ah +" "+ar+" "+ab + "\n");


				int rh; int rr; int rb;
				if (removedHeart==null) {
					rh = 0;
				}
				else 
					rh = removedHeart.id;


				if (removedRoad==null) {
					rr = 0;
				}
				else
					rr = removedRoad.id;

				if (removedBliss==null) {
					rb = 0;
				}
				else
					rb = removedBliss.id;
				fileWriter.write(rh + " " + rr + " " + rb + "\n");
				
				
			}

			if (array[0].equals("ASK")) {

				
				boolean[] songHash = new boolean[numSongs+1];
				PriorityQueue askHeap = new PriorityQueue("ask");
				
				
				for (int k = 1; k<=numPlaylists; k++) {
					for (int j = 1; j<=playlistQueuesforHeart[k].currentSize; j++) {
						
						songHash[playlistQueuesforHeart[k].array[j].id] = true;
						askHeap.appendSong(playlistQueuesforHeart[k].array[j]);

					}

					for (int j = 1; j<=playlistQueuesforRoad[k].currentSize; j++) {

						if (songHash[playlistQueuesforRoad[k].array[j].id] == false) {

							songHash[playlistQueuesforRoad[k].array[j].id] = true;
							askHeap.appendSong(playlistQueuesforRoad[k].array[j]);
						}
					}
					
					for (int j = 1; j<=playlistQueuesforBliss[k].currentSize; j++) {

						if (songHash[playlistQueuesforBliss[k].array[j].id] == false) {

							songHash[playlistQueuesforBliss[k].array[j].id] = true;
							askHeap.appendSong(playlistQueuesforBliss[k].array[j]);
						}
					}			
					
				}
				

				askHeap.buildHeap();
	
				
				while (askHeap.currentSize != 0) {
					fileWriter.write(askHeap.deleteMin().id + " ");
				}
				
				fileWriter.write("\n");
				

				
				

				
			}







		}

		fileWriter.flush();
		fileWriter.close();
		scanner2.close();

	}






	public static void buildCategoryforEpicBlend(MinPriorityQueue minimums, PriorityQueue waitingMaximums,
			PriorityQueue[] waitingPlaylistQueues, int playlistLim, int catLim, 
			int numPlaylists, MinPriorityQueue[] playlistQueues, String type) {

		int catCount = 0;
		ArrayList<Song> tempErasedMaximums = new ArrayList<>();
		while (catCount < catLim &&
				waitingMaximums.currentSize != 0) {
			
			if (playlistQueues[waitingMaximums.array[1].playlist].currentSize == playlistLim) {

				Song temp = waitingMaximums.deleteMin();
				
				tempErasedMaximums.add(temp);
				
			}

			
			else {
				Song added = waitingMaximums.deleteMin();
				waitingPlaylistQueues[added.playlist].deleteMin();
				if (waitingPlaylistQueues[added.playlist].currentSize != 0 ) {
					waitingMaximums.insert(waitingPlaylistQueues[added.playlist].array[1]);
				}
				
				
				
				if (playlistQueues[added.playlist].currentSize != 0) {

					if (type.equals("heart")) {
						if (isAfter("heart", added, playlistQueues[added.playlist].array[1])) {
							minimums.remove(playlistQueues[added.playlist].array[1].name);
							minimums.insert(added);
						}
					}


					else if (type.equals("road")) {
						if (isAfter("road", added, playlistQueues[added.playlist].array[1])) {
							minimums.remove(playlistQueues[added.playlist].array[1].name);
							minimums.insert(added);
						}
					}

					else if (type.equals("bliss")) {
						if (isAfter("bliss", added, playlistQueues[added.playlist].array[1])) {
							minimums.remove(playlistQueues[added.playlist].array[1].name);
							minimums.insert(added);
						}
					}
				}
				
				else {
					
					minimums.insert(added);
				}
				
				
				playlistQueues[added.playlist].insert(added);
				
				catCount++;
			}
			
		}
			
			//build heap instead
			
		for (Song s: tempErasedMaximums) {
			waitingMaximums.insert(s);	
		}


	}





	public static boolean isPrior(String s, Song k1, Song k2) {

		if (s.equals("heart")) {
			if (k1.heartScore>k2.heartScore) {
				return true;
			}
			else if (k1.heartScore == k2.heartScore) {
				if (k1.name.compareTo(k2.name)<0) {
					return true;
				}

			}
			else {
				return false;
			}

		}

		else if (s.equals("road")) {

			if (k1.roadScore>k2.roadScore) {
				return true;
			}
			else if (k1.roadScore == k2.roadScore) {
				if (k1.name.compareTo(k2.name)<0) {
					return true;
				}
			}
			else {
				return false;
			}

		}

		else if (s.equals("bliss")) {

			if (k1.blissScore>k2.blissScore) {
				return true;
			}
			else if (k1.blissScore == k2.blissScore) {
				if (k1.name.compareTo(k2.name)<0) {
					return true;
				}
			}
			else {
				return false;
			}

		}
		
		else if (s.equals("ask")) {
			
			if (k1.playCount > k2.playCount) {
				return true;
			}
			else if (k1.playCount == k2.playCount) {
				if (k1.name.compareTo(k2.name)<0) {
					return true;
				}
			}
			else
				return false;
		
		}

		else
			return false;
		
		return false;



	}


	public static boolean isAfter(String s, Song k1, Song k2) {

		if (s.equals("heart")) {
			if (k1.heartScore<k2.heartScore) {
				return true;
			}
			else if (k1.heartScore == k2.heartScore) {
				if (k1.name.compareTo(k2.name)>0) {
					return true;
				}

			}
			else {
				return false;
			}

		}

		else if (s.equals("road")) {

			if (k1.roadScore<k2.roadScore) {
				return true;
			}
			else if (k1.roadScore == k2.roadScore) {
				if (k1.name.compareTo(k2.name)>0) {
					return true;
				}
			}
			else {
				return false;
			}

		}

		else if (s.equals("bliss")) {

			if (k1.blissScore<k2.blissScore) {
				return true;
			}
			else if (k1.blissScore == k2.blissScore) {
				if (k1.name.compareTo(k2.name)>0) {
					return true;
				}
			}
			else {
				return false;
			}

		}

		else
			return false;
		
		return false;



	}





}