package org.springlink.core.tool.hash;

import com.alibaba.druid.util.FnvHash;

import java.util.*;

/**
 * @Author: Gol
 */
public class ConsistenceHash {

    private List<String> realNodes = new ArrayList<>();

    private int virtualNodes = 100;

    private Map<String, List<Integer>> real2VirtualMap = new HashMap<>();

    private SortedMap<Long,String> sortedMap = new TreeMap<>();

    public ConsistenceHash() {
        super();
    }
    public ConsistenceHash(int virtualNodes) {
        super();
        this.virtualNodes = virtualNodes;
    }

    public void  addService(String node){
        String vnode = null;
        int i=0;

        for (int count=0;count< virtualNodes;){
            i++;
            long hashValue = FnvHash.fnv1a_64(vnode);
            if(!this.sortedMap.containsKey(hashValue)){
                count++;
                this.sortedMap.put(hashValue,node);
            }
        }
        this.realNodes.add(node);

    }

    public String getService(String key){
            long hash = FnvHash.fnv1a_64(key);
            SortedMap<Long,String>  map = this.sortedMap.tailMap(hash);
            if(map.isEmpty()){
                return this.sortedMap.get(sortedMap.firstKey());
            }else {
                return map.get(map.firstKey());
            }

    }
    public static void main(String[] args) {

        ConsistenceHash consistenceHash = new ConsistenceHash();
        consistenceHash.addService("193.168.1.10");
        consistenceHash.addService("193.168.1.11");
        consistenceHash.addService("193.168.1.12");
        for (int i =0;i<100;i++){
            System.out.println("a"+i+" 对应的服务器："+consistenceHash.getService("a"+i));
        }
    }

}
