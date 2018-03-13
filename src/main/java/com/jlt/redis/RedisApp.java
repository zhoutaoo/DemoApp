package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;

public class RedisApp {
    private static JedisPool jedisPool;

    static {
        jedisPool = new JedisPool("localhost");
    }

    public static void main(String[] args) {
        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "zhangsan");
        jedis.set("age", "123");
        jedis.hset("user1", "name", "lisi");
        jedis.hmset("user2", new HashMap<String, String>() {
            {
                put("name", "wangwu");
                put("age", "123");
            }
        });

        System.out.println(jedis.get("name"));
        System.out.println(jedis.keys("*"));
        jedisPool.returnResource(jedis);
        jedisPool.destroy();
    }

}
