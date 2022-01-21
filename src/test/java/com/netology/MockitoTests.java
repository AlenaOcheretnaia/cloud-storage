package com.netology;

import com.netology.aloch.auth.JwtTokenUtil;
import com.netology.aloch.auth.JwtUserDetailsService;
import com.netology.aloch.exceptions.ErrorInputData;
import com.netology.aloch.model.FileMyDB;
import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.model.UserToken;
import com.netology.aloch.repository.FileRepository;
import com.netology.aloch.repository.TokenRepository;
import com.netology.aloch.repository.UserRepository;
import com.netology.aloch.service.FileService;
import com.netology.aloch.service.TokenService;
import com.netology.aloch.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MockitoTests {

    private UserRepository userRepository;
    private TokenService tokenService;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService jwtUserDetailsService;

    @Test
    void testCheckUserDBExist() {

        String login = "user";
        String pass = "pass";

        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        Mockito.when(mockUserRepository.findByLoginAndPassword(login, pass)).
                thenReturn(Optional.of(new UserMyDB(login, pass)));

        UserService userService = new UserService(mockUserRepository, tokenService, jwtTokenUtil, jwtUserDetailsService);
        boolean actualResult = userService.checkUserDB(login, pass);

        Assertions.assertTrue(actualResult);
    }

    @Test
    void testCheckUserDBDosntExist() {

        String login = "user";
        String pass = "pass";

        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        Mockito.when(mockUserRepository.findByLoginAndPassword(login, pass)).
                thenReturn(Optional.empty());

        UserService userService = new UserService(mockUserRepository, tokenService, jwtTokenUtil, jwtUserDetailsService);
        boolean actualResult = userService.checkUserDB(login, pass);

        Assertions.assertFalse(actualResult);
    }

    @Test
    void testFindUserByNameExist() {

        String username = "user";
        String password = "pass";

        UserMyDB expectedResult = new UserMyDB(username, password);

        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        Mockito.when(mockUserRepository.findByLogin(username)).
                thenReturn(new UserMyDB(username, password));

        UserService userService = new UserService(mockUserRepository, tokenService, jwtTokenUtil, jwtUserDetailsService);
        UserMyDB actualResult = userService.findUserByName(username);

        Assertions.assertEquals(expectedResult, actualResult, "Received username does not match expected ");
    }

    @Test
    void testFindUserByToken() {

        String token = "someToken";
        String username = "user";

        String expectedResult = "user";

        TokenRepository mockTokenRepository = Mockito.mock(TokenRepository.class);
        Mockito.when(mockTokenRepository.findByToken(token)).thenReturn(Optional.of(new UserToken(username, token)));

        String actualResult = mockTokenRepository.findByToken(token).get().getUsername();

        Assertions.assertEquals(expectedResult, actualResult, "Username not found");
    }

    @Test
    void testGetFilesByUser() {

        String username = "user";

        ArrayList<FileMyDB> list = (ArrayList<FileMyDB>) Stream.of(
                new FileMyDB("name1.txt", "text/plain", new byte[48], username),
                new FileMyDB("name2.txt", "text/plain", new byte[50], username))
                .collect(Collectors.toList());

        ArrayList<FileMyDB> expectedResult = (ArrayList<FileMyDB>) Stream.of(
                new FileMyDB("name1.txt", "text/plain", new byte[48], username),
                new FileMyDB("name2.txt", "text/plain", new byte[50], username))
                .collect(Collectors.toList());

        FileRepository mockFileRepository = Mockito.mock(FileRepository.class);
        Mockito.when(mockFileRepository.findByUsername(username)).thenReturn(list);

        FileService fileService = new FileService(mockFileRepository);

        List<FileMyDB> actualResult = fileService.getFilesByUser(username);

        Assertions.assertEquals(expectedResult, actualResult, "Files dosn't match");

    }

    @Test
    void testDeleteFileByFilenameError() {

        String username = "user";
        String filename = "file1";

        FileRepository mockFileRepository = Mockito.mock(FileRepository.class);
        Mockito.when(mockFileRepository.findByFilenameAndUsername(filename, username)).thenReturn(new ArrayList<>());

        FileService fileService = new FileService(mockFileRepository);

        Exception exception = assertThrows(ErrorInputData.class, () -> {
            fileService.deleteFileByFilename(filename, username);
        });

        String expectedMessage = "Error delete";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }


}
