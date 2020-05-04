package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.iss.CMS.Services.PaperService;
import ro.ubb.iss.CMS.converter.PaperConverter;

@RestController
public class PresentationController {
    public static final Logger log = LoggerFactory.getLogger(PresentationController.class);

    @Autowired
    private PresentationService service;

    @Autowired private PresentationConverter converter;
}
