package uk.co.myyard.devmaster.page;

import org.assertj.core.api.Assertions;

import java.util.Objects;

public class WasteGrade {

    private WasteOrder order;
    private String name = "";
    private String weight = "";

    public String getName() {
        return name;
    }

    public WasteGrade setName(String name) {
        this.name = name;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public WasteGrade setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public WasteOrder getOrder() {
        return order;
    }

    public WasteGrade setOrder(WasteOrder order) {
        this.order = order;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WasteGrade that = (WasteGrade) o;
        return Objects.equals(order.getId(), that.order.getId()) && Objects.equals(name, that.name) && Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, name, weight);
    }

    @Override
    public String toString() {
        return "WasteGrade{" +
                "order=" + order.getId() +
                ", name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }

    public void verifyWeightEquals(String testWaste) {
        Assertions.assertThat(weight).isEqualTo(testWaste);
    }
}
