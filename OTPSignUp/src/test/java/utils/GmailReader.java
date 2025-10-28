package utils;

import jakarta.mail.*;
import jakarta.mail.search.FlagTerm;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailReader {
    private final String user;
    private final String password; // app password

    public GmailReader(String user, String password) {
        this.user = "tayyab@gmail.com";
        this.password ="tayy yaba wanm alik";
    }

    public String fetchLatestOtp(int waitSeconds, String otpRegex) throws Exception {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", user, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // wait loop for email arrival
        long end = System.currentTimeMillis() + waitSeconds * 1000L;
        Message found = null;
        while (System.currentTimeMillis() < end) {
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            // check newest first
            for (int i = messages.length - 1; i >= 0; i--) {
                Message m = messages[i];
                Address[] froms = m.getFrom();
                String subject = m.getSubject();
                String content = getTextFromMessage(m);

                // crude heuristic: OTP code present in content by regex
                if (content != null) {
                    Pattern p = Pattern.compile(otpRegex);
                    Matcher matcher = p.matcher(content);
                    if (matcher.find()) {
                        found = m;
                        String code = matcher.group(1);
                        inbox.close(false);
                        store.close();
                        return code;
                    }
                }
            }

            // sleep a bit then try again
            Thread.sleep(3000);
            inbox.close(false);
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
        }

        inbox.close(false);
        store.close();
        throw new RuntimeException("OTP email not found within wait time");
    }

    private String getTextFromMessage(Message message) throws Exception {
        Object content = message.getContent();
        if (content instanceof String) {
            return (String) content;
        } else if (content instanceof Multipart) {
            Multipart mp = (Multipart) content;
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart bp = mp.getBodyPart(i);
                if (bp.getContent() instanceof String) {
                    return (String) bp.getContent();
                }
            }
        }
        return null;
    }
}
