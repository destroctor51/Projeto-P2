package utils;

import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class Internet {

	private Internet() {}

	public static void enviaEmail(String destinatario, String titulo, String mensagem)
	{
		final String username = "noreply.riviera@gmail.com";
		final String password = "vambriviera";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("\"Riviera Hotel\" <noreply.riviera@gmail.com>"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			message.setSubject(titulo);
			message.setContent(mensagem, "text/html");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isEmailValido(String email) {
		return Pattern.matches("^[a-zA-Z0-9_.]{3,}@[a-zA-Z]{3,}\\.[a-zA-Z]{3,}", email);
	}
}
