package crm_connector.connectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.Input;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class SampleCRMConnector extends CRMConnector {
    protected Gson gson = new GsonBuilder().create();

    public SampleCRMConnector(Input config) {
        super(config);
    }

    protected String getRawData() throws IOException {
        URL url = new URL(config.endpoint);
        return new Scanner(url.openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
    }
}
