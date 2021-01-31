package com.entrevista.bicevida.dto;

import com.entrevista.bicevida.model.Policy;


public class PolicyData {

    private Boolean success;
    private Policy policy;


    public PolicyData() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }


    @Override
    public String toString() {
        return "PolicyData{" +
                "success=" + success +
                ", policy=" + policy +
                '}';
    }
}
