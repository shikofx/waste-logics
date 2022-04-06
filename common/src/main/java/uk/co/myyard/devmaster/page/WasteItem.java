package uk.co.myyard.devmaster.page;

import org.assertj.core.api.Assertions;

import java.util.Objects;

public class WasteItem {

    private WasteGrade grade;
    private String entity;
    private String description = "";
    private String after = "";
    private String quantity;
    private String price = "";
    private String foreignPrice = "";
    private String lineTotal = "";
    private String foreignLineTotal = "";

    public WasteGrade getGrade() {
        return grade;
    }

    public WasteItem setGrade(WasteGrade grade) {
        this.grade = grade;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public WasteItem setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WasteItem setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAfter() {
        return after;
    }

    public WasteItem setAfter(String after) {
        this.after = after;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public WasteItem setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public WasteItem setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getForeignPrice() {
        return foreignPrice;
    }

    public WasteItem setForeignPrice(String foreignPrice) {
        this.foreignPrice = foreignPrice;
        return this;
    }

    public String getLineTotal() {
        return lineTotal;
    }

    public WasteItem setLineTotal(String lineTotal) {
        this.lineTotal = lineTotal;
        return this;
    }

    public String getForeignLineTotal() {
        return foreignLineTotal;
    }

    public WasteItem setForeignLineTotal(String foreignLineTotal) {
        this.foreignLineTotal = foreignLineTotal;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WasteItem wasteItem = (WasteItem) o;
        return Objects.equals(grade, wasteItem.grade) && Objects.equals(entity, wasteItem.entity) && Objects.equals(description, wasteItem.description) && Objects.equals(after, wasteItem.after) && Objects.equals(quantity, wasteItem.quantity) && Objects.equals(price, wasteItem.price) && Objects.equals(foreignPrice, wasteItem.foreignPrice) && Objects.equals(lineTotal, wasteItem.lineTotal) && Objects.equals(foreignLineTotal, wasteItem.foreignLineTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, entity, description, after, quantity, price, foreignPrice, lineTotal, foreignLineTotal);
    }

    @Override
    public String toString() {
        return "WasteItem{" +
                "grade=" + grade.getName() +
                ", entity='" + entity + '\'' +
                ", description='" + description + '\'' +
                ", after='" + after + '\'' +
                ", quantity=" + quantity +
                ", price='" + price + '\'' +
                ", foreignPrice='" + foreignPrice + '\'' +
                ", lineTotal='" + lineTotal + '\'' +
                ", foreignLineTotal='" + foreignLineTotal + '\'' +
                '}';
    }

    public void verifyTotalEquals(String total) {
        Assertions.assertThat(lineTotal).isEqualTo(total);
    }
}
