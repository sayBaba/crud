package com.hz.crud.service;

import com.hz.crud.vo.SendEmailRlt;
import com.hz.crud.vo.SendEmailVo;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * 发送邮件
 */
public interface IMailService {

    /**
     *
     * @param sendEmailVo
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public SendEmailRlt sendEmail(SendEmailVo sendEmailVo) throws MessagingException, UnsupportedEncodingException;
}
