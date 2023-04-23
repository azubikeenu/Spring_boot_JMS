package com.azubike.ellipsis.spring_jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJmsApplication{



    public static void main(String[] args) throws Exception {
        // setup an embedded activeMQ server
//        final ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl().setPersistenceEnabled(false)
//                .setJournalDirectory("target/data/journal").setSecurityEnabled(false)
//                .addAcceptorConfiguration("invm", "vm://1"));
//        server.start();
        SpringApplication.run(SpringJmsApplication.class, args);
    }

}
