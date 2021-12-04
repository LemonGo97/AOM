package cn.lemongo97.aom.common;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Application {
    MYSQL("MySQL"),
    MONGODB("MongoDB"),
    POSTGRESQL("PostgreSQL"),
    CLICKHOUSE("ClickHouse"),
    REDIS("Redis"),
    ELASTICSEARCH("Elasticsearch"),
    ZOOKEEPER("Zookeeper"),
    KAFKA("Kafka"),
    ROCKETMQ("RocketMQ"),
    RABBITMQ("RabbitMQ"),
    NODEJS("NodeJS"),
    JDK("JDK"),
    PYTHON("Python"),
    NGINX("Nginx");

    @EnumValue
    private final String name;

    Application(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
