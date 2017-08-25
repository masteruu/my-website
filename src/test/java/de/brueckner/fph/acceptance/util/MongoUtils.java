package de.brueckner.fph.acceptance.util;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * MongoUtils class - used to run migration scripts on mongoDb
 *
 * @author Ionela Sasu
 */
@Component
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acceptance-test.xml"})
public class MongoUtils implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MongoUtils.class);

    private static final String RESOURCES_PATH = "src/test/resources/testdata/mongo/";
    private static final String RESOURCE_EXT = ".js";

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private Integer port;

    @Value("${mongo.dbname}")
    private String dbName;

    private MongoClient mongoClient;
    private DB database;

    public void afterPropertiesSet() throws Exception {
        if (hasInvalidData()) {
            String msg = "Invalid mongo db configuration: host:" + host + " port:" + port + " dbName:" + dbName;
            logger.error("afterPropertiesSet :: Error:", msg);
            throw new IllegalArgumentException(msg);
        }
        if (mongoClient == null) {
            this.mongoClient = new MongoClient(this.host, this.port);
        }
        if (database == null) {
            database = mongoClient.getDB(dbName);
        }
        if (database == null) {
            throw new IllegalArgumentException("Invalid mongo db configuration:" + dbName);
        }
    }

    public void executeScript(String scriptName) throws IOException {
        String scriptContent = getFileContent(scriptName);
        if (scriptContent != null) {
            database.doEval(scriptContent);
        }
    }

    public void destroy() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    private boolean hasInvalidData() {
        return StringUtils.isBlank(host) || port == null || StringUtils.isBlank(dbName);
    }

    private String getFileContent(String scriptName) throws IOException {
        return StringUtils.isBlank(scriptName) ? null
                : FileUtils.readFileToString(new File(RESOURCES_PATH + scriptName + RESOURCE_EXT));
    }

}
