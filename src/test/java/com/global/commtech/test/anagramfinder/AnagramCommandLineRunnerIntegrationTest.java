package com.global.commtech.test.anagramfinder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest(args = {"src/test/resources/example1.txt"})
@ExtendWith(OutputCaptureExtension.class)
class AnagramCommandLineRunnerIntegrationTest {

    @Autowired
    AnagramCommandLineRunner anagramCommandLineRunner;

    @Test
    void shouldFindAnagramsFromExample1_txt(final CapturedOutput capturedOutput) {
        assertThat(capturedOutput.getOut()).contains("abc,bac,cba");
        assertThat(capturedOutput.getOut()).contains("fun,unf");
        assertThat(capturedOutput.getOut()).contains("hello");
    }

    @Test
    void shouldFindAnagramsFromExample2_txt(final CapturedOutput capturedOutput) {
        assertThat(capturedOutput.getOut()).contains("ab,ba");
        assertThat(capturedOutput.getOut()).contains("de,ed");
        assertThat(capturedOutput.getOut()).contains("aal,ala");
        assertThat(capturedOutput.getOut()).contains("abs,bas,sab");
        assertThat(capturedOutput.getOut()).contains("alb,bal,lab");
        assertThat(capturedOutput.getOut()).contains("acme,came,mace");
        assertThat(capturedOutput.getOut()).contains("apers,apres,asper,pares,parse,pears,prase,presa,rapes,reaps,spare,spear");
    }


}