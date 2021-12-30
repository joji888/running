package cn.edu.jsu.zjj.running.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailSend {


    private JavaMailSenderImpl javaMailSender;

    public MailSend(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    String sender;

    /**
     *
     * @param receiver
     * @param vCode
     */
    @Async
    public void send(String receiver, String vCode){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {

            //防止邮件被丢垃圾箱中
            mimeMessage.addHeader("X-Priority", "3");
            mimeMessage.addHeader("X-MSMail-Priority", "Normal");
            mimeMessage.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            mimeMessage.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869");

            mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receiver);//收件人邮箱
            mimeMessageHelper.setSubject("runningApp");//邮件的主题

            mimeMessageHelper.setText("" +
                            "    <div>\n" +
                            "        <div id=\"top\" style=\"height: 60px;line-height: 60px;padding-left: 20px;background-color: #1989fa\">\n" +
                            "            <span style=\"line-height: 60px;color: white\">\n" +
                            "                <b>&nbsp;&nbsp;RunningApp</b>\n" +
                            "            </span>\n" +
                            "        </div>\n" +
                            "        <div id=\"main\" style=\"margin-top: 20px;margin-left: 30px;margin-bottom: 50px\">\n" +
                            "            <div>尊敬的用户"+receiver+" 您好：</div><br>\n" +
                            "            <div style=\"text-indent: 2em\">您的验证码为&nbsp;&nbsp;<b>"+vCode+"</b>，该验证码请尽快使用！</div>\n" +
                            "            <br><div><b>注：</b>该邮件为系统发送请勿回复或转发。</div>\n" +
                            "            <div style=\"float: right;margin-right: 30px\"><b>RunningApp</b></div>\n" +
                            "        </div>\n" +
                            "        <div style=\"padding-left: 20px;padding-top: 10px;background-color: #1989fa;padding-bottom: 8px;\">\n" +
                            "            <span style=\"line-height: 15px;color: white;font-size: 14px\">RunningApp</span>\n" +
                            "            <div style=\"font-size: 10px;color: white;margin-top: 12px\">\n" +
                            "                <div>官方网站：127.0.0.1</div>\n" +
                            "                <div>客服电话：1517369362</div>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>"
                    ,true);//邮件内容

            javaMailSender.send(mimeMessage);//邮件发送
        } catch (MessagingException e) {
        }
    }
}
