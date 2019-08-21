package com.hz.crud.service.impl;


import com.hz.crud.common.EmailCommon;
import com.hz.crud.service.IMailService;
import com.hz.crud.vo.SendEmailRlt;
import com.hz.crud.vo.SendEmailVo;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 邮件服务实现类
 */
@Service
public class MailServiceImpl implements IMailService {


    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(EmailCommon.EMAIL_HOST);
        sender.setPort(EmailCommon.EMAIL_PORT); //端口
        sender.setUsername(EmailCommon.EMAIL_SENDER); //发件人
        sender.setPassword(EmailCommon.EMAIL_PASSWORD); //授权码
        sender.setDefaultEncoding(EmailCommon.UTF_8);  //编码格式
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", EmailCommon.EMAIL_TIME_OUT); //超时时间
        p.setProperty("mail.smtp.auth", EmailCommon.EMAIL_AUTH);  //
        sender.setJavaMailProperties(p);
        return sender;
    }

    @Override
    public SendEmailRlt sendEmail(SendEmailVo sendEmailVo) throws MessagingException, UnsupportedEncodingException {
        System.out.println("收到发送邮件请求,emailAdress:"+sendEmailVo.getEmailAdress());
        SendEmailRlt sendEmailRlt = new SendEmailRlt();
        if (sendEmailVo == null){
            sendEmailRlt.setMsg("请求对象不能为空");
            sendEmailRlt.setCode("-1");
            return sendEmailRlt;
        }
        if(StringUtils.isEmpty(sendEmailVo.getEmailAdress())){
            sendEmailRlt.setMsg("发件人不能为空");
            sendEmailRlt.setCode("-1");
            return sendEmailRlt;
        }

        if(StringUtils.isEmpty(sendEmailVo.getText())){
            sendEmailRlt.setMsg("邮件内容不能为空");
            sendEmailRlt.setCode("-1");
            return sendEmailRlt;
        }

        if(StringUtils.isEmpty(sendEmailVo.getSubject())){
            sendEmailRlt.setMsg("发送主题不能为空");
            sendEmailRlt.setCode("-1");
            return sendEmailRlt;
        }

        JavaMailSenderImpl mailSender = createMailSender();

        //邮件内容
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, EmailCommon.UTF_8);

        messageHelper.setFrom(EmailCommon.EMAIL_SENDER, EmailCommon.EMAIL_SENDER_NAME); //发送邮箱，和发件人名称

        messageHelper.setTo(sendEmailVo.getEmailAdress().trim()); //收件人
        messageHelper.setSubject(sendEmailVo.getSubject().trim());
        messageHelper.setText(sendEmailVo.getText().trim(), true);
//      messageHelper.addAttachment("", new File(""));//附件
        mailSender.send(mimeMessage);

        sendEmailRlt.setCode("0");
        sendEmailRlt.setMsg("发送成功");
        return sendEmailRlt;
    }
}
