package sample.cafekisok.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekisok.spring.client.mail.MailSendClient;
import sample.cafekisok.spring.domain.history.mail.MailSendHistory;
import sample.cafekisok.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    //순수 Mockito 테스트
    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        given(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString()))
            .willReturn(true);


        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));

        assertThat(result).isTrue();
    }

}
