package uk.co.myyard.devmaster.page;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static uk.co.myyard.devmaster.browser.BrowserManager.browser;

public class InvoiceTableBot {
    List<WasteItem> wasteItems;

    public InvoiceTableBot() {
        wasteItems = getWasteItems();
    }

    public InvoiceTableBot updateWasteLids() {
        wasteItems = getWasteItems();
        return this;
    }

    private List<WasteItem> getWasteItems() {
        List<WebElement> items = browser().getDriver().findElements(By.xpath("//tbody[contains(@class, 'gl-')]"));
        LinkedList<WebElement> linkedList = new LinkedList<>(items);
        Invoice invoice = null;
        WasteOrder order = null;
        WasteGrade grade = null;
        List<WasteItem> wasteList = new ArrayList<>();
        while (linkedList.size() > 0) {
            WasteItem waste;
            WebElement item = linkedList.peekFirst();
            List<WebElement> params = item.findElements(By.tagName("td"));
            if (item.getAttribute("class").contains("gl-0")) {
                invoice = new Invoice()
                        .setDate(params.get(1).getText())
                        .setNumber(params.get(2).getText())
                        .setOpenClosed(params.get(3).getText())
                        .setCompany(params.get(4).getText())
                        .setAddress(params.get(5).getText())
                        .setTax(params.get(6).getText())
                        .setNetTotal(params.get(7).getText())
                        .setForeignNetTotal(params.get(8).getText());
            }

            if (item.getAttribute("class").contains("gl-1")) {
                order = new WasteOrder()
                        .setInvoice(invoice)
                        .setJobDate(params.get(1).getText())
                        .setId(params.get(2).getText())
                        .setPo(params.get(3).getText())
                        .setQuantity(params.get(4).getText())
                        .setService(params.get(5).getText())
                        .setAddress(params.get(6).getText())
                        .setSubtotal(params.get(7).getText())
                        .setForeignSubtotal(params.get(8).getText());
            }

            if (item.getAttribute("class").contains("gl-2")) {
                grade = new WasteGrade()
                        .setOrder(order)
                        .setName(params.get(1).getText())
                        .setWeight(params.get(2).getText());
            }

            if (item.getAttribute("class").contains("gl-3")) {
                waste = new WasteItem()
                        .setGrade(grade)
                        .setEntity(params.get(1).getText())
                        .setDescription(params.get(2).getText())
                        .setAfter(params.get(3).getText())
                        .setQuantity(params.get(4).getText())
                        .setPrice(params.get(5).getText())
                        .setForeignPrice(params.get(6).getText())
                        .setLineTotal(params.get(7).getText())
                        .setForeignLineTotal(params.get(8).getText());

                wasteList.add(waste);
            }
            linkedList.removeFirst();
        }
        return wasteList;
    }

    public Invoice findInvoiceByOrderId(String orderId) {
        return wasteItems.stream().filter(item -> item.getGrade()
                        .getOrder()
                        .getId().equals(orderId))
                .findFirst().orElseThrow()
                .getGrade().getOrder().getInvoice();
    }

    public List<WasteGrade> findGradesByOrderId(String orderId) {
        return wasteItems.stream().map(WasteItem::getGrade)
                .filter(grade -> grade
                                .getOrder()
                                .getId().equals(orderId))
                .collect(Collectors.toList());
    }

    private List<WasteItem> findWasteItemsByOrderId(String orderId) {
        return wasteItems.stream().filter(item -> item.getGrade()
                        .getOrder()
                        .getId().equals(orderId))
                .collect(Collectors.toList());
    }

    public WasteItem findWasteByEntityAndOrderId(String orderId, String wasteEntity) {
        Optional<WasteItem> waste = findWasteItemsByOrderId(orderId).stream()
                .filter(wasteItem -> wasteItem.getEntity().equals(wasteEntity))
                .findAny();

        Assertions.assertThat(waste)
                .as(String.format("Order #%s with waste '%s'", orderId, wasteEntity))
                .isPresent();

        return waste.orElseThrow();
    }

    public WasteGrade findGradesByNameAndOrderId(String orderId, String testGradeName) {
        Optional<WasteGrade> wasteGrade = findGradesByOrderId(orderId).stream()
                .filter(grade -> grade.getName().equals(testGradeName))
                .findAny();

        Assertions.assertThat(wasteGrade)
                .as(String.format("Order #%s with grade '%s'", orderId, testGradeName))
                .isPresent();

        return wasteGrade.orElseThrow();
    }
}