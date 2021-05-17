package kz.reserve.backend.service;

import kz.reserve.backend.configuration.UserDetailsImpl;
import kz.reserve.backend.domain.Meal;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

@Service
public class ServiceUtils {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private UserRepository userRepository;

    @Value("${upload.folder}")
    private String uploadFolder;

    public String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();

        while (stringBuilder.length() < length) {
            int index = random.nextInt(chars.length());
            stringBuilder.append(chars.charAt(index));
        }

        return stringBuilder.toString();
    }

    @Async
    public void sendMessageToUser(User user, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    public User getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.getOne(userDetails.getId());
    }

    public String saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolder + uuid.toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            Files.write(path, bytes);
            return uuid.toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        }
        return null;
    }
}
