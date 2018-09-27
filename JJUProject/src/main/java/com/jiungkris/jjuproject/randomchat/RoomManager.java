package com.jiungkris.jjuproject.randomchat;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class RoomManager {
	
	private static List<Room> roomList;
	
	static {
		roomList = new LinkedList<Room>();
	}
	
	public static Room makeRoom() {
		Room room = new Room();
		roomList.add(room);
		
		return room;
	}

	public static Room findRoomOfOnePerson() {
		Room onePersonRoom = null;
		
		// To make roomList random, use Collection.shuffle
		Collections.shuffle(roomList);
		
		for(Room room : roomList) {
			if(room.getNumberOfPeople() == 1) {
				onePersonRoom = room;
				break;
			}
		}
		return onePersonRoom;
	}

	public static Room findRoomBySession(WebSocketSession session) {
		Room myRoom = null;
		for(Room room : roomList) {
			myRoom = room.findSession(session);
			if(myRoom != null) break;
		}
		return myRoom;
	}
	
	public static Room findRoomBySession(String session) {
		Room myRoom = null;
		for(Room room : roomList) {
			myRoom = room.findSession(session);
			if(myRoom != null) break;
		}
		return myRoom;
	}
	
	public static void removeRoom(Room room) {
		roomList.remove(room);
	}
}
