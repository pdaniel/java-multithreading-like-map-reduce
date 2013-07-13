package com.toptoolz;

import com.toptoolz.mapreduce.MapReduce;
import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.reduce.MultithreadReducer;
import com.toptoolz.mapreduce.reduce.Reducer;

import java.util.*;

/**
 * @author: danielpo
 * Date: 7/9/13
 * Time: 3:53 PM
 */
public class MapReduceTests extends AbstractBaseTest {
    static Vector docs = new Vector();

    static {
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
    }

    public void testWordCount() {
        long start = System.currentTimeMillis();
        MapReduce mr = new MapReduce.Builder().input(docs).mapper(new Mapper() {
            @Override
            public Object map(Object data) {
                String doc = (String) data;
                String[] tokens = doc.trim().split("\\s+");
                HashMap results = new HashMap();
                for (int i = 0; i < tokens.length; i++) {
                    accumulate(tokens[i], results);
                }
                return results;

            }
        }).reducer(new MultithreadReducer() {
            @Override
            public Object reduce(Collection c) {
                HashMap h = new HashMap();
                for (Iterator i = c.iterator(); i.hasNext(); ) {
                    Collection entries = ((HashMap) i.next()).entrySet();
                    for (Iterator j = entries.iterator(); j.hasNext(); ) {
                        Map.Entry e = (Map.Entry) j.next();
                        Object key = e.getKey();
                        Integer val = (Integer) e.getValue();
                        if (h.containsKey(key)) {
                            Integer oldval = (Integer) h.get(key);
                            h.put(key, new Integer(val.intValue() + oldval.intValue()));
                        } else {
                            h.put(key, val);
                        }
                    }
                }
                return h;

            }
        }).workersNo(100).build();

        mr.begin();
        Object result = mr.master.reduceResults();
        System.out.printf("Program took %d sec to execute.\n", (System.currentTimeMillis() - start) / 1000);
        System.out.println(result.toString());
    }

    private void accumulate(String s, HashMap acc) {
        String key = s.toLowerCase();
        if (acc.containsKey(key)) {
            Integer I = (Integer) acc.get(key);
            int newval = I.intValue() + 1;
            acc.put(key, new Integer(newval));
        } else {
            acc.put(key, new Integer(1));
        }
    }


}
