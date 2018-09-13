package com.jiungkris.jjuproject.message;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageRoomManager {
	private static Logger logger = LoggerFactory.getLogger(MessageRoomManager.class);
	
	private static List<MessageRoom> roomList;
	private static AtomicInteger atomicInteger;
	
	static {
		roomList = new LinkedList<MessageRoom>();
		atomicInteger = new AtomicInteger(-1);
	}
	
	public static MessageRoom makeRoom(String targetId) {
		int roomNumber = atomicInteger.incrementAndGet();
		MessageRoom room = new MessageRoom(roomNumber);
		room.setTargetId(targetId);
		roomList.add(room);
		
//		logger.info("room list: " + roomList);
		
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
//		logger.info("findRoomById called!");
		MessageRoom myRoom = null;
		for(MessageRoom room : roomList) {
			if(room.findId(id)) {
				myRoom = room;
				return myRoom;
			}
//			logger.info("This should print once");
		}
//		logger.info("This should not print");
		return myRoom;
	}
}
