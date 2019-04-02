package ar.com.macro.demogitproperties.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoProjectController {

    @GetMapping(value = "/version", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String versionInformation() {
        return readGitProperties();
    }
    private String readGitProperties() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("git.properties");
        try {
            return readFromInputStream(inputStream);
        } catch (IOException e) {
            return "Version information could not be retrieved";
        }
    }
    private String readFromInputStream(InputStream inputStream)
    throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
    
    @GetMapping(value = "/ping", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean ping() {
        return true;
    }
   
}
