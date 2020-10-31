package net.springBootAuthentication.springBootAuthentication.services;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import net.springBootAuthentication.springBootAuthentication.model.MessageModel;
import net.springBootAuthentication.springBootAuthentication.repository.MessageRepository;

public class ChatWebsocketHandler extends TextWebSocketHandler{

    private final List<WebSocketSession> websocketsession = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MessageRepository messageRespository;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        websocketsession.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MessageModel res = new MessageModel();
            MessageModel data = objectMapper.readValue(message.getPayload(), MessageModel.class);
            res.setMessage(data.getMessage());
            res.setSenderId(data.getSenderId());
            res.setRoomId(data.getRoomId());

            messageRespository.save(res);

        for (WebSocketSession webSocketSession2 : websocketsession) {
            webSocketSession2.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        websocketsession.add(session);

    }
}
