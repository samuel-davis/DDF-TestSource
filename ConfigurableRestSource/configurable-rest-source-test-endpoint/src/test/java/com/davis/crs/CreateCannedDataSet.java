package com.davis.crs;

import com.davis.ddf.crs.utils.RandomUtil;
import com.davis.ddf.crs.SourceEndpoint;
import com.davis.ddf.crs.data.CRSEndpointResponse;
import com.davis.ddf.crs.data.InMemoryDataStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This software was created for
 * rights to this software belong to
 * appropriate licenses and restrictions apply.
 *
 * @author Samuel Davis created on 4/6/17.
 */
public class CreateCannedDataSet {
    public static final String outputDir = "target/runner-output";
    private static final DecimalFormat decFormat = new DecimalFormat("#.#####");
    private Date DEFAULT_START;
    private Date DEFAULT_END;
    private Date LAST_THREE_YEARS;
    private SimpleDateFormat dateFormat;

    private SourceEndpoint sourceEndpoint = new SourceEndpoint();

    @Test
    public void generateCannedData() {


        InMemoryDataStore dataStore = new InMemoryDataStore();
        ArrayList<CRSEndpointResponse> responses = new ArrayList<>();
        for (int x = 0; x < dataStore.getOriginateUnit().size(); x++) {
            responses.add(RandomUtil.createRandomResult(dataStore, x, 0));
        }
        try {
            writeDataObjectsToJsonFile(responses, "usaResults.json");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Write data objects to json file.
     *
     * @param dataHolders the data holders
     * @param fileName    the file name
     * @throws IOException the io exception
     */
    public static void writeDataObjectsToJsonFile(ArrayList<CRSEndpointResponse> dataHolders, String fileName) throws IOException {
        System.out.println("Size of the CRSEndpointResponse Array = " + dataHolders.size());
        File file = new File(outputDir);

        if (!file.exists()) {
            file.mkdirs();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dataHolders);
        File file1 = new File(outputDir + "/" + fileName);

        FileUtils.writeStringToFile(file1, json);
    }

}
