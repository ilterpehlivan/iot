package com.ericsson.iot.rest;

import com.ericsson.iot.base.model.BaseResponse;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value = "Resources")
public class ResourceList extends BaseResponse {
    List<ResourceResponse> resources;
    int nbrOfResources;
    int pageSize;
    int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ResourceResponse> getResources() {
        return resources;
    }

    public void setResources(List<ResourceResponse> resources) {
        this.resources = resources;
    }

    public int getNbrOfResources() {
        return nbrOfResources;
    }

    public void setNbrOfResources(int nbrOfResources) {
        this.nbrOfResources = nbrOfResources;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
