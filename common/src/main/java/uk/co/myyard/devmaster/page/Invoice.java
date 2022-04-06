package uk.co.myyard.devmaster.page;

import org.assertj.core.api.Assertions;

import java.util.Objects;

public class Invoice {

    private String date;
    private String number;
    private String openClosed = "";
    private String company = "";
    private String address = "";
    private String tax = "";
    private String netTotal = "";
    private String foreignNetTotal = "";

    public String getDate() {
        return date;
    }

    public Invoice setDate(String date) {
        this.date = date;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Invoice setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getOpenClosed() {
        return openClosed;
    }

    public Invoice setOpenClosed(String openClosed) {
        this.openClosed = openClosed;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Invoice setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Invoice setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTax() {
        return tax;
    }

    public Invoice setTax(String tax) {
        this.tax = tax;
        return this;
    }

    public String getNetTotal() {
        return netTotal;
    }

    public Invoice setNetTotal(String netTotal) {
        this.netTotal = netTotal;
        return this;
    }

    public String getForeignNetTotal() {
        return foreignNetTotal;
    }

    public Invoice setForeignNetTotal(String foreignNetTotal) {
        this.foreignNetTotal = foreignNetTotal;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice that = (Invoice) o;
        return Objects.equals(date, that.date) && Objects.equals(number, that.number) && Objects.equals(openClosed, that.openClosed) && Objects.equals(company, that.company) && Objects.equals(address, that.address) && Objects.equals(tax, that.tax) && Objects.equals(netTotal, that.netTotal) && Objects.equals(foreignNetTotal, that.foreignNetTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, number, openClosed, company, address, tax, netTotal, foreignNetTotal);
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "date='" + date + '\'' +
                ", number='" + number + '\'' +
                ", openClosed='" + openClosed + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", tax='" + tax + '\'' +
                ", netTotal='" + netTotal + '\'' +
                ", foreignNetTotal='" + foreignNetTotal + '\'' +
                '}';
    }

    public void verifyCompanyEquals(String testCompanyName) {
        Assertions.assertThat(company).isEqualTo(testCompanyName);
    }

    public void verifyAddressEquals(String testAddress) {
        Assertions.assertThat(address).isEqualTo(testAddress);
    }
}
