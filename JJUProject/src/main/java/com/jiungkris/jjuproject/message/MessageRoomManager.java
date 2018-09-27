package com.jiungkris.jjuproject.message;

import java.util.LinkedList;
import java.util.List;

public class MessageRoomManager {
	
	private static List<MessageRoom> roomList;
	
	static {
		roomList = new LinkedList<MessageRoom>();
	}
	
	// Target ID is other's ID so it can define who the room targets. 
	public static MessageRoom makeRoom(String targetId) {
		MessageRoom room = new MessageRoom();
		room.setTargetId(targetId);
		roomList.add(room);
		
		return room;
	}
	
	public static void removeRoom(MessageRoom room) {
		roomList.remove(room);
	}

	public static MessageRoom findOnePersonRoomById(String myId, String otherId) {
		MessageRoom myRoom = null;
		for(MessageRoom room : roomList) {
			if(room.getNumberOfPeople() == 1 && room.findId(otherId) && room.getTargetId().equals(myId)) {
				myRoom = room;
				break;
			}
		}
		return myRoom;
	}

	public static MessageRoom findRoomById(String id) {
		MessageRoom myRoom = null;
		for(MessageRoom room : roomList) {
			if(room.findId(id)) {
				myRoom = room;
				return myRoom;
			}
		}
		return myRoom;
	}
}
