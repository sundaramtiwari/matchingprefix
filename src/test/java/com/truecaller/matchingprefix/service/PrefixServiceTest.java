package com.truecaller.matchingprefix.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PrefixServiceTest {

    @Test
    public void testMatchingPrefix() {
        PrefixService service = new PrefixService();

        assertThat(service.find("truecaller")).isEqualTo("truec");
        assertThat(service.find("KAWeq7t")).isEqualTo("KAWeq7");
        assertThat(service.find("KAWeq7td")).isEqualTo("KAWeq7td");
        assertThat(service.find("asdf")).isEqualTo(null);
        assertThat(service.find("")).isEqualTo(null);
        assertThat(service.find(null)).isEqualTo(null);
    }
}
