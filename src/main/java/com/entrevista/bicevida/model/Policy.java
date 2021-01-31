package com.entrevista.bicevida.model;

import java.util.ArrayList;
import java.util.List;

public class Policy {
    private List<List<Workers>> workers = new ArrayList<>();
    private Boolean has_dental_care;
    private Integer company_percentage;

    public Policy() {
    }

    public List<List<Workers>> getWorkers() {
        return workers;
    }

    public void setWorkers(List<List<Workers>> workers) {
        this.workers = workers;
    }

    public Boolean getHas_dental_care() {
        return has_dental_care;
    }

    public void setHas_dental_care(Boolean has_dental_care) {
        this.has_dental_care = has_dental_care;
    }

    public Integer getCompany_percentage() {
        return company_percentage;
    }

    public void setCompany_percentage(Integer company_percentage) {
        this.company_percentage = company_percentage;
    }
}
