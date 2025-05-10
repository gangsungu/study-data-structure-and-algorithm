package cache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LRUCacheTest {

    @Test
    public void basic() {
        LRUCache<String, Integer> cache = new LRUCache<>(2);
        cache.put("k1", 1);
        assertEquals(1, cache.size());
        assertEquals(cache.get("k1"), 1);

        cache.put("k2", 2);
        assertEquals(2, cache.size());
        assertEquals(cache.get("k1"), 1);
        assertEquals(cache.get("k2"), 2);

        cache.put("k3", 3);
        assertEquals(2, cache.size());
        // 가장 마지막에 참조된 k1 은 삭제된 상태
        assertEquals(cache.get("k1"), null);
        assertEquals(cache.get("k2"), 2);
        assertEquals(cache.get("k3"), 3);

        cache.put("k3", 4);
        assertEquals(2, cache.size());
        assertEquals(cache.get("k2"), 2);
        assertEquals(cache.get("k3"), 4);
    }
}
