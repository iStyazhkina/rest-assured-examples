package rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource implements Serializable {

    private List <ResourcesData> data;

    public List<ResourcesData> getData() {
        return data;
    }

    public void setData(List<ResourcesData> data) {
        this.data = data;
    }
}
