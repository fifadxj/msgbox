package com.cangshudoudou.msgbox.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisHandler {
	private static JedisHandler jedisHandler = new JedisHandler();
	private JedisPool pool;
	
	private JedisHandler() {
	    pool = new JedisPool(new JedisPoolConfig(), "localhost");
	}
	
	public static JedisHandler getInstance() {
		return jedisHandler;
	}
	
	public void hset(String key, String name, String value) {
		Jedis jedis = pool.getResource();
		jedis.select(0);
		try {
		  jedis.hset(key, name, value);
		  jedis.expire(key, 60);
		} catch (JedisConnectionException e) {
		    // returnBrokenResource when the state of the object is unrecoverable
		    if (null != jedis) {
		        pool.returnBrokenResource(jedis);
		        jedis = null;
		    }
		} finally {
		  /// ... it's important to return the Jedis instance to the pool once you've finished using it
		  if (null != jedis)
		    pool.returnResource(jedis);
		}
	}
	
	public String hget(String key, String name) {
		Jedis jedis = pool.getResource();
		jedis.select(0);
		String value = null;
		try {
		  value = jedis.hget(key, name);
		} catch (JedisConnectionException e) {
		    // returnBrokenResource when the state of the object is unrecoverable
		    if (null != jedis) {
		        pool.returnBrokenResource(jedis);
		        jedis = null;
		    }
		} finally {
		  /// ... it's important to return the Jedis instance to the pool once you've finished using it
		  if (null != jedis)
		    pool.returnResource(jedis);
		}
		
		return value;
	}
	
	public Boolean hexist(String key, String name) {
		Jedis jedis = pool.getResource();
		jedis.select(0);
		Boolean value = null;
		try {
		  value = jedis.hexists(key, name);
		} catch (JedisConnectionException e) {
		    // returnBrokenResource when the state of the object is unrecoverable
		    if (null != jedis) {
		        pool.returnBrokenResource(jedis);
		        jedis = null;
		    }
		} finally {
		  /// ... it's important to return the Jedis instance to the pool once you've finished using it
		  if (null != jedis)
		    pool.returnResource(jedis);
		}
		
		return value;
	}
	
	public void set(String key, String value) {
		Jedis jedis = pool.getResource();
		jedis.select(0);
		try {
		  jedis.set(key, value);
		  jedis.expire(key, 60);
		} catch (JedisConnectionException e) {
		    // returnBrokenResource when the state of the object is unrecoverable
		    if (null != jedis) {
		        pool.returnBrokenResource(jedis);
		        jedis = null;
		    }
		} finally {
		  /// ... it's important to return the Jedis instance to the pool once you've finished using it
		  if (null != jedis)
		    pool.returnResource(jedis);
		}
	}
	
	public String get(String key) {
		Jedis jedis = pool.getResource();
		jedis.select(0);
		String value = null;
		try {
		  value = jedis.get(key);
		} catch (JedisConnectionException e) {
		    // returnBrokenResource when the state of the object is unrecoverable
		    if (null != jedis) {
		        pool.returnBrokenResource(jedis);
		        jedis = null;
		    }
		} finally {
		  /// ... it's important to return the Jedis instance to the pool once you've finished using it
		  if (null != jedis)
		    pool.returnResource(jedis);
		}
		
		return value;
	}
}
