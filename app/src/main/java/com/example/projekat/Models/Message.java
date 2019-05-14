package com.example.projekat.Models;

import java.util.Date;

public class Message {
    private int id;
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private Date dateTime;
    private String subject;
    private String content;

    public Message() {
//        id = 0;
//        from = null;
//        to = null;
//        cc = null;
//        bcc = null;
//        dateTime = null;
//        subject = null;
//        content = null;
    }

    public Message(int id, String from, String to, String cc, String bcc, Date dateTime, String subject, String content) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.dateTime = dateTime;
        this.subject = subject;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
