package com.fox.main.model;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataFetcher {
    private static AmazonDynamoDB getDBClient() {
        Map<String, String> env = System.getenv();
        return AmazonDynamoDBClientBuilder.standard().withCredentials(
        		new ProfileCredentialsProvider(env.get("SUPPORT_AGGREGATION_HUB_AWS_PROFILE"))
		).withRegion(env.get("SUPPORT_AGGREGATION_HUB_AWS_REGION")).build();
    }

    public static List<ReducedCases> fetch(List<CRM> crms) {
        List<Map<String, String>> serializedCrms = crms
                .stream()
                .map(CRM::asMap)
                .collect(Collectors.toList());
		Gson gson = new GsonBuilder().create();
		String key = gson.toJson(serializedCrms);
        DynamoDB dynamoDB = new DynamoDB(getDBClient());
        Table table = dynamoDB.getTable("SupportAggregationHubCache");
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("key", key);
		Item item = table.getItem(spec);
		if (item != null) {
            Type listType = new TypeToken<ArrayList<ReducedCases>>() {}.getType();
            return gson.fromJson(item.getString("data"), listType);
        } else {
		    return List.of();
        }
    }
}
