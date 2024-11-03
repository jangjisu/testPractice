package sample.cafekisok.spring.domain.history.mail;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekisok.spring.domain.BaseEntity;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mail")
@Entity
public class MailSendHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromEmail;
    private String toEmail;
    private String subject;
    private String content;

    @Builder
    public MailSendHistory(String fromEmail, String toEmail, String subject, String content) {
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
    }
}
