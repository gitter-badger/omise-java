package co.omise.resources;

import co.omise.models.Charge;
import co.omise.models.ScopedList;
import org.junit.Test;

import java.io.IOException;

public class ChargeResourceTest extends ResourceTest {
    private final String CHARGE_ID = "chrg_test_4yq7duw15p9hdrjp8oq";

    @Test
    public void testList() throws IOException {
        ScopedList<Charge> list = resource().list();
        assertRequested("GET", "/charges", 200);
        assertEquals(2, list.getTotal());
        assertEquals(20, list.getLimit());

        Charge charge = list.getData().get(0);
        assertEquals("charge", charge.getObject());
        assertEquals(CHARGE_ID, charge.getId());
    }

    @Test
    public void testGet() throws IOException {
        Charge charge = resource().get(CHARGE_ID);
        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertEquals(100000L, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals("trxn_test_4yq7duwb9jts1vxgqua", charge.getTransaction());
    }

    @Test
    public void testCreate() throws IOException {
        Charge charge = resource().create(new Charge.Create()
                .amount(100000)
                .currency("thb")
                .description("Charge for order 3947"));
        assertRequested("POST", "/charges", 200);

        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertEquals(100000L, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals("trxn_test_4yq7duwb9jts1vxgqua", charge.getTransaction());
    }

    @Test
    public void testUpdate() throws IOException {
        Charge charge = resource().update(CHARGE_ID, new Charge.Update()
                .description("Charge for order 3947 (XXL)"));
        assertRequested("PATCH", "/charges/" + CHARGE_ID, 200);

        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertEquals("Charge for order 3947 (XXL)", charge.getDescription());
    }

    private ChargeResource resource() {
        return new ChargeResource(testClient());
    }
}