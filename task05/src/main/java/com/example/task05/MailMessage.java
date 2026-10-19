package com.example.task05;

import java.util.Objects;

public class MailMessage implements Sendable<String> {
    private String to;
    private String from;
    private String content;

    public MailMessage(String fromAddr, String toAddr, String mailContent) {
        Objects.requireNonNull(toAddr);
        Objects.requireNonNull(fromAddr);
        Objects.requireNonNull(mailContent);

        this.to = toAddr;
        this.from = fromAddr;
        this.content = mailContent;
    }

    @Override
    public String getTo() { return this.to; }

    @Override
    public String getFrom() { return this.from; }

    @Override
    public String getContent() { return this.content; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        MailMessage mailMessage = (MailMessage) obj;

        return Objects.equals(this.to, mailMessage.to) &&
                Objects.equals(this.from, mailMessage.from) &&
                Objects.equals(this.content, mailMessage.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.to, this.from, this.content);
    }
}