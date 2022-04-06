package uk.co.myyard.devmaster.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.co.myyard.devmaster.annotations.WebUITests;
import uk.co.myyard.devmaster.base.TestBase;
import uk.co.myyard.devmaster.page.InvoiceTableBot;

@WebUITests
class InvoiceTableTest extends TestBase {

    private static final String ORDER_ID = "146566";
    private static InvoiceTableBot table;

    @BeforeAll
    static void initInvoices() {
        table = bot.getInvoicePage().results();
    }

    @Test
    void testInvoiceCompanyForOrder() {
        table.findInvoiceByOrderId(ORDER_ID)
                .verifyCompanyEquals("TEST CUSTOMER");
    }

    @Test
    void testInvoiceAddressForOrder() {
        table.findInvoiceByOrderId(ORDER_ID)
                .verifyAddressEquals("TEST ADDRESS, TEST TOWN, 111111");
    }

    @Test
    void testGradeForOrder() {
        table.findGradesByNameAndOrderId(ORDER_ID, "Mixed Municipal Waste")
                .verifyWeightEquals("0.460 T");
    }

    @ParameterizedTest
    @CsvSource({
            "Flat charge, £100.00",
            "per tonne, £4.60",
            "Item, £110.00"
    })
    void testTotalValuesForOrder(String wasteEntity, String wasteTotal) {
        table.findWasteByEntityAndOrderId(ORDER_ID, wasteEntity)
                .verifyTotalEquals(wasteTotal);
    }
}