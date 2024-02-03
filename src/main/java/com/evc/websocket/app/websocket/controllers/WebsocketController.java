package com.evc.websocket.app.websocket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.evc.websocket.app.websocket.models.Message;
// import com.evc.websocket.app.websocket.models.MessageDTO;

@Controller
public class WebsocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }
    
    // @MessageMapping("/status")
    // @SendTo("/topic/messages")
    // public MessageDTO sendMessage(MessageDTO message, @Header("simpSessionId") String sessionId) {
    //     // Do something
    //     return new MessageDTO("Message with text : " + message.getText()
    //             + " received ", " from " + message.getName());
    // }
}
