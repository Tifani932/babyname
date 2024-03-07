package ch.zhaw.urlshortener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.urlshortener.model.ShortUrl;
import ch.zhaw.urlshortener.model.ShortUrlDTO;



@RestController
public class UrlController {

    HashMap<String, ShortUrl> urlMap = new HashMap<>();
    
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody ShortUrlDTO shortUrlDTO) {
        String shortUrl = UUID.randomUUID().toString().substring(0,8);
        if (shortUrlDTO.getLongUrl().startsWith("http://")) {
            urlMap.put(shortUrl, new ShortUrl(shortUrl, shortUrlDTO.getLongUrl(), System.currentTimeMillis()));
            return ResponseEntity.ok(shortUrl);
        } else {
            return ResponseEntity.badRequest().body("URL must start with http://");
        }
    }

    @GetMapping("/urls")
    public Map<String, ShortUrl> getMethodName() {
        return urlMap;
    }


}
