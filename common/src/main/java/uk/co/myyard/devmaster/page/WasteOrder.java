package uk.co.myyard.devmaster.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WasteOrder {

    private Invoice invoice;
    private String jobDate;
    private String id;
    private String po = "";
    private String quantity = "";
    private String service = "";
    private String address = "";
    private String subtotal = "";
    private String foreignSubtotal = "";

    private List<WasteGrade> items = new ArrayList<>();

    public String getJobDate() {
        return jobDate;
    }

    public WasteOrder setJobDate(String jobDate) {
        this.jobDate = jobDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public WasteOrder setId(String id) {
        this.id = id;
        return this;
    }

    public String getPo() {
        return po;
    }

    public WasteOrder setPo(String po) {
        this.po = po;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public WasteOrder setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getService() {
        return service;
    }

    public WasteOrder setService(String service) {
        this.service = service;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public WasteOrder setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public WasteOrder setSubtotal(String subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public String getForeignSubtotal() {
        return foreignSubtotal;
    }

    public WasteOrder setForeignSubtotal(String foreignSubtotal) {
        this.foreignSubtotal = foreignSubtotal;
        return this;
    }

    public List<WasteGrade> getItems() {
        return items;
    }

    public WasteOrder setItems(List<WasteGrade> items) {
        this.items = items;
        return this;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public WasteOrder setInvoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WasteOrder order = (WasteOrder) o;
        return Objects.equals(invoice.getNumber(), order.invoice.getNumber()) && Objects.equals(jobDate, order.jobDate) && Objects.equals(id, order.id) && Objects.equals(po, order.po) && Objects.equals(quantity, order.quantity) && Objects.equals(service, order.service) && Objects.equals(address, order.address) && Objects.equals(subtotal, order.subtotal) && Objects.equals(foreignSubtotal, order.foreignSubtotal) && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice, jobDate, id, po, quantity, service, address, subtotal, foreignSubtotal, items);
    }

    @Override
    public String toString() {
        return "WasteOrder{" +
                "invoice=" + invoice.getNumber() +
                ", jobDate='" + jobDate + '\'' +
                ", id='" + id + '\'' +
                ", po='" + po + '\'' +
                ", quantity='" + quantity + '\'' +
                ", service='" + service + '\'' +
                ", address='" + address + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", foreignSubtotal='" + foreignSubtotal + '\'' +
                ", items=" + items +
                '}';
    }
}
