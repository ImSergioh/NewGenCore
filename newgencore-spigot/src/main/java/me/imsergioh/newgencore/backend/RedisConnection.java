package me.imsergioh.newgencore.backend;

import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisConnection {
    private JedisPool pool;
    @Getter
    private final Jedis jedis;

    public RedisConnection(String hostname, int port, String password) {
        if (password == null) {
            pool = new JedisPool(new JedisPoolConfig(),
                    hostname,
                    port,
                    Protocol.DEFAULT_TIMEOUT);
        } else {
            pool = new JedisPool(new JedisPoolConfig(),
                    hostname,
                    port,
                    Protocol.DEFAULT_TIMEOUT,
                    password);
        }
        jedis = pool.getResource();
    }


}
