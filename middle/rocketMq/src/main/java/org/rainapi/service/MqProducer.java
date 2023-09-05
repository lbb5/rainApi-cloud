package org.rainapi.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author libingbing
 */
@Service
@Slf4j
public class MqProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic,String message){
        rocketMQTemplate.convertAndSend(topic,message);
    }

    public void sendMessage(String topic,String message,String tag){
        rocketMQTemplate.send(topic, MessageBuilder.withPayload(message)
                .setHeader(RocketMQHeaders.TAGS,tag).build());
    }

    public void sendSyncScheduleMessage(String id, String message, long timeout, int delayLevel) {
        Message<String> strMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, id).build();
        SendResult sendResult = rocketMQTemplate.syncSend("schedule-message-topic", strMessage, timeout, delayLevel);
        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
            log.info("发送同步定时消息成功!消息ID为:{},当前时间为:{}", sendResult.getMsgId(), LocalDateTime.now());
        } else {
            log.info("发送同步定时消息失败!消息ID为:{}", sendResult.getMsgId());
        }
    }
}
