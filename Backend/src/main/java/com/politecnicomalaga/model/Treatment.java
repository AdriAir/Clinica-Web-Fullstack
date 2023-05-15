package com.politecnicomalaga.model;

public class Treatment {
    public enum treatmentAttributes {CODE, DESCRIPTION, DATE, PRICE, IS_PAID}

    ;
    protected String code;
    protected String description;
    protected String date;
    protected float price;
    protected boolean isPaid;

    //Constructor
    public Treatment(String code, String description, String date, float price) {
        this.code = code;
        this.description = description;
        this.date = date;
        this.price = price;

        isPaid = (price == 0f);
    }

    public Treatment(String csv) {
        String[] attributes = csv.split(";");

        if (attributes[0].equals("Tratamiento")) {
            this.code = attributes[1];
            this.description = attributes[2];
            this.date = attributes[3];
            this.price = Float.valueOf(attributes[4]);
            this.isPaid = Boolean.valueOf(attributes[5]);
        } else {
            this.code = "";
            this.description = "";
            this.date = "";
            this.price = 0f;

            isPaid = true;
        }
    }

    //Getters y Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float charge() {
        this.isPaid = true;
        return price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean compare(String field, treatmentAttributes treatmentAttributes) {
        char comparator;
        String data;
        switch (treatmentAttributes) {
            case CODE -> {
                return this.code.contains(field);
            }
            case DESCRIPTION -> {
                return this.description.contains(field);
            }
            case DATE -> {
                return this.date.contains(field);
            }
            case IS_PAID -> {
                if (field.equals("true")) return isPaid;
                else return !isPaid;
            }
            case PRICE -> {
                comparator = field.charAt(0);
                if (comparator == '>') {
                    data = field.substring(1);
                    return Double.parseDouble(data) < price;
                } else if (comparator == '<') {
                    data = field.substring(1);
                    return Double.parseDouble(data) > price;
                } else {
                    return Double.parseDouble(field) == price;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("%6s#" +
                        "%30s#" +
                        "%10s#" +
                        "%4.2f# " +
                        "IS_PAID: %b",
                this.code,
                this.description,
                this.date,
                this.price,
                this.isPaid);
    }

    public String toCSV() {
        return String.format("Tratamiento;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%b\n",
                this.code,
                this.description,
                this.date,
                this.price,
                this.isPaid);
    }

}