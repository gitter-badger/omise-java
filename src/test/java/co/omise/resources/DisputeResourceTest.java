package co.omise.resources;

import co.omise.models.Dispute;
import co.omise.models.DisputeStatus;
import co.omise.models.ScopedList;
import org.junit.Test;

import java.io.IOException;

public class DisputeResourceTest extends ResourceTest {
    private static final String DISPUTE_ID = "dspt_test_5089off452g5m5te7xs";

    @Test
    public void testList() throws IOException {
        ScopedList<Dispute> list = resource().list();
        assertRequested("GET", "/disputes", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());
    }

    @Test
    public void testListWithStatus() throws IOException {
        ScopedList<Dispute> list = resource().list(DisputeStatus.Closed);
        assertRequested("GET", "/disputes/closed", 200);
        assertEquals(DisputeStatus.Won, list.getData().get(0).getStatus());

        list = resource().list(DisputeStatus.Open);
        assertRequested("GET", "/disputes/open", 200);
        assertEquals(DisputeStatus.Open, list.getData().get(0).getStatus());

        list = resource().list(DisputeStatus.Pending);
        assertRequested("GET", "/disputes/pending", 200);
        assertEquals(DisputeStatus.Pending, list.getData().get(0).getStatus());
    }

    @Test
    public void testGet() throws IOException {
        Dispute dispute = resource().get(DISPUTE_ID);
        assertRequested("GET", "/disputes/" + DISPUTE_ID, 200);

        assertEquals("dspt_test_5089off452g5m5te7xs", dispute.getId());
        assertEquals(100000L, dispute.getAmount());
        assertEquals("thb", dispute.getCurrency());
        assertEquals("chrg_test_5089odjlzg9j7tw4i1q", dispute.getCharge());
    }

    @Test
    public void testUpdate() throws IOException {
        Dispute dispute = resource().update(DISPUTE_ID, new Dispute.Update()
                .message("Your dispute message"));
        assertRequested("PATCH", "/disputes/" + DISPUTE_ID, 200);

        assertEquals("dspt_test_5089off452g5m5te7xs", dispute.getId());
        assertEquals("Your dispute message", dispute.getMessage());
    }

    private DisputeResource resource() {
        return new DisputeResource(testClient());
    }
}