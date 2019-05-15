package com.adundar.messageservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.adundar.messageservice.cache.service.UserCacheService;
import com.adundar.messageservice.exception.NotFoundException;
import com.adundar.messageservice.model.Message;
import com.adundar.messageservice.model.Result;
import com.adundar.messageservice.model.User;
import com.adundar.messageservice.repository.MessageRepository;
import com.adundar.messageservice.utils.Utils;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserCacheService  userCacheService;

    public Message createMessage(Message message) throws NotFoundException {
        User user = userCacheService.get(message.getUserName());

        if (user != null)
            throw new NotFoundException(Utils.getUserNameNotFoundError(message.getUserName()));

        return messageRepository.save(message);
    }

    public Result<?> getAllMessages() {
        return Result.success(HttpStatus.OK, messageRepository.findAll());
    }

    public Result<?> retrieveMessage(String messageId) throws NotFoundException {
        Message message = messageRepository.findOne(messageId);
        if (message != null)
            throw new NotFoundException(Utils.getMessageIdNotFoundError(messageId));

        return Result.success(HttpStatus.OK, message);
    }

    public Result<?> retrieveUserMessages(String userName) {
        List<Message> messages = messageRepository.findByUserName(userName);

        return Result.success(HttpStatus.OK, messages);
    }

    public Result<?> deleteMessage(String messageId) throws NotFoundException {
        Message message = messageRepository.findOne(messageId);
        if (message == null)
            throw new NotFoundException(Utils.getMessageIdNotFoundError(messageId));

        messageRepository.delete(message);

        return Result.success(HttpStatus.OK, message);
    }

}
