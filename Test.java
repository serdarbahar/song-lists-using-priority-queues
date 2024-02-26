import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "				if (playlistQueuesforHeart[playlistId].contains(song.name)) {\n"
				+ "					\n"
				+ "					removedHeart = song;\n"
				+ "					\n"
				+ "					if (song.equals(playlistQueuesforHeart[playlistId].array[1])) {\n"
				+ "					\n"
				+ "						playlistQueuesforHeart[playlistId].deleteMin();\n"
				+ "						minimumsHeart.remove(removedHeart.name);\n"
				+ "						minimumsHeart.insert(playlistQueuesforHeart[playlistId].array[1]);	\n"
				+ "					}\n"
				+ "					else {	\n"
				+ "						playlistQueuesforHeart[playlistId].remove(removedHeart.name);\n"
				+ "					}	\n"
				+ "					\n"
				+ "					addedHeart = maximumsHeart.findNextMax(playlistQueuesforHeart, 1, playlistLim, \"heart\");\n"
				+ "					if (addedHeart != null) {\n"
				+ "						maximumsHeart.remove(addedHeart.name);\n"
				+ "						waitingPlaylistQueuesforHeart[addedHeart.playlist].deleteMin();\n"
				+ "						maximumsHeart.insert(waitingPlaylistQueuesforHeart[addedHeart.playlist].array[1]);\n"
				+ "						\n"
				+ "						if (playlistQueuesforHeart[addedHeart.playlist].currentSize != 0) {\n"
				+ "							if (isAfter(\"heart\",addedHeart,playlistQueuesforHeart[addedHeart.playlist].array[1])) {\n"
				+ "								minimumsHeart.remove(playlistQueuesforHeart[addedHeart.playlist].array[1].name);\n"
				+ "								minimumsHeart.insert(addedHeart);\n"
				+ "							}\n"
				+ "						}\n"
				+ "						else {\n"
				+ "							minimumsHeart.insert(addedHeart);\n"
				+ "						}\n"
				+ "						playlistQueuesforHeart[addedHeart.playlist].insert(addedHeart);\n"
				+ "					}\n"
				+ "					\n"
				+ "					\n"
				+ "				}\n"
				+ "\n"
				+ "				//not in epic blend\n"
				+ "				else {\n"
				+ "					\n"
				+ "					if (song.equals(waitingPlaylistQueuesforHeart[playlistId].array[1])) {\n"
				+ "										\n"
				+ "						maximumsHeart.remove(waitingPlaylistQueuesforHeart[playlistId].deleteMin().name);\n"
				+ "						maximumsHeart.insert(waitingPlaylistQueuesforHeart[playlistId].array[1]);\n"
				+ "\n"
				+ "					}\n"
				+ "					waitingPlaylistQueuesforHeart[playlistId].remove(song.name);\n"
				+ "					\n"
				+ "				}";
		
		String modifiedS = s.replace("heart","bliss");
		String modifiedS1 = modifiedS.replace("Heart","Bliss");
		
		System.out.println(modifiedS1);
		

	}

}
