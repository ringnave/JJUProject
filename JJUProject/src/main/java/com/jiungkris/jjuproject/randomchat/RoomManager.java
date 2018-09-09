package com.jiungkris.jjuproject.randomchat;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

public class RoomManager {
	private static Logger logger = LoggerFactory.getLogger(RoomManager.class);
	
	private static List<Room> roomList;
	private static AtomicInteger atomicInteger;
	
	static {
		roomList = new LinkedList<Room>();
		atomicInteger = new AtomicInteger(-1);
	}
	
	public static Room makeRoom() {
		int roomNumber = atomicInteger.incrementAndGet();
		Room room = new Room(roomNumber);
		roomList.add(room);
		
		logger.info("room list: " + roomList);
		
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
