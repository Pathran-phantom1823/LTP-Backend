package net.springBootAuthentication.springBootAuthentication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomChats;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomRooms;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.MessageModel;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
import net.springBootAuthentication.springBootAuthentication.model.RoomModel;
import net.springBootAuthentication.springBootAuthentication.repository.MessageRepository;
import net.springBootAuthentication.springBootAuthentication.repository.RoomRespository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ltp")
public class ChatController {
    
    @Autowired
    private RoomRespository roomRespository;

    @Autowired
    private MessageRepository messageRepository;


    @PostMapping(value="/createRoom")
    public ResponseEntity<?> postMethodName(@RequestBody RoomModel entity) {
        try {
            RoomModel roomModel = new RoomModel();

            roomModel.setMemberId(entity.getMemberId());
            roomModel.setOwnerId(entity.getOwnerId());
            roomModel.setName(entity.getName());
            roomModel.setTopic(entity.getTopic());
            roomModel.setDate(entity.getDate());
            roomModel.setFromTime(entity.getFromTime());
            roomModel.setToTime(entity.getToTime());

            roomRespository.save(roomModel);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("room was created");
    }

    @PostMapping(value="/getMyRooms")
    public ResponseEntity<?> getMyRooms(@RequestBody RegisterModel entity) {
        try {
            Long id = entity.getId();
            List<CustomRooms> list = roomRespository.getMyRooms(id);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/getRoomById")
    public ResponseEntity<?> getRoomById(@RequestBody RoomModel entity) throws ResourceNotFoundException {
        List<Object> list = new ArrayList<>();
        try{
        Long id = entity.getId();
        RoomModel room = roomRespository.findById(entity.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));        
        String endpoint = room.getTopic();
        List<CustomChats> chat = messageRepository.getRoomsById(id);

        list.add(chat);
        list.add(endpoint);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(list);
    }
    
    // @PostMapping(value="/addMessage")
    // public ResponseEntity<?> addMessage(@RequestBody MessageModel entity) {
    //     MessageModel messageModel = new MessageModel();
    //     try {
    //         messageModel.setMessage(entity.getMessage());
    //         messageModel.setSenderId(entity.getSenderId());
    //         messageModel.setRoomId(entity.getRoomId());

    //         messageRepository.saveAndFlush(messageModel);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    //     }
    //     return ResponseEntity.ok("message sent");
    // }
    
    
    
}
