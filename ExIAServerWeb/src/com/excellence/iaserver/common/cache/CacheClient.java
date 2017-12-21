package com.excellence.iaserver.common.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.danga.MemCached.MemCachedClient;

/**
 * 缓存对象类
 * @author huangjinyuan
 *
 */
public class CacheClient {
	
	
	@SuppressWarnings("resource")
	public static CacheClient getInstance() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");  
		CacheClient cacheClient = (CacheClient) ctx.getBean("cacheClient"); 
		return cacheClient;
	}
	
	/**
	 * mcc
	 */
	@Autowired
	@Qualifier("mcc")
	private MemCachedClient mcc;
	

	/**
	 * @param mcc
	 */
	public void setMemCachedClient(MemCachedClient mcc) {
		this.mcc = mcc;
	}

	/**
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return mcc.get(key);
	}

	/**
	 * @param keys
	 * @return
	 */
	public Map<String, Object> getMulti(String[] keys) {
		return mcc.getMulti(keys);
	}

	/**
	 * @param keys
	 * @return
	 */
	public Object[] getMultiArray(String[] keys) {
		return mcc.getMultiArray(keys);
	}

	/**
	 * @param keys
	 * @param hashCodes
	 * @param asString
	 * @return
	 */
	public Object[] getMultiArray(String[] keys, Integer[] hashCodes, boolean asString) {
		return mcc.getMultiArray(keys, hashCodes, asString);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		return mcc.set(key, value);
	}

	/**
	 * @param key
	 * @return
	 */
	public boolean delete(String key) {
		return mcc.delete(key);
	}

	/**
	 * @param key
	 * @param count
	 * @return
	 */
	public long addOrIncr(String key, long count) {
		return mcc.addOrIncr(key, count);
	}

	/**
	 * @param key
	 * @return
	 */
	public long getCounter(String key) {
		return mcc.getCounter(key);
	}

	/**
	 * @param key
	 * @param count
	 * @return
	 */
	public boolean storeCounter(String key, long count) {
		return mcc.storeCounter(key, count);
	}

	/**
	 * @param key
	 * @param value
	 * @param expiryAt
	 * @return
	 */
	public boolean set(String key, Object value, Date expiryAt) {
		return mcc.set(key, value, expiryAt);
	}
	
	public boolean set(String key, Object value, Integer expireTime) {
		return mcc.set(key, value, expireTime);
	}

	/**
	 * @return
	 */
	public MemCachedClient getMcc() {
		return mcc;
	}

	/**
	 * @param mcc
	 */
	public void setMcc(MemCachedClient mcc) {
		this.mcc = mcc;
	}
	
	/**
	 * 刷新所有缓存
	 * <br>
	 * @author Jack, 2014-11-4.<br>
	 */
	public boolean flushAll() {
		return mcc.flushAll();
	}

	/**
	 * @param rname
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getCacheEntrys(String rname) {
		List entrys = new ArrayList();
		Map emtrysMap = new HashMap();
		Map<String, Map<String, String>> servers = mcc.statsItems(rname == null ? null : new String[] { rname });
		if (servers == null)
			return entrys;
		for (Entry server : servers.entrySet()) {
			Map<String, Object> slabs = (Map) server.getValue();
			for (Entry slab : slabs.entrySet()) {
				String slabItem = (String) slab.getKey();
				if (slabItem.endsWith("number")) {
					String[] slabStr = slabItem.split(":");
					if (slabStr.length == 3) {
						Map<String, Map<String, String>> items = mcc.statsCacheDump(Integer.parseInt(slabStr[1]), 0);
						for (Entry item : items.entrySet()) {
							Map<String, Object> kvs = (Map<String, Object>) item.getValue();
							for (Entry kv : kvs.entrySet()) {
								emtrysMap.put(kv.getKey(), kv);
							}
						}
					}
				}
			}
		}
		Object[] keys = emtrysMap.keySet().toArray();
		Arrays.sort(keys);
		for (Object key : keys) {
			entrys.add(emtrysMap.get(key));
		}
		return entrys;
	}

	/**
	 * @param rname
	 * @param part
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removePartmatch(String rname, String part) {
		Date b = new Date(System.currentTimeMillis());
		List<Entry> result = getCacheEntrys(rname);
		for (Entry e : result) {
			String key = String.valueOf(e.getKey());
			if (key.toLowerCase().indexOf(part.toLowerCase()) != -1)
				mcc.delete(key);
		}
		Date e = new Date(System.currentTimeMillis());
		System.out.println("run removePartmatch method time(s):" + ((e.getTime() - b.getTime()) / 1000));
	}

	/**
	 * @param rname
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LinkedList buildElementInfo(String rname) throws Exception {
		LinkedList records = new LinkedList();
		List<Entry> result = getCacheEntrys(rname);
		for (Entry e : result) {
			if (e.getValue() == null)
				continue;
			CacheElementInfo elementInfo = new CacheElementInfo();
			elementInfo.key = String.valueOf(e.getKey());
			elementInfo.value = e.getValue();
			records.add(elementInfo);
		}
		return records;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LinkedList buildCacheInfo() throws Exception {
		Map<String, Map<String, String>> stats = mcc.stats();
		LinkedList cacheInfo = new LinkedList();
		if (stats == null)
			return cacheInfo;
		for (Entry e : stats.entrySet()) {
			if (e.getValue() == null)
				continue;
			CacheRegionInfo regionInfo = new CacheRegionInfo();
			regionInfo.name = (String) e.getKey();
			regionInfo.stats = (Map) e.getValue();
			cacheInfo.add(regionInfo);
		}
		return cacheInfo;
	}

	/**
	 * 删除全部缓存
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void clearAllRegions() throws IOException {
		// mcc.flushAll();
		List<Entry> result = getCacheEntrys(null);
		for (Entry e : result) {
			mcc.delete((String) e.getKey());
		}
	}

	/**
	 * 删除某一范围的所有缓存
	 * 
	 * @param name
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void clearRegion(String rname) throws IOException {
		// mcc.flushAll(new String[]{rname} );
		List<Entry> result = getCacheEntrys(rname);
		for (Entry e : result) {
			mcc.delete((String) e.getKey());
		}
	}

	/**
	 * 删除某个缓存
	 * 
	 * @param name
	 * @param key
	 * @throws IOException
	 */
	public void removeItem(String key) throws IOException {
		mcc.delete(key);
	}

	/**
	 * 缓存元素信息
	 * 
	 * @author WuQuangui.
	 * @Time 2013-12-23
	 * @Version 1.0
	 */
	public class CacheElementInfo {

		public CacheElementInfo() {
			key = null;
			byteCount = 0l;
			expiresTime = null;
			value = null;
		}

		public String getKey() {
			return key;
		}

		public Date getExpiresTime() {
			if (value != null && expiresTime == null) {
				String vs = ((String) value).replaceAll("\\[", "").replaceAll("\\]", "");
				String[] ss = vs.split(";");
				if (ss.length == 2) {
					try {
						expiresTime = new Date(Long.parseLong(ss[1].replaceAll("s", "").trim()) * 1000);
					} catch (NumberFormatException ne) {
						ne.printStackTrace();
					}
				}
			}
			return expiresTime;
		}

		public Object getByteCount() {
			if (value != null && byteCount <= 0) {
				String vs = ((String) value).replaceAll("\\[", "").replaceAll("\\]", "");
				String[] ss = vs.split(";");
				if (ss.length == 2) {
					try {
						byteCount = Long.parseLong(ss[0].replaceAll("b", "").trim());
					} catch (NumberFormatException ne) {
						ne.printStackTrace();
					}
				}
			}
			return byteCount;
		}

		public Object getValue() {
			return value;
		}

		String key;
		long byteCount;
		Date expiresTime;
		Object value;
	}

	/**
	 * CacheRegionInfo
	 * 
	 * @author WuQuangui.
	 * @Time 2013-12-23
	 * @Version 1.0
	 */
	public class CacheRegionInfo {

		/**
		 * 
		 */
		public CacheRegionInfo() {
			name = null;
			stats = null;
		}

		/**
		 * 
		 * @return
		 */
		public String getName() {
			return name;
		}

		/**
		 * 
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		public Map getStats() {
			return stats;
		}

		/**
		 * 缓存名称
		 */
		private String name;
		
		/**
		 * 统计信息
		 */
		@SuppressWarnings("rawtypes")
		private Map stats;

	}
}
