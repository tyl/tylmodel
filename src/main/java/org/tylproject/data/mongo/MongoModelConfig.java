/*
 * Copyright 2015 Tyl Consulting s.a.s.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tylproject.data.mongo;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.tylproject.data.mongo.common.Signature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.tylproject.data.mongo.config.TylAuditorAware;
import org.tylproject.data.mongo.party.Party;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableMongoAuditing(auditorAwareRef="tylAuditorAware")
@ComponentScan
public class MongoModelConfig {

    @Bean(name = "tylAuditorAware")
    public AuditorAware<Signature> tylAuditorAware(){
        return new TylAuditorAware();
    }

    public static void createIndexes(MongoOperations mongoOps){
        mongoOps.indexOps(Party.class).ensureIndex(new Index().on("code", Sort.Direction.ASC));
        mongoOps.indexOps(Party.class).ensureIndex(new Index().on("partyIdentifier.identifier", Sort.Direction.ASC));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MongoModelConfig.class, args);
    }
}
