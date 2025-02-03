package com.example.myproject;
//
//public class Customer {
//    public String fullName;
//    public String mobileNumber;
//    public String email;
//    public String age;
//    public String gender;
//    public String address;
//    public String annualIncome;
//    public String policyName;
//    public String totalPolicyAmount;
//    public String planTerm;
//    public String premiumPayingFrequency;
//
//    // Default constructor required for calls to DataSnapshot.getValue(Customer.class)
//    public Customer() {
//    }
//
//    public Customer(String fullName, String mobileNumber, String email, String age, String gender,
//                    String address, String annualIncome, String policyName, String totalPolicyAmount,
//                    String planTerm, String premiumPayingFrequency) {
//        this.fullName = fullName;
//        this.mobileNumber = mobileNumber;
//        this.email = email;
//        this.age = age;
//        this.gender = gender;
//        this.address = address;
//        this.annualIncome = annualIncome;
//        this.policyName = policyName;
//        this.totalPolicyAmount = totalPolicyAmount;
//        this.planTerm = planTerm;
//        this.premiumPayingFrequency = premiumPayingFrequency;
//    }
//}
//

//
//public class Customer {
//
//    public String id, name, mobile, email, age, gender, address, income, policy, totalAmount, term, premiumAmount, frequency;
//
//    public Customer() {
//        // Default constructor required for calls to DataSnapshot.getValue(Customer.class)
//    }
//
//    public Customer(String id, String name, String mobile, String email, String age, String gender, String address, String income, String policy, String totalAmount, String term, String premiumAmount, String frequency) {
//        this.id = id;
//        this.name = name;
//        this.mobile = mobile;
//        this.email = email;
//        this.age = age;
//        this.gender = gender;
//        this.address = address;
//        this.income = income;
//        this.policy = policy;
//        this.totalAmount = totalAmount;
//        this.term = term;
//        this.premiumAmount = premiumAmount;
//        this.frequency = frequency;
//    }
//}


import java.io.Serializable;

public class Customer implements Serializable {

    private String id;
    private String name;
    private String mobile;
    private String email;
    private String age;
    private String gender;
    private String address;
    private String income;
    private String policy;
    private String totalAmount;
    private String term;
    private String premiumAmount;
    private String frequency;

    // Default constructor required for calls to DataSnapshot.getValue(Customer.class)
    public Customer() {
    }

    public Customer(String id, String name, String mobile, String email, String age, String gender, String address, String income, String policy, String totalAmount, String term, String premiumAmount, String frequency) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.income = income;
        this.policy = policy;
        this.totalAmount = totalAmount;
        this.term = term;
        this.premiumAmount = premiumAmount;
        this.frequency = frequency;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(String premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
