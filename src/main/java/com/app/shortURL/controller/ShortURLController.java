package com.app.shortURL.controller;

import com.app.shortURL.service.ShortUrlService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

@RestController
public class ShortURLController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/shortenUrl")
    public ResponseEntity<String> getShortenUrl(@RequestBody @NotNull @URL(protocol = "http") String fullUrl) throws MalformedURLException {
        return new ResponseEntity<String>(shortUrlService.getShortUrl(fullUrl), HttpStatus.OK);
    }

    @RequestMapping(value = "/s/{shortChar}", method = RequestMethod.GET)
    public String getFullUrl(HttpServletResponse response, @PathVariable("shortChar") @NotNull String randomString) throws IOException {
        Optional<String> shortURLOpt = Optional.ofNullable(shortUrlService.getTinyUrlMap().get(randomString));
        if (shortURLOpt.isPresent()) {
            response.sendRedirect(shortURLOpt.get());
            return null;
        } else return "No URL Found!!";
    }

}
