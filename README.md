# kafka-spring

## check broker from host

```shell
$ cd /tmp/ && wget https://dlcdn.apache.org/kafka/3.0.0/kafka-3.0.0-src.tgz

$ tar zxvf kafka-3.0.0-src.tgz

$ ./bin/kafka-topics.sh --bootstrap-server localhost:9092 --list

$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic library-events

$ ./bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic library-events
```
