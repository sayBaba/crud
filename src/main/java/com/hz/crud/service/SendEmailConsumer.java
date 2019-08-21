package com.hz.crud.service;

import com.hz.crud.req.LoginReq;
import com.hz.crud.vo.SendEmailRlt;
import com.hz.crud.vo.SendEmailVo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

/**
 *发送邮件的消费者
 */
public class SendEmailConsumer implements ChannelAwareMessageListener {

    @Autowired
    private IMailService iMailService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("---------------正在监听------------------");

        //没有消息就返回
        if(StringUtils.isEmpty(new String(message.getBody(),"UTF-8"))) {
            System.err.println("没有消息............");
            return;
        }

        //反序列化，强转你放入的内形
        LoginReq loginReq = ( LoginReq)SerializationUtils.deserialize(message.getBody());

        //发送邮件
        String email = loginReq.getMail();
        //设置，并发送
        SendEmailVo vo = new SendEmailVo();
        vo.setEmailAdress(email);
        vo.setSubject(loginReq.getUserName());
        vo.setText("你好厉害"); //邮件内容
        SendEmailRlt rlt = iMailService.sendEmail(vo);
        //手动确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.err.println("rlt=====["+rlt+"]");

    }

}
