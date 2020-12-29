package commons;

import java.util.List;

public class MockContext extends Context {
    @Override
    public String getEnvironmentVariable(String name) {
        switch (name) {
            case "CACHE_TTL":
                long mockCacheTTL = 15 * 60 * 1000L;
                return Long.toString(mockCacheTTL);
            case "AGGREGATION_INTERVAL":
                long mockAggregationInterval = 30 * 60 * 1000L;
                return Long.toString(mockAggregationInterval);
            case "SUPPORTED_PRODUCTS":
                return "RED,GREEN,BLUE";
            default:
                return null;
        }
    }

    @Override
    public List<ReducedCases> getCachedItem(String key) {
        return null;
    }

    @Override
    public void cacheItem(String key, List<ReducedCases> data) {
    }
}
