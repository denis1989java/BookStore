package ru.mail.denis.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.denis.repositories.MessageDAO;
import ru.mail.denis.repositories.UserDAO;
import ru.mail.denis.repositories.model.*;
import ru.mail.denis.service.MessageService;
import ru.mail.denis.service.model.*;
import ru.mail.denis.service.util.UserConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Denis Monich on 28.08.2017.
 */
@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    private final MessageDAO messageDAO;
    private final UserDAO userDAO;


    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO, UserDAO userDAO) {
        this.messageDAO = messageDAO;
        this.userDAO = userDAO;
    }


    @Override
    @Transactional
    public void saveMessage(MessageDTO messageDTO){
        saveMessage(converter(messageDTO));
    }

    @Override
    @Transactional
    public ViewDTO viewPage(Integer page, String userEmail, String messageID){
        int total=7;
        int pageNumber=page;
        if (page != 0) {
            page = page * total;
        }
        List<Message> messages=getMessagesByparts(page,total);
        if (messages.isEmpty()){
            page=0;
            messages=getMessagesByparts(page,total);
        }
        List<MessageDTO> messageDTOS= converter(messages);
        Long newsQuantity =messageQuantity();
        List<Long> pagination = new ArrayList();
        Long pageQuantity = Long.valueOf(0);
        if (newsQuantity % total == 0) {
            pageQuantity = newsQuantity / total;
        } else {
            pageQuantity = newsQuantity / total + 1;
        }
        for (Long i = Long.valueOf(0); i < pageQuantity; i++) {
            pagination.add(i);
        }
        Map<String,Object> map=new HashMap<>();
        if (userEmail != null) {
            User user = userDAO.findByEmail(userEmail);
            if(user==null){
                map.put("newuser","newuser");
            }else{
                map.put("userDTO", UserConverter.converter(user));
            }
            map.put("messageID", messageID);
        }
        map.put("page",pageNumber);
        map.put("messages",messageDTOS);
        map.put("pagination",pagination);
        ViewDTO viewDTO=new ViewDTO();
        viewDTO.setViewMap(map);
        return viewDTO;
    }

    private Long messageQuantity () {
        logger.debug("Finding message quantity");
        Long quantity = messageDAO.getMessagesQuantity();
        return quantity;
    }

    private List<Message>  getMessagesByparts (Integer pageId, Integer total) {
        logger.debug("Getting messages by parts");
        List<Message>   messages = messageDAO.getMessagesByParts(pageId, total);
        return messages;
    }

    private void saveMessage(Message message) {
        logger.debug("Saving new message");
        messageDAO.save(message);
    }

    private Message converter(MessageDTO messageDTO){
        Message message=new Message();
        message.setAuthorEmail(messageDTO.getAuthorEmail());
        message.setMessageId(messageDTO.getMessageId());
        message.setMessageText(messageDTO.getMessageText());
        return message;
    }
    private MessageDTO converter(Message message){
        MessageDTO messageDTO=new MessageDTO();
        messageDTO.setAuthorEmail(message.getAuthorEmail());
        messageDTO.setMessageId(message.getMessageId());
        messageDTO.setMessageText(message.getMessageText());
        return messageDTO;
    }


    private List<MessageDTO> converter (List <Message> messages){
        List <MessageDTO> messageDTOS=new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO=converter(message);
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }

}
