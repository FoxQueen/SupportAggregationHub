package commons;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Context {
    private final Gson gson = new GsonBuilder().create();

    public String getEnvironmentVariable(String name) {
        return System.getenv().get(name);
    }

    public List<ReducedCases> getCachedItem(String key) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("SupportAggregationHubCache");
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("key", key);
        Item item = table.getItem(spec);
        if (item == null) return null;
        long now = new Timestamp(System.currentTimeMillis()).getTime();
        long timeSinceLastAggregation = now - item.getLong("timestamp");
        if (timeSinceLastAggregation < Long.parseLong(getEnvironmentVariable("CACHE_TTL")) ||
                timeSinceLastAggregation <Long.parseLong(getEnvironmentVariable("AGGREGATION_INTERVAL"))) {
            Type listType = new TypeToken<ArrayList<ReducedCases>>() {}.getType();
            return gson.fromJson(item.getString("data"), listType);
        } else {
            return null;
        }
    }

    public void cacheItem(String key, List<ReducedCases> data) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("SupportAggregationHubCache");
        table.putItem(new Item()
                .withPrimaryKey("key", key)
                .withLong("timestamp", new Timestamp(System.currentTimeMillis()).getTime())
                .withString("data", gson.toJson(data))
        );
    }
}
