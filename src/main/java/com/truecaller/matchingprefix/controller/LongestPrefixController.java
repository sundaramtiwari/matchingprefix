package com.truecaller.matchingprefix.controller;

import com.truecaller.matchingprefix.service.PrefixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LongestPrefixController {

  @Autowired
  PrefixService prefixService;

  @RequestMapping(path = "/findLongestPrefix")
  public String getLongestPrefix(@RequestParam String input) {
    return prefixService.getLongestPrefix(input);
  }
}